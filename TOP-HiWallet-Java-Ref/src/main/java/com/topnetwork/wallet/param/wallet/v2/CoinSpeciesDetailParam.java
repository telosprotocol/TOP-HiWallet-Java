package com.topnetwork.wallet.param.wallet.v2;

import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.common.enums.LanguageEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Enum;
import com.gitee.magic.core.valid.annotation.NotNull;

public class CoinSpeciesDetailParam {

    @Schema(title = "币种英文全称名字", required = true)
    @NotNull(CodeRes.CODE_14026)
    private String englishName;

    @Schema(title = "语言", required = true)
    @NotNull(CodeRes.CODE_14011)
    @Enum(CodeRes.CODE_14011)
    private LanguageEnum language;

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public LanguageEnum getLanguage() {
        return language;
    }

    public void setLanguage(LanguageEnum language) {
        this.language = language;
    }
}
