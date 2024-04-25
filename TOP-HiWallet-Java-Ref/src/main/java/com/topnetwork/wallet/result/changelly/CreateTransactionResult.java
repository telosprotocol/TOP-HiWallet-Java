package com.topnetwork.wallet.result.changelly;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

/**
 * @ClassName CreateTransactionResult
 * @Description
 * @Author bran
 * @Date 2020/6/15 14:49
 */
public class CreateTransactionResult {

    @Schema(title="期望转账金额")
    private BigDecimal amountExpectedFrom;
    @Schema(title="期望地址")
    private String payinAddress;
    @Schema(title="兑换记录id")
    private String exchangeId;

    public BigDecimal getAmountExpectedFrom() {
        return amountExpectedFrom;
    }

    public void setAmountExpectedFrom(BigDecimal amountExpectedFrom) {
        this.amountExpectedFrom = amountExpectedFrom;
    }

    public String getPayinAddress() {
        return payinAddress;
    }

    public void setPayinAddress(String payinAddress) {
        this.payinAddress = payinAddress;
    }

    public String getExchangeId() {
        return exchangeId;
    }

    public void setExchangeId(String exchangeId) {
        this.exchangeId = exchangeId;
    }
}
