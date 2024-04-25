package com.topnetwork.wallet.service.impl;

import com.topnetwork.common.service.RedisLock;
import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.common.enums.BalanceType;
import com.topnetwork.wallet.common.enums.RewardSide;
import com.topnetwork.wallet.common.enums.withdraw.FreezeType;
import com.topnetwork.wallet.entity.*;
import com.topnetwork.wallet.service.AppFreezeLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import com.gitee.magic.framework.head.exception.BusinessException;
import com.base.core.framework.sql.service.impl.SqlBaseServiceImpl;
import com.topnetwork.wallet.dao.AppUserBalanceDao;
import com.topnetwork.wallet.service.AppUserBalanceService;
import com.gitee.magic.jdbc.persistence.source.jdbc.sqlplus.conditions.query.QueryWrapper;

import java.math.BigDecimal;
import java.util.*;

@Service("appUserBalanceService")
@Transactional
public class AppUserBalanceServiceImpl extends SqlBaseServiceImpl<AppUserBalance, Long>
        implements AppUserBalanceService {

    @SuppressWarnings("unused")
    private AppUserBalanceDao appUserBalanceDao;
    @Autowired
    private AppFreezeLogService appFreezeLogService;
    @Autowired
    private RedisLock redisLock;

    public AppUserBalanceServiceImpl(@Qualifier("appUserBalanceDao") AppUserBalanceDao appUserBalanceDao) {
        super(appUserBalanceDao);
        this.appUserBalanceDao = appUserBalanceDao;
    }

    @Override
    public void addTopBalance(Long uid, BigDecimal balance) {
        AppUserBalance appUserBalance = new AppUserBalance();
        appUserBalance.setBalance(balance);
        appUserBalance.setFreezeBalance(BigDecimal.ZERO);
        appUserBalance.setType(BalanceType.TOP);
        appUserBalance.setUid(uid);
        appUserBalance.setCreateTime(new Date());
        save(appUserBalance);
    }

    @Override
    public AppUserBalance getTopBalance(Long uid) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("uid", uid).eq("type", BalanceType.TOP.toString());
        return get(appUserBalanceDao.queryForList(wrapper));
    }

    @Override
    public void receiveAward(AppActivityReward appActivityReward) {
        String key = "receive_award_" + appActivityReward.getId();
        boolean lock = redisLock.lock(key);
        // 执行逻辑操作
        if (lock) {
            QueryWrapper wrapper = new QueryWrapper();
            wrapper.eq("uid", appActivityReward.getUid()).eq("type", appActivityReward.getUnit().toString());
            AppUserBalance appUserBalance = get(appUserBalanceDao.queryForList(wrapper));
            if (appUserBalance == null) {
                if (appActivityReward.getSide().equals(RewardSide.SPEND)) {
                    redisLock.delete(key);
                    throw new BusinessException(CodeRes.CODE_15034);
                }
                appUserBalance = new AppUserBalance();
                appUserBalance.setCreateTime(new Date());
                appUserBalance.setUid(appActivityReward.getUid());
                appUserBalance.setFreezeBalance(BigDecimal.ZERO);
                appUserBalance.setType(appActivityReward.getUnit());
                appUserBalance.setBalance(appActivityReward.getAmount());
            } else {
                if (appActivityReward.getSide().equals(RewardSide.SPEND)) {
                    if ((appUserBalance.getBalance().subtract(appUserBalance.getFreezeBalance())).compareTo(appActivityReward.getAmount()) == -1) {
                        redisLock.delete(key);
                        throw new BusinessException(CodeRes.CODE_15034);
                    } else {
                        appUserBalance.setBalance(appUserBalance.getBalance().subtract(appActivityReward.getAmount()));
                    }
                } else {
                    appUserBalance.setBalance(appUserBalance.getBalance().add(appActivityReward.getAmount()));
                }
            }
            save(appUserBalance);
            redisLock.delete(key);
        } else {
            throw new BusinessException(CodeRes.CODE_429);
        }
    }

    @Override
    public void unfreezeOnly(List<AppWithdrawOrder> updateList) {
        for (AppWithdrawOrder appWithdrawOrder : updateList) {
            unfreezeOnly(appWithdrawOrder);
        }
    }


    @Override
    public void unfreezeOnly(AppWithdrawOrder appWithdrawOrder) {
        String key = "freeze_unfreeze_balance_" + appWithdrawOrder.getUserId();
        boolean lock = redisLock.lock(key);
        // 执行逻辑操作
        if (lock) {
            AppUserBalance appUserBalance = getTopBalance(appWithdrawOrder.getUserId());
            BigDecimal bigDecimal = appWithdrawOrder.getAmount().add(appWithdrawOrder.getFee());
            if (appUserBalance.getFreezeBalance().compareTo(bigDecimal) < 0) {
                redisLock.delete(key);
                return;
            }
            appUserBalance.setFreezeBalance(appUserBalance.getFreezeBalance().subtract(bigDecimal));
            save(appUserBalance);

            AppFreezeLog appFreezeLog = new AppFreezeLog();
            appFreezeLog.setAmount(bigDecimal);
            appFreezeLog.setType(FreezeType.UNFREEZE);
            appFreezeLog.setUserId(appUserBalance.getUid());
            appFreezeLog.setCreatedDate(new Date());
            appFreezeLog.setModifiedDate(new Date());
            appFreezeLogService.save(appFreezeLog);
            redisLock.delete(key);
        } else {
            throw new BusinessException(CodeRes.CODE_429);
        }
    }


    @Override
    public void freeze(Long id, BigDecimal amount) {
        String key = "freeze_unfreeze_balance_" + id;
        boolean lock = redisLock.lock(key);
        // 执行逻辑操作
        if (lock) {
            AppUserBalance topBalance = getTopBalance(id);
            AppFreezeLog appFreezeLog = new AppFreezeLog();
            BigDecimal freeze = topBalance.getFreezeBalance().add(amount);
            if (freeze.compareTo(topBalance.getBalance()) > 0) {
                redisLock.delete(key);
                throw new BusinessException(CodeRes.CODE_15034);
            }
            topBalance.setFreezeBalance(freeze);
            save(topBalance);

            appFreezeLog.setAmount(amount);
            appFreezeLog.setType(FreezeType.FREEZE);
            appFreezeLog.setUserId(id);
            appFreezeLog.setCreatedDate(new Date());
            appFreezeLog.setModifiedDate(new Date());
            appFreezeLogService.save(appFreezeLog);

            redisLock.delete(key);
        } else {
            throw new BusinessException(CodeRes.CODE_429);
        }
    }

    @Override
    public Long countSameBalance(AppUser appUser) {
        String countSql = "SELECT COUNT(1) FROM wal_app_user_balance WHERE balance= " + getTopBalance(appUser.getId()).getBalance().toString();
        return appUserBalanceDao.queryForLong(countSql, null);
    }
}
