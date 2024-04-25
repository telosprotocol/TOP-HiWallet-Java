package com.topnetwork.wallet.result.wallet;

import io.swagger.v3.oas.annotations.media.Schema;

public class HelpSearchResult {

    @Schema(title = "帮助id", required = true)
    private Long id;
    @Schema(title = "帮助标题", required = true)
    private String title;
    @Schema(title = "帮助英文标题", required = true)
    private String englishTitle;
    @Schema(title = "帮助内容", required = true)
    private String content;
    @Schema(title = "帮助英文内容", required = true)
    private String englishContent;
    @Schema(title = "一级目录中文名", required = true)
    private String stname_cn;
    @Schema(title = "一级目录英文名", required = true)
    private String stname_en;
    @Schema(title = "二级目录中文名", required = true)
    private String ndname_cn;
    @Schema(title = "二级目录英文名", required = true)
    private String ndname_en;
    @Schema(title = "一级目录ID", required = true)
    private Long stCategoryId;
    @Schema(title = "二级级目录ID", required = true)
    private Long ndCategoryId;

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

    public String getEnglishTitle() {
        return englishTitle;
    }

    public void setEnglishTitle(String englishTitle) {
        this.englishTitle = englishTitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEnglishContent() {
        return englishContent;
    }

    public void setEnglishContent(String englishContent) {
        this.englishContent = englishContent;
    }

    public String getStname_cn() {
        return stname_cn;
    }

    public void setStname_cn(String stname_cn) {
        this.stname_cn = stname_cn;
    }

    public String getStname_en() {
        return stname_en;
    }

    public void setStname_en(String stname_en) {
        this.stname_en = stname_en;
    }

    public String getNdname_cn() {
        return ndname_cn;
    }

    public void setNdname_cn(String ndname_cn) {
        this.ndname_cn = ndname_cn;
    }

    public String getNdname_en() {
        return ndname_en;
    }

    public void setNdname_en(String ndname_en) {
        this.ndname_en = ndname_en;
    }
}
