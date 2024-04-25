package com.topnetwork.wallet.param.wallet.changelly;

import com.topnetwork.wallet.common.CodeRes;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Format;
import com.gitee.magic.core.valid.annotation.Length;
import com.gitee.magic.core.valid.annotation.NotNull;

import java.math.BigDecimal;

/**
 * @ClassName AddCurrencyParam
 * @Description
 * @Author bran
 * @Date 2020/6/15 10:19
 */
public class AddCurrencyParam {

    @Schema(title = "币种id", required = true)
    @NotNull(CodeRes.CODE_14025)
    @Format(type = Format.FormatType.LONG, message = CodeRes.CODE_14025)
    @Length(message = CodeRes.CODE_14025)
    private Long coinId;
    @Schema(title = "是否禁用", required = true)
    @NotNull(CodeRes.CODE_21000)
    @Format(type = Format.FormatType.BOOLEAN, message = CodeRes.CODE_21000)
    @Length(message = CodeRes.CODE_21000)
    private Boolean disable;
    @Schema(title = "最小值是否自定义", required = true)
    @NotNull(CodeRes.CODE_21003)
    @Format(type = Format.FormatType.BOOLEAN, message = CodeRes.CODE_21003)
    @Length(message = CodeRes.CODE_21003)
    private Boolean customized;
    @Schema(title = "最大交易金额", required = true)
    @NotNull(CodeRes.CODE_21001)
    @Format(type = Format.FormatType.DOUBLE, message = CodeRes.CODE_21001)
    @Length(message = CodeRes.CODE_21001)
    private BigDecimal maxSellAmount;
    @Schema(title = "最小交易金额", required = false)
    @Format(type = Format.FormatType.DOUBLE, message = CodeRes.CODE_21001)
    private BigDecimal minSellAmount;

    public Long getCoinId() {
        return coinId;
    }

    public void setCoinId(Long coinId) {
        this.coinId = coinId;
    }

    public Boolean getDisable() {
        return disable;
    }

    public void setDisable(Boolean disable) {
        this.disable = disable;
    }

    public BigDecimal getMaxSellAmount() {
        return maxSellAmount;
    }

    public void setMaxSellAmount(BigDecimal maxSellAmount) {
        this.maxSellAmount = maxSellAmount;
    }

    public Boolean getCustomized() {
        return customized;
    }

    public void setCustomized(Boolean customized) {
        this.customized = customized;
    }

    public BigDecimal getMinSellAmount() {
        return minSellAmount;
    }

    public void setMinSellAmount(BigDecimal minSellAmount) {
        this.minSellAmount = minSellAmount;
    }
}
