package com.topnetwork.wallet.result.wyre;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

/**
 * @ClassName GetInfoResult
 * @Description
 * @Author bran
 * @Date 2020/8/13 17:42
 */
public class GetInfoResult {

    @Schema(title="所属国家钱币信息")
    private List<Currency> currency;
    @Schema(title="支持买入的币的列表")
    private List<CoinResult> buy;

    public List<Currency> getCurrency() {
        return currency;
    }

    public void setCurrency(List<Currency> currency) {
        this.currency = currency;
    }

    public List<CoinResult> getBuy() {
        return buy;
    }

    public void setBuy(List<CoinResult> buy) {
        this.buy = buy;
    }
}
