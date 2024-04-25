package com.topnetwork.reward.airdrop;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.base.service.base.system.service.ConfigureService;
import com.topnetwork.reward.bean.RewardMessage;
import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.common.enums.ActivityType;
import com.topnetwork.wallet.common.enums.GrantType;
import com.topnetwork.wallet.common.enums.RewardFromType;
import com.topnetwork.wallet.common.enums.RewardSide;
import com.topnetwork.wallet.entity.AppActivityReward;
import com.topnetwork.wallet.entity.AppBehaviorConfig;
import com.topnetwork.wallet.service.AppActivityRewardService;
import com.topnetwork.wallet.service.AppBehaviorConfigService;

import com.gitee.magic.framework.head.exception.BusinessException;

@Component
public class AirdropReward extends AirdropAbstract {

    @Autowired
    private AppBehaviorConfigService appBehaviorConfigService;
    @Autowired
    private AppActivityRewardService appActivityRewardService;
    @Autowired
    private ConfigureService configureService;

    @Override
    public ActivityType getActivityType() {
        return ActivityType.AIRDROP;
    }

    @Override
    public void award(RewardMessage rewardMessage) {
        if (rewardMessage.getAppActivity() == null) return;
        AppBehaviorConfig appBehaviorConfig = appBehaviorConfigService.findByAid(rewardMessage.getAppActivity().getId());
        if (appBehaviorConfig == null) throw new BusinessException(CodeRes.CODE_15029);

        //判断是否超过每天限制
        if (appBehaviorConfig.getTimeInDay() > 0) {
            List<AppActivityReward> rewardList = appActivityRewardService.getThisDayByType(rewardMessage.getAppActivity().getBehaviorType(), rewardMessage.getUid(), RewardFromType.AIRDROP);
            if (rewardList != null && (rewardList.size() / (appBehaviorConfig.getSizeInOnce() * appBehaviorConfig.getTimeInOnce())) >= appBehaviorConfig.getTimeInDay()) {
                return;
            }
        }
        //获取空投过期时间配置
        String effectiveTimeConf = String.valueOf(configureService.getValue("airdropEffectiveTime"));
        Integer hours = appBehaviorConfig.getGrowthTime();
        Date createTime = new Date();
        Calendar cal = Calendar.getInstance();
        List<AppActivityReward> rewardList = new ArrayList<>();
        for (int i = 0; i < appBehaviorConfig.getTimeInOnce(); i++) {
            BigDecimal amount = new BigDecimal(getAmount(appBehaviorConfig.getMinAmount(), appBehaviorConfig.getMaxAmount())).divide(new BigDecimal(String.valueOf(appBehaviorConfig.getSizeInOnce())), 2, BigDecimal.ROUND_HALF_DOWN);
            for (int j = 0; j < appBehaviorConfig.getSizeInOnce(); j++) {
                AppActivityReward appActivityReward = new AppActivityReward();
                appActivityReward.setAid(rewardMessage.getAppActivity().getId());
                appActivityReward.setComment(rewardMessage.getAppActivity().getTitle() + "," + rewardMessage.getAppActivity().getEnglishTitle());
                cal.setTime(createTime);
                if (appBehaviorConfig.getFristTime().equals(GrantType.NEXTDAY)) {
                    cal.add(Calendar.HOUR, hours * (i + 1));
                } else {
                    cal.add(Calendar.HOUR, hours * i);
                }
                Date effectiveTime = cal.getTime();
                appActivityReward.setEffectiveTime(effectiveTime);
                cal.setTime(effectiveTime);
                cal.add(Calendar.DATE, Integer.valueOf(effectiveTimeConf));
                appActivityReward.setExpirationTime(cal.getTime());
                appActivityReward.setCreateTime(createTime);
                appActivityReward.setUid(rewardMessage.getUid());
                appActivityReward.setType(rewardMessage.getAppActivity().getBehaviorType());
                appActivityReward.setSide(RewardSide.REWARD);
                appActivityReward.setSource(RewardFromType.AIRDROP);
                appActivityReward.setVerification(false);
                appActivityReward.setReceive(false);
                appActivityReward.setAmount(amount);
                appActivityReward.setUnit(appBehaviorConfig.getUnit());
                rewardList.add(appActivityReward);
            }
        }
        appActivityRewardService.saveList(rewardList);
    }

    private String getAmount(Integer minAmount, Integer maxAmount) {
        Random random = new Random();
        Integer i = random.nextInt(maxAmount) % (maxAmount - minAmount + 1) + minAmount;
        return String.valueOf(i);
    }


    @Override
    public void start(RewardMessage rewardMessage) {
    }

