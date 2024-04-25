package com.topnetwork.wallet.param.wallet;

import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.common.enums.CurrencyUnitEnum;

import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Enum;
import com.gitee.magic.core.valid.annotation.Format;
import com.gitee.magic.core.valid.annotation.NotNull;

public class ETHGetCoinPriceV2Param {

    @Schema(title = "币种id", required = true)
    @NotNull(CodeRes.CODE_14025)
    @Format(type = Format.FormatType.LONG, message = CodeRes.CODE_14025)
    private Long coinId;
    @Schema(title = "法币名称，CNY或者USD）", required = true)
    @Enum(CodeRes.CODE_14040)
    private CurrencyUnitEnum vsCurrency;

    public Long getCoinId() {
        return coinId;
    }

    public void setCoinId(Long coinId) {
        this.coinId = coinId;
    }

    public CurrencyUnitEnum getVsCurrency() {
        return vsCurrency;
    }

    public void setVsCurrency(CurrencyUnitEnum vsCurrency) {
        this.vsCurrency = vsCurrency;
    }
}
