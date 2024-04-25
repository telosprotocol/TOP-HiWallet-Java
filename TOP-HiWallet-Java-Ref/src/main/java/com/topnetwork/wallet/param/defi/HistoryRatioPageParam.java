package com.topnetwork.wallet.param.defi;

import com.base.core.head.ao.PageAO;

import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.NotNull;
import com.gitee.magic.core.valid.annotation.number.LongValid;

public class HistoryRatioPageParam extends PageAO{
	
	@Schema(title = "合约地址")
	@NotNull
	private String contractAddress;
	
	@Schema(title = "当前产品Id")
	@LongValid
	@NotNull
	private String productId;

	public String getContractAddress() {
		return contractAddress;
	}

	public void setContractAddress(String contractAddress) {
		this.contractAddress = contractAddress;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}
	
}