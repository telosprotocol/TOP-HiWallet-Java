package com.topnetwork.wallet.result.wallet.activity;

import com.topnetwork.wallet.common.enums.UpsAndDowns;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

public class GuessConfigResult {

    @Schema(title = "奖池余额")
    private BigDecimal jackpot;
    @Schema(title = "本期开始时btc价格")
    private BigDecimal btcPrice;
    @Schema(title = "本期开始时间")
    private Long startTime;
    @Schema(title = "本期结束时间")
    private Long endTime;
    @Schema(title = "本期猜涨人数")
    private Integer guessUpSize;
    @Schema(title = "本期猜跌人数")
    private Integer guessDownSize;
    @Schema(title = "本期当前用户猜涨跌状态")
    private UpsAndDowns userStatus;

    public BigDecimal getJackpot() {
        return jackpot;
    }

    public void setJackpot(BigDecimal jackpot) {
        this.jackpot = jackpot;
    }

    public BigDecimal getBtcPrice() {
        return btcPrice;
    }

    public void setBtcPrice(BigDecimal btcPrice) {
        this.btcPrice = btcPrice;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Integer getGuessUpSize() {
        return guessUpSize;
    }

    public void setGuessUpSize(Integer guessUpSize) {
        this.guessUpSize = guessUpSize;
    }

    public Integer getGuessDownSize() {
        return guessDownSize;
    }

    public void setGuessDownSize(Integer guessDownSize) {
        this.guessDownSize = guessDownSize;
    }

    public UpsAndDowns getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UpsAndDowns userStatus) {
        this.userStatus = userStatus;
    }
}
