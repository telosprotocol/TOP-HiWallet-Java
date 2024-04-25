package com.topnetwork.wallet.result.wallet;

import io.swagger.v3.oas.annotations.media.Schema;

public class ETHQueryBalanceResult {

    @Schema(title = "余额", required = true)
    private String balance;

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
}
