package com.topnetwork.wallet.result.defi;

import java.math.BigDecimal;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

public class ProductDetailResult extends ProductPageResult{
	
	@Schema(title = "每日投资年化收益率列表")
	private List<InvestRateResult> investRateList;
	
	@Schema(title = "最终年化收益率")
	private BigDecimal investRate;
	
	public List<InvestRateResult> getInvestRateList() {
		return investRateList;
	}

	public void setInvestRateList(List<InvestRateResult> investRateList) {
		this.investRateList = investRateList;
	}

	public BigDecimal getInvestRate() {
		return investRate;
	}

	public void setInvestRate(BigDecimal investRate) {
		this.investRate = investRate;
	}

}
