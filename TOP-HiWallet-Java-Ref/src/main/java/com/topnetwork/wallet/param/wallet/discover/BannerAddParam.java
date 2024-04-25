package com.topnetwork.wallet.param.wallet.discover;

import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.common.enums.ChainTypeEnum;
import com.topnetwork.wallet.common.enums.discover.BannerType;
import com.topnetwork.wallet.common.enums.discover.DappShowType;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.framework.head.converter.TimeStampConverterEditor;
import com.gitee.magic.core.converter.PropertyConverter;
import com.gitee.magic.core.valid.annotation.Enum;
import com.gitee.magic.core.valid.annotation.Format;
import com.gitee.magic.core.valid.annotation.Length;
import com.gitee.magic.core.valid.annotation.NotNull;

import java.util.Date;

public class BannerAddParam {

    @Schema(title = "标题", required = true)
    @NotNull(CodeRes.CODE_15050)
    @Length(message = CodeRes.CODE_15050)
    private String title;

    @Schema(title = "英文标题", required = true)
    @NotNull(CodeRes.CODE_15050)
    @Length(message = CodeRes.CODE_15050)
    private String englishTitle;

    @Schema(title = "Banner类型", required = true)
    @NotNull(CodeRes.CODE_15051)
    @Enum(CodeRes.CODE_15051)
    private BannerType type;

    @Schema(title = "公链类型", required = false)
    @Enum(CodeRes.CODE_15044)
    private ChainTypeEnum chainType;

    @Schema(title = "图片url", required = true)
    @NotNull(CodeRes.CODE_15052)
    @Length(message = CodeRes.CODE_15052)
    private String bannerUrl;

    @Schema(title = "图片英文url", required = true)
    @NotNull(CodeRes.CODE_15052)
    @Length(message = CodeRes.CODE_15052)
    private String englishBannerUrl;

    @Schema(title = "跳转链接地址", required = false)
    private String actionUrl;

    @Schema(title = "目标id", required = false)
    @Format(type = Format.FormatType.LONG, message = CodeRes.CODE_14023)
    private Long targetId;

    @Schema(title = "是否显示", required = true)
    @NotNull(CodeRes.CODE_15053)
    @Format(type = Format.FormatType.BOOLEAN, message = CodeRes.CODE_15053)
    @Length(message = CodeRes.CODE_15053)
    private Boolean bannerShow;

    @Schema(title = "排序，数字越小越靠前", required = true)
    @NotNull(CodeRes.CODE_15054)
    @Length(message = CodeRes.CODE_15054)
    @Format(type = Format.FormatType.INTEGER, message = CodeRes.CODE_15054)
    private Integer bannerOrder;

    @Schema(title = "开始时间", required = true)
    @NotNull(CodeRes.CODE_14006)
    @PropertyConverter(TimeStampConverterEditor.class)
    private Date startTime;

    @Schema(title = "结束时间", required = true)
    @NotNull(CodeRes.CODE_14007)
    @PropertyConverter(TimeStampConverterEditor.class)
    private Date endTime;

    @Schema(title = "展示类型（ 审核时可用：examine, 非审核时可用： normal, 无限制、任何时候都显示： all）")
    @Enum(CodeRes.CODE_15076)
    @NotNull(CodeRes.CODE_15076)
    private DappShowType showType;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BannerType getType() {
        return type;
    }

    public void setType(BannerType type) {
        this.type = type;
    }

    public ChainTypeEnum getChainType() {
        return chainType;
    }

    public void setChainType(ChainTypeEnum chainType) {
        this.chainType = chainType;
    }

    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }

    public String getActionUrl() {
        return actionUrl;
    }

    public void setActionUrl(String actionUrl) {
        this.actionUrl = actionUrl;
    }

    public String getEnglishTitle() {
        return englishTitle;
    }

    public void setEnglishTitle(String englishTitle) {
        this.englishTitle = englishTitle;
    }

    public String getEnglishBannerUrl() {
        return englishBannerUrl;
    }

    public void setEnglishBannerUrl(String englishBannerUrl) {
        this.englishBannerUrl = englishBannerUrl;
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

    public Long getTargetId() {
        return targetId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    public Boolean getBannerShow() {
        return bannerShow;
    }

    public void setBannerShow(Boolean bannerShow) {
        this.bannerShow = bannerShow;
    }

    public Integer getBannerOrder() {
        return bannerOrder;
    }

    public void setBannerOrder(Integer bannerOrder) {
        this.bannerOrder = bannerOrder;
    }

    public DappShowType getShowType() {
        return showType;
    }

    public void setShowType(DappShowType showType) {
        this.showType = showType;
    }
}
