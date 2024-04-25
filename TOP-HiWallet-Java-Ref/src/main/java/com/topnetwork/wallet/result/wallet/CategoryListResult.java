package com.topnetwork.wallet.result.wallet;

import io.swagger.v3.oas.annotations.media.Schema;

public class CategoryListResult {

    @Schema(title = "id", required = true)
    private Long id;

    @Schema(title = "分级名称", required = true)
    private String name;

    @Schema(title = "分级英文名称", required = true)
    private String englishName;

    @Schema(title = "级别，1、2", required = true)
    private Integer level;

    @Schema(title = "上级分级id", required = true)
    private Long fatherId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Long getFatherId() {
        return fatherId;
    }

    public void setFatherId(Long fatherId) {
        this.fatherId = fatherId;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }
}
