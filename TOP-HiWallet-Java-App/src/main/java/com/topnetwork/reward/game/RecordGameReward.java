package com.topnetwork.reward.game;

import com.common.utils.DateUtils;
import com.topnetwork.reward.Reward;
import com.topnetwork.reward.bean.RewardMessage;
import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.common.enums.*;
import com.topnetwork.wallet.entity.AppActivityReward;
import com.topnetwork.wallet.entity.AppGameConfig;
import com.topnetwork.wallet.entity.AppGameHist;
import com.topnetwork.wallet.service.AppActivityHistService;
import com.topnetwork.wallet.service.AppActivityRewardService;
import com.topnetwork.wallet.service.AppGameConfigService;
import com.topnetwork.wallet.service.AppUserBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.gitee.magic.framework.head.exception.BusinessException;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
public class RecordGameReward extends GameAbstract implements Reward {

    @Autowired
    private AppGameConfigService appGameConfigService;
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
        AppGameHist appGameHist = appActivityHistService.findWaitHist(rewardMessage.getUid(), rewardMessage.getAppActivity().getId());
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
    public void count(RewardMessage rewardMessage) {
        if (rewardMessage.getAppActivity() == null) return;
        List<AppGameConfig> configList = appGameConfigService.findByAid(rewardMessage.getAppActivity().getId());
        AppGameHist appGameHist = appActivityHistService.findWaitHist(rewardMessage.getUid(), rewardMessage.getAppActivity().getId());
        if (appGameHist == null) return;
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
    }

    @Override
    @Transactional
    public void start(RewardMessage rewardMessage) {

        if (rewardMessage.getAppActivity() == null) return;
        Integer freeSize = rewardMessage.getAppActivity().getFreeSize();
        List<AppGameConfig> configList = appGameConfigService.findByAid(rewardMessage.getAppActivity().getId());

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
        appGameHist.setPhase(DateUtils.format(now, DateUtils.YYYYMMDDHHMMSS_SSS));
        appGameHist.setCreateTime(now);
        appGameHist.setAid(rewardMessage.getAppActivity().getId());
        appGameHist.setReset(0);


        Integer ticketSize = 0;
        Integer sizeInDay = 0;
        for (AppGameConfig appGameConfig : configList) {
            if (appGameConfig.getType().equals(GameConfigType.TICKET)) {
                ticketSize = appGameConfig.getAmount();
                appActivityReward.setUnit(appGameConfig.getUnit());
                appGameHist.setUnit(appGameConfig.getUnit());
                sizeInDay = appGameConfig.getTimeInDay();
            }
        }


        List<AppGameHist> histList = appActivityHistService.findByAidAndUid(rewardMessage.getAppActivity().getId(), rewardMessage.getUid());

        if (rewardMessage.getAppActivity().getBehaviorType().equals(RewardType.ANSWER)
                || rewardMessage.getAppActivity().getBehaviorType().equals(RewardType.BRICK)) {
            //答题游戏限制次数
            if (histList != null && histList.size() >= sizeInDay) {
                throw new BusinessException(CodeRes.CODE_15030);
            }
        }

        if (freeSize != null && freeSize > 0) {
            if (histList != null && histList.size() >= freeSize) {
                //收取门票
                appActivityReward.setAmount(new BigDecimal(ticketSize));
            } else {
                //门票为零
                appActivityReward.setAmount(new BigDecimal("0"));
            }
        } else {
            //收取门票
            appActivityReward.setAmount(new BigDecimal(ticketSize));
        }


        long rewardId = appActivityRewardService.saveReturnId(appActivityReward);
        appUserBalanceService.receiveAward(appActivityReward);
        appGameHist.setRewardId(rewardId);
        appActivityHistService.save(appGameHist);

    }

    @Override
    @Transactional
    public void reset(RewardMessage rewardMessage) {
        if (rewardMessage.getAppActivity() == null) return;
        List<AppGameConfig> configList = appGameConfigService.findByAid(rewardMessage.getAppActivity().getId());

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

        AppGameHist appGameHist = appActivityHistService.findWaitHist(rewardMessage.getUid(), rewardMessage.getAppActivity().getId());
        if (appGameHist == null) return;

        Integer resetAmount = -1;
        for (AppGameConfig appGameConfig : configList) {
            if (appGameConfig.getType().equals(GameConfigType.RESET) && appGameConfig.getRecord().equals(appGameHist.getReset() + 1)) {
                resetAmount = appGameConfig.getAmount();
                appActivityReward.setUnit(appGameConfig.getUnit());
            }
        }
        if (resetAmount == -1) {
            throw new BusinessException(CodeRes.CODE_15035);
        }
        appActivityReward.setAmount(new BigDecimal(resetAmount));
        appActivityRewardService.save(appActivityReward);
        appUserBalanceService.receiveAward(appActivityReward);
        appGameHist.setReset(appGameHist.getReset() + 1);
        appActivityHistService.save(appGameHist);
    }

    @Override
    public List<RewardType> getRewardType() {
        return Arrays.asList(RewardType.ANSWER, RewardType.BRICK);
    }
}
