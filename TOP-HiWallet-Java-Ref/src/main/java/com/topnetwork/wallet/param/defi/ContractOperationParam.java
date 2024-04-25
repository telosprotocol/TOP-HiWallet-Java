/**
 * 
 */
package com.topnetwork.wallet.param.defi;

import com.topnetwork.wallet.common.enums.defi.SyncHashTypeEnum;
import com.topnetwork.wallet.common.valid.RegexStr;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Enum;
import com.gitee.magic.core.valid.annotation.NotNull;
import com.gitee.magic.core.valid.annotation.Regex;
import com.gitee.magic.core.valid.annotation.number.LongValid;

/**
 * 
 * @author jode
 * @date Nov 23, 2020
 */
@Schema(title = "操作合约参数")
public class ContractOperationParam {
	
	@Schema(title = "产品id",required = true)
	@LongValid
	@NotNull
    private Long productId;
	
	@Schema(title = "合约类型：OPEN_CONTRACT：开启合约用户可投资，LIQUIDATION_CONTRACT：清算;INVESTMENT_CONTRACT: 启动投资操作；CANCEL_CONTRACT:取消投资操作",required = true)
	@Enum
	@NotNull
	private SyncHashTypeEnum hashType;
	
	@Schema(title = "操作合约交易hash", required = true)
	@NotNull
	@Regex(regex = RegexStr.HASH)
	private String hash;
	
	@Schema(title = "取消原因CN(取消操作必填)", required = false)
	private String cancelReasonCn;
	
	@Schema(title = "取消原因EN(取消操作必填)", required = false)
	private String cancelReasonEn;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public SyncHashTypeEnum getHashType() {
		return hashType;
	}

	public void setHashType(SyncHashTypeEnum hashType) {
		this.hashType = hashType;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getCancelReasonCn() {
		return cancelReasonCn;
	}

	public void setCancelReasonCn(String cancelReasonCn) {
		this.cancelReasonCn = cancelReasonCn;
	}

	public String getCancelReasonEn() {
		return cancelReasonEn;
	}

	public void setCancelReasonEn(String cancelReasonEn) {
		this.cancelReasonEn = cancelReasonEn;
	}
	
}
