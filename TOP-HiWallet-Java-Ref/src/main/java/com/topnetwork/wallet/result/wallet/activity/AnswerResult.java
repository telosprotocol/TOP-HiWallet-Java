package com.topnetwork.wallet.result.wallet.activity;

import com.topnetwork.wallet.common.enums.Answer;
import io.swagger.v3.oas.annotations.media.Schema;

public class AnswerResult {

    @Schema(title = "答案选项")
    private Answer type;
    @Schema(title = "答案描述")
    private String desc;

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
}
