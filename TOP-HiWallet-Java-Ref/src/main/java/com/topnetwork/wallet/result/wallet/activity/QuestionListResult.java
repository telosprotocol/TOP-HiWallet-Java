package com.topnetwork.wallet.result.wallet.activity;

import com.topnetwork.wallet.common.enums.Answer;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public class QuestionListResult {

    @Schema(title = "问题id")
    private Long qid;
    @Schema(title = "问题")
    private String question;

    @Schema(title = "答案")
    private Answer answer;
    @Schema(title = "选项列表")
    private List<AnswerResult> answerList;

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

    public List<AnswerResult> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<AnswerResult> answerList) {
        this.answerList = answerList;
    }
}
