package com.topnetwork.wallet.result.changelly;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

/**
 * @ClassName GetCurrencyAllResult
 * @Description
 * @Author bran
 * @Date 2020/6/16 17:52
 */
public class GetCurrencyAllResult {

    @Schema(title="支持买入的币的列表")
    private List<GetCurrencyResult> buy;
    @Schema(title="支持卖出的币的列表")
    private List<GetCurrencyResult> sell;

    public List<GetCurrencyResult> getBuy() {
        return buy;
    }

    public void setBuy(List<GetCurrencyResult> buy) {
        this.buy = buy;
    }

    public List<GetCurrencyResult> getSell() {
        return sell;
    }

    public void setSell(List<GetCurrencyResult> sell) {
        this.sell = sell;
    }
}
