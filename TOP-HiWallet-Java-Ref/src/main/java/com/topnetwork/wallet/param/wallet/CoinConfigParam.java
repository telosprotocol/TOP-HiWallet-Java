package com.topnetwork.wallet.param.wallet;

import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.common.enums.ChainTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Enum;
import com.gitee.magic.core.valid.annotation.Length;
import com.gitee.magic.core.valid.annotation.NotNull;

public class CoinConfigParam {

    @Schema(title = "公链类型", required = true)
    @Enum(CodeRes.CODE_15044)
    @NotNull(CodeRes.CODE_15044)
    @Length(message = CodeRes.CODE_15044)
    private ChainTypeEnum chainType;

    public ChainTypeEnum getChainType() {
        return chainType;
    }

    public void setChainType(ChainTypeEnum chainType) {
        this.chainType = chainType;
    }
}
