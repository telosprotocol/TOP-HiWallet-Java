package com.topnetwork.wallet.result.wallet.appuser;

import com.topnetwork.wallet.common.enums.BalanceType;
import com.topnetwork.wallet.common.enums.RewardSide;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

public class RewardHistResult {

    @Schema(title = "可领取数量")
    private BigDecimal amount;
    @Schema(title = "奖励类型（注册空投、新用户空投、每日空投、转账空投、答题空投、区块链游戏空投/奖励、竞猜空投/奖励）")
    private String type;
    @Schema(title = "奖励单位")
    private BalanceType unit;
    @Schema(title = "记录方向，扣费/奖励")
    private RewardSide side;
    @Schema(title = "领取时间")
    private Long receiveTime;
    @Schema(title = "奖励id")
    private Long rewardId;

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

    public Long getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Long receiveTime) {
        this.receiveTime = receiveTime;
    }

    public Long getRewardId() {
        return rewardId;
    }

    public void setRewardId(Long rewardId) {
        this.rewardId = rewardId;
    }
}
