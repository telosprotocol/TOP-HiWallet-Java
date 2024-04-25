package com.topnetwork.wallet.result.wallet.withdraw;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

/**
 * @ClassName GetOrderDetailResult
 * @Description
 * @Author bran
 * @Date 2020/7/28 14:05
 */
public class GetOrderDetailResult {

    @Schema(title="注册时间")
    private Long createTime;
    @Schema(title="等于此余额的用户个数")
    private Long userSize;
    @Schema(title="最近7天的空投领取数量")
    private BigDecimal airDropAmount;
    @Schema(title="最近7天的空投领取次数")
    private Long airDropTimes;
    @Schema(title="最近7天的奖励领取数量")
    private BigDecimal rewardAmount;
    @Schema(title="最近7天的奖励领取次数")
    private Long rewardTimes;

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUserSize() {
        return userSize;
    }

    public void setUserSize(Long userSize) {
        this.userSize = userSize;
    }

    public BigDecimal getAirDropAmount() {
        return airDropAmount;
    }

    public void setAirDropAmount(BigDecimal airDropAmount) {
        this.airDropAmount = airDropAmount;
    }

    public Long getAirDropTimes() {
        return airDropTimes;
    }

    public void setAirDropTimes(Long airDropTimes) {
        this.airDropTimes = airDropTimes;
    }

    public BigDecimal getRewardAmount() {
        return rewardAmount;
    }

    public void setRewardAmount(BigDecimal rewardAmount) {
        this.rewardAmount = rewardAmount;
    }

    public Long getRewardTimes() {
        return rewardTimes;
    }

    public void setRewardTimes(Long rewardTimes) {
        this.rewardTimes = rewardTimes;
    }
}
