package com.topnetwork.wallet.param.wallet.v2;

import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.common.enums.LanguageEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Enum;
import com.gitee.magic.core.valid.annotation.Format;
import com.gitee.magic.core.valid.annotation.NotNull;

public class NoticeDetailV2Param {

    @Schema(title = "公告id", required = true)
    @NotNull(CodeRes.CODE_14001)
    @Format(type = Format.FormatType.LONG, message = CodeRes.CODE_14001)
    private Long id;

    @Schema(title = "语言", required = true)
    @NotNull(CodeRes.CODE_14011)
    @Enum(CodeRes.CODE_14011)
    private LanguageEnum language;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LanguageEnum getLanguage() {
        return language;
    }

    public void setLanguage(LanguageEnum language) {
        this.language = language;
    }
}
