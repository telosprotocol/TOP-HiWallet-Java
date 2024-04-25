package com.topnetwork.wallet.param.wallet.v2;

import com.topnetwork.wallet.common.CodeRes;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.NotNull;

import java.util.List;

public class DeviceReportParam extends DeviceBaseParam {

    @Schema(title = "上报的币种地址及合约地址以及对应的主链（ChainType）", required = true)
    @NotNull(CodeRes.CODE_15003)
    private List<DeviceCoinParam> coins;

    public List<DeviceCoinParam> getCoins() {
        return coins;
    }

    public void setCoins(List<DeviceCoinParam> coins) {
        this.coins = coins;
    }

    @Override
    public String toString() {
        return "DeviceReportParam{" +
                ", coins=" + coins +
                '}';
    }
}
