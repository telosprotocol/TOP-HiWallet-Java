package com.topnetwork.wallet.result.wallet.activity;

import com.topnetwork.wallet.common.enums.GuessStatus;
import com.topnetwork.wallet.common.enums.UpsAndDowns;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.math.BigInteger;

public class GameHistResult {

    @Schema(title = "分数")
    private Integer record;

    @Schema(title = "游戏期数")
    private String phase;

    @Schema(title = "押金数量")
    private Integer amount;

    @Schema(title = "竞猜涨跌")
    private UpsAndDowns upsAndDowns;

    @Schema(title = "游戏状态、成功、失败、未完成")
    private GuessStatus status;

    @Schema(title = "创建时间")
    private Long createTime;

    @Schema(title = "奖励数量")
    private BigDecimal reward;
    @Schema(title = "复活数量")
    private Integer reset;
    @Schema(title = "扣费记录id")
    private Long rewardId;

    public Integer getRecord() {
        return record;
    }

    public void setRecord(Integer record) {
        this.record = record;
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public UpsAndDowns getUpsAndDowns() {
        return upsAndDowns;
    }

    public void setUpsAndDowns(UpsAndDowns upsAndDowns) {
        this.upsAndDowns = upsAndDowns;
    }

    public GuessStatus getStatus() {
        return status;
    }

    public void setStatus(GuessStatus status) {
        this.status = status;
    }

    public BigDecimal getReward() {
        return reward;
    }

    public void setReward(BigDecimal reward) {
        this.reward = reward;
    }

    public Integer getReset() {
        return reset;
    }

    public void setReset(Integer reset) {
        this.reset = reset;
    }

    public Long getRewardId() {
        return rewardId;
    }

    public void setRewardId(Long rewardId) {
        this.rewardId = rewardId;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}
