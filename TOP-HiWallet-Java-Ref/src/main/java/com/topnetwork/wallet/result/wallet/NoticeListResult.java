package com.topnetwork.wallet.result.wallet;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Date;

public class NoticeListResult {

    @Schema(title = "id", required = true)
    private Long id;
    @Schema(title = "标题", required = true)
    private String title;
    @Schema(title = "英文标题", required = true)
    private String englishTitle;
    @Schema(title = "更新时间", required = true)
    private Date updateTime;
    @Schema(title = "创建时间", required = true)
    private Date createTime;
    @Schema(title = "操作人", required = true)
    private String username;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEnglishTitle() {
        return englishTitle;
    }

    public void setEnglishTitle(String englishTitle) {
        this.englishTitle = englishTitle;
    }

}
