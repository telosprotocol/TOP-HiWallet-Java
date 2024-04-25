package com.topnetwork.wallet.param.wallet.activity;

import com.topnetwork.wallet.common.CodeRes;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Format;
import com.gitee.magic.core.valid.annotation.Length;
import com.gitee.magic.core.valid.annotation.NotNull;

public class QuestionDetailParam {

    @Schema(title = "问题 id", required = true)
    @NotNull(CodeRes.CODE_15022)
    @Length(message = CodeRes.CODE_15022)
    @Format(type = Format.FormatType.LONG, message = CodeRes.CODE_15022)
    private Long questionId;

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }
}
