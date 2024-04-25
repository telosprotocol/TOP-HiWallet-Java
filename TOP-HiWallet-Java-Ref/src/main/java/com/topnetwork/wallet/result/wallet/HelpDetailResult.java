package com.topnetwork.wallet.result.wallet;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Date;

public class HelpDetailResult {

    @Schema(title = "帮助id")
    private Long id;

    @Schema(title = "标题")
    private String title;

    @Schema(title = "英语标题")
    private String englishTitle;

    @Schema(title = "主题内容")
    private String content;

    @Schema(title = "英文内容")
    private String englishContent;

    @Schema(title = "一级标题id")
    private Long stCategoryId;

    @Schema(title = "二级标题id")
    private Long ndCategoryId;

    @Schema(title = "创建时间")
    private Date createTime;

    @Schema(title = "创建人id")
    private Integer createId;

    @Schema(title = "更新时间")
    private Date updateTime;

    @Schema(title = "更新id")
    private Integer updateId;


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
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
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
}
