package com.topnetwork.wallet.result.wallet;

import io.swagger.v3.oas.annotations.media.Schema;

public class BTCFeeResult {

    @Schema(title = "快", required = true)
    private String fastestFee;
    @Schema(title = "中", required = true)
    private String halfHourFee;
    @Schema(title = "慢", required = true)
    private String hourFee;

    public String getFastestFee() {
        return fastestFee;
    }

    public void setFastestFee(String fastestFee) {
        this.fastestFee = fastestFee;
    }

    public String getHalfHourFee() {
        return halfHourFee;
    }

    public void setHalfHourFee(String halfHourFee) {
        this.halfHourFee = halfHourFee;
    }

    public String getHourFee() {
        return hourFee;
    }

    public void setHourFee(String hourFee) {
        this.hourFee = hourFee;
    }
}
