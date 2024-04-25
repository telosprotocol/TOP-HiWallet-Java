package com.topnetwork.wallet.result.wallet.activity;

import com.topnetwork.wallet.common.enums.Answer;
import io.swagger.v3.oas.annotations.media.Schema;

public class AnswerDetailResult {

    @Schema(title = "答案选项")
    private Answer type;
    @Schema(title = "答案描述cn")
    private String desc;
    @Schema(title = "答案描述en")
    private String englishDesc;
    @Schema(title = "答案id")
    private Long answerId;

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

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }
}
