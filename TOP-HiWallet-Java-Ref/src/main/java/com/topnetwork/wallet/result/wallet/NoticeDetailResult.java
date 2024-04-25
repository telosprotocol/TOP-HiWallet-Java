package com.topnetwork.wallet.result.wallet;

import com.topnetwork.wallet.common.enums.PushType;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Date;

public class NoticeDetailResult {

    @Schema(title = "id", required = true)
    private Long id;

    @Schema(title = "标题", required = true)
    private String title;
    @Schema(title = "内容", required = true)
    private String content;

    @Schema(title = "英文标题", required = true)
    private String englishTitle;
    @Schema(title = "英文内容", required = true)
    private String englishContent;

    @Schema(title = "生效开始时间", required = true)
    private Date startTime;

    @Schema(title = "生效结束时间", required = true)
    private Date endTime;

    @Schema(title = "创建时间", required = true)
    private Date createTime;

    @Schema(title = "创建人id", required = true)
    private Integer createId;

    @Schema(title = "更新时间", required = true)
    private Date updateTime;
    @Schema(title = "更新人id", required = true)
    private Integer updateId;

    @Schema(title = "通知类型", required = true)
    private PushType noticeType;

    @Schema(title = "活动url链接", required = true)
    private String url;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public Integer getCreateId() {
        return createId;
    }

    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Integer updateId) {
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
