package com.topnetwork.wallet.result.changelly;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @ClassName CheckCurrencyResult
 * @Description
 * @Author bran
 * @Date 2020/6/16 15:07
 */
public class CheckCurrencyV2Result{

    @Schema(title="支持或者不支持币币兑换，true支持，false不支持")
    private Boolean swapConfig;
    @Schema(title="支持或者不支持法币买入，true支持，false不支持")
    private Boolean getCoinConfig;

    public Boolean getSwapConfig() {
        return swapConfig;
    }

    public void setSwapConfig(Boolean swapConfig) {
        this.swapConfig = swapConfig;
    }

    public Boolean getGetCoinConfig() {
        return getCoinConfig;
    }

    public void setGetCoinConfig(Boolean getCoinConfig) {
        this.getCoinConfig = getCoinConfig;
    }
}
