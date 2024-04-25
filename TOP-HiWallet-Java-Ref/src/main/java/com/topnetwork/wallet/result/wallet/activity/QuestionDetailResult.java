package com.topnetwork.wallet.result.wallet.activity;

import com.topnetwork.wallet.common.enums.Answer;
import com.topnetwork.wallet.common.enums.DifficultyLevel;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public class QuestionDetailResult {

    @Schema(title = "问题id")
    private Long questionId;
    @Schema(title = "问题")
    private String question;
    @Schema(title = "英文问题")
    private String englishQuestion;
    @Schema(title = "难度等级（SIMPLE,MEDIUM,DIFFICULTY）")
    private DifficultyLevel difficultyLevel;

    @Schema(title = "答案")
    private Answer answer;
    @Schema(title = "选项列表")
    private List<AnswerDetailResult> answerList;

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getEnglishQuestion() {
        return englishQuestion;
    }

    public void setEnglishQuestion(String englishQuestion) {
        this.englishQuestion = englishQuestion;
    }

    public DifficultyLevel getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(DifficultyLevel difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public List<AnswerDetailResult> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<AnswerDetailResult> answerList) {
        this.answerList = answerList;
    }
}
