package com.topnetwork.wallet.param.wallet;

import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.common.enums.ChainTypeEnum;

import io.swagger.v3.oas.annotations.media.Schema;

import com.gitee.magic.core.valid.annotation.Enum;
import com.gitee.magic.core.valid.annotation.NotNull;

public class ETHQueryBalanceParam {
	
	@Schema(title = "链类型")
	@Enum(CodeRes.CODE_15004)
	private ChainTypeEnum chainType;

    @Schema(title = "eth地址", required = true)
    @NotNull(CodeRes.CODE_14036)
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
