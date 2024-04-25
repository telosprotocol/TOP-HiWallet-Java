package com.topnetwork.wallet.param.wallet.changelly;

import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.common.enums.LanguageEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Enum;
import com.gitee.magic.core.valid.annotation.Format;

import java.util.List;

public class GetCurrenciesParam {


    @Schema(title = "语言（CN：中文，EN：英文）", required = false)
    @Enum(CodeRes.CODE_14011)
    private LanguageEnum language;
    @Schema(title = "字段标识主链类型，比如 以太坊主链是ETH", required = false)
    private List<String> chainTypes;

    public LanguageEnum getLanguage() {
        return language;
    }

    public void setLanguage(LanguageEnum language) {
        this.language = language;
    }

    public List<String> getChainTypes() {
        return chainTypes;
    }

    public void setChainTypes(List<String> chainTypes) {
        this.chainTypes = chainTypes;
    }
}
