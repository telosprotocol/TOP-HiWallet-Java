package com.topnetwork.wallet.param.wallet.activity;

import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.common.enums.Answer;
import com.topnetwork.wallet.common.enums.DifficultyLevel;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Enum;
import com.gitee.magic.core.valid.annotation.Length;
import com.gitee.magic.core.valid.annotation.NotNull;

import java.util.List;

public class QuestionAddParam {


    @Schema(title = "中文问题", required = true)
    @NotNull(CodeRes.CODE_15057)
    @Length(message = CodeRes.CODE_15057)
    private String question;
    @Schema(title = "英文问题", required = true)
    @NotNull(CodeRes.CODE_15057)
    @Length(message = CodeRes.CODE_15057)
    private String englishQuestion;
    @Schema(title = "难度等级（SIMPLE,MEDIUM,DIFFICULTY）", required = true)
    @NotNull(CodeRes.CODE_15058)
    @Enum(CodeRes.CODE_15058)
    private DifficultyLevel difficultyLevel;
    @Schema(title = "答案", required = true)
    @NotNull(CodeRes.CODE_15059)
    @Enum(CodeRes.CODE_15059)
    private Answer answer;
    @Schema(title = "选项列表", required = true)
    @NotNull(CodeRes.CODE_15060)
    private List<AnswerAddParam> answerList;


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

    public List<AnswerAddParam> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<AnswerAddParam> answerList) {
        this.answerList = answerList;
    }
}
