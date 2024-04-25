package com.topnetwork.wallet.result.defi;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

public class IncreaseInterestResult {

    @Schema(title = ">=最小值", required = true)
    private Long min;

    @Schema(title = "<最大值，可以为空")
    private Long max;

    @Schema(title = "加息(0.1/1/1.5)", required = true)
    private BigDecimal interest;

    public IncreaseInterestResult() {
    }

    public IncreaseInterestResult(Long min, Long max, BigDecimal interest) {
        this.min = min;
        this.max = max;
        this.interest = interest;
    }

    public Long getMin() {
        return min;
    }

    public void setMin(Long min) {
        this.min = min;
    }

    public Long getMax() {
        return max;
    }

    public void setMax(Long max) {
        this.max = max;
    }

    public BigDecimal getInterest() {
        return interest;
    }

    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }

}
