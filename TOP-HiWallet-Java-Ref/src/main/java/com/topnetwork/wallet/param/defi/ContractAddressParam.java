package com.topnetwork.wallet.param.defi;

import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.NotNull;

public class ContractAddressParam {
	
	@Schema(title = "合约地址")
	@NotNull
	private String contractAddress;

	public String getContractAddress() {
		return contractAddress;
	}

	public void setContractAddress(String contractAddress) {
		this.contractAddress = contractAddress;
	}
	
}
