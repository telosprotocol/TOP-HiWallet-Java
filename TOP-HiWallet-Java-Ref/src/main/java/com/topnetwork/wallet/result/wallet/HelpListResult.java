package com.topnetwork.wallet.result.wallet;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Date;

public class HelpListResult {

    @Schema(title = "id", required = true)
    private Long id;
    @Schema(title = "更新时间", required = true)
    private Date updateTime;
    @Schema(title = "语言", required = true)
    private Integer language;
    @Schema(title = "1级目录id", required = true)
    private Long stCategoryId;
    @Schema(title = "2级目录id", required = true)
    private Long ndCategoryId;
    @Schema(title = "标题", required = true)
    private String title;
    @Schema(title = "英文标题", required = true)
    private String englishTitle;
    @Schema(title = "操作人", required = true)
    private String username;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getLanguage() {
        return language;
    }

    public void setLanguage(Integer language) {
        this.language = language;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
