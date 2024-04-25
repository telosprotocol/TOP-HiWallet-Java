package com.topnetwork.wallet.result.wyre;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @ClassName GetReservationV2Result
 * @Description
 * @Author bran
 * @Date 2020/9/16 17:30
 */
public class GetReservationV2Result {

    @Schema(title="跳转链接地址")
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
