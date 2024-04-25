package com.topnetwork.wallet.result.wallet.top;

import java.math.BigDecimal;
import java.math.BigInteger;

import com.topnetwork.wallet.common.enums.top.TimeUnit;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @ClassName TransferGasResult
 * @Description
 * @Author bran
 * @Date 2020/10/12 13:53
 */
public class TransferGasResult {

    @Schema(title="交易费")
    private BigInteger gas;
    @Schema(title="转账速度")
    private BigDecimal speed;
    @Schema(title="转账速度单位")
    private TimeUnit speedUnit;

    public BigInteger getGas() {
        return gas;
    }

    public void setGas(BigInteger gas) {
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
