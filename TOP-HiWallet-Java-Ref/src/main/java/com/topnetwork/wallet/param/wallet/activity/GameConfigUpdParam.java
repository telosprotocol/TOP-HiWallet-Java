package com.topnetwork.wallet.param.wallet.activity;

import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.common.enums.BalanceType;
import com.topnetwork.wallet.common.enums.GameConfigType;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Enum;
import com.gitee.magic.core.valid.annotation.Format;
import com.gitee.magic.core.valid.annotation.Length;
import com.gitee.magic.core.valid.annotation.NotNull;

public class GameConfigUpdParam {

    @Schema(title = "配置id", required = true)
    @NotNull(CodeRes.CODE_15063)
    @Length(message = CodeRes.CODE_15063)
    @Format(type = Format.FormatType.LONG, message = CodeRes.CODE_15063)
    private Long configId;
    @Schema(title = "每天参与次数", required = true)
    @NotNull(CodeRes.CODE_15066)
    @Length(message = CodeRes.CODE_15066)
    @Format(type = Format.FormatType.INTEGER, message = CodeRes.CODE_15066)
    private Integer timeInDay;
    @Schema(title = "类型（门票，重置，奖励", required = true)
    @NotNull(CodeRes.CODE_15067)
    @Enum(CodeRes.CODE_15067)
    private GameConfigType type;
    @Schema(title = "游戏分数/重置次数", required = true)
    @NotNull(CodeRes.CODE_15068)
    @Length(message = CodeRes.CODE_15068)
    @Format(type = Format.FormatType.INTEGER, message = CodeRes.CODE_15068)
    private Integer record;
    @Schema(title = "分数/重置次数/门票三种类型对应消耗或奖励数量", required = true)
    @NotNull(CodeRes.CODE_15069)
    @Length(message = CodeRes.CODE_15069)
    @Format(type = Format.FormatType.INTEGER, message = CodeRes.CODE_15069)
    private Integer amount;
    @Schema(title = "奖励单位", required = true)
    @NotNull(CodeRes.CODE_15070)
    @Enum(CodeRes.CODE_15070)
    private BalanceType unit;

    public Long getConfigId() {
        return configId;
    }

    public void setConfigId(Long configId) {
        this.configId = configId;
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
}
