package com.topnetwork.wallet.result.defi;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;

public class DayIncomePageResult{

	@Schema(title = "产品id")
	private Long productId;
	
	@Schema(title = "计算收益率日期")
	private Long rateDate;
	
	@Schema(title = "dai余额")
	private BigDecimal daiBalance;
	
	@Schema(title = "dai增量")
	private BigDecimal daiIncr;
	
	@Schema(title = "comp余额")
	private BigDecimal compBalance;
	
	@Schema(title = "comp增量")
	private BigDecimal compIncr;
	
	@Schema(title = "dai的总收益")
	private BigDecimal daiTotalIncome;
	
	@Schema(title = "comp总收益")
	private BigDecimal compTotalIncome;
	
	@Schema(title = "每日收益总额")
	private BigDecimal dayTotalIncome;

	@Schema(title = "收益总额")
	private BigDecimal totalIncome;

	@Schema(title = "截止时间年化收益率")
	private BigDecimal investRate;
	
	@Schema(title = "日年化收益率")
	private BigDecimal dayInvestRate;
	
	@Schema(title = "dai余额转usdt")
	private BigDecimal daiBalanceToUsdt;

	@Schema(title = "comp余额转usdt")
	private BigDecimal compBalanceToUsdt;

	@Schema(title = "comp余额转Dai")
	private BigDecimal compBalanceToDai;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getRateDate() {
		return rateDate;
	}

	public void setRateDate(Long rateDate) {
		this.rateDate = rateDate;
	}
	
	public BigDecimal getDaiBalance() {
		return daiBalance;
	}

	public void setDaiBalance(BigDecimal daiBalance) {
		this.daiBalance = daiBalance;
	}

	public BigDecimal getDaiIncr() {
		return daiIncr;
	}

	public void setDaiIncr(BigDecimal daiIncr) {
		this.daiIncr = daiIncr;
	}

	public BigDecimal getCompBalance() {
		return compBalance;
	}

	public void setCompBalance(BigDecimal compBalance) {
		this.compBalance = compBalance;
	}

	public BigDecimal getCompIncr() {
		return compIncr;
	}

	public void setCompIncr(BigDecimal compIncr) {
		this.compIncr = compIncr;
	}

	public BigDecimal getDaiTotalIncome() {
		return daiTotalIncome;
	}

	public void setDaiTotalIncome(BigDecimal daiTotalIncome) {
		this.daiTotalIncome = daiTotalIncome;
	}

	public BigDecimal getCompTotalIncome() {
		return compTotalIncome;
	}

	public void setCompTotalIncome(BigDecimal compTotalIncome) {
		this.compTotalIncome = compTotalIncome;
	}

	public BigDecimal getDayTotalIncome() {
		return dayTotalIncome;
	}

	public void setDayTotalIncome(BigDecimal dayTotalIncome) {
		this.dayTotalIncome = dayTotalIncome;
	}

	public BigDecimal getInvestRate() {
		return investRate;
	}

	public void setInvestRate(BigDecimal investRate) {
		this.investRate = investRate;
	}

	public BigDecimal getTotalIncome() {
		return totalIncome;
	}

	public void setTotalIncome(BigDecimal totalIncome) {
		this.totalIncome = totalIncome;
	}

	public BigDecimal getDayInvestRate() {
		return dayInvestRate;
	}

	public void setDayInvestRate(BigDecimal dayInvestRate) {
		this.dayInvestRate = dayInvestRate;
	}

	public BigDecimal getDaiBalanceToUsdt() {
		return daiBalanceToUsdt;
	}

	public void setDaiBalanceToUsdt(BigDecimal daiBalanceToUsdt) {
		this.daiBalanceToUsdt = daiBalanceToUsdt;
	}

	public BigDecimal getCompBalanceToUsdt() {
		return compBalanceToUsdt;
	}

	public void setCompBalanceToUsdt(BigDecimal compBalanceToUsdt) {
		this.compBalanceToUsdt = compBalanceToUsdt;
	}

	public BigDecimal getCompBalanceToDai() {
		return compBalanceToDai;
	}

	public void setCompBalanceToDai(BigDecimal compBalanceToDai) {
		this.compBalanceToDai = compBalanceToDai;
	}
	
}
