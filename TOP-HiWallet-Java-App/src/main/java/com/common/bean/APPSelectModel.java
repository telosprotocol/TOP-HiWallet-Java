package com.common.bean;

import com.topnetwork.wallet.common.enums.ChainTypeEnum;
import com.topnetwork.wallet.entity.DiscoverBase;

/**
 * @ClassName APPSelectModel
 * @Description
 * @Author bran
 * @Date 2020/5/26 10:02
 */
public class APPSelectModel extends DiscoverBase {

    private String url;
    private String subUrl;
    private ChainTypeEnum chainType;

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

    public ChainTypeEnum getChainType() {
        return chainType;
    }

    public void setChainType(ChainTypeEnum chainType) {
        this.chainType = chainType;
    }
}
