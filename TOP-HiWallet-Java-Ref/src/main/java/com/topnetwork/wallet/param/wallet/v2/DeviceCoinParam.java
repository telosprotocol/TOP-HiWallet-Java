package com.topnetwork.wallet.param.wallet.v2;

import com.topnetwork.wallet.common.CodeRes;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.NotNull;

import java.util.List;

public class DeviceCoinParam {

    @Schema(title = "主链下的代币账户地址及合约地址", required = true)
    @NotNull(CodeRes.CODE_15005)
    private List<TokenParam> token;

    @Schema(title = "设备Id", required = true)
    @NotNull(CodeRes.CODE_15001)
    private String clientId;

    @Schema(title = "用户唯一标识", required = true)
    @NotNull(CodeRes.CODE_15001)
    private String userId;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<TokenParam> getToken() {
        return token;
    }

    public void setToken(List<TokenParam> token) {
        this.token = token;
    }

    public static class Token {


    }

    @Override
    public String toString() {
        return "DeviceCoinParam{" +
                ", token=" + token +
                '}';
    }
}
