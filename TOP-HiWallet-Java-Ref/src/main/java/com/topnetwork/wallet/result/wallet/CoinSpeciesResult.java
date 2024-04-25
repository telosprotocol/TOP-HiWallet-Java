package com.topnetwork.wallet.result.wallet;

import io.swagger.v3.oas.annotations.media.Schema;

public class CoinSpeciesResult {

    @Schema(title = "id", required = true)
    private Long id;
    @Schema(title = "英文全称", required = true)
    private String englishName;
    @Schema(title = "中文全称", required = true)
    private String chineseName;
    @Schema(title = "精度", required = true)
    private Integer decimals;
    @Schema(title = "名称", required = true)
    private String symbol;
    @Schema(title = "币种级别", required = true)
    private Boolean mainChain;
    @Schema(title = "合约地址", required = true)
    private String contractAddress;
    @Schema(title = "logoKey", required = true)
    private String logoUrl;
    @Schema(title = "logo链接", required = true)
    private String iconUrl;
    @Schema(title = "代币描述链接", required = true)
    private String infoURL;
    @Schema(title = "币简介", required = true)
    private String introduce;

    @Schema(title = "字段标识主链类型，比如 以太坊主链是ETH", required = true)
    private String chainType;


    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Integer getDecimals() {
        return decimals;
    }

    public void setDecimals(Integer decimals) {
        this.decimals = decimals;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getContractAddress() {
        return contractAddress;
    }

    public void setContractAddress(String contractAddress) {
        this.contractAddress = contractAddress;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getInfoURL() {
        return infoURL;
    }

    public void setInfoURL(String infoURL) {
        this.infoURL = infoURL;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getChainType() {
        return chainType;
    }

    public void setChainType(String chainType) {
        this.chainType = chainType;
    }

    public Boolean getMainChain() {
        return mainChain;
    }

    public void setMainChain(Boolean mainChain) {
        this.mainChain = mainChain;
    }
}
