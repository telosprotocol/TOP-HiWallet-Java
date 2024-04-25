package com.topnetwork.wallet.entity;

import com.topnetwork.wallet.common.enums.Answer;
import com.topnetwork.wallet.common.enums.DifficultyLevel;
import com.base.core.framework.sql.entity.Base;
import com.gitee.magic.jdbc.persistence.annotation.Entity;
import com.gitee.magic.jdbc.persistence.annotation.Table;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.ColumnDef;

import java.util.Date;

@Entity("appActivityQuestion")
@Table("wal_app_activity_question")
public class AppActivityQuestion extends Base {

    private static final long serialVersionUID = 1L;

    public AppActivityQuestion() {
    }

    @ColumnDef(comment = "问题")
    private String question;
    @ColumnDef(comment = "英文问题")
    private String englishQuestion;
    @ColumnDef(comment = "答案（ABCD）")
    private Answer answer;
    @ColumnDef(comment = "难度等级（SIMPLE,MEDIUM,DIFFICULTY）")
    private DifficultyLevel difficultyLevel;
    @ColumnDef(comment = "更新时间", isNull = true)
    private Date updateTime;
    @ColumnDef(comment = "创建时间")
    private Date createTime;

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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
