package com.topnetwork.wallet.common.bean;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;

public class IncreaseInterestBean {

	@Schema(title =  "最小")
	private BigDecimal min;

	@Schema(title =  "最大")
	private BigDecimal max;

	@Schema(title =  "加息值")
	private BigDecimal interest;
	
	public BigDecimal getMin() {
		return min;
	}

	public void setMin(BigDecimal min) {
		this.min = min;
	}

	public BigDecimal getMax() {
		return max;
	}

	public void setMax(BigDecimal max) {
		this.max = max;
	}

	public BigDecimal getInterest() {
		return interest;
	}

	public void setInterest(BigDecimal interest) {
		this.interest = interest;
	}
	
}
