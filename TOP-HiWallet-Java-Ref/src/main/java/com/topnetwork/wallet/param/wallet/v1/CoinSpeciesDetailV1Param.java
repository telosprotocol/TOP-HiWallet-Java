package com.topnetwork.wallet.param.wallet.v1;

import com.topnetwork.wallet.common.CodeRes;

import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Format;
import com.gitee.magic.core.valid.annotation.NotNull;

public class CoinSpeciesDetailV1Param {

    @Schema(title = "币种英文全称名字", required = true)
    @NotNull(CodeRes.CODE_14026)
    private String englishName;

    @Schema(title = "语言", required = true)
    @NotNull(CodeRes.CODE_14011)
    @Format(type = Format.FormatType.INTEGER, message = CodeRes.CODE_14011)
    private Integer language;

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public Integer getLanguage() {
        return language;
    }

    public void setLanguage(Integer language) {
        this.language = language;
    }

}
