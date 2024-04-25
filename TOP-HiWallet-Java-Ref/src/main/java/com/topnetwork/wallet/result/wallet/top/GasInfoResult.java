package com.topnetwork.wallet.result.wallet.top;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @ClassName GasInfoResult
 * @Description
 * @Author bran
 * @Date 2020/10/12 11:41
 */
public class GasInfoResult {

    @Schema(title="可用gas")
    private BigInteger availableGas;
    @Schema(title="未使用的免费gas")
    private BigInteger unUsedFreeGas;
    @Schema(title="总共的免费gas")
    private BigInteger totalFreeGas;
    @Schema(title="未使用的质押gas")
    private BigInteger unUsedStakeGas;
    @Schema(title="总质押gas")
    private BigInteger totalStakeGas;
    @Schema(title="质押gas回复速度 1h恢复多少tgas")
    private BigDecimal stakeRecoverySpeed;
    @Schema(title="免费gas回复速度 1h恢复多少tgas")
    private BigDecimal freeRecoverySpeed;

    @Schema(title="可赎回的质押到tgas的top 单位utop")
    private BigInteger gasStakedToken;

    public BigInteger getAvailableGas() {
        return availableGas;
    }

    public void setAvailableGas(BigInteger availableGas) {
        this.availableGas = availableGas;
    }

    public BigInteger getUnUsedFreeGas() {
        return unUsedFreeGas;
    }

    public void setUnUsedFreeGas(BigInteger unUsedFreeGas) {
        this.unUsedFreeGas = unUsedFreeGas;
    }

    public BigInteger getTotalFreeGas() {
        return totalFreeGas;
    }

    public void setTotalFreeGas(BigInteger totalFreeGas) {
        this.totalFreeGas = totalFreeGas;
    }

    public BigInteger getUnUsedStakeGas() {
        return unUsedStakeGas;
    }

    public void setUnUsedStakeGas(BigInteger unUsedStakeGas) {
        this.unUsedStakeGas = unUsedStakeGas;
    }

    public BigInteger getTotalStakeGas() {
        return totalStakeGas;
    }

    public void setTotalStakeGas(BigInteger totalStakeGas) {
        this.totalStakeGas = totalStakeGas;
    }

    public BigDecimal getStakeRecoverySpeed() {
        return stakeRecoverySpeed;
    }

    public void setStakeRecoverySpeed(BigDecimal stakeRecoverySpeed) {
        this.stakeRecoverySpeed = stakeRecoverySpeed;
    }

    public BigDecimal getFreeRecoverySpeed() {
        return freeRecoverySpeed;
    }

    public void setFreeRecoverySpeed(BigDecimal freeRecoverySpeed) {
        this.freeRecoverySpeed = freeRecoverySpeed;
    }

    public BigInteger getGasStakedToken() {
        return gasStakedToken;
    }

    public void setGasStakedToken(BigInteger gasStakedToken) {
        this.gasStakedToken = gasStakedToken;
    }
}
