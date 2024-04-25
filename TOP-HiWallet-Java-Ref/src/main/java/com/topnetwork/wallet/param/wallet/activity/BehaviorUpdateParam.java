package com.topnetwork.wallet.param.wallet.activity;

import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.common.enums.BalanceType;
import com.topnetwork.wallet.common.enums.GrantType;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Enum;
import com.gitee.magic.core.valid.annotation.Format;
import com.gitee.magic.core.valid.annotation.Length;
import com.gitee.magic.core.valid.annotation.NotNull;

public class BehaviorUpdateParam {

    @Schema(title = "行为配置id", required = true)
    @NotNull(CodeRes.CODE_15063)
    @Length(message = CodeRes.CODE_15063)
    @Format(type = Format.FormatType.LONG, message = CodeRes.CODE_15063)
    private Long behaviorId;
    @Schema(title = "每天参与次数", required = true)
    @NotNull(CodeRes.CODE_15066)
    @Length(message = CodeRes.CODE_15066)
    @Format(type = Format.FormatType.INTEGER, message = CodeRes.CODE_15066)
    private Integer timeInDay;
    @Schema(title = "每次发放期数", required = true)
    @NotNull(CodeRes.CODE_15071)
    @Length(message = CodeRes.CODE_15071)
    @Format(type = Format.FormatType.INTEGER, message = CodeRes.CODE_15071)
    private Integer timeInOnce;
    @Schema(title = "每期间隔时长（h）", required = true)
    @NotNull(CodeRes.CODE_15072)
    @Length(message = CodeRes.CODE_15072)
    @Format(type = Format.FormatType.INTEGER, message = CodeRes.CODE_15072)
    private Integer growthTime;
    @Schema(title = "每期泡泡个数", required = true)
    @NotNull(CodeRes.CODE_15073)
    @Length(message = CodeRes.CODE_15073)
    @Format(type = Format.FormatType.INTEGER, message = CodeRes.CODE_15073)
    private Integer sizeInOnce;
    @Schema(title = "奖励最小金额", required = true)
    @NotNull(CodeRes.CODE_15074)
    @Length(message = CodeRes.CODE_15074)
    @Format(type = Format.FormatType.INTEGER, message = CodeRes.CODE_15074)
    private Integer minAmount;
    @Schema(title = "奖励最大金额", required = true)
    @NotNull(CodeRes.CODE_15074)
    @Length(message = CodeRes.CODE_15074)
    @Format(type = Format.FormatType.INTEGER, message = CodeRes.CODE_15074)
    private Integer maxAmount;
    @Schema(title = "首次发放时间（当日，次日）", required = true)
    @NotNull(CodeRes.CODE_15075)
    @Enum(CodeRes.CODE_15075)
    private GrantType fristTime;
    @Schema(title = "奖励单位", required = true)
    @NotNull(CodeRes.CODE_15070)
    @Enum(CodeRes.CODE_15070)
    private BalanceType unit;


    public Long getBehaviorId() {
        return behaviorId;
    }

    public void setBehaviorId(Long behaviorId) {
        this.behaviorId = behaviorId;
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
}
