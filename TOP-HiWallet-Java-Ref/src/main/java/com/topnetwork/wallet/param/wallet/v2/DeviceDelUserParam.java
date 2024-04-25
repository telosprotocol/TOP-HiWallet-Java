package com.topnetwork.wallet.param.wallet.v2;

import com.topnetwork.wallet.common.CodeRes;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.NotNull;

public class DeviceDelUserParam {

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
}
