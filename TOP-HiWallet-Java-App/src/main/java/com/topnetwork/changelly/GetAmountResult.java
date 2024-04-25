package com.topnetwork.changelly;

import java.math.BigDecimal;

/**
 * @ClassName GetMinAmountResult
 * @Description
 * @Author bran
 * @Date 2020/6/11 15:03
 */
public class GetAmountResult extends ChangellyResponse {

    private BigDecimal result;

    public BigDecimal getResult() {
        return result;
    }

    public void setResult(BigDecimal result) {
        this.result = result;
    }
}
