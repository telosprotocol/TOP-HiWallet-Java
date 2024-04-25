package com.topnetwork.wallet.result.defi;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;

public class InvestRateResult {
	
	@Schema(title = "计算收益率日期")
	private Long rateDate;
	
	@Schema(title = "截止时间年化收益率")
	private BigDecimal investRate;

	public Long getRateDate() {
		return rateDate;
	}

	public void setRateDate(Long rateDate) {
		this.rateDate = rateDate;
	}

	public BigDecimal getInvestRate() {
		return investRate;
	}

	public void setInvestRate(BigDecimal investRate) {
		this.investRate = investRate;
	}
	
}
