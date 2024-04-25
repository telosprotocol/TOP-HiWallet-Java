package com.topnetwork.wallet.result.wallet.statistics;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @ClassName AddressResult
 * @Description
 * @Author bran
 * @Date 2020/6/22 14:24
 */
public class AddressResult {

    @Schema(title="所有账户数")
    private Integer all;
    @Schema(title="ios所有账户数")
    private Integer ios;
    @Schema(title="android所有账户数")
    private Integer android;

    public AddressResult() {
        this.all = 0;
        this.ios = 0;
        this.android = 0;
    }

    public Integer getAll() {
        return all;
    }

    public void setAll(Integer all) {
        this.all = all;
    }

    public Integer getIos() {
        return ios;
    }

    public void setIos(Integer ios) {
        this.ios = ios;
    }

    public Integer getAndroid() {
        return android;
    }

    public void setAndroid(Integer android) {
        this.android = android;
    }
}
