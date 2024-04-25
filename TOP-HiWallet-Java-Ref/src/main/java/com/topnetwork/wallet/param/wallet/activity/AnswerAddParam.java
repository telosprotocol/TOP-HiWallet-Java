package com.topnetwork.wallet.param.wallet.activity;

import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.common.enums.Answer;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Enum;
import com.gitee.magic.core.valid.annotation.Length;
import com.gitee.magic.core.valid.annotation.NotNull;

public class AnswerAddParam {

    @Schema(title = "答案选项", required = true)
    @NotNull(CodeRes.CODE_15062)
    @Enum(CodeRes.CODE_15062)
    private Answer type;
    @Schema(title = "答案描述cn", required = true)
    @NotNull(CodeRes.CODE_15061)
    @Length(message = CodeRes.CODE_15061)
    private String desc;
    @Schema(title = "答案描述en", required = true)
    @NotNull(CodeRes.CODE_15061)
    @Length(message = CodeRes.CODE_15061)
    private String englishDesc;

    public Answer getType() {
        return type;
    }

    public void setType(Answer type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getEnglishDesc() {
        return englishDesc;
    }

    public void setEnglishDesc(String englishDesc) {
        this.englishDesc = englishDesc;
    }
}
