package com.topnetwork.reward.airdrop;

import com.topnetwork.reward.Reward;
import com.topnetwork.reward.bean.RewardMessage;
import com.topnetwork.wallet.common.enums.RewardType;
import com.topnetwork.wallet.entity.AppActivity;

import java.util.List;

public abstract class AirdropAbstract implements Reward {
    @Override
    public List<RewardType> getRewardType() {
        return null;
    }
}
