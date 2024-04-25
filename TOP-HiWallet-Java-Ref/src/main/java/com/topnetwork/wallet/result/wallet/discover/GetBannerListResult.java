package com.topnetwork.wallet.result.wallet.discover;

import com.topnetwork.wallet.common.enums.discover.BannerNewType;
import com.topnetwork.wallet.common.enums.discover.DappShowType;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @ClassName GetBannerListResult
 * @Description
 * @Author bran
 * @Date 2020/5/25 14:34
 */
public class GetBannerListResult {

    @Schema(title="banner类型 TOPSTAKING,//top staking ；PICONLY,纯图片，不跳转；HTML,跳转至H5，用内置浏览器打开； CENTRALIAPP,跳转至新青年中心化app；DECENTRALIAPP,跳转至DApp等去中心化app；HTMLDAPP,H5带详情")
    private BannerNewType type;
    @Schema(title="是否显示")
    private Boolean bannerShow;
    @Schema(title="banner 标题")
    private String title;
    @Schema(title="banner id")
    private Long id;
    @Schema(title="绑定的app id")
    private Long dappId;

    @Schema(title="跳转链接")
    private String url;
    @Schema(title="显示类型")
    private DappShowType showType;

    public BannerNewType getType() {
        return type;
    }

    public void setType(BannerNewType type) {
        this.type = type;
    }

    public Boolean getBannerShow() {
        return bannerShow;
    }

    public void setBannerShow(Boolean bannerShow) {
        this.bannerShow = bannerShow;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDappId() {
        return dappId;
    }

    public void setDappId(Long dappId) {
        this.dappId = dappId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public DappShowType getShowType() {
        return showType;
    }

    public void setShowType(DappShowType showType) {
        this.showType = showType;
    }
}
