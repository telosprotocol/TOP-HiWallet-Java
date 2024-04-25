package com.topnetwork.wallet.result.wallet.discover;


import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import com.base.core.head.converter.UploadKeyConverterEditor;
import com.topnetwork.wallet.common.enums.ChainTypeEnum;
import com.topnetwork.wallet.common.enums.discover.BannerNewType;

import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.converter.PropertyConverter;

public class GetNewBannersResult implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(title = "banner类型 TOPSTAKING,//top staking ；PICONLY,//1.纯图片，不跳转；HTML,//2.跳转至H5，用内置浏览器打开； CENTRALIAPP,//4.跳转至新青年等中心化app；DECENTRALIAPP,//3.跳转至DApp等去中心化app；HTMLDAPP//5.详情页的html。")
    private BannerNewType type;
    @Schema(title = "关联dappId,如非关联dapp类型，则为bannerid")
    private Long id;
    @Schema(title = "banner图片url")
    @PropertyConverter(UploadKeyConverterEditor.class)
    private String imageUrl;
    @Schema(title = "跳转url，如果类型为CENTRALIAPP，此为路由地址（币币兑换：HiWallet://open/change；新青年：HiWallet://open/NewYang）")
    private String url;
    @Schema(title = "app标题")
    private String title;
    @Schema(title = "app副标题")
    private String subTitle;
    @Schema(title = "主链类型")
    private ChainTypeEnum chainType;
    @Schema(title = "是否显示详情")
    private Boolean hasDetails;
    @Schema(title = "应用详情")
    private AppInfoResult info;


    public BannerNewType getType() {
    	//start 临时解决topstaking 映射配置问题
    	List<Long> dappIds = Arrays.asList(844154501400100864l,868257065867415552l);
    	if(id != null && dappIds.contains(id)) {
    		type = BannerNewType.TOPSTAKING;
    	}
    	//end
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public ChainTypeEnum getChainType() {
        return chainType;
    }

    public void setChainType(ChainTypeEnum chainType) {
        this.chainType = chainType;
    }

    public AppInfoResult getInfo() {
        return info;
    }

    public void setInfo(AppInfoResult info) {
        this.info = info;
    }

    public Boolean getHasDetails() {
        return hasDetails;
    }

    public void setHasDetails(Boolean hasDetails) {
        this.hasDetails = hasDetails;
    }
}
