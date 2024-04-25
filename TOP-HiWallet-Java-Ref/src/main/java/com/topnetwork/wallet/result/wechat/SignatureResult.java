package com.topnetwork.wallet.result.wechat;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @program: TOP-HiWallet-Java
 * @description:
 * @author: Tyrone
 * @create: 2020-05-06 13:50
 **/
public class SignatureResult {
    @Schema(title = "签名")
    private String signature;
    @Schema(title = "时间戳")
    private String timestamp;
    @Schema(title = "随机数")
    private String noncestr;
    @Schema(title = "appid")
    private String appId;

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    @Override
    public String toString() {
        return "SignatureResult{" +
                "signature='" + signature + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", noncestr='" + noncestr + '\'' +
                ", appId='" + appId + '\'' +
                '}';
    }
}
