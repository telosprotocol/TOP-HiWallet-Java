package com.topnetwork.wallet.result.wallet.top;

import java.math.BigDecimal;

import com.topnetwork.wallet.common.enums.top.TimeUnit;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 
 * @author jode
 * @date Nov 19, 2020
 */
public class TransferGasFreeGasResult {

    @Schema(title="交易费")
    private BigDecimal gas;
    @Schema(title="转账速度")
    private BigDecimal speed;
    @Schema(title="转账速度单位")
    private TimeUnit speedUnit;

    public BigDecimal getGas() {
        return gas;
    }

    public void setGas(BigDecimal gas) {
        this.gas = gas;
    }

    public BigDecimal getSpeed() {
        return speed;
    }

    public void setSpeed(BigDecimal speed) {
        this.speed = speed;
    }

    public TimeUnit getSpeedUnit() {
        return speedUnit;
    }

    public void setSpeedUnit(TimeUnit speedUnit) {
        this.speedUnit = speedUnit;
    }
}
