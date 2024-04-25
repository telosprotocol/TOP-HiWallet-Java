package com.topnetwork.wallet.param.wallet;

import com.topnetwork.wallet.common.CodeRes;

import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Format;
import com.gitee.magic.core.valid.annotation.Length;
import com.gitee.magic.core.valid.annotation.NotNull;

public class CoinAddParam {

    @Schema(title = "英文全称", required = true)
    @NotNull(CodeRes.CODE_14028)
    private String englishName;

    @Schema(title = "中文全称", required = true)
    @NotNull(CodeRes.CODE_14028)
    private String chineseName;

    @Schema(title = "名称", required = true)
    @NotNull(CodeRes.CODE_14028)
    private String nickname;

    @Schema(title = "查询币价的该币id", required = true)
    @NotNull(CodeRes.CODE_14031)
    @Length(message = CodeRes.CODE_14031)
    private String ids;

    @Schema(title = "排序", required = true)
    @NotNull(CodeRes.CODE_14029)
    @Format(type = Format.FormatType.INTEGER, message = CodeRes.CODE_14029)
    @Length(max = 1000000, message = CodeRes.CODE_14029)
    private Integer tokenOrder;

    @Schema(title = "是否隐藏", required = true)
    @NotNull(CodeRes.CODE_14027)
    private Integer isHide;

    @Schema(title = "logo链接", required = true)
    @NotNull(CodeRes.CODE_14030)
    private String logoUrl;

    @Schema(title = "父币种Id", required = true)
    @Format(type = Format.FormatType.LONG, message = CodeRes.CODE_14025)
    private Long fatherId;

    @Schema(title = "合约地址", required = false)
    private String contractAddress;

    @Schema(title = "代币描述url", required = false)
    private String tokenDescriptionUrl;

    @Schema(title = "中文描述", required = true)
    @NotNull(CodeRes.CODE_14032)
    private String chineseDescription;

    @Schema(title = "英文描述", required = true)
    @NotNull(CodeRes.CODE_14032)
    private String englishDescription;

    @Schema(title = "是否热门币种", required = true)
    @NotNull(CodeRes.CODE_14033)
    @Format(type = Format.FormatType.INTEGER, message = CodeRes.CODE_14033)
    private Integer isHot;

    @Schema(title = "币种级别", required = true)
    @NotNull(CodeRes.CODE_14017)
    @Format(type = Format.FormatType.INTEGER, message = CodeRes.CODE_14017)
    private Integer level;

    @Schema(title = "代币精度", required = true)
    @NotNull(CodeRes.CODE_14034)
    @Format(type = Format.FormatType.INTEGER, message = CodeRes.CODE_14034)
    private Integer tokenDecimals;

    @Schema(title = "显示精度", required = true)
    @NotNull(CodeRes.CODE_14034)
    @Format(type = Format.FormatType.INTEGER, message = CodeRes.CODE_14034)
    private Integer showDecimals;

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

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }
}
