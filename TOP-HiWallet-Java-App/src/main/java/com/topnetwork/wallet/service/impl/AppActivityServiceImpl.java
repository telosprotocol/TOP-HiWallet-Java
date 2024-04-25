package com.topnetwork.wallet.service.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.base.core.mvc.business.CommonBusiness;
import com.common.utils.DateUtils;
import com.base.service.base.system.service.ConfigureService;
import com.topnetwork.coin.BitCoinService;
import com.topnetwork.common.service.IconService;
import com.topnetwork.reward.RewardService;
import com.topnetwork.reward.bean.MethodName;
import com.topnetwork.reward.bean.RewardMessage;
import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.common.enums.ActivityType;
import com.topnetwork.wallet.common.enums.BalanceType;
import com.topnetwork.wallet.common.enums.GameConfigType;
import com.topnetwork.wallet.common.enums.GuessAmount;
import com.topnetwork.wallet.common.enums.GuessStatus;
import com.topnetwork.wallet.common.enums.LanguageEnum;
import com.topnetwork.wallet.common.enums.RewardFromType;
import com.topnetwork.wallet.common.enums.RewardSide;
import com.topnetwork.wallet.common.enums.RewardType;
import com.topnetwork.wallet.common.enums.UpsAndDowns;
import com.topnetwork.wallet.dao.AppActivityDao;
import com.topnetwork.wallet.entity.AppActivity;
import com.topnetwork.wallet.entity.AppActivityQuestion;
import com.topnetwork.wallet.entity.AppActivityReward;
import com.topnetwork.wallet.entity.AppBehaviorConfig;
import com.topnetwork.wallet.entity.AppBtcPrice;
import com.topnetwork.wallet.entity.AppGameConfig;
import com.topnetwork.wallet.entity.AppGameHist;
import com.topnetwork.wallet.entity.AppUser;
import com.topnetwork.wallet.entity.AppUserBalance;
import com.topnetwork.wallet.param.wallet.activity.ActivityAddParam;
import com.topnetwork.wallet.param.wallet.activity.ActivityBaseParam;
import com.topnetwork.wallet.param.wallet.activity.ActivityConfParam;
import com.topnetwork.wallet.param.wallet.activity.ActivityListParam;
import com.topnetwork.wallet.param.wallet.activity.ActivityPageParam;
import com.topnetwork.wallet.param.wallet.activity.ActivityTicketParam;
import com.topnetwork.wallet.param.wallet.activity.ActivityUpdParam;
import com.topnetwork.wallet.param.wallet.activity.AnswerParam;
import com.topnetwork.wallet.param.wallet.activity.BehaviorPageParam;
import com.topnetwork.wallet.param.wallet.activity.GameConfigPageParam;
import com.topnetwork.wallet.param.wallet.activity.GuessReportParam;
import com.topnetwork.wallet.param.wallet.activity.PartInGuessParam;
import com.topnetwork.wallet.param.wallet.activity.RecBrickParam;
import com.topnetwork.wallet.param.wallet.activity.RecQuestionParam;
import com.topnetwork.wallet.param.wallet.activity.RecSucTranParam;
import com.topnetwork.wallet.param.wallet.activity.RewardGuessParam;
import com.topnetwork.wallet.param.wallet.activity.RewardPageParam;
import com.topnetwork.wallet.param.wallet.activity.RewardParam;
import com.topnetwork.wallet.result.wallet.activity.ActivityListResult;
import com.topnetwork.wallet.result.wallet.activity.ActivityPageResult;
import com.topnetwork.wallet.result.wallet.activity.ActivityTicketResult;
import com.topnetwork.wallet.result.wallet.activity.BehaviorPageResult;
import com.topnetwork.wallet.result.wallet.activity.GameConfigPageResult;
import com.topnetwork.wallet.result.wallet.activity.RecBrickResult;
import com.topnetwork.wallet.result.wallet.activity.RecQuestionResult;
import com.topnetwork.wallet.result.wallet.activity.RewardPageResult;
import com.topnetwork.wallet.service.AppActivityHistService;
import com.topnetwork.wallet.service.AppActivityRewardService;
import com.topnetwork.wallet.service.AppActivityService;
import com.topnetwork.wallet.service.AppBtcPriceService;
import com.topnetwork.wallet.service.AppGameConfigService;
import com.topnetwork.wallet.service.AppQuestionService;
import com.topnetwork.wallet.service.AppUserBalanceService;
import com.topnetwork.wallet.service.AppUserService;

import com.gitee.magic.framework.head.exception.BusinessException;
import com.gitee.magic.framework.base.result.QueryResult;
import com.gitee.magic.framework.head.utils.TimeUtils;
import com.base.core.framework.sql.service.impl.SqlBaseServiceImpl;
import com.gitee.magic.jdbc.persistence.source.jdbc.sqlplus.conditions.query.QueryWrapper;

