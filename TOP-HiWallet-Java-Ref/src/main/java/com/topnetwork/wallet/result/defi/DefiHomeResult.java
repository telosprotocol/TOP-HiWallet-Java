package com.topnetwork.wallet.result.defi;

import java.math.BigDecimal;

import com.topnetwork.wallet.common.converter.NumberToBigdecimalConverterEditor;

import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.converter.PropertyConverter;

public class DefiHomeResult {

	@Schema(title = "总资产（usdt）", required = true)
	@PropertyConverter(NumberToBigdecimalConverterEditor.class)
	private BigDecimal totalAmount;

	@Schema(title = "总收益（usdt）", required = true)
	@PropertyConverter(NumberToBigdecimalConverterEditor.class)
	private BigDecimal totalIncome;

	@Schema(title = "昨日总收益（usdt）", required = true)
	@PropertyConverter(NumberToBigdecimalConverterEditor.class)
	private BigDecimal yesterdayIncome;

	public DefiHomeResult(BigDecimal totalAmount, BigDecimal totalIncome, BigDecimal yesterdayIncome) {
		this.totalAmount = totalAmount;
		this.totalIncome = totalIncome;
		this.yesterdayIncome = yesterdayIncome;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public BigDecimal getTotalIncome() {
		return totalIncome;
	}

	public void setTotalIncome(BigDecimal totalIncome) {
		this.totalIncome = totalIncome;
	}

	public BigDecimal getYesterdayIncome() {
		return yesterdayIncome;
	}

	public void setYesterdayIncome(BigDecimal yesterdayIncome) {
		this.yesterdayIncome = yesterdayIncome;
	}

}
