package com.topnetwork.wallet.param.wallet;

import com.topnetwork.wallet.common.CodeRes;

import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Format;
import com.gitee.magic.core.valid.annotation.NotNull;

public class CoinUpdParam extends CoinAddParam {

    @Schema(title = "币种id", required = true)
    @NotNull(CodeRes.CODE_14025)
    @Format(type = Format.FormatType.LONG, message = CodeRes.CODE_14025)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
