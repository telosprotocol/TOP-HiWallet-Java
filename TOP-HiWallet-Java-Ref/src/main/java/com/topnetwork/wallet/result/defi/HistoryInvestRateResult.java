package com.topnetwork.wallet.result.defi;

import java.math.BigDecimal;
import java.util.Date;

import com.topnetwork.wallet.common.converter.DateTimeStampEditoer;

import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.converter.PropertyConverter;

public class HistoryInvestRateResult {
	
	@Schema(title = "挖矿周期开始日期")
	@PropertyConverter(DateTimeStampEditoer.class)
	private Date investStartDate;
	
	@Schema(title = "挖矿周期结束日期")
	@PropertyConverter(DateTimeStampEditoer.class)
	private Date investEndDate;
	
	@Schema(title = "截止时间年化收益率")
	private BigDecimal investRate;
	
	public Date getInvestStartDate() {
		return investStartDate;
	}

	public void setInvestStartDate(Date investStartDate) {
		this.investStartDate = investStartDate;
	}

	public Date getInvestEndDate() {
		return investEndDate;
	}

	public void setInvestEndDate(Date investEndDate) {
		this.investEndDate = investEndDate;
	}

	public BigDecimal getInvestRate() {
		return investRate;
	}

	public void setInvestRate(BigDecimal investRate) {
		this.investRate = investRate;
	}
	
}
