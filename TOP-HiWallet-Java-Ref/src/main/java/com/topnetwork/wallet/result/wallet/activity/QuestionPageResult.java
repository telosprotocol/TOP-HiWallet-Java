package com.topnetwork.wallet.result.wallet.activity;

import com.topnetwork.wallet.common.enums.Answer;
import com.topnetwork.wallet.common.enums.DifficultyLevel;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public class QuestionPageResult {

    @Schema(title = "问题id")
    private Long qid;
    @Schema(title = "问题")
    private String question;
    @Schema(title = "答案")
    private Answer answer;
    @Schema(title = "难度等级（SIMPLE,MEDIUM,DIFFICULTY）")
    private DifficultyLevel difficultyLevel;
    @Schema(title = "更新时间")
    private Long updateTime;

    public Long getQid() {
        return qid;
    }

    public void setQid(Long qid) {
        this.qid = qid;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public DifficultyLevel getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(DifficultyLevel difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }
}
