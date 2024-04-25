package com.topnetwork.wallet.result.wallet;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Date;

public class CoinDetailListResult {

    @Schema(title = "id", required = true)
    private Long id;
    @Schema(title = "英文全称", required = true)
    private String englishName;
    @Schema(title = "中文全称", required = true)
    private String chineseName;
    @Schema(title = "查询币价的该币id", required = true)
    private String ids;
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
    @Schema(title = "合约地址", required = true)
    private String contractAddress;
    @Schema(title = "代币描述url", required = true)
    private String tokenDescriptionUrl;
    @Schema(title = "中文描述", required = true)
    private String chineseDescription;
    @Schema(title = "英文描述", required = true)
    private String englishDescription;
    @Schema(title = "是否热门币种", required = true)
    private Integer isHot;
    @Schema(title = "币种级别", required = true)
    private Integer level;
    @Schema(title = "创建时间", required = true)
    private Date createTime;
    @Schema(title = "更新时间", required = true)
    private Date updateTime;
    @Schema(title = "代币精度", required = true)
    private Integer tokenDecimals;
    @Schema(title = "显示精度", required = true)
    private Integer showDecimals;
    @Schema(title = "创建人id", required = true)
    private Integer createId;
    @Schema(title = "更新人id", required = true)
    private Integer updateId;


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

    public String getContractAddress() {
        return contractAddress;
    }

    public void setContractAddress(String contractAddress) {
        this.contractAddress = contractAddress;
    }

    public String getTokenDescriptionUrl() {
        return tokenDescriptionUrl;
    }

    public void setTokenDescriptionUrl(String tokenDescriptionUrl) {
        this.tokenDescriptionUrl = tokenDescriptionUrl;
    }

    public String getChineseDescription() {
        return chineseDescription;
    }

    public void setChineseDescription(String chineseDescription) {
        this.chineseDescription = chineseDescription;
    }

    public String getEnglishDescription() {
        return englishDescription;
    }

    public void setEnglishDescription(String englishDescription) {
        this.englishDescription = englishDescription;
    }

    public Integer getIsHot() {
        return isHot;
    }

    public void setIsHot(Integer isHot) {
        this.isHot = isHot;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getTokenDecimals() {
        return tokenDecimals;
    }

    public void setTokenDecimals(Integer tokenDecimals) {
        this.tokenDecimals = tokenDecimals;
    }

    public Integer getShowDecimals() {
        return showDecimals;
    }

    public void setShowDecimals(Integer showDecimals) {
        this.showDecimals = showDecimals;
    }

    public Integer getCreateId() {
        return createId;
    }

    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    public Integer getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Integer updateId) {
        this.updateId = updateId;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }
}
