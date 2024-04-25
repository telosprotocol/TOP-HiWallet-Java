package com.topnetwork.wallet.param.wallet.discover;

import com.base.core.head.ao.PageAO;
import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.common.enums.LanguageEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Enum;
import com.gitee.magic.core.valid.annotation.NotNull;

public class RecommendPageParam extends PageAO {

    @Schema(title = "语言（CN，EN）", required = true)
    @Enum(CodeRes.CODE_14011)
    private LanguageEnum language;

    @Schema(title = "游戏分类id", required = true)
    @NotNull(CodeRes.CODE_15040)
    private Long classifyId;

    @Schema(title = "版本", required = false)
    private String version;

    public LanguageEnum getLanguage() {
        return language;
    }

    public void setLanguage(LanguageEnum language) {
        this.language = language;
    }

    public Long getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(Long classifyId) {
        this.classifyId = classifyId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
