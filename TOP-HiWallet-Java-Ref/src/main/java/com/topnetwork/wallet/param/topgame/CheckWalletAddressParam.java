package com.topnetwork.wallet.param.topgame;

import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.common.enums.ChainTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Enum;
import com.gitee.magic.core.valid.annotation.NotNull;

/**
 * @ClassName CheckWalletAddressParam
 * @Description
 * @Author bran
 * @Date 2020/8/12 17:17
 */
public class CheckWalletAddressParam {

    @Schema(title="公链类型")
    @NotNull(CodeRes.CODE_15044)
    @Enum(CodeRes.CODE_15044)
    private ChainTypeEnum chainType;
    @Schema(title="地址")
    @NotNull(CodeRes.CODE_21005)
    private String address;

    public ChainTypeEnum getChainType() {
        return chainType;
    }

    public void setChainType(ChainTypeEnum chainType) {
        this.chainType = chainType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
