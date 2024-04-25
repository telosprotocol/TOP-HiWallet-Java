package com.topnetwork.wallet.result.changelly;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

/**
 * @ClassName GetPairInfoResult
 * @Description
 * @Author bran
 * @Date 2020/6/15 14:30
 */
public class GetPairInfoResult {

    @Schema(title="以买入为基准的汇率")
    private BigDecimal rate;
    @Schema(title="最小卖出数量")
    private BigDecimal minAmount;
    @Schema(title="最大卖出数量")
    private BigDecimal maxAmount;

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public BigDecimal getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(BigDecimal minAmount) {
        this.minAmount = minAmount;
    }

    public BigDecimal getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(BigDecimal maxAmount) {
        this.maxAmount = maxAmount;
    }
}
