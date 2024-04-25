package com.topnetwork.wallet.result.wallet.appuser;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

public class UserInfoResult {

    @Schema(title = "余额")
    private BigDecimal balance;
    @Schema(title = "累计空投")
    private BigDecimal airdrop;
    @Schema(title = "是否新用户")
    private Boolean newUser;

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getAirdrop() {
        return airdrop;
    }

    public void setAirdrop(BigDecimal airdrop) {
        this.airdrop = airdrop;
    }

    public Boolean getNewUser() {
        return newUser;
    }

    public void setNewUser(Boolean newUser) {
        this.newUser = newUser;
    }
}
