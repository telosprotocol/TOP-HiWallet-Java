package com.topnetwork.wallet.entity;

import com.base.core.framework.sql.entity.Base;
import com.gitee.magic.jdbc.persistence.annotation.Entity;
import com.gitee.magic.jdbc.persistence.annotation.Table;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.ColumnDef;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.Indexes;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.ScriptConverter;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.TableDef;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.indexes.Normal;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.converter.FieldLongText;

import java.util.Date;

@Entity("appHelp")
@Table("wal_appHelp")
@TableDef(comment = "App帮助")
public class AppHelp extends Base {

    private static final long serialVersionUID = 1L;

    public AppHelp() {

    }

    @ColumnDef(comment = "标题")
    private String title;

    @ColumnDef(comment = "英文标题")
    private String englishTitle;

    @ScriptConverter(FieldLongText.class)
    @ColumnDef(comment = "主题内容")
    private String content;

    @ScriptConverter(FieldLongText.class)
    @ColumnDef(comment = "英文主题内容")
    private String englishContent;

    @ColumnDef(comment = "一级标题", indexes = @Indexes(normal = @Normal))
    private Long stCategoryId;

    @ColumnDef(comment = "二级标题", indexes = @Indexes(normal = @Normal))
    private Long ndCategoryId;

    @ColumnDef(comment = "创建时间")
    private Date createTime;

    @ColumnDef(comment = "创建人id")
    private Long createId;

    @ColumnDef(comment = "更新时间", isNull = true)
    private Date updateTime;

    @ColumnDef(comment = "更新id", isNull = true)
    private Long updateId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Long getStCategoryId() {
        return stCategoryId;
    }

    public void setStCategoryId(Long stCategoryId) {
        this.stCategoryId = stCategoryId;
    }

    public Long getNdCategoryId() {
        return ndCategoryId;
    }

    public void setNdCategoryId(Long ndCategoryId) {
        this.ndCategoryId = ndCategoryId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreateId() {
        return createId;
    }

    public void setCreateId(Long createId) {
        this.createId = createId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Long updateId) {
        this.updateId = updateId;
    }

    public String getEnglishTitle() {
        return englishTitle;
    }

    public void setEnglishTitle(String englishTitle) {
        this.englishTitle = englishTitle;
    }

    public String getEnglishContent() {
        return englishContent;
    }

    public void setEnglishContent(String englishContent) {
        this.englishContent = englishContent;
    }
}