package com.topnetwork.wallet.result.defi;

import com.topnetwork.wallet.common.enums.defi.SyncStatusEnum;

import io.swagger.v3.oas.annotations.media.Schema;

public class DefiHashStatusResult {

	@Schema(title = "hash", required = true)
	private String hash;
	
	@Schema(title = "hash执行状态", required = true)
	private SyncStatusEnum status;

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public SyncStatusEnum getStatus() {
		return status;
	}

	public void setStatus(SyncStatusEnum status) {
		this.status = status;
	}

}
