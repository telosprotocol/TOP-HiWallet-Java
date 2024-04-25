package com.topnetwork.wallet.result.wallet.invite;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

/**
 * @ClassName RewardNumberResult
 * @Description
 * @Author bran
 * @Date 2020/5/7 14:59
 */
public class RewardNumberResult {

    @Schema(title="新用户奖励")
    private BigDecimal newUserReward;
    @Schema(title="邀请奖励")
    private BigDecimal inviteReward;
    @Schema(title="邀请奖励过期时间（天）")
    private Integer inviteTimeLimit;
    @Schema(title="top币价")
    private String price;

    public BigDecimal getNewUserReward() {
        return newUserReward;
    }

    public void setNewUserReward(BigDecimal newUserReward) {
        this.newUserReward = newUserReward;
    }

    public BigDecimal getInviteReward() {
        return inviteReward;
    }

    public void setInviteReward(BigDecimal inviteReward) {
        this.inviteReward = inviteReward;
    }

    public Integer getInviteTimeLimit() {
        return inviteTimeLimit;
    }

    public void setInviteTimeLimit(Integer inviteTimeLimit) {
        this.inviteTimeLimit = inviteTimeLimit;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
