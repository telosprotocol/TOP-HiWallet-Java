package com.topnetwork.wallet.result.wallet;

import java.util.List;

import com.topnetwork.wallet.common.enums.CoinFeeType;
import com.topnetwork.wallet.common.enums.RequestMethodEnum;

import io.swagger.v3.oas.annotations.media.Schema;

public class CoinConfigResult {

    @Schema(title = "交易费类型 NOFEE,API,SELFCONF")
    private CoinFeeType type;
    @Schema(title = "api请求地址")
    private String requestUrl;
    @Schema(title = "api请求参数")
    private String requestParam;
    @Schema(title = "api请求方法 GET,POST,PUT,DELETE")
    private RequestMethodEnum requestMethod;
    @Schema(title = "该主链自定义交易费列表")
    private List<CoinFeeResult> fee;

    public CoinFeeType getType() {
        return type;
    }

    public void setType(CoinFeeType type) {
        this.type = type;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getRequestParam() {
        return requestParam;
    }

    public void setRequestParam(String requestParam) {
        this.requestParam = requestParam;
    }

    public RequestMethodEnum getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(RequestMethodEnum requestMethod) {
        this.requestMethod = requestMethod;
    }

    public List<CoinFeeResult> getFee() {
        return fee;
    }

    public void setFee(List<CoinFeeResult> fee) {
        this.fee = fee;
    }
}
