package com.topnetwork.wallet.result.changelly;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

/**
 * @ClassName GetOrderDetailResult
 * @Description
 * @Author bran
 * @Date 2020/6/15 16:20
 */
public class GetOrderDetailResult extends GetOrderPageResult {

    @Schema(title="汇率")
    private BigDecimal rate;
    @Schema(title="卖出账户")
    private String fromAddress;
    @Schema(title="买入账户")
    private String toAddress;
    @Schema(title="changelly查询交易详情url")
    private String trackUrl;

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public String getTrackUrl() {
        return trackUrl;
    }

    public void setTrackUrl(String trackUrl) {
        this.trackUrl = trackUrl;
    }
}
