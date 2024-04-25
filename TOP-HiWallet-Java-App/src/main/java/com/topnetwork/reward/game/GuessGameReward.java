package com.topnetwork.reward.game;

import com.common.utils.DateUtils;
import com.topnetwork.reward.Reward;
import com.topnetwork.reward.bean.RewardMessage;
import com.topnetwork.wallet.common.enums.*;
import com.topnetwork.wallet.entity.AppActivityReward;
import com.topnetwork.wallet.entity.AppGameHist;
import com.topnetwork.wallet.service.AppActivityHistService;
import com.topnetwork.wallet.service.AppActivityRewardService;
import com.topnetwork.wallet.service.AppUserBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
public class GuessGameReward extends GameAbstract implements Reward {

    @Autowired
    private AppActivityRewardService appActivityRewardService;
    @Autowired
    private AppActivityHistService appActivityHistService;
    @Autowired
    private AppUserBalanceService appUserBalanceService;

    @Override
    public ActivityType getActivityType() {
        return ActivityType.GAME;
    }

    @Override
    public void award(RewardMessage rewardMessage) {
        if (rewardMessage.getAppActivity() == null) return;
        List<AppGameHist> appGameHistList = appActivityHistService.findWaitHist(rewardMessage.getAppActivity().getId(), rewardMessage.getAppBtcPrice().getPhase());
        if (appGameHistList == null || appGameHistList.isEmpty()) return;
        BigDecimal pool = new BigDecimal("0");
        BigDecimal poolThisStatus = new BigDecimal("0");
        for (AppGameHist appGameHist : appGameHistList) {
            pool = pool.add(new BigDecimal(appGameHist.getAmount()));
            if (appGameHist.getUpsAndDowns().equals(rewardMessage.getUpsAndDowns())) {
                poolThisStatus = poolThisStatus.add(new BigDecimal(appGameHist.getAmount()));
            }
        }
        for (AppGameHist appGameHist : appGameHistList) {
            if (appGameHist.getUpsAndDowns().equals(rewardMessage.getUpsAndDowns())) {
                //根据比例发放奖励
                BigDecimal reward = new BigDecimal(appGameHist.getAmount()).multiply(pool).divide(poolThisStatus, 2, BigDecimal.ROUND_HALF_UP);
                Date now = new Date();
                AppActivityReward appActivityReward = new AppActivityReward();
                appActivityReward.setReceive(true);
                appActivityReward.setReceiveTime(now);
                appActivityReward.setSource(RewardFromType.GAME);
                appActivityReward.setVerification(false);
                appActivityReward.setSide(RewardSide.REWARD);
                appActivityReward.setExpirationTime(now);
                appActivityReward.setType(rewardMessage.getAppActivity().getBehaviorType());
                appActivityReward.setUid(appGameHist.getUid());
                appActivityReward.setCreateTime(now);
                appActivityReward.setEffectiveTime(now);
                appActivityReward.setComment(rewardMessage.getAppActivity().getTitle() + "," + rewardMessage.getAppActivity().getEnglishTitle());
                appActivityReward.setAid(rewardMessage.getAppActivity().getId());
                appActivityReward.setUnit(appGameHist.getUnit());
                appActivityReward.setAmount(reward);
                long rewardId = appActivityRewardService.saveReturnId(appActivityReward);
                appUserBalanceService.receiveAward(appActivityReward);
                appGameHist.setReward(reward);
                appGameHist.setStatus(GuessStatus.SUCCESS);
            } else {
                appGameHist.setReward(new BigDecimal("0.00"));
                appGameHist.setStatus(GuessStatus.FAIL);
            }
            appActivityHistService.save(appGameHist);
        }
    }

    @Override
    public void start(RewardMessage rewardMessage) {
        if (rewardMessage.getAppActivity() == null) return;

        Date now = new Date();
        AppActivityReward appActivityReward = new AppActivityReward();
        appActivityReward.setReceive(true);
        appActivityReward.setReceiveTime(now);
        appActivityReward.setSource(RewardFromType.GAME);
        appActivityReward.setVerification(false);
        appActivityReward.setSide(RewardSide.SPEND);
        appActivityReward.setExpirationTime(now);
        appActivityReward.setType(rewardMessage.getAppActivity().getBehaviorType());
        appActivityReward.setUid(rewardMessage.getUid());
        appActivityReward.setCreateTime(now);
        appActivityReward.setEffectiveTime(now);
        appActivityReward.setComment(rewardMessage.getAppActivity().getTitle() + "," + rewardMessage.getAppActivity().getEnglishTitle());
        appActivityReward.setAid(rewardMessage.getAppActivity().getId());


        AppGameHist appGameHist = new AppGameHist();
        appGameHist.setUid(rewardMessage.getUid());
        appGameHist.setStatus(GuessStatus.WAIT);
        appGameHist.setPhase(DateUtils.getGuessGamePhase(now));
        appGameHist.setCreateTime(now);
        appGameHist.setAid(rewardMessage.getAppActivity().getId());
        appGameHist.setReset(0);
        appGameHist.setUpsAndDowns(rewardMessage.getUpsAndDowns());

        appActivityReward.setUnit(BalanceType.TOP);
        appGameHist.setUnit(BalanceType.TOP);

        //扣除质押金额
        appActivityReward.setAmount(new BigDecimal(rewardMessage.getRecord()));
        long rewardId = appActivityRewardService.saveReturnId(appActivityReward);
        appUserBalanceService.receiveAward(appActivityReward);

        appGameHist.setRewardId(rewardId);
        //记录抵押金额
        appGameHist.setAmount(rewardMessage.getRecord());
        appActivityHistService.save(appGameHist);


    }

    @Override
    public void reset(RewardMessage rewardMessage) {

    }

    @Override
    public void count(RewardMessage rewardMessage) {
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
        appActivityReward.setUnit(BalanceType.TOP);
        appActivityReward.setAmount(rewardMessage.getReward());
        appActivityRewardService.saveReturnId(appActivityReward);
        appUserBalanceService.receiveAward(appActivityReward);
    }

    @Override
    public List<RewardType> getRewardType() {
        return Arrays.asList(RewardType.GUESS);
    }
}
