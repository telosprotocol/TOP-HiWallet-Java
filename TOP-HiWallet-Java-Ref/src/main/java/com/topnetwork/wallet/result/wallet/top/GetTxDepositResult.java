package com.topnetwork.wallet.result.wallet.top;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

/**
 * @ClassName GetTxDepositResult
 * @Description
 * @Author bran
 * @Date 2020/10/12 11:49
 */
public class GetTxDepositResult {

    @Schema(title="交易质押保证金（单位top）")
    private BigDecimal deposit;

    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }
}
