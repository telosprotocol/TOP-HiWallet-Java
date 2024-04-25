package com.topnetwork.wallet.entity;

import com.topnetwork.wallet.common.enums.PushType;
import com.base.core.framework.sql.entity.Base;
import com.gitee.magic.jdbc.persistence.annotation.Entity;
import com.gitee.magic.jdbc.persistence.annotation.Table;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.ColumnDef;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.ScriptConverter;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.TableDef;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.converter.FieldLongText;

import java.util.Date;


@Entity("appNotice")
@Table("wal_appNotice")
@TableDef(comment = "公告")
public class AppNotice extends Base {

    private static final long serialVersionUID = 1L;

    public AppNotice() {

    }

    @ColumnDef(comment = "标题")
    private String title;

    @ColumnDef(comment = "英文标题")
    private String englishTitle;

    @ScriptConverter(FieldLongText.class)
    @ColumnDef(comment = "公告内容")
    private String content;

    @ScriptConverter(FieldLongText.class)
    @ColumnDef(comment = "英文公告内容")
    private String englishContent;

    @ColumnDef(comment = "公告生效开始时间")
    private Date startTime;

    @ColumnDef(comment = "公告生效结束时间")
    private Date endTime;

    @ColumnDef(comment = "创建时间")
    private Date createTime;

    @ColumnDef(comment = "创建人id")
    private Long createId;

    @ColumnDef(comment = "更新时间", isNull = true)
    private Date updateTime;

    @ColumnDef(comment = "更新人id", isNull = true)
    private Long updateId;
    @ColumnDef(comment = "通知类型", isNull = true)
    private PushType noticeType;

    @ColumnDef(comment = "活动链接url", isNull = true)
    private String url;

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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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

    public PushType getNoticeType() {
        return noticeType;
    }

    public void setNoticeType(PushType noticeType) {
        this.noticeType = noticeType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}