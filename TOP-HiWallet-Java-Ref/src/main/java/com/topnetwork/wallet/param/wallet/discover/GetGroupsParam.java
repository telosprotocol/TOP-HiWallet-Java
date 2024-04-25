package com.topnetwork.wallet.param.wallet.discover;

import com.base.core.head.ao.PageAO;
import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.common.enums.LanguageEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Enum;
import com.gitee.magic.core.valid.annotation.Length;
import com.gitee.magic.core.valid.annotation.NotNull;

/**
 * @ClassName GetGroupsParam
 * @Description
 * @Author bran
 * @Date 2020/5/19 16:11
 */
public class GetGroupsParam extends PageAO {

    @Schema(title = "语言")
    @Enum(CodeRes.CODE_14011)
    @NotNull(CodeRes.CODE_14011)
    @Length(message = CodeRes.CODE_14011)
    private LanguageEnum language;

    public LanguageEnum getLanguage() {
        return language;
    }

    public void setLanguage(LanguageEnum language) {
        this.language = language;
    }
}
