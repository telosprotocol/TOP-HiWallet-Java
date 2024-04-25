package com.common.bean;

import com.topnetwork.wallet.common.enums.ChainTypeEnum;
import com.topnetwork.wallet.common.enums.PlatformEnum;

/**
 * @ClassName CoinsAdress
 * @Description
 * @Author bran
 * @Date 2020/6/22 14:31
 */
public class CoinsAddress {

    private ChainTypeEnum chainType;
    private String address;
    private String contract;
    private Boolean effectiveAddress;
    private PlatformEnum platform;

    public ChainTypeEnum getChainType() {
        return chainType;
    }

    public void setChainType(ChainTypeEnum chainType) {
        this.chainType = chainType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public Boolean getEffectiveAddress() {
        return effectiveAddress;
    }

    public void setEffectiveAddress(Boolean effectiveAddress) {
        this.effectiveAddress = effectiveAddress;
    }

    public PlatformEnum getPlatform() {
        return platform;
    }

    public void setPlatform(PlatformEnum platform) {
        this.platform = platform;
    }
}
