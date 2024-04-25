/**
 * 
 */
package com.topnetwork.wallet.result.defi;

import java.math.BigDecimal;

import com.base.core.head.converter.UploadKeyConverterEditor;
import com.topnetwork.wallet.common.enums.defi.DefiOrderStatus;
import com.topnetwork.wallet.common.enums.defi.DefiOrderTypeEnum;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.converter.PropertyConverter;

/**
 * @author jode
 * @date Nov 13, 2020
 */
@Schema(title="交易订单明细返回参数")
public class DefiOrderListResult {

	@Schema(title = "产品名称", required = true)
	private String name;

	@Schema(title = "产品id", required = true)
	private Long productId;

	@Schema(title = "产品logo url", required = true)
	@PropertyConverter(UploadKeyConverterEditor.class)
	private String logo;

	@Schema(title = "金额（usdt）", required = true)
	private BigDecimal amount;

	@Schema(title = "交易hash", required = true)
	private String hash;

	@Schema(title = "交易编号", required = true)
	private Long orderId;

	@Schema(title = "交易时间戳（毫秒）", required = true)
	private Long transferTime;

	@Schema(title = "订单状态", required = true)
	private DefiOrderStatus orderStatus;

	@Schema(title = "订单类型", required = true)
	private DefiOrderTypeEnum orderType;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getTransferTime() {
		return transferTime;
	}

	public void setTransferTime(Long transferTime) {
		this.transferTime = transferTime;
	}

	public DefiOrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(DefiOrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public DefiOrderTypeEnum getOrderType() {
		return orderType;
	}

	public void setOrderType(DefiOrderTypeEnum orderType) {
		this.orderType = orderType;
	}

}
