package com.topnetwork.wallet.param.wallet;

import com.gitee.magic.core.valid.annotation.Enum;
import com.gitee.magic.core.valid.annotation.NotNull;
import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.common.enums.ChainTypeEnum;

import io.swagger.v3.oas.annotations.media.Schema;

public class ETHSendTransactionParam {
	
	@Schema(title = "链类型")
	@Enum(CodeRes.CODE_15004)
	private ChainTypeEnum chainType;

    @Schema(title = "交易hexValue串", required = true)
    @NotNull(CodeRes.CODE_14038)
    private String hexValue;
    
    public ChainTypeEnum getChainType() {
		return chainType;
	}

	public void setChainType(ChainTypeEnum chainType) {
		this.chainType = chainType;
	}

	public String getHexValue() {
        return hexValue;
    }

    public void setHexValue(String hexValue) {
        this.hexValue = hexValue;
    }
}
