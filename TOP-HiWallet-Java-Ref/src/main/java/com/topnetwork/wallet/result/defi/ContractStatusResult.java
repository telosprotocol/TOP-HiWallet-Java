package com.topnetwork.wallet.result.defi;

import io.swagger.v3.oas.annotations.media.Schema;

public class ContractStatusResult {

	@Schema(title = "合约status；0：未使用；1：募集中；2：募集结束，等待发车中；3：发车成功，收益中", required = true)
	private Integer status;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
