package com.topnetwork.wallet.result.wallet.activity;

import com.topnetwork.wallet.common.enums.BalanceType;
import com.topnetwork.wallet.common.enums.GrantType;
import io.swagger.v3.oas.annotations.media.Schema;

public class BehaviorPageResult {

    @Schema(title = "行为配置id")
    private Long behaviorId;
    @Schema(title = "活动名称")
    private String gameName;
    @Schema(title = "每天参与次数")
    private Integer timeInDay;
    @Schema(title = "每次发放期数")
    private Integer timeInOnce;
    @Schema(title = "每期间隔时长（h）")
    private Integer growthTime;
    @Schema(title = "每期泡泡个数")
    private Integer sizeInOnce;
    @Schema(title = "奖励最小金额")
    private Integer minAmount;
    @Schema(title = "奖励最大金额")
    private Integer maxAmount;
    @Schema(title = "首次发放时间（当日，次日）")
    private GrantType fristTime;
    @Schema(title = "奖励单位")
    private BalanceType unit;
    @Schema(title = "更新时间")
    private Long updateTime;

    public Long getBehaviorId() {
        return behaviorId;
    }

    public void setBehaviorId(Long behaviorId) {
        this.behaviorId = behaviorId;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public Integer getTimeInDay() {
        return timeInDay;
    }

    public void setTimeInDay(Integer timeInDay) {
        this.timeInDay = timeInDay;
    }

    public Integer getTimeInOnce() {
        return timeInOnce;
    }

    public void setTimeInOnce(Integer timeInOnce) {
        this.timeInOnce = timeInOnce;
    }

    public Integer getGrowthTime() {
        return growthTime;
    }

    public void setGrowthTime(Integer growthTime) {
        this.growthTime = growthTime;
    }

    public Integer getSizeInOnce() {
        return sizeInOnce;
    }

    public void setSizeInOnce(Integer sizeInOnce) {
        this.sizeInOnce = sizeInOnce;
    }

    public Integer getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(Integer minAmount) {
        this.minAmount = minAmount;
    }

    public Integer getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(Integer maxAmount) {
        this.maxAmount = maxAmount;
    }

    public GrantType getFristTime() {
        return fristTime;
    }

    public void setFristTime(GrantType fristTime) {
        this.fristTime = fristTime;
    }

    public BalanceType getUnit() {
        return unit;
    }

    public void setUnit(BalanceType unit) {
        this.unit = unit;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }
}
