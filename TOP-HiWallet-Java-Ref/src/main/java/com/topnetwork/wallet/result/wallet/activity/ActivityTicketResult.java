package com.topnetwork.wallet.result.wallet.activity;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

public class ActivityTicketResult {

    @Schema(title = "门票消耗top数量")
    private BigDecimal amount;

    @Schema(title = "总共免费次数")
    private Integer freeTimes;

    @Schema(title = "剩余免费次数")
    private Integer surplusTimes;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getFreeTimes() {
        return freeTimes;
    }

    public void setFreeTimes(Integer freeTimes) {
        this.freeTimes = freeTimes;
    }

    public Integer getSurplusTimes() {
        return surplusTimes;
    }

    public void setSurplusTimes(Integer surplusTimes) {
        this.surplusTimes = surplusTimes;
    }
}
