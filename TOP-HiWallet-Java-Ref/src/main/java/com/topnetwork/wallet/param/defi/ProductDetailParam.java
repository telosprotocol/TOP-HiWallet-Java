package com.topnetwork.wallet.param.defi;

import com.base.core.head.ao.IDAO;

import io.swagger.v3.oas.annotations.media.Schema;

public class ProductDetailParam extends IDAO{
	
	@Schema(title = "ETH地址")
	private String address;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
}
