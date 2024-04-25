package com.topnetwork.wallet.result.wyre;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @ClassName GetReservationResult
 * @Description
 * @Author bran
 * @Date 2020/8/20 9:38
 */
public class GetReservationResult {

    @Schema(title="账户id")
    private String accountId;
    @Schema(title="reservation id")
    private String reservation;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getReservation() {
        return reservation;
    }

    public void setReservation(String reservation) {
        this.reservation = reservation;
    }

}
