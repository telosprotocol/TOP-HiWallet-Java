package com.topnetwork.wallet.result.defi;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;

public class DefiApproveBalanceResult {
	
	@Schema(title = "地址", required = true)
	private String address;

	@Schema(title = "余额", required = true)
	private BigDecimal balanceOf;
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public BigDecimal getBalanceOf() {
		return balanceOf;
	}

	public void setBalanceOf(BigDecimal balanceOf) {
		this.balanceOf = balanceOf;
	}
}
