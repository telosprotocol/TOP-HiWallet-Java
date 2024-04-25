/**
 * 
 */
package com.topnetwork.wallet.param.defi;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.NotNull;
import com.gitee.magic.core.valid.annotation.number.LongValid;

/**
 * 
 * @author jode
 * @date Nov 23, 2020
 */
@Schema(title = "传递地址参数")
public class DefiApproveParam {
	
	@Schema(title = "授权产品id",required = true)
	@LongValid
	@NotNull
    private Long productId;
	
	@Schema(title = "授权金额（usdt）", required = true)
	@NotNull
	private BigDecimal amount;
	
	@Schema(title = "授权签名交易体", required = true)
	@NotNull
	private String signData;

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

	public String getSignData() {
		return signData;
	}

	public void setSignData(String signData) {
		this.signData = signData;
	}
	
}
