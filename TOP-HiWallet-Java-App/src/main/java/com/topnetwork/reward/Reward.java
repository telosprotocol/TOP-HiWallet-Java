package com.topnetwork.reward;

import com.topnetwork.reward.bean.RewardMessage;
import com.topnetwork.wallet.common.enums.ActivityType;
import com.topnetwork.wallet.common.enums.RewardType;

import java.util.List;

public interface Reward {

    ActivityType getActivityType();

    List<RewardType> getRewardType();

    /**
     * 发放奖励
     */
    void award(RewardMessage rewardMessage);

    /**
     * 开始
     */
    void start(RewardMessage rewardMessage);

    /**
     * 重置
     */
    void reset(RewardMessage rewardMessage);

    /**
     * 计算奖励
     */
    void count(RewardMessage rewardMessage);

}
