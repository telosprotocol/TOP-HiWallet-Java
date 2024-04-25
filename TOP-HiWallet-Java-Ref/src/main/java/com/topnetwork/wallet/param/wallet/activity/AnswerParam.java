package com.topnetwork.wallet.param.wallet.activity;

import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.common.enums.Answer;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Enum;
import com.gitee.magic.core.valid.annotation.Format;
import com.gitee.magic.core.valid.annotation.NotNull;

public class AnswerParam {

    @Schema(title = "奖励id", required = true)
    @NotNull(CodeRes.CODE_15022)
    @Format(type = Format.FormatType.LONG, message = CodeRes.CODE_15022)
    private Long qid;
    @Schema(title = "答案（ABCD）", required = true)
    @Enum(CodeRes.CODE_15023)
    private Answer answer;

    public Long getQid() {
        return qid;
    }

    public void setQid(Long qid) {
        this.qid = qid;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }
}
