package com.topnetwork.recharge.service.impl;

import com.common.bean.PhoneCodeRedisData;
import com.base.core.mvc.business.CommonBusiness;
import com.common.utils.PhoneCodeUtils;
import com.topnetwork.system.entity.User;
import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.common.enums.*;
import com.topnetwork.wallet.entity.AppActivity;
import com.topnetwork.wallet.entity.AppActivityReward;
import com.topnetwork.wallet.entity.AppUserCert;
import com.topnetwork.wallet.param.recharge.GetRechargeHistoryParam;
import com.topnetwork.wallet.param.recharge.RechargeListParam;
import com.topnetwork.wallet.param.recharge.RechargeParam;
import com.topnetwork.wallet.result.recharge.GetRechargeHistoryResult;
import com.topnetwork.wallet.service.AppActivityRewardService;
import com.topnetwork.wallet.service.AppActivityService;
import com.topnetwork.wallet.service.AppMessageLogService;
import com.topnetwork.wallet.service.AppUserCertService;
import com.topnetwork.wallet.service.AppUserBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import com.gitee.magic.framework.head.exception.BusinessException;
import com.gitee.magic.framework.base.result.PageResponse;
import com.gitee.magic.framework.base.result.QueryResult;
import com.base.core.framework.sql.service.impl.SqlBaseServiceImpl;
import com.topnetwork.recharge.dao.AppUserRechargeDao;
import com.topnetwork.recharge.entity.AppUserRecharge;
import com.topnetwork.recharge.service.AppUserRechargeService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("appUserRechargeService")
@Transactional
public class AppUserRechargeServiceImpl extends SqlBaseServiceImpl<AppUserRecharge, Long>
        implements AppUserRechargeService {

    @SuppressWarnings("unused")
    private AppUserRechargeDao appUserRechargeDao;

    @Autowired
    private AppUserCertService appUserCertService;
    @Autowired
    private AppUserBalanceService appUserBalanceService;
    @Autowired
    private AppActivityService appActivityService;
    @Autowired
    private AppActivityRewardService appActivityRewardService;
    @Autowired
    private AppMessageLogService appMessageLogService;
    @Autowired
    private RedisTemplate<String, PhoneCodeRedisData> redisTemplate;

    private final String mobile = "18268070310";

    public AppUserRechargeServiceImpl(@Qualifier("appUserRechargeDao") AppUserRechargeDao appUserRechargeDao) {
        super(appUserRechargeDao);
        this.appUserRechargeDao = appUserRechargeDao;
    }

    @Override
    public QueryResult<List<GetRechargeHistoryResult>> getRechargeHistory(GetRechargeHistoryParam param) {
        Map<String, Object> params = CommonBusiness.httpPageParam(param);
        QueryResult<List<GetRechargeHistoryResult>> queryResult = new QueryResult<>();
        PageResponse.PageInfo pageInfo = new PageResponse.PageInfo();
        String whereSql = "";
        if (!StringUtils.isEmpty(param.getCertNum())) {
            whereSql = " WHERE certNum='" + param.getCertNum() + "'";
        }
        String countSql = "SELECT COUNT(1) FROM wal_app_user_recharge " + whereSql;
        pageInfo.setTotalCount(appUserRechargeDao.queryForInt(countSql));
        queryResult.setPageInfo(pageInfo);
        if (pageInfo.getTotalCount() > 0) {
            String selectSql = "SELECT *" +
                    " FROM wal_app_user_recharge  "
                    + whereSql
                    + " ORDER BY created_date DESC "
                    + " LIMIT " + params.get("pageIndex") + "," + params.get("pageSize");
            List<AppUserRecharge> list = appUserRechargeDao.queryForList(selectSql);
            List<GetRechargeHistoryResult> results = new ArrayList<>();

            for (AppUserRecharge appUserRecharge : list) {
                GetRechargeHistoryResult getRechargeHistoryResult = new GetRechargeHistoryResult();
                getRechargeHistoryResult.setAmount(appUserRecharge.getAmount());
                getRechargeHistoryResult.setCertNum(appUserRecharge.getCertNum());
                getRechargeHistoryResult.setCreateTime(appUserRecharge.getCreatedDate().getTime());
                results.add(getRechargeHistoryResult);
            }
            queryResult.setResult(results);
        } else {
            queryResult.setResult(new ArrayList<>());
        }
        return queryResult;
    }

    @Override
    public void recharge(RechargeListParam paramList, User user) {
        PhoneCodeUtils.verifyPhoneCode(mobile + "recharge", paramList.getPhoneCode(), redisTemplate);
        for (RechargeParam param : paramList.getRechargeList()) {
            AppUserCert appUserCert = appUserCertService.getByCertNum(param.getCertNum());
            if (appUserCert == null) throw new BusinessException(CodeRes.CODE_23000 + param.getCertNum());
            Date now = new Date();
            AppUserRecharge appUserRecharge = new AppUserRecharge();
            appUserRecharge.setAmount(param.getAmount());
            appUserRecharge.setCertNum(param.getCertNum());
            appUserRecharge.setCreateId(user.getId());
            appUserRecharge.setUserId(appUserCert.getUid());
            appUserRecharge.setCreatedDate(now);
            appUserRecharge.setModifiedDate(now);
            save(appUserRecharge);
            AppActivityReward appActivityReward = new AppActivityReward();
            appActivityReward.setVerification(false);
            appActivityReward.setUnit(BalanceType.TOP);
            appActivityReward.setType(RewardType.RECHARGE);
            appActivityReward.setSource(RewardFromType.AIRDROP);
            appActivityReward.setSide(RewardSide.REWARD);
            appActivityReward.setReceiveTime(now);
            appActivityReward.setReceive(true);
            appActivityReward.setExpirationTime(now);
            appActivityReward.setEffectiveTime(now);
            appActivityReward.setCreateTime(now);
            AppActivity activityByType = appActivityService.findActivityByType(RewardType.RECHARGE);
            appActivityReward.setComment(activityByType.getTitle() + "," + activityByType.getEnglishTitle());
            appActivityReward.setAmount(param.getAmount());
            appActivityReward.setAid(activityByType.getId());
            appActivityReward.setUid(appUserCert.getUid());
            appActivityRewardService.save(appActivityReward);
            appUserBalanceService.receiveAward(appActivityReward);
        }
    }

    @Override
    public void getPhoneCode() {
        String code = PhoneCodeUtils.getCode();
        PhoneCodeUtils.sendRechargeCode(code, mobile, redisTemplate);
        appMessageLogService.saveLog("86" + mobile, code, LanguageEnum.CN);
    }
}
