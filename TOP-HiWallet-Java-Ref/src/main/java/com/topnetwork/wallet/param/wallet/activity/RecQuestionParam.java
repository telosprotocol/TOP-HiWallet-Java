package com.topnetwork.wallet.param.wallet.activity;

import com.topnetwork.wallet.common.CodeRes;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.NotNull;

import java.util.List;

public class RecQuestionParam extends ActivityBaseParam{

    @Schema(title = "答案列表", required = true)
    @NotNull(CodeRes.CODE_15024)
    private List<AnswerParam> answerList;

    public List<AnswerParam> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<AnswerParam> answerList) {
        this.answerList = answerList;
    }


}
