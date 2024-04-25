package com.topnetwork.wallet.result.wallet.activity;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

public class RecQuestionResult {

    @Schema(title = "正确个数")
    private Integer correct;
    @Schema(title = "可获得奖励")
    private BigDecimal reward;

    public Integer getCorrect() {
        return correct;
    }

    public void setCorrect(Integer correct) {
        this.correct = correct;
    }

    public BigDecimal getReward() {
        return reward;
    }

    public void setReward(BigDecimal reward) {
        this.reward = reward;
    }
}
