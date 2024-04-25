package com.topnetwork.wallet.result.wallet.discover;

import io.swagger.v3.oas.annotations.media.Schema;

public class GameClassifyResult {

    @Schema(title = "中文标题")
    private String title;
    @Schema(title = "英文标题")
    private String englishTitle;
    @Schema(title = "所属类别id")
    private Long id;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
