package com.topnetwork.wallet.param.wallet.v2;

import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.common.enums.ChainTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Enum;
import com.gitee.magic.core.valid.annotation.NotNull;

public class TokenParam {

    @Schema(title = "账户地址", required = true)
    @NotNull(CodeRes.CODE_15006)
    private String address;
    @Schema(title = "合约地址,为空表示当前币为主币，否则为chainType下的代币")
    private String contract;
    @Schema(title = "主链symbol，ETH：以太坊，BTC：比特币，TOP：top", required = true)
    @NotNull(CodeRes.CODE_15004)
    @Enum(CodeRes.CODE_15004)
    private ChainTypeEnum chainType;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public ChainTypeEnum getChainType() {
        return chainType;
    }

    public void setChainType(ChainTypeEnum chainType) {
        this.chainType = chainType;
    }

    @Override
    public String toString() {
        return "TokenParam{" +
                "address='" + address + '\'' +
                ", contract='" + contract + '\'' +
                ", chainType=" + chainType +
                '}';
    }
}
