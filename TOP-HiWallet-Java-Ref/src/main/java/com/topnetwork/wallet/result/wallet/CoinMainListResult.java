package com.topnetwork.wallet.result.wallet;

import io.swagger.v3.oas.annotations.media.Schema;

public class CoinMainListResult {

    @Schema(title = "id", required = true)
    private Long id;
    @Schema(title = "英文全称", required = true)
    private String englishName;
    @Schema(title = "中文全称", required = true)
    private String chineseName;
    @Schema(title = "名称", required = true)
    private String nickname;
    @Schema(title = "排序", required = true)
    private Integer tokenOrder;
    @Schema(title = "是否隐藏", required = true)
    private Integer isHide;
    @Schema(title = "logoKey", required = true)
    private String logoUrl;
    @Schema(title = "logo链接", required = true)
    private String iconUrl;
    @Schema(title = "代币数量", required = true)
    private Integer tokenSize;
    @Schema(title = "父币种英文全称", required = true)
    private String fatherName;
    @Schema(title = "父币种Id", required = true)
    private Long fatherId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getTokenOrder() {
        return tokenOrder;
    }

    public void setTokenOrder(Integer tokenOrder) {
        this.tokenOrder = tokenOrder;
    }

    public Integer getIsHide() {
        return isHide;
    }

    public void setIsHide(Integer isHide) {
        this.isHide = isHide;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public Integer getTokenSize() {
        return tokenSize;
    }

    public void setTokenSize(Integer tokenSize) {
        this.tokenSize = tokenSize;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public Long getFatherId() {
        return fatherId;
    }

    public void setFatherId(Long fatherId) {
        this.fatherId = fatherId;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }
}
