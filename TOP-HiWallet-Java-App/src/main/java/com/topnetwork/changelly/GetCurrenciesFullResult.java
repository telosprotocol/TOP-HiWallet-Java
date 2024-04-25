package com.topnetwork.changelly;

import java.util.List;

/**
 * @ClassName GetCurrenciesFullResult
 * @Description
 * @Author bran
 * @Date 2020/6/11 15:03
 */
public class GetCurrenciesFullResult extends ChangellyResponse {

    private List<CurrencyInfo> result;

    public List<CurrencyInfo> getResult() {
        return result;
    }

    public void setResult(List<CurrencyInfo> result) {
        this.result = result;
    }
}
