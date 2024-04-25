package com.topnetwork.wallet.param.wyre;

import com.topnetwork.wallet.common.CodeRes;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Format;
import com.gitee.magic.core.valid.annotation.Length;
import com.gitee.magic.core.valid.annotation.NotNull;

public class GetCoinPriceParam {

    @Schema(title = "币种id", required = true)
    @NotNull(CodeRes.CODE_14026)
    @Length(message = CodeRes.CODE_14026)
    private String englishName;
    @Schema(title = "法币名称，CNY或者USD）", required = true)
    @NotNull(CodeRes.CODE_14040)
    @Length(message = CodeRes.CODE_14040)
    private String vsCurrency;

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getVsCurrency() {
        return vsCurrency;
    }

    public void setVsCurrency(String vsCurrency) {
        this.vsCurrency = vsCurrency;
    }
}
