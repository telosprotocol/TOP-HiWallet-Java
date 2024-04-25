package com.topnetwork.wallet.param.wallet.appuser;

import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.common.enums.LanguageEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Enum;
import com.gitee.magic.core.valid.annotation.Length;
import com.gitee.magic.core.valid.annotation.NotNull;

public class UserRewardParam {

    @Schema(title = "语言（CN：中文，EN：英文）", required = true)
    @Enum(CodeRes.CODE_14011)
    private LanguageEnum language;

    public LanguageEnum getLanguage() {
        return language;
    }

    public void setLanguage(LanguageEnum language) {
        this.language = language;
    }
}
