package com.topnetwork.wallet.param.defi;

import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.number.LongValid;

public class ProductUpdateParam extends ProductAddParam{
	
	@Schema(title =  "产品ID")
	@LongValid
	private Long productId;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

}