    /**
     * 用作发放注册用户空投
     *
     * @param rewardMessage
     */
    @Override
    public void reset(RewardMessage rewardMessage) {

        if (rewardMessage.getAppActivity() == null) return;
        AppBehaviorConfig appBehaviorConfig = appBehaviorConfigService.findByAid(rewardMessage.getAppActivity().getId());
        if (appBehaviorConfig == null) throw new BusinessException(CodeRes.CODE_15029);
        //获取空投过期时间配置
        String effectiveTimeConf = String.valueOf(configureService.getValue("airdropEffectiveTime"));
        Integer hours = appBehaviorConfig.getGrowthTime();
        Date createTime = new Date();
        Calendar cal;
        Calendar cal_temp = Calendar.getInstance();
        cal_temp.setTime(rewardMessage.getUserCreateTime());

        for (int day = 0; day < rewardMessage.getNotSendDay().size(); day++) {
            Integer notSendDay = rewardMessage.getNotSendDay().get(day);
            List<AppActivityReward> rewardList = new ArrayList<>();

            BigDecimal amount = new BigDecimal(getAmount(appBehaviorConfig.getMinAmount(), appBehaviorConfig.getMaxAmount())).divide(new BigDecimal(String.valueOf(appBehaviorConfig.getSizeInOnce())), 2, BigDecimal.ROUND_HALF_DOWN);
            for (int j = 0; j < appBehaviorConfig.getSizeInOnce(); j++) {
                cal = Calendar.getInstance();
                cal.setTime(createTime);
                cal.add(Calendar.DATE, -notSendDay);
                cal.set(Calendar.HOUR_OF_DAY, cal_temp.get(Calendar.HOUR_OF_DAY));
                cal.set(Calendar.MINUTE, cal_temp.get(Calendar.MINUTE));
                cal.set(Calendar.SECOND, cal_temp.get(Calendar.SECOND));
                if (appBehaviorConfig.getFristTime().equals(GrantType.NEXTDAY)) {
                    cal.add(Calendar.HOUR, hours);
                }
                AppActivityReward appActivityReward = new AppActivityReward();
                appActivityReward.setAid(rewardMessage.getAppActivity().getId());
                appActivityReward.setComment(rewardMessage.getAppActivity().getTitle() + "," + rewardMessage.getAppActivity().getEnglishTitle());
                Date effectiveTime = cal.getTime();
                appActivityReward.setEffectiveTime(effectiveTime);
                appActivityReward.setCreateTime(effectiveTime);

                cal.setTime(effectiveTime);
                cal.add(Calendar.DATE, Integer.valueOf(effectiveTimeConf));
                appActivityReward.setExpirationTime(cal.getTime());

                appActivityReward.setUid(rewardMessage.getUid());
                appActivityReward.setType(rewardMessage.getAppActivity().getBehaviorType());
                appActivityReward.setSide(RewardSide.REWARD);
                appActivityReward.setSource(RewardFromType.AIRDROP);
                appActivityReward.setVerification(false);
                appActivityReward.setReceive(false);
                appActivityReward.setAmount(amount);
                appActivityReward.setUnit(appBehaviorConfig.getUnit());
                rewardList.add(appActivityReward);
            }
            appActivityRewardService.saveList(rewardList);
        }
    }

    /**
     * 用作每日奖励的发放
     */
    @Override
    public void count(RewardMessage rewardMessage) {
        if (rewardMessage.getAppActivity() == null) return;
        AppBehaviorConfig appBehaviorConfig = appBehaviorConfigService.findByAid(rewardMessage.getAppActivity().getId());
        if (appBehaviorConfig == null) throw new BusinessException(CodeRes.CODE_15029);
        //获取空投过期时间配置
        String effectiveTimeConf = String.valueOf(configureService.getValue("airdropEffectiveTime"));
        Integer hours = appBehaviorConfig.getGrowthTime();
        Date createTime = new Date();
        Calendar cal;
        for (int day = 0; day < rewardMessage.getNotSendDay().size(); day++) {
            Integer notSendDay = rewardMessage.getNotSendDay().get(day);
            List<AppActivityReward> rewardList = new ArrayList<>();
            for (int i = 0; i < appBehaviorConfig.getTimeInOnce(); i++) {
                BigDecimal amount = new BigDecimal(getAmount(appBehaviorConfig.getMinAmount(), appBehaviorConfig.getMaxAmount())).divide(new BigDecimal(String.valueOf(appBehaviorConfig.getSizeInOnce())), 2, BigDecimal.ROUND_HALF_DOWN);
                for (int j = 0; j < appBehaviorConfig.getSizeInOnce(); j++) {
                    cal = Calendar.getInstance();
                    cal.setTime(createTime);
                    cal.add(Calendar.DATE, -notSendDay);
                    cal.set(Calendar.HOUR_OF_DAY, 1);
                    cal.set(Calendar.MINUTE, 0);
                    cal.set(Calendar.SECOND, 0);
                    if (appBehaviorConfig.getFristTime().equals(GrantType.NEXTDAY)) {
                        cal.add(Calendar.HOUR, hours * (i + 1));
                    } else {
                        cal.add(Calendar.HOUR, hours * i);
                    }
                    AppActivityReward appActivityReward = new AppActivityReward();
                    appActivityReward.setAid(rewardMessage.getAppActivity().getId());
                    appActivityReward.setComment(rewardMessage.getAppActivity().getTitle() + "," + rewardMessage.getAppActivity().getEnglishTitle());
                    appActivityReward.setEffectiveTime(cal.getTime());
                    appActivityReward.setCreateTime(cal.getTime());
                    cal.add(Calendar.DATE, Integer.valueOf(effectiveTimeConf));
                    appActivityReward.setExpirationTime(cal.getTime());
                    appActivityReward.setUid(rewardMessage.getUid());
                    appActivityReward.setType(rewardMessage.getAppActivity().getBehaviorType());
                    appActivityReward.setSide(RewardSide.REWARD);
                    appActivityReward.setSource(RewardFromType.AIRDROP);
                    appActivityReward.setVerification(false);
                    appActivityReward.setReceive(false);
                    appActivityReward.setAmount(amount);
                    appActivityReward.setUnit(appBehaviorConfig.getUnit());
                    rewardList.add(appActivityReward);
                }
            }
            appActivityRewardService.saveList(rewardList);
        }

    }
}
