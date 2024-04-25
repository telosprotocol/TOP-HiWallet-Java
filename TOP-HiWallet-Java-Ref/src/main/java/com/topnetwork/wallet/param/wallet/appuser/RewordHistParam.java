package com.topnetwork.wallet.param.wallet.appuser;

import com.base.core.head.ao.PageAO;
import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.common.enums.LanguageEnum;
import com.topnetwork.wallet.common.enums.RewardSide;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Enum;
import com.gitee.magic.core.valid.annotation.Length;
import com.gitee.magic.core.valid.annotation.NotNull;

public class RewordHistParam extends PageAO {

    @Schema(title = "支出或者收入或者全部", required = true)
    @Enum(CodeRes.CODE_15019)
    private RewardSide side;
    @Schema(title = "语言（CN：中文，EN：英文）", required = true)
    @Enum(CodeRes.CODE_14011)
    private LanguageEnum language;

    public RewardSide getSide() {
        return side;
    }

    public void setSide(RewardSide side) {
        this.side = side;
    }

    public LanguageEnum getLanguage() {
        return language;
    }

    public void setLanguage(LanguageEnum language) {
        this.language = language;
    }
}
