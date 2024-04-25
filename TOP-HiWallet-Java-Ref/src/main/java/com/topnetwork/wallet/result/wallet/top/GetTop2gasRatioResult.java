package com.topnetwork.wallet.result.wallet.top;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

/**
 * @ClassName GetTop2gasRatioResult
 * @Description
 * @Author bran
 * @Date 2020/10/12 11:49
 */
public class GetTop2gasRatioResult {

    @Schema(title="1top可兑换的gas数量")
    private BigDecimal ratio;

    public BigDecimal getRatio() {
        return ratio;
    }

    public void setRatio(BigDecimal ratio) {
        this.ratio = ratio;
    }
}
