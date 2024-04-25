package com.topnetwork.wallet.result.wallet.discover;

import com.topnetwork.wallet.common.enums.ChainTypeEnum;
import com.topnetwork.wallet.common.enums.discover.BannerType;
import com.topnetwork.wallet.common.enums.discover.DappShowType;
import io.swagger.v3.oas.annotations.media.Schema;

public class BannerListResult {

    @Schema(title = "Banner id")
    private Long id;
    @Schema(title = "banner标题")
    private String title;
    @Schema(title = "banner英文标题")
    private String englishTitle;
    @Schema(title = "banner类型")
    private BannerType type;
    @Schema(title = "公链类型")
    private ChainTypeEnum chainType;
    @Schema(title = "目标id，如跳转公告详情则为公告id")
    private Long targetId;
    @Schema(title = "banner图片url")
    private String bannerUrl;
    @Schema(title = "banner英文图片url")
    private String englishBannerUrl;
    @Schema(title = "跳转url")
    private String actionUrl;
    @Schema(title = "是否展示")
    private Boolean bannerShow;
    @Schema(title = "排序，数字越小越靠前")
    private Integer bannerOrder;
    @Schema(title = "banner活动开始时间")
    private Long startTime;
    @Schema(title = "banner活动结束时间")
    private Long endTime;

    @Schema(title = "展示类型（ 审核时可用：examine, 非审核时可用： normal, 无限制、任何时候都显示： all）")
    private DappShowType showType;


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

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Long getTargetId() {
        return targetId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
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
