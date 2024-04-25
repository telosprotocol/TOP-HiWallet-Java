package com.topnetwork.wallet.result.wyre;

import com.base.core.head.converter.UploadKeyConverterEditor;

import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.converter.PropertyConverter;

/**
 * @ClassName GetEOSNodeResult
 * @Description 获取eos节点列表返回值
 * @Author bran
 * @Date 2020/4/18 10:36
 */
public class CoinResult {

    @Schema(title = "id", required = true)
    private Long id;
    @Schema(title = "英文全称", required = true)
    private String englishName;
    @Schema(title = "精度", required = true)
    private Integer decimals;
    @Schema(title = "symbol名称", required = true)
    private String symbol;
    @Schema(title = "币种级别", required = true)
    private Boolean mainChain;
    @Schema(title = "合约地址", required = true)
    private String contractAddress;
    @Schema(title = "logo链接", required = true)
    @PropertyConverter(UploadKeyConverterEditor.class)
    private String iconUrl;
    @Schema(title = "字段标识主链类型，比如 以太坊主链是ETH", required = true)
    private String chainType;

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

    public Boolean getMainChain() {
        return mainChain;
    }

    public void setMainChain(Boolean mainChain) {
        this.mainChain = mainChain;
    }

    public String getContractAddress() {
        return contractAddress;
    }

    public void setContractAddress(String contractAddress) {
        this.contractAddress = contractAddress;
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
}
