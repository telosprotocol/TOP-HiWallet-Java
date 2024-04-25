package com.topnetwork.wallet.param.wallet.top;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @ClassName GetBalanceParam
 * @Description
 * @Author bran
 * @Date 2020/10/10 19:12
 */
public class GetBaseTokenParam extends TopBaseParam{

    @Schema(title = "top 请求token", required = false)
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
