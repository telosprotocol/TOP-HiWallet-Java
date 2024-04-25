package com.topnetwork.wallet.result.wallet.discover;


import com.topnetwork.wallet.common.enums.discover.BannerType;
import io.swagger.v3.oas.annotations.media.Schema;

public class GetBannersResult {

    @Schema(title = "banner类型 TOPSTAKING,//top staking ；PICONLY,//1.纯图片，不跳转；HTML,//2.跳转至H5，用内置浏览器打开； CENTRALIAPP,//4.跳转至新青年等中心化app；DECENTRALIAPP,//3.跳转至DApp等去中心化app；NOTICEDETAIL//5.跳转至公告详情页。")
    private BannerType type;
    @Schema(title = "目标id，如跳转公告详情则为公告id")
    private Long targetId;
    @Schema(title = "banner图片url")
    private String bannerUrl;
    @Schema(title = "跳转url")
    private String actionUrl;


    public BannerType getType() {
        return type;
    }

    public void setType(BannerType type) {
        this.type = type;
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
}
