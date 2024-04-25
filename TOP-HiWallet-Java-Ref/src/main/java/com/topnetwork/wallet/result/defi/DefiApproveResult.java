package com.topnetwork.wallet.result.defi;

import io.swagger.v3.oas.annotations.media.Schema;

public class DefiApproveResult {

	@Schema(title = "hash", required = true)
	private String hash;
	
	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

}
