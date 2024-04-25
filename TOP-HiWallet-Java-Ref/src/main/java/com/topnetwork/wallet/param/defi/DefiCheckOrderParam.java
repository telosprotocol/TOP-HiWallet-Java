/**
 * 
 */
package com.topnetwork.wallet.param.defi;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.NotNull;

/**
 * 
 * @author jode
 * @date Jan 18, 2021
 */
@Schema(title = "上报交易数据传入参数")
public class DefiCheckOrderParam {
	
	@Schema(title = "产品id", required = true)
	@NotNull
	private Long productId;
	
	@Schema(title = "金额（usdt）", required = true)
	@NotNull
	private BigDecimal amount;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
}
