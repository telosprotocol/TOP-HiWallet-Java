package com.topnetwork.wallet.result.defi.v2;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import com.topnetwork.wallet.result.defi.HistoryInvestRateResult;
import com.topnetwork.wallet.result.defi.InvestRateResult;

import io.swagger.v3.oas.annotations.media.Schema;

public class ProductDetailV2Result extends ProductPageV2Result{
	
	@Schema(title = "每日投资年化收益率列表")
	private List<InvestRateResult> investRateList;
	
	@Schema(title = "最终年化收益率")
	private BigDecimal investRate;
	
	@Schema(title = "合约历史年化收益")
	private List<HistoryInvestRateResult> historyInvestRates;
	
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

	public List<HistoryInvestRateResult> getHistoryInvestRates() {
		return historyInvestRates;
	}

	public void setHistoryInvestRates(List<HistoryInvestRateResult> historyInvestRates) {
		this.historyInvestRates = historyInvestRates;
	}
	
}
