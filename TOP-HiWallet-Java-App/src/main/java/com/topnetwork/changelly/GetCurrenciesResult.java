package com.topnetwork.changelly;

/**
 * @ClassName GetCurrenciesResult
 * @Description
 * @Author bran
 * @Date 2020/6/11 15:03
 */
public class GetCurrenciesResult extends ChangellyResponse {

    private String[] result;

    public String[] getResult() {
        return result;
    }

    public void setResult(String[] result) {
        this.result = result;
    }

}
