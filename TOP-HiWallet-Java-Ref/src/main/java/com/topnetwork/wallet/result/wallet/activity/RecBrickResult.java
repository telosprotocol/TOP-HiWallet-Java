package com.topnetwork.wallet.result.wallet.activity;

import io.swagger.v3.oas.annotations.media.Schema;

public class RecBrickResult {

    @Schema(title = "可获取奖励")
    private Integer reword;
    @Schema(title = "再完成区块个数")
    private Integer recordMore;
    @Schema(title = "再完成指定区块个数可获得的奖励")
    private Integer rewordMore;
    @Schema(title = "复活扣费数量")
    private Integer resetAmount;
    @Schema(title = "是否可复活")
    private Boolean canReset;
    @Schema(title = "剩余复活次数")
    private Integer resetSurplusTimes;

    public Integer getReword() {
        return reword;
    }

    public void setReword(Integer reword) {
        this.reword = reword;
    }

    public Integer getRecordMore() {
        return recordMore;
    }

    public void setRecordMore(Integer recordMore) {
        this.recordMore = recordMore;
    }

    public Integer getRewordMore() {
        return rewordMore;
    }

    public void setRewordMore(Integer rewordMore) {
        this.rewordMore = rewordMore;
    }

    public Integer getResetAmount() {
        return resetAmount;
    }

    public void setResetAmount(Integer resetAmount) {
        this.resetAmount = resetAmount;
    }

    public Boolean getCanReset() {
        return canReset;
    }

    public void setCanReset(Boolean canReset) {
        this.canReset = canReset;
    }

    public Integer getResetSurplusTimes() {
        return resetSurplusTimes;
    }

    public void setResetSurplusTimes(Integer resetSurplusTimes) {
        this.resetSurplusTimes = resetSurplusTimes;
    }
}
