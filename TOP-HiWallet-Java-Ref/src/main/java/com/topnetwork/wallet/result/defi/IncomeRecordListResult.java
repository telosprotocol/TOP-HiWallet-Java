package com.topnetwork.wallet.result.defi;

import java.math.BigDecimal;

import com.topnetwork.wallet.common.converter.NumberToBigdecimalConverterEditor;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.converter.PropertyConverter;

@Schema(title="收益记录明细返回参数")
public class IncomeRecordListResult {
	
	@Schema(title = "每日日期",required = true)
	private Long incomeDate;
	
	@Schema(title = "总资产（usdt）",required = true)
	@PropertyConverter(NumberToBigdecimalConverterEditor.class)
	private BigDecimal totalIncome;

	public Long getIncomeDate() {
		return incomeDate;
	}

	public void setIncomeDate(Long incomeDate) {
		this.incomeDate = incomeDate;
	}

	public BigDecimal getTotalIncome() {
		return totalIncome;
	}

	public void setTotalIncome(BigDecimal totalIncome) {
		this.totalIncome = totalIncome;
	}
	
}
