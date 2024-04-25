package com.topnetwork.wallet.param.wallet;

import io.swagger.v3.oas.annotations.media.Schema;

public class ETHGetCoinPriceParam {

    @Schema(title = "法币名称，cny或者usd（The target currency of market data (usd, eur, jpy, etc.)）", required = true)
    private String vsCurrency;
    @Schema(title = "查询的币ids(The ids of the coin, comma separated crytocurrency symbols (base). refers to /coins/list.When left empty, returns numbers the coins observing the params limit and start)，如ethereum", required = true)
    private String ids;
    @Schema(title = "是否测试币，是true，否false", required = false)
    private Boolean test;

    public String getVsCurrency() {
        return vsCurrency;
    }

    public void setVsCurrency(String vsCurrency) {
        this.vsCurrency = vsCurrency;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public Boolean getTest() {
        return test;
    }

    public void setTest(Boolean test) {
        this.test = test;
    }
}
