package com.topnetwork.wallet.result.wallet.discover;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @ClassName GetHtmlDetailResult
 * @Description
 * @Author bran
 * @Date 2020/5/26 15:06
 */
public class GetHtmlDetailResult extends GetAppDetailBaseResult {

    @Schema(title="跳转链接")
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
