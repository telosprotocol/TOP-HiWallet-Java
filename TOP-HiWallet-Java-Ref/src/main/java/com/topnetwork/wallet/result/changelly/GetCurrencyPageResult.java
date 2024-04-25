package com.topnetwork.wallet.result.changelly;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

/**
 * @ClassName GetCurrencyPageResult
 * @Description 获取eos节点列表返回值
 * @Author bran
 * @Date 2020/4/18 10:36
 */
public class GetCurrencyPageResult {

    @Schema(title = "币种名称")
    private String symbol;
    @Schema(title = "绑定币种id")
    private Long coinId;
    @Schema(title = "Changelly支持币种id")
    private Long currencyId;
    @Schema(title = "是否禁用")
    private Boolean disable;
    @Schema(title = "最小值是否自定义")
    private Boolean customized;
    @Schema(title = "最大交易金额")
    private BigDecimal maxSellAmount;
    @Schema(title = "最小交易金额")
    private BigDecimal minSellAmount;
    @Schema(title = "更新时间")
    private Long updateTime;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Long getCoinId() {
        return coinId;
    }

    public void setCoinId(Long coinId) {
        this.coinId = coinId;
    }

    public Boolean getDisable() {
        return disable;
    }

    public void setDisable(Boolean disable) {
        this.disable = disable;
    }

    public BigDecimal getMaxSellAmount() {
        return maxSellAmount;
    }

    public void setMaxSellAmount(BigDecimal maxSellAmount) {
        this.maxSellAmount = maxSellAmount;
    }

    public Long getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Long currencyId) {
        this.currencyId = currencyId;
    }

    public BigDecimal getMinSellAmount() {
        return minSellAmount;
    }

    public void setMinSellAmount(BigDecimal minSellAmount) {
        this.minSellAmount = minSellAmount;
    }

    public Boolean getCustomized() {
        return customized;
    }

    public void setCustomized(Boolean customized) {
        this.customized = customized;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }
}
