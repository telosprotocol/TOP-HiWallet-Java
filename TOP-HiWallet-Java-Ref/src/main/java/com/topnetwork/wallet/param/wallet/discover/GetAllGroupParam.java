package com.topnetwork.wallet.param.wallet.discover;

import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.common.enums.LanguageEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Enum;

/**
 * @ClassName GetAllGroupParam
 * @Description
 * @Author bran
 * @Date 2020/5/26 10:55
 */
public class GetAllGroupParam {

    @Schema(title = "语言（CN,EN,KR,JP,VN...）", required = false)
    @Enum(CodeRes.CODE_14011)
    private LanguageEnum language;

    public LanguageEnum getLanguage() {
        return language;
    }

    public void setLanguage(LanguageEnum language) {
        this.language = language;
    }
}
