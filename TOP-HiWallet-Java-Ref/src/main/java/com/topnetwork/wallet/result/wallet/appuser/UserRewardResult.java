package com.topnetwork.wallet.result.wallet.appuser;

import com.topnetwork.wallet.common.enums.BalanceType;
import com.topnetwork.wallet.common.enums.RewardSide;
import com.topnetwork.wallet.common.enums.RewardType;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

public class UserRewardResult {

    @Schema(title = "可领取数量")
    private BigDecimal amount;
    @Schema(title = "奖励类型描述（注册空投、新用户空投、每日空投、转账空投、答题空投、区块链游戏空投/奖励、竞猜空投/奖励）")
    private String type;
    @Schema(title = "奖励类型（REGISTER,//注册空投;NEWUSER,//新用户空投;DAILY,//每日空投;TRANSFER,//转账空投;ANSWER,//回答问题;BRICK,//砖块游戏;GUESS,//竞猜;OTHER,//其他")
    private RewardType rewardType;
    @Schema(title = "倒计时")
    private Long countDown;
    @Schema(title = "是否可领取")
    private Boolean canReceive;
    @Schema(title = "奖励id")
    private Long rewardId;
    @Schema(title = "奖励单位")
    private BalanceType unit;
    @Schema(title = "记录方向，扣费/奖励")
    private RewardSide side;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getCountDown() {
        return countDown;
    }

    public void setCountDown(Long countDown) {
        this.countDown = countDown;
    }

    public Boolean getCanReceive() {
        return canReceive;
    }

    public void setCanReceive(Boolean canReceive) {
        this.canReceive = canReceive;
    }

    public Long getRewardId() {
        return rewardId;
    }

    public void setRewardId(Long rewardId) {
        this.rewardId = rewardId;
    }

    public BalanceType getUnit() {
        return unit;
    }

    public void setUnit(BalanceType unit) {
        this.unit = unit;
    }

    public RewardSide getSide() {
        return side;
    }

    public void setSide(RewardSide side) {
        this.side = side;
    }

    public RewardType getRewardType() {
        return rewardType;
    }

    public void setRewardType(RewardType rewardType) {
        this.rewardType = rewardType;
    }
}
