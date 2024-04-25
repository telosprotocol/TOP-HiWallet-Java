package com.topnetwork.wallet.result.wallet.gas;

/**
 * @program: TOP-HiWallet-Java
 * @description:
 * @author: Tyrone
 * @create: 2020-11-11 14:40
 **/
public class GasPriceResult {
    private String LastBlock;
    private String SafeGasPrice;
    private String ProposeGasPrice;
    private String FastGasPrice;

    public String getLastBlock() {
        return LastBlock;
    }

    public void setLastBlock(String lastBlock) {
        LastBlock = lastBlock;
    }

    public String getSafeGasPrice() {
        return SafeGasPrice;
    }

    public void setSafeGasPrice(String safeGasPrice) {
        SafeGasPrice = safeGasPrice;
    }

    public String getProposeGasPrice() {
        return ProposeGasPrice;
    }

    public void setProposeGasPrice(String proposeGasPrice) {
        ProposeGasPrice = proposeGasPrice;
    }

    public String getFastGasPrice() {
        return FastGasPrice;
    }

    public void setFastGasPrice(String fastGasPrice) {
        FastGasPrice = fastGasPrice;
    }

    @Override
    public String toString() {
        return "GasPriceResult{" +
                "LastBlock='" + LastBlock + '\'' +
                ", SafeGasPrice='" + SafeGasPrice + '\'' +
                ", ProposeGasPrice='" + ProposeGasPrice + '\'' +
                ", FastGasPrice='" + FastGasPrice + '\'' +
                '}';
    }
}
