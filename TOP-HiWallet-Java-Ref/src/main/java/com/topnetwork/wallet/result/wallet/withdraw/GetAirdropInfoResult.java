package com.topnetwork.wallet.result.wallet.withdraw;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

/**
 * @ClassName GetAirdropInfoResult
 * @Description
 * @Author bran
 * @Date 2020/7/7 14:28
 */
public class GetAirdropInfoResult {

    @Schema(title="提现费用")
    private BigDecimal fee;
    @Schema(title="最小提现金额")
    private BigDecimal minAmount;
    @Schema(title="最大提现金额")
    private BigDecimal maxAmount;
    @Schema(title="可用余额")
    private BigDecimal availableAmount;
    @Schema(title="手机号")
    private String phone;


    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
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

    public BigDecimal getAvailableAmount() {
        return availableAmount;
    }

    public void setAvailableAmount(BigDecimal availableAmount) {
        this.availableAmount = availableAmount;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
