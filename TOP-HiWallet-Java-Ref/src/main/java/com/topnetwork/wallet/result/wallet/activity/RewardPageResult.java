package com.topnetwork.wallet.result.wallet.activity;

import com.topnetwork.wallet.common.enums.BalanceType;
import com.topnetwork.wallet.common.enums.RewardFromType;
import com.topnetwork.wallet.common.enums.RewardSide;
import com.topnetwork.wallet.common.enums.RewardType;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.Date;

public class RewardPageResult {
    @Schema(title = "奖励id")
    private Long rewardId;
    @Schema(title = "用户新青年号")
    private String certCode;
    @Schema(title = "奖励类型")
    private RewardType type;
    @Schema(title = "奖励、消耗数量")
    private BigDecimal amount;
    @Schema(title = "奖励单位")
    private BalanceType unit;
    @Schema(title = "奖励生效时间")
    private Date effectiveTime;
    @Schema(title = "奖励过期时间")
    private Date expirationTime;
    @Schema(title = "奖励领取时间")
    private Date receiveTime;
    @Schema(title = "奖励创建时间")
    private Date createTime;
    @Schema(title = "奖励来源（空投/游戏奖励）")
    private RewardFromType source;
    @Schema(title = "奖励方向（花费、奖励）")
    private RewardSide side;
    @Schema(title = "shi否被领取")
    private Boolean receive;

    public Long getRewardId() {
        return rewardId;
    }

    public void setRewardId(Long rewardId) {
        this.rewardId = rewardId;
    }

    public String getCertCode() {
        return certCode;
    }

    public void setCertCode(String certCode) {
        this.certCode = certCode;
    }

    public RewardType getType() {
        return type;
    }

    public void setType(RewardType type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BalanceType getUnit() {
        return unit;
    }

    public void setUnit(BalanceType unit) {
        this.unit = unit;
    }

    public Date getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(Date effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    public Date getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(Date expirationTime) {
        this.expirationTime = expirationTime;
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public RewardFromType getSource() {
        return source;
    }

    public void setSource(RewardFromType source) {
        this.source = source;
    }

    public RewardSide getSide() {
        return side;
    }

    public void setSide(RewardSide side) {
        this.side = side;
    }

    public Boolean getReceive() {
        return receive;
    }

    public void setReceive(Boolean receive) {
        this.receive = receive;
    }
}
