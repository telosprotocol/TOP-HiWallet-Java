package com.topnetwork.wallet.service.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.base.core.mvc.business.CommonBusiness;
import com.common.utils.DateUtils;
import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.common.enums.GuessStatus;
import com.topnetwork.wallet.common.enums.UpsAndDowns;
import com.topnetwork.wallet.dao.AppActivityHistDao;
import com.topnetwork.wallet.entity.AppBtcPrice;
import com.topnetwork.wallet.entity.AppGameHist;
import com.topnetwork.wallet.entity.AppUser;
import com.topnetwork.wallet.param.wallet.activity.ActivityBaseParam;
import com.topnetwork.wallet.param.wallet.activity.GameHistParam;
import com.topnetwork.wallet.param.wallet.activity.GuessConfigParam;
import com.topnetwork.wallet.result.wallet.activity.GameHistResult;
import com.topnetwork.wallet.result.wallet.activity.GuessConfigResult;
import com.topnetwork.wallet.service.AppActivityHistService;
import com.topnetwork.wallet.service.AppBtcPriceService;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.gitee.magic.framework.head.exception.BusinessException;
import com.gitee.magic.framework.base.result.QueryResult;
import com.gitee.magic.framework.head.utils.TimeUtils;
import com.base.core.framework.sql.service.impl.SqlBaseServiceImpl;
import com.gitee.magic.jdbc.persistence.source.jdbc.sqlplus.conditions.query.QueryWrapper;

