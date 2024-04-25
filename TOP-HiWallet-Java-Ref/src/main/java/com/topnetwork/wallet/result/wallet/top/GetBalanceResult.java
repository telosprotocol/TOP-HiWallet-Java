package com.topnetwork.wallet.result.wallet.top;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigInteger;

/**
 * @ClassName GetBalanceResult
 * @Description
 * @Author bran
 * @Date 2020/10/10 17:02
 */
public class GetBalanceResult {

    @Schema(title="余额")
    private BigInteger balance;

    public BigInteger getBalance() {
        return balance;
    }

    public void setBalance(BigInteger balance) {
        this.balance = balance;
    }
}
