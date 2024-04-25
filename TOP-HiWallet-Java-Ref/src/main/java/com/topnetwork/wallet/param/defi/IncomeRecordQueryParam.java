package com.topnetwork.wallet.param.defi;

import com.base.core.head.ao.PageAO;

import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.NotNull;
import com.gitee.magic.core.valid.annotation.number.LongValid;

public class IncomeRecordQueryParam extends PageAO{
	
	@Schema(title="产品Id")
	@LongValid
	@NotNull
	private Long productId;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

}