@Service("appActivityService")
@Transactional
public class AppActivityServiceImpl extends SqlBaseServiceImpl<AppActivity, Long>
        implements AppActivityService {

    @SuppressWarnings("unused")
    private AppActivityDao appActivityDao;

    @Autowired
    private AppGameConfigService appGameConfigService;
    @Autowired
    private AppActivityHistService appActivityHistService;
    @Autowired
    private ConfigureService configureService;
    @Autowired
    private RewardService rewardService;
    @Autowired
    private AppUserService appUserService;
    @Autowired
    private AppQuestionService appQuestionService;
    @Autowired
    private AppUserBalanceService appUserBalanceService;
    @Autowired
    private BitCoinService bitCoinService;
    @Autowired
    private AppBtcPriceService appBtcPriceService;

    public AppActivityServiceImpl(@Qualifier("appActivityDao") AppActivityDao appActivityDao) {
        super(appActivityDao);
        this.appActivityDao = appActivityDao;
    }

    @Override
    public List<ActivityListResult> getActivityList(ActivityListParam param, AppUser appUser) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.ne("activityType", ActivityType.AIRDROP.toString());
        List<AppActivity> activityList = appActivityDao.queryForList(wrapper);
        return transformation(activityList, param.getLanguage(), appUser);
    }

    @Override
    public void start(ActivityConfParam param, AppUser appUser) {

        AppActivity appActivity = findActivityById(param.getAid());
        if (appActivity == null) return;
        RewardMessage rewardMessage = new RewardMessage();
        rewardMessage.setUid(appUser.getId());
        rewardMessage.setAppActivity(appActivity);
        rewardMessage.setActivityType(ActivityType.GAME);
        rewardMessage.setMethodName(MethodName.start);
        rewardService.execute(rewardMessage);

        //发送游戏参与空投
        rewardMessage.setActivityType(ActivityType.AIRDROP);
        rewardMessage.setMethodName(MethodName.award);
        rewardService.execute(rewardMessage);
    }

    @Override
    public void awardDaily() {
        AppActivity appActivity = findActivityByType(RewardType.DAILY);
        if (appActivity == null) return;
        RewardMessage rewardMessage = new RewardMessage();
        rewardMessage.setAppActivity(appActivity);
        rewardMessage.setActivityType(ActivityType.AIRDROP);
        rewardMessage.setMethodName(MethodName.count);
        rewardService.execute(rewardMessage);
    }

    @Override
    public void registerDaily() {
        AppActivity appActivity = findActivityByType(RewardType.REGISTER);
        if (appActivity == null) return;
        RewardMessage rewardMessage = new RewardMessage();
        rewardMessage.setAppActivity(appActivity);
        rewardMessage.setActivityType(ActivityType.AIRDROP);
        rewardMessage.setMethodName(MethodName.reset);
        rewardService.execute(rewardMessage);
    }

    @Override
    public void countGuess() {
        Date date = new Date();
        String lastPhase = DateUtils.getGuessGameLastPhase(date);
        AppBtcPrice appBtcPrice = appBtcPriceService.getByPhase(lastPhase);
        if (appBtcPrice != null && appBtcPrice.getStatus() != null) {
            AppActivity appActivity = findActivityByType(RewardType.GUESS);
            RewardMessage rewardMessage = new RewardMessage();
            rewardMessage.setMethodName(MethodName.award);
            rewardMessage.setAppActivity(appActivity);
            rewardMessage.setUpsAndDowns(appBtcPrice.getStatus());
            rewardMessage.setAppBtcPrice(appBtcPrice);
            rewardMessage.setActivityType(ActivityType.GAME);
            rewardService.execute(rewardMessage);
        }
    }

    @Override
    public void saveBtcPrice() {
        Date date = new Date();
        String start = DateUtils.getGuessLastStartTime(date);
        String end = DateUtils.getGuessLastEndTime(date);
        String phase = DateUtils.getGuessGamePhase(date);
        String lastPhase = DateUtils.getGuessGameLastPhase(date);
        List<BigDecimal> lastPhasePrices = null;
        lastPhasePrices = bitCoinService.getBestPrice(DateUtils.format(start, TimeUtils.YYYYMMDDHHMMSS).getTime(), DateUtils.format(end, TimeUtils.YYYYMMDDHHMMSS).getTime());
        AppBtcPrice appBtcPrice = appBtcPriceService.getByPhase(lastPhase);
        if (appBtcPrice == null || appBtcPrice.getStatus() == null) {
            if (appBtcPrice == null) {
                appBtcPrice = new AppBtcPrice();
                appBtcPrice.setCreateTime(date);
                appBtcPrice.setPercentage(getPercentage(lastPhasePrices));
                appBtcPrice.setPhase(lastPhase);
                appBtcPrice.setOpenPrice(lastPhasePrices.get(0));
                appBtcPrice.setClosePrice(lastPhasePrices.get(1));
            }

            if (lastPhasePrices.get(0).compareTo(lastPhasePrices.get(1)) == -1) {
                appBtcPrice.setStatus(UpsAndDowns.RISE);
            } else if (lastPhasePrices.get(0).compareTo(lastPhasePrices.get(1)) == 1) {
                appBtcPrice.setStatus(UpsAndDowns.FALL);
            } else {
                //持平算涨
                appBtcPrice.setStatus(UpsAndDowns.RISE);
            }
            appBtcPrice.setPercentage(getPercentage(lastPhasePrices));
            appBtcPrice.setClosePrice(lastPhasePrices.get(1));
            appBtcPriceService.save(appBtcPrice);
        }
        AppBtcPrice appBtcPriceNow = appBtcPriceService.getByPhase(phase);
        if (appBtcPriceNow == null) {
            appBtcPriceNow = new AppBtcPrice();
            appBtcPriceNow.setOpenPrice(lastPhasePrices.get(0));
            appBtcPriceNow.setPhase(phase);
            appBtcPriceNow.setCreateTime(date);
            appBtcPriceService.save(appBtcPriceNow);
        }
    }

    private String getPercentage(List<BigDecimal> lastPhasePrices) {
        BigDecimal open = lastPhasePrices.get(0);
        BigDecimal close = lastPhasePrices.get(1);
        BigDecimal percentage = (close.subtract(open)).multiply(new BigDecimal("100")).divide(open, 2, BigDecimal.ROUND_HALF_UP);
        return percentage.toString();
    }

    @Override
    public void reset(ActivityConfParam param, AppUser appUser) {
        AppActivity appActivity = findActivityById(param.getAid());
        if (appActivity == null) return;
        rewardService.execute(RewardMessage.getInstance(MethodName.reset, appUser.getId(), appActivity, ActivityType.GAME, null));
    }

    @Override
    public ActivityTicketResult findActivityTicket(ActivityTicketParam param, AppUser appUser) {

        AppGameConfig appGameConfig = appGameConfigService.findTicketByAid(param.getAid());
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("id", param.getAid());
        AppActivity appActivity = get(appActivityDao.queryForList(wrapper));
        if (appGameConfig == null || appActivity == null) {
            throw new BusinessException(CodeRes.CODE_15021);
        }
        List<AppGameHist> histList = appActivityHistService.findByAidAndUid(param.getAid(), appUser.getId());
        Integer surplusTimes = 0;
        if (histList == null || histList.size() == 0) {
            surplusTimes = appActivity.getFreeSize();
        } else if (appActivity.getFreeSize() == null) {
            surplusTimes = null;
        } else {
            surplusTimes = appActivity.getFreeSize() - histList.size();
        }

        ActivityTicketResult activityTicketResult = new ActivityTicketResult();
        activityTicketResult.setAmount(new BigDecimal(appGameConfig.getAmount()));
        activityTicketResult.setFreeTimes(appActivity.getFreeSize());
        activityTicketResult.setSurplusTimes(surplusTimes == null ? null : (surplusTimes <= 0 ? 0 : surplusTimes));
        return activityTicketResult;
    }

    @Override
    public AppActivity findActivityById(Long aid) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("id", aid);
        return get(appActivityDao.queryForList(wrapper));
    }

    @Override
    public void recSucTran(RecSucTranParam param, AppUser appUser) {

        //判断交易hash是否存在
        AppActivity appActivity = findActivityByType(RewardType.TRANSFER);

        boolean hashHasExit = appActivityHistService.isHasExit(param.getHash(), appUser.getId(), appActivity.getId());
        if (hashHasExit) {
            throw new BusinessException(CodeRes.CODE_15012);
        }
        //发送空投
        rewardService.execute(RewardMessage.getInstance(MethodName.award, appUser.getId(), appActivity, ActivityType.AIRDROP, null));

        //存储记录
        AppGameHist appGameHist = new AppGameHist();
        appGameHist.setAid(appActivity.getId());
        appGameHist.setCreateTime(new Date());
        appGameHist.setPhase(param.getHash());
        appGameHist.setStatus(GuessStatus.SUCCESS);
        appGameHist.setUid(appUser.getId());
        appGameHist.setUnit(BalanceType.TOP);
        appActivityHistService.save(appGameHist);

    }

    @Override
    public RecQuestionResult recQuestionResult(RecQuestionParam param, AppUser appUser) {

        RecQuestionResult recQuestionResult = new RecQuestionResult();

        Integer correctSize = 0;
        List<AnswerParam> answerList = param.getAnswerList();
        for (AnswerParam answerParam : answerList) {
            AppActivityQuestion appActivityQuestion = appQuestionService.findById(answerParam.getQid());
            if (appActivityQuestion == null) continue;
            if (appActivityQuestion.getAnswer().equals(answerParam.getAnswer())) {
                correctSize += 1;
            }
        }


        RewardMessage rewardMessage = new RewardMessage();
        rewardMessage.setUid(appUser.getId());
        rewardMessage.setMethodName(MethodName.count);
        rewardMessage.setAppActivity(findActivityById(param.getAid()));
        rewardMessage.setActivityType(ActivityType.GAME);
        rewardMessage.setRecord(correctSize);

        AppGameHist appGameHist = count(rewardMessage);
        award(rewardMessage, appGameHist);

        recQuestionResult.setCorrect(correctSize);
        recQuestionResult.setReward(appGameHist.getReward());
        return recQuestionResult;

    }

    @Autowired
    private AppActivityRewardService appActivityRewardService;

    public void award(RewardMessage rewardMessage, AppGameHist appGameHist) {
        if (appGameHist == null) return;
        if (appGameHist.getStatus().equals(GuessStatus.WAIT) && appGameHist.getReward() != null) {
            if (appGameHist.getReward().compareTo(new BigDecimal("0.00")) == 0) {
                appGameHist.setStatus(GuessStatus.SUCCESS);
                appActivityHistService.save(appGameHist);
                return;
            }
            Date now = new Date();
            AppActivityReward appActivityReward = new AppActivityReward();
            appActivityReward.setReceive(true);
            appActivityReward.setReceiveTime(now);
            appActivityReward.setSource(RewardFromType.GAME);
            appActivityReward.setVerification(false);
            appActivityReward.setSide(RewardSide.REWARD);
            appActivityReward.setExpirationTime(now);
            appActivityReward.setType(rewardMessage.getAppActivity().getBehaviorType());
            appActivityReward.setUid(rewardMessage.getUid());
            appActivityReward.setCreateTime(now);
            appActivityReward.setEffectiveTime(now);
            appActivityReward.setComment(rewardMessage.getAppActivity().getTitle() + "," + rewardMessage.getAppActivity().getEnglishTitle());
            appActivityReward.setAid(rewardMessage.getAppActivity().getId());
            appActivityReward.setUnit(appGameHist.getUnit());
            appActivityReward.setAmount(appGameHist.getReward());
            appActivityRewardService.save(appActivityReward);
            appUserBalanceService.receiveAward(appActivityReward);
            appGameHist.setStatus(GuessStatus.SUCCESS);
            appActivityHistService.save(appGameHist);
        }
    }

    @Override
    public void recGuessResult(GuessReportParam param, AppUser appUser) {

        AppActivity appActivity = findActivityById(param.getAid());
        if (appActivity == null) {
            return;
        }
        Date now = new Date();

        if (!canGuess(now)) {
            throw new BusinessException(CodeRes.CODE_15038);
        }
        AppGameHist appGameHist = appActivityHistService.findWaitHist(appUser.getId(), param.getAid(), DateUtils.getGuessGamePhase(new Date()));

        if (appGameHist != null) {
            throw new BusinessException(CodeRes.CODE_15037);
        }

        RewardMessage rewardMessage = new RewardMessage();
        rewardMessage.setUid(appUser.getId());
        rewardMessage.setMethodName(MethodName.start);
        rewardMessage.setAppActivity(appActivity);
        rewardMessage.setActivityType(ActivityType.GAME);
        rewardMessage.setUpsAndDowns(param.getStatus());
        if (param.getAmount().equals(GuessAmount.TOP20)) {
            rewardMessage.setRecord(20);
        } else if (param.getAmount().equals(GuessAmount.TOP50)) {
            rewardMessage.setRecord(50);
        } else if (param.getAmount().equals(GuessAmount.TOP200)) {
            rewardMessage.setRecord(200);
        } else if (param.getAmount().equals(GuessAmount.TOP500)) {
            rewardMessage.setRecord(500);
        }

        AppUserBalance appUserBalance = appUserBalanceService.getTopBalance(appUser.getId());

        if ((appUserBalance.getBalance().subtract(appUserBalance.getFreezeBalance()))
                .compareTo(new BigDecimal(rewardMessage.getRecord())) < 0) {
            throw new BusinessException(CodeRes.CODE_15034);
        }

        //扣除门票及抵押金额
        rewardService.execute(rewardMessage);

        //发送空投奖励
        rewardService.execute(RewardMessage.getInstance(MethodName.award, appUser.getId(), appActivity, ActivityType.AIRDROP, null));
    }

    @Override
    public void partInGuess(PartInGuessParam param) {
        AppActivity appActivity = findActivityByType(RewardType.GUESS);
        if (appActivity == null) {
            throw new BusinessException(CodeRes.CODE_15021);
        }
        AppUser appUser = appUserService.getUserByUid(param.getUid());
        if (appUser == null) {
            throw new BusinessException(CodeRes.CODE_15009);
        }
        RewardMessage rewardMessage = new RewardMessage();
        rewardMessage.setUid(appUser.getId());
        rewardMessage.setMethodName(MethodName.start);
        rewardMessage.setAppActivity(appActivity);
        rewardMessage.setActivityType(ActivityType.GAME);
        rewardMessage.setRecord(param.getAmount());

        AppUserBalance appUserBalance = appUserBalanceService.getTopBalance(appUser.getId());

        if (appUserBalance == null
                || (appUserBalance.getBalance().subtract(appUserBalance.getFreezeBalance()))
                .compareTo(new BigDecimal(rewardMessage.getRecord())) < 0) {
            throw new BusinessException(CodeRes.CODE_15034);
        }

        //扣除门票及抵押金额
        rewardService.execute(rewardMessage);

        //发送空投奖励
        rewardService.execute(RewardMessage.getInstance(MethodName.award, appUser.getId(), appActivity, ActivityType.AIRDROP, null));
    }

    @Override
    public void reward(RewardGuessParam params) {
        AppActivity appActivity = findActivityByType(RewardType.GUESS);
        RewardMessage rewardMessage = new RewardMessage();
        for (RewardParam param : params.getRewardList()) {
            AppUser appUser = appUserService.getUserByUid(param.getUid());
            rewardMessage.setUid(appUser.getId());
            rewardMessage.setMethodName(MethodName.count);
            rewardMessage.setAppActivity(appActivity);
            rewardMessage.setActivityType(ActivityType.GAME);
            rewardMessage.setReward(param.getAmount());
            rewardService.execute(rewardMessage);
        }
    }

    public boolean canGuess(Date date) {
        boolean flag = false;
        String start = DateUtils.getGuessStartTime(date);
        String end = DateUtils.getGuessEndTime(date);
        Date startDate = DateUtils.format(start, TimeUtils.YYYYMMDDHHMMSS);
        startDate = DateUtils.addMin(startDate, 1);
        Date endDate = DateUtils.format(end, TimeUtils.YYYYMMDDHHMMSS);
        endDate = DateUtils.addMin(endDate, -15);
        if (endDate.after(date) && startDate.before(date)) {
            flag = true;
        }
        return flag;
    }

    @Override
    public RecBrickResult recBrickResult(RecBrickParam param, AppUser appUser) {
        RecBrickResult recBrickResult = new RecBrickResult();
        AppActivity appActivity = findActivityById(param.getAid());
        if (appActivity == null) return recBrickResult;
        RewardMessage rewardMessage = new RewardMessage();
        rewardMessage.setUid(appUser.getId());
        rewardMessage.setAppActivity(appActivity);
        rewardMessage.setActivityType(ActivityType.GAME);
        rewardMessage.setMethodName(MethodName.count);
        rewardMessage.setRecord(param.getRecord());

        Integer maxSurplusTimes = 0;
        Integer resetAmount = null;
        Boolean canReset = false;
        AppGameHist appGameHist = count(rewardMessage);
        if (appGameHist == null) {
            return recBrickResult;
        }
        recBrickResult.setReword(appGameHist.getReward() == null ? 0 : appGameHist.getReward().intValue());
        List<AppGameConfig> configList = appGameConfigService.findByAid(rewardMessage.getAppActivity().getId());
        List<AppGameConfig> configListMore = new ArrayList<>();
        for (AppGameConfig appGameConfig : configList) {
            if (appGameConfig.getType().equals(GameConfigType.REWARD) && appGameConfig.getAmount() > recBrickResult.getReword()) {
                configListMore.add(appGameConfig);
            }
            if (appGameConfig.getType().equals(GameConfigType.RESET)) {
                if (appGameConfig.getRecord() == (appGameHist.getReset() + 1)) {
                    resetAmount = appGameConfig.getAmount();
                    canReset = true;
                }

                if (appGameConfig.getRecord() > appGameHist.getReset() && appGameConfig.getRecord() > maxSurplusTimes) {
                    maxSurplusTimes = appGameConfig.getRecord();
                }

            }
        }
        Collections.sort(configListMore, new Comparator<AppGameConfig>() {
            @Override
            public int compare(AppGameConfig o1, AppGameConfig o2) {
                if (o1.getRecord() > o2.getRecord()) {
                    return 1;
                } else if (o1.getRecord().equals(o2.getRecord())) {
                    if (o1.getAmount() > o2.getAmount()) {
                        return 1;
                    } else if (o1.getAmount().equals(o2.getAmount())) {
                        return 0;
                    } else {
                        return -1;
                    }
                } else {
                    return -1;
                }
            }
        });
        if (configListMore.size() == 0) {
            recBrickResult.setRecordMore(0);
            recBrickResult.setRewordMore(0);
        } else {
            AppGameConfig appGameConfig = configListMore.get(0);
            recBrickResult.setRecordMore(appGameConfig.getRecord() - param.getRecord());
            recBrickResult.setRewordMore(appGameConfig.getAmount());
        }

        recBrickResult.setCanReset(canReset);
        recBrickResult.setResetSurplusTimes(maxSurplusTimes == 0 ? maxSurplusTimes : (maxSurplusTimes - appGameHist.getReset()));
        recBrickResult.setResetAmount(resetAmount);
        return recBrickResult;
    }

    public AppGameHist count(RewardMessage rewardMessage) {
        if (rewardMessage.getAppActivity() == null) return null;
        List<AppGameConfig> configList = appGameConfigService.findByAid(rewardMessage.getAppActivity().getId());
        AppGameHist appGameHist = appActivityHistService.findWaitHist(rewardMessage.getUid(), rewardMessage.getAppActivity().getId());
        if (appGameHist == null) return null;
        appGameHist.setRecord(rewardMessage.getRecord());
        if (rewardMessage.getRecord() > 0) {
            Integer maxRecordConfig = 0;
            Integer maxAmount = 0;
            for (AppGameConfig appGameConfig : configList) {
                if (appGameConfig.getType().equals(GameConfigType.REWARD) && appGameConfig.getRecord() > maxRecordConfig) {
                    maxRecordConfig = appGameConfig.getRecord();
                    maxAmount = appGameConfig.getAmount();
                }
                if (appGameConfig.getType().equals(GameConfigType.REWARD) && appGameConfig.getRecord().equals(rewardMessage.getRecord())) {
                    appGameHist.setReward(new BigDecimal(appGameConfig.getAmount()));
                    break;
                }
            }
            if (rewardMessage.getRecord() > maxRecordConfig) {
                appGameHist.setReward(new BigDecimal(maxAmount));
            }
        } else {
            appGameHist.setReward(new BigDecimal("0.00"));
        }
        appActivityHistService.save(appGameHist);
        return appGameHist;
    }

    @Override
    public void end(ActivityBaseParam param, AppUser appUser) {
        AppActivity appActivity = findActivityById(param.getAid());
        if (appActivity == null) return;
        rewardService.execute(RewardMessage.getInstance(MethodName.award, appUser.getId(), appActivity, ActivityType.GAME, null));
    }

    @Override
    public AppActivity findActivityByType(RewardType rewardType) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("behaviorType", rewardType.toString());
        return get(appActivityDao.queryForList(wrapper));
    }

    @Autowired
    @Qualifier("iconService")
    private IconService iconService;

    @Override
    public QueryResult<List<ActivityPageResult>> getActivityPage(ActivityPageParam param) {

        QueryResult<List<ActivityPageResult>> queryResult = new QueryResult<>();
        Map<String, Object> params = CommonBusiness.httpPageParam(param);
        QueryResult<List<AppActivity>> queryResultSource = queryForPage(AppActivity.class, "findActivityPage", params);
        if(ObjectUtils.isEmpty(queryResultSource)) {
        	return queryResult;
        }
        List<AppActivity> list = queryResultSource.getResult();
        List<ActivityPageResult> results = new ArrayList<>();
        for (AppActivity appActivity : list) {
            ActivityPageResult activityPageResult = new ActivityPageResult();
            activityPageResult.setTitle(appActivity.getTitle());
            activityPageResult.setActivityType(appActivity.getActivityType());
            activityPageResult.setEnglishTitle(appActivity.getEnglishTitle());
            activityPageResult.setFreeSize(appActivity.getFreeSize());
            activityPageResult.setId(appActivity.getId());
            activityPageResult.setBehaviorType(appActivity.getBehaviorType());
            activityPageResult.setUpdateTime(appActivity.getUpdateTime() == null ? null : appActivity.getUpdateTime().getTime());

            activityPageResult.setActivityUrl(appActivity.getActivityUrl());
            //获取url
            if (!StringUtils.isEmpty(appActivity.getBackgroundUrl())) {
                String logoUrl = "";
                if (appActivity.getBackgroundUrl().contains("http")) {
                    logoUrl = appActivity.getBackgroundUrl();
                } else {
                    logoUrl = iconService.buildURL(appActivity.getBackgroundUrl());
                }
                activityPageResult.setBackgroundUrl(logoUrl);
            }

            activityPageResult.setDesc(appActivity.getChineseDesc());
            activityPageResult.setEnglishDesc(appActivity.getEnglishDesc());
            activityPageResult.setOrder(appActivity.getActivityOrder());
            results.add(activityPageResult);
        }
        queryResult.setPageInfo(queryResultSource.getPageInfo());
        queryResult.setResult(results);
        return queryResult;
    }

    @Override
    public QueryResult<List<RewardPageResult>> findRewardPage(RewardPageParam param) {
        Map<String, Object> params = CommonBusiness.httpPageParam(param);
        QueryResult<List<RewardPageResult>> queryResultSource = queryForPage(RewardPageResult.class, "findRewardPage", params);
        return queryResultSource;
    }

    @Override
    public void updateActivity(ActivityUpdParam param) {
        if (ActivityType.GAME.equals(param.getActivityType()) || ActivityType.GAMEWITHAIRDROP.equals(param.getActivityType())) {
            if (StringUtils.isEmpty(param.getActivityUrl())) {
                throw new BusinessException(CodeRes.CODE_15046);
            }
            if (StringUtils.isEmpty(param.getFreeSize())) {
                throw new BusinessException(CodeRes.CODE_15056);
            }
            if (StringUtils.isEmpty(param.getBackgroundUrl())) {
                throw new BusinessException(CodeRes.CODE_15052);
            }
        }
        AppActivity appActivity = findActivityById(param.getId());
        if (appActivity == null) {
            throw new BusinessException(CodeRes.CODE_15021);
        }
        appActivity.setActivityType(param.getActivityType());
        appActivity.setActivityUrl(param.getActivityUrl());
        appActivity.setBackgroundUrl(param.getBackgroundUrl());
        appActivity.setBehaviorType(param.getBehaviorType());
        appActivity.setChineseDesc(param.getDesc());
        appActivity.setEnglishDesc(param.getEnglishDesc());
        appActivity.setEnglishTitle(param.getEnglishTitle());
        appActivity.setFreeSize(param.getFreeSize());
        appActivity.setActivityOrder(param.getOrder());
        appActivity.setTitle(param.getTitle());
        appActivity.setUpdateTime(new Date());
        save(appActivity);
    }

    @Override
    public void addActivity(ActivityAddParam param) {
        if (ActivityType.GAME.equals(param.getActivityType()) || ActivityType.GAMEWITHAIRDROP.equals(param.getActivityType())) {
            if (StringUtils.isEmpty(param.getActivityUrl())) {
                throw new BusinessException(CodeRes.CODE_15046);
            }
            if (StringUtils.isEmpty(param.getFreeSize())) {
                throw new BusinessException(CodeRes.CODE_15056);
            }
            if (StringUtils.isEmpty(param.getBackgroundUrl())) {
                throw new BusinessException(CodeRes.CODE_15052);
            }
        }
        AppActivity appActivity = new AppActivity();
        appActivity.setActivityType(param.getActivityType());
        appActivity.setActivityUrl(param.getActivityUrl());
        appActivity.setBackgroundUrl(param.getBackgroundUrl());
        appActivity.setBehaviorType(param.getBehaviorType());
        appActivity.setChineseDesc(param.getDesc());
        appActivity.setEnglishDesc(param.getEnglishDesc());
        appActivity.setEnglishTitle(param.getEnglishTitle());
        appActivity.setFreeSize(param.getFreeSize());
        appActivity.setActivityOrder(param.getOrder());
        appActivity.setTitle(param.getTitle());
        appActivity.setCreateTime(new Date());
        save(appActivity);
    }

    @Override
    public QueryResult<List<GameConfigPageResult>> findGameConfigPage(GameConfigPageParam param) {

        QueryResult<List<GameConfigPageResult>> queryResult = new QueryResult<>();
        Map<String, Object> params = CommonBusiness.httpPageParam(param);
        QueryResult<List<AppGameConfig>> queryResultSource = queryForPage(AppGameConfig.class, "findGameConfigPage", params);
        if(ObjectUtils.isEmpty(queryResultSource)) {
        	return queryResult;
        }
        List<AppGameConfig> list = queryResultSource.getResult();
        List<GameConfigPageResult> results = new ArrayList<>();

        List<AppActivity> activityList = new ArrayList<>();
        if (list != null && !list.isEmpty()) {
            activityList = queryForAll();
        }
        for (AppGameConfig appGameConfig : list) {
            GameConfigPageResult gameConfigPageResult = new GameConfigPageResult();
            gameConfigPageResult.setAmount(appGameConfig.getAmount());
            gameConfigPageResult.setConfigId(appGameConfig.getId());
            gameConfigPageResult.setGameId(appGameConfig.getAid());
            for (AppActivity appActivity : activityList) {
                if (appActivity.getId().equals(appGameConfig.getAid())) {
                    gameConfigPageResult.setGameName(appActivity.getTitle());
                }
            }
            gameConfigPageResult.setRecord(appGameConfig.getRecord());
            gameConfigPageResult.setTimeInDay(appGameConfig.getTimeInDay());
            gameConfigPageResult.setType(appGameConfig.getType());
            gameConfigPageResult.setUnit(appGameConfig.getUnit());
            gameConfigPageResult.setUpdateTime(appGameConfig.getUpdateTime() == null ? null : appGameConfig.getUpdateTime().getTime());
            results.add(gameConfigPageResult);
        }
        queryResult.setPageInfo(queryResultSource.getPageInfo());
        queryResult.setResult(results);
        return queryResult;
    }


    @Override
    public QueryResult<List<BehaviorPageResult>> findBehaviorPage(BehaviorPageParam param) {
        QueryResult<List<BehaviorPageResult>> queryResult = new QueryResult<>();
        Map<String, Object> params = CommonBusiness.httpPageParam(param);
        QueryResult<List<AppBehaviorConfig>> queryResultSource = queryForPage(AppBehaviorConfig.class, "findBehaviorPage", params);
        if(ObjectUtils.isEmpty(queryResultSource)) {
        	return queryResult;
        }
        List<AppBehaviorConfig> list = queryResultSource.getResult();
        List<BehaviorPageResult> results = new ArrayList<>();

        List<AppActivity> activityList = new ArrayList<>();
        if (list != null && !list.isEmpty()) {
            activityList = queryForAll();
        }
        for (AppBehaviorConfig appBehaviorConfig : list) {
            BehaviorPageResult behaviorPageResult = new BehaviorPageResult();
            for (AppActivity appActivity : activityList) {
                if (appActivity.getId().equals(appBehaviorConfig.getAid())) {
                    behaviorPageResult.setGameName(appActivity.getTitle());
                }
            }
            behaviorPageResult.setBehaviorId(appBehaviorConfig.getId());
            behaviorPageResult.setFristTime(appBehaviorConfig.getFristTime());
            behaviorPageResult.setGrowthTime(appBehaviorConfig.getGrowthTime());
            behaviorPageResult.setMaxAmount(appBehaviorConfig.getMaxAmount());
            behaviorPageResult.setMinAmount(appBehaviorConfig.getMinAmount());
            behaviorPageResult.setSizeInOnce(appBehaviorConfig.getSizeInOnce());
            behaviorPageResult.setTimeInDay(appBehaviorConfig.getTimeInDay());
            behaviorPageResult.setTimeInOnce(appBehaviorConfig.getTimeInOnce());
            behaviorPageResult.setUnit(appBehaviorConfig.getUnit());
            behaviorPageResult.setUpdateTime(appBehaviorConfig.getUpdateTime() == null ? null : appBehaviorConfig.getUpdateTime().getTime());
            results.add(behaviorPageResult);
        }
        queryResult.setPageInfo(queryResultSource.getPageInfo());
        queryResult.setResult(results);
        return queryResult;
    }

    private List<ActivityListResult> transformation(List<AppActivity> activityList, LanguageEnum language, AppUser appUser) {
        List<ActivityListResult> activityListResults = new ArrayList<>();
        for (AppActivity appActivity : activityList) {
            ActivityListResult activityListResult = new ActivityListResult();
            activityListResult.setActivityUrl(appActivity.getActivityUrl());
            activityListResult.setAid(appActivity.getId());
            String logoUrl = "";
            if (!StringUtils.isEmpty(appActivity.getBackgroundUrl())) {
                if (appActivity.getBackgroundUrl().contains("http")) {
                    logoUrl = appActivity.getBackgroundUrl();
                } else {
                    logoUrl = iconService.buildURL(appActivity.getBackgroundUrl());
                }
                activityListResult.setBackgroundUrl(logoUrl);
            }
            if (language.equals(LanguageEnum.CN)) {
                activityListResult.setTitle(appActivity.getTitle());
                activityListResult.setDesc(appActivity.getChineseDesc());
            } else {
                activityListResult.setTitle(appActivity.getEnglishTitle());
                activityListResult.setDesc(appActivity.getEnglishDesc());
            }
            AppGameConfig appGameConfig = appGameConfigService.findTicketByAid(appActivity.getId());
            if (appGameConfig == null) throw new BusinessException(CodeRes.CODE_15029);
            activityListResult.setUnit(appGameConfig.getUnit());
            BigDecimal record = appActivityHistService.countRewardByUidAndAid(appUser.getId(), appActivity.getId());
            BigInteger timeSize = appActivityHistService.countTimeByAid(appActivity.getId());
            activityListResult.setRecord(record);
            String key = "timeDesc-";
            if (LanguageEnum.CN.equals(language)) {
                key = key + language.toString();
            } else {
                key = key + LanguageEnum.EN.toString();
            }
            String desc = String.valueOf(configureService.getString(key));
            activityListResult.setTimeDesc(String.format(desc, timeSize.intValue() * getMultiple()));
            activityListResult.setTicketPrice(appGameConfig.getAmount());
            activityListResults.add(activityListResult);
        }
        return activityListResults;
    }

    private Integer getMultiple() {
        String multiple = String.valueOf(configureService.getString("timeDesc-multiple"));
        return Integer.valueOf(multiple);
    }

}
