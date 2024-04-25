package com.topnetwork.wallet.result.wallet.withdraw;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

/**
 * @ClassName AirdropDetailResult
 * @Description
 * @Author bran
 * @Date 2020/7/7 15:11
 */
public class AirdropDetailResult extends AirdropSimpleResult {

    @Schema(title="提现费用")
    private BigDecimal fee;
    @Schema(title="提现账户")
    private String address;
    @Schema(title="完成时间")
    private Long endTime;

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

}
