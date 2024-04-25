package com.topnetwork.wallet.result.wallet.invite;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

/**
 * @ClassName UserInfoResult
 * @Description
 * @Author bran
 * @Date 2020/5/7 14:32
 */
public class UserInfoResult {

    @Schema(title = "总收入")
    private BigDecimal totalRev;
    @Schema(title = "邀请人数")
    private Integer number;

    public BigDecimal getTotalRev() {
        return totalRev;
    }

    public void setTotalRev(BigDecimal totalRev) {
        this.totalRev = totalRev;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
