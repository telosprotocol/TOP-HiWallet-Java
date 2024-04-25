package com.topnetwork.wallet.param.wallet.v2;

import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.common.enums.ChainTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Enum;
import com.gitee.magic.core.valid.annotation.NotNull;

public class DeviceDelChainParam {

    @Schema(title = "主链symbol，ETH：以太坊，BTC：比特币，TOP：top", required = true)
    @NotNull(CodeRes.CODE_15004)
    @Enum(CodeRes.CODE_15004)
    private ChainTypeEnum chainType;

    @Schema(title = "设备Id", required = true)
    @NotNull(CodeRes.CODE_15001)
    private String clientId;

    @Schema(title = "用户唯一标识", required = true)
    @NotNull(CodeRes.CODE_15001)
    private String userId;

    @Schema(title = "账户地址", required = true)
    @NotNull(CodeRes.CODE_15006)
    private String address;

    public ChainTypeEnum getChainType() {
        return chainType;
    }

    public void setChainType(ChainTypeEnum chainType) {
        this.chainType = chainType;
    }

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