@Service("appActivityHistService")
@Transactional
public class AppActivityHistServiceImpl extends SqlBaseServiceImpl<AppGameHist, Long>
        implements AppActivityHistService {

    @SuppressWarnings("unused")
    private AppActivityHistDao appActivityHistDao;

    @Autowired
    private AppBtcPriceService appBtcPriceService;

    public AppActivityHistServiceImpl(@Qualifier("appActivityHistDao") AppActivityHistDao appActivityHistDao) {
        super(appActivityHistDao);
        this.appActivityHistDao = appActivityHistDao;
    }

    @Override
    public BigDecimal countRewardByUidAndAid(Long uid, Long aid) {
        Map<String, Object> param = new HashMap<>();
        param.put("aid", aid);
        param.put("uid", uid);
        List<Amount> amounts = queryForMapMapper(Amount.class, "countRewardByUidAndAid", param);
        BigDecimal sum = amounts.get(0).getSum();
        return sum == null ? new BigDecimal("0.00") : sum;
    }

    @Override
    public BigInteger countTimeByAid(Long aid) {
        ActivityBaseParam param = new ActivityBaseParam();
        param.setAid(aid);
        List<Amount> amounts = queryForMapMapper(Amount.class, "countTimeByAid", param);
        Integer count = amounts.get(0).getCount();
        return count == null ? new BigInteger("0") : new BigInteger(count.toString());
    }

    @Override
    public boolean isHasExit(String hash, Long uid, Long aid) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("aid", aid).eq("uid", uid).eq("phase", hash);
        AppGameHist appGameHist = get(appActivityHistDao.queryForList(wrapper));
        return appGameHist != null;
    }

    @Override
    public List<AppGameHist> findByAidAndUid(Long aid, Long uid) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("aid", aid).eq("uid", uid).ge("createTime", DateUtils.getThisDayStart());
        return appActivityHistDao.queryForList(wrapper);
    }

    @Override
    public AppGameHist findWaitHist(Long uid, Long aid) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("aid", aid).eq("uid", uid).eq("status", GuessStatus.WAIT.toString());
        wrapper.orderByDesc("createTime");
        List<AppGameHist> list = appActivityHistDao.queryForList(wrapper);
        if (list == null || list.isEmpty()) {
            throw new BusinessException(CodeRes.CODE_15039);
        }
        return list.get(0);
    }

    @Override
    public List<AppGameHist> findWaitHist(Long aid, String phase) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("aid", aid).eq("status", GuessStatus.WAIT.toString()).eq("phase", phase);
        wrapper.orderByDesc("createTime");
        List<AppGameHist> list = appActivityHistDao.queryForList(wrapper);
        return list;
    }

    @Override
    public AppGameHist findWaitHist(Long uid, Long aid, String phase) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("aid", aid).eq("uid", uid).eq("status", GuessStatus.WAIT.toString()).eq("phase", phase);
        wrapper.orderByDesc("createTime");
        return get(appActivityHistDao.queryForList(wrapper));
    }

    @Override
    public QueryResult<List<GameHistResult>> findGameHist(GameHistParam param, AppUser appUser) {

        Map<String, Object> params = CommonBusiness.httpPageParam(param);
        params.put("uid", appUser.getId());
        QueryResult<List<AppGameHist>> queryResult = queryForPage(AppGameHist.class, "findGameHist", params);

        QueryResult<List<GameHistResult>> listQueryResult = new QueryResult<>();
        if(ObjectUtils.isEmpty(queryResult)) {
        	return listQueryResult;
        }
        List<AppGameHist> appGameHistList = queryResult.getResult();
        List<GameHistResult> results = new ArrayList<>();
        for (AppGameHist appGameHist : appGameHistList) {
            GameHistResult gameHistResult = new GameHistResult();
            gameHistResult.setAmount(appGameHist.getAmount());
            gameHistResult.setPhase(appGameHist.getPhase());
            gameHistResult.setRecord(appGameHist.getRecord());
            gameHistResult.setReset(appGameHist.getReset());
            gameHistResult.setReward(appGameHist.getReward());
            gameHistResult.setRewardId(appGameHist.getRewardId());
            gameHistResult.setStatus(appGameHist.getStatus());
            gameHistResult.setUpsAndDowns(appGameHist.getUpsAndDowns());
            gameHistResult.setCreateTime(appGameHist.getCreateTime().getTime());
            results.add(gameHistResult);
        }
        listQueryResult.setResult(results);
        listQueryResult.setPageInfo(queryResult.getPageInfo());
        return listQueryResult;
    }

    @Override
    public GuessConfigResult findGuessGameConfig(GuessConfigParam param, AppUser appUser) {
        Date now = new Date();
        GuessConfigResult guessConfigResult = new GuessConfigResult();
        guessConfigResult.setEndTime(DateUtils.format(DateUtils.getGuessEndTime(now), TimeUtils.YYYYMMDDHHMMSS).getTime());
        guessConfigResult.setStartTime(DateUtils.format(DateUtils.getGuessStartTime(now), TimeUtils.YYYYMMDDHHMMSS).getTime());
        QueryWrapper wrapper = new QueryWrapper();
        Integer up = 0;
        Integer down = 0;
        BigDecimal pool = new BigDecimal("0");

        String phase = DateUtils.getGuessGamePhase(now);

        wrapper.eq("phase", phase).eq("status", GuessStatus.WAIT.toString()).eq("aid", param.getAid());
        List<AppGameHist> list = appActivityHistDao.queryForList(wrapper);
        for (AppGameHist appGameHist : list) {
            if (appUser.getId().equals(appGameHist.getUid())) {
                guessConfigResult.setUserStatus(appGameHist.getUpsAndDowns());
            }
            if (appGameHist.getUpsAndDowns().equals(UpsAndDowns.FALL)) {
                down += 1;
            } else {
                up += 1;
            }
            pool = pool.add(new BigDecimal(appGameHist.getAmount()));
        }
        guessConfigResult.setGuessDownSize(down);
        guessConfigResult.setGuessUpSize(up);
        guessConfigResult.setJackpot(pool);
        AppBtcPrice appBtcPrice = appBtcPriceService.getByPhase(phase);
        if (appBtcPrice != null) {
            guessConfigResult.setBtcPrice(appBtcPrice.getOpenPrice());
        }
        return guessConfigResult;
    }

    public static class Amount {
        private BigDecimal sum;
        private Integer count;

        public BigDecimal getSum() {
            return sum;
        }

        public void setSum(BigDecimal sum) {
            this.sum = sum;
        }

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }
    }
}
