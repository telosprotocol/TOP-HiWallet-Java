package com.topnetwork.wallet.entity;

import com.topnetwork.wallet.common.enums.Answer;
import com.base.core.framework.sql.entity.Base;
import com.gitee.magic.jdbc.persistence.annotation.Entity;
import com.gitee.magic.jdbc.persistence.annotation.Table;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.ColumnDef;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.Indexes;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.indexes.Normal;

import java.util.Date;

@Entity("appActivityAnswer")
@Table("wal_app_activity_answer")
public class AppActivityAnswer extends Base {

    private static final long serialVersionUID = 1L;

    public AppActivityAnswer() {
    }

    @ColumnDef(comment = "描述")
    private String chineseDesc;
    @ColumnDef(comment = "英文描述")
    private String englishDesc;
    @ColumnDef(comment = "选项类型")
    private Answer type;
    @ColumnDef(comment = "问题id",indexes = @Indexes(normal = @Normal))
    private Long qid;
    @ColumnDef(comment = "创建时间")
    private Date createTime;

    public String getChineseDesc() {
        return chineseDesc;
    }

    public void setChineseDesc(String chineseDesc) {
        this.chineseDesc = chineseDesc;
    }

    public String getEnglishDesc() {
        return englishDesc;
    }

    public void setEnglishDesc(String englishDesc) {
        this.englishDesc = englishDesc;
    }

    public Answer getType() {
        return type;
    }

    public void setType(Answer type) {
        this.type = type;
    }

    public Long getQid() {
        return qid;
    }

    public void setQid(Long qid) {
        this.qid = qid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
