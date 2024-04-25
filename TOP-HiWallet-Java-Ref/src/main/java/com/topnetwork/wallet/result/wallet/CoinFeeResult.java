package com.topnetwork.wallet.result.wallet;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

public class CoinFeeResult {

    @Schema(title = "交易费描述")
    private String desc;
    @Schema(title = "交易费数量")
    private BigDecimal amount;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
