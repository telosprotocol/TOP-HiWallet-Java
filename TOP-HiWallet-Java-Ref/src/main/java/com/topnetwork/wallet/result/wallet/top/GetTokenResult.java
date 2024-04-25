package com.topnetwork.wallet.result.wallet.top;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @ClassName GetTokenResult
 * @Description
 * @Author bran
 * @Date 2020/10/10 17:12
 */
public class GetTokenResult {

    @Schema(title="top 返回的token")
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
