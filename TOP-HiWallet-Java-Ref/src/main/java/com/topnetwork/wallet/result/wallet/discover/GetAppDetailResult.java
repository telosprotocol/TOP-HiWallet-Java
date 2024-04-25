package com.topnetwork.wallet.result.wallet.discover;


import com.base.core.head.converter.UploadKeyArrayConverterEditor;
import com.base.core.head.converter.UploadKeyConverterEditor;
import com.topnetwork.wallet.common.enums.ChainTypeEnum;
import com.topnetwork.wallet.common.enums.discover.BannerNewType;

import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.converter.PropertyConverter;

public class GetAppDetailResult {

    @Schema(title = "banner类型 TOPSTAKING,//top staking ；PICONLY,//1.纯图片，不跳转；HTML,//2.跳转至H5，用内置浏览器打开； CENTRALIAPP,//4.跳转至新青年等中心化app；DECENTRALIAPP,//3.跳转至DApp等去中心化app；HTMLDAPP//5.详情页的html。")
    private BannerNewType type;
    @Schema(title = "关联dappId,如非关联dapp类型，则为bannerid")
    private Long id;
    @Schema(title = "app标题")
    private String title;
    @Schema(title = "主链类型")
    private ChainTypeEnum chainType;
    @Schema(title = "标签数组")
    private String[] tips;
    @Schema(title = "描述图片数组")
    @PropertyConverter(UploadKeyArrayConverterEditor.class)
    private String[] image;
    @Schema(title = "详细描述")
    private String detail;
    @Schema(title = "简略描述")
    private String desc;
    @Schema(title = "下载地址/跳转地址")
    private String url;
    @Schema(title = "应用图标地址")
    @PropertyConverter(UploadKeyConverterEditor.class)
    private String iconUrl;
    @Schema(title = "备用下载地址")
    private String subUrl;

    public BannerNewType getType() {
        return type;
    }

    public void setType(BannerNewType type) {
        this.type = type;
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

    public ChainTypeEnum getChainType() {
        return chainType;
    }

    public void setChainType(ChainTypeEnum chainType) {
        this.chainType = chainType;
    }

    public String[] getTips() {
        return tips;
    }

    public void setTips(String[] tips) {
        this.tips = tips;
    }

    public String[] getImage() {
        return image;
    }

    public void setImage(String[] image) {
        this.image = image;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSubUrl() {
        return subUrl;
    }

    public void setSubUrl(String subUrl) {
        this.subUrl = subUrl;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }
}
