/**
 * 
 */
package com.topnetwork.wallet.param.defi;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.NotNull;

/**
 * @author jode
 * @date Nov 13, 2020
 */
@Schema(title = "上报交易数据传入参数")
public class DefiOrderAddParam {
	
	@Schema(title = "产品id", required = true)
	@NotNull
	private Long productId;
	
	@Schema(title = "合约地址", required = true)
	@NotNull
	private String contractAddress;

	@Schema(title = "金额（usdt）", required = true)
	@NotNull
	private BigDecimal amount;
	
	@Schema(title = "交易gasPrice", required = false)
	private BigDecimal gasPrice;
	
	@Schema(title = "交易gasLimit", required = false)
	private BigDecimal gasLimit;
	
	@Schema(title = "签名交易体", required = true)
	@NotNull
	private String signData;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getContractAddress() {
		return contractAddress;
	}

	public void setContractAddress(String contractAddress) {
		this.contractAddress = contractAddress;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getGasPrice() {
		return gasPrice;
	}

	public void setGasPrice(BigDecimal gasPrice) {
		this.gasPrice = gasPrice;
	}

	public BigDecimal getGasLimit() {
		return gasLimit;
	}

	public void setGasLimit(BigDecimal gasLimit) {
		this.gasLimit = gasLimit;
	}

	public String getSignData() {
		return signData;
	}

	public void setSignData(String signData) {
		this.signData = signData;
	}
}
