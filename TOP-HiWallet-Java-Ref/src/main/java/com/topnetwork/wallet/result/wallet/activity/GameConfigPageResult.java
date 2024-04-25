package com.topnetwork.wallet.result.wallet.activity;

import com.topnetwork.wallet.common.enums.BalanceType;
import com.topnetwork.wallet.common.enums.GameConfigType;
import io.swagger.v3.oas.annotations.media.Schema;

public class GameConfigPageResult {

    @Schema(title = "配置id")
    private Long configId;
    @Schema(title = "游戏名称")
    private String gameName;
    @Schema(title = "游戏id")
    private Long gameId;
    @Schema(title = "每天参与次数")
    private Integer timeInDay;
    @Schema(title = "类型（门票，重置，奖励")
    private GameConfigType type;
    @Schema(title = "游戏分数/重置次数")
    private Integer record;
    @Schema(title = "分数/重置次数/门票对应消耗或奖励数量")
    private Integer amount;
    @Schema(title = "奖励单位")
    private BalanceType unit;
    @Schema(title = "更新时间")
    private Long updateTime;

    public Long getConfigId() {
        return configId;
    }

    public void setConfigId(Long configId) {
        this.configId = configId;
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

    public GameConfigType getType() {
        return type;
    }

    public void setType(GameConfigType type) {
        this.type = type;
    }

    public Integer getRecord() {
        return record;
    }

    public void setRecord(Integer record) {
        this.record = record;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
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

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }
}
