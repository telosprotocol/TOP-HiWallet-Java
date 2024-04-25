package com.topnetwork.wallet.param.defi;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.topnetwork.wallet.common.bean.IncreaseInterestBean;
import com.topnetwork.wallet.common.enums.CurrencyEnum;

import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.framework.head.converter.TimeStampConverterEditor;
import com.gitee.magic.core.converter.PropertyConverter;
import com.gitee.magic.core.valid.annotation.BooleanValid;
import com.gitee.magic.core.valid.annotation.Enum;
import com.gitee.magic.core.valid.annotation.Format;
import com.gitee.magic.core.valid.annotation.Format.FormatType;
import com.gitee.magic.core.valid.annotation.NotNull;
import com.gitee.magic.core.valid.annotation.number.DoubleValid;
import com.gitee.magic.core.valid.annotation.number.FloatValid;
import com.gitee.magic.core.valid.annotation.number.IntegerValid;
import com.gitee.magic.core.valid.annotation.number.LongValid;

public class ProductAddParam{
	
	@Schema(title =  "投资币种",required = true)
	@NotNull
	@Enum
	private CurrencyEnum currency;
	
	@Schema(title =  "年化利率范围最小值",required = true)
	@NotNull
	@FloatValid
	private Float minRate;
	
	@Schema(title =  "年化利率范围最大值",required = true)
	@NotNull
	@FloatValid
	private Float maxRate;
	
	@Schema(title =  "起投金额",required = true)
	@NotNull
	@DoubleValid
	private BigDecimal investAmount;

	@Schema(title =  "募集总资金",required = true)
	@NotNull
	@DoubleValid
	private BigDecimal totalAmount;
	
	@Schema(title =  "参与日期",required = true)
	@NotNull
	@LongValid
//	@TimeFormat
//	@PropertyConverter(DateTimeConverterEditor.class)
	@PropertyConverter(TimeStampConverterEditor.class)
	private Date startDate;
	
	@Schema(title =  "募集周期(天)",required = true)
	@NotNull@IntegerValid
	private Integer raiseDay;

	@Schema(title =  "项目周期(天)",required = true)
	@NotNull@IntegerValid
	private Integer investDay;

	@Schema(title =  "结算周期(天)",required = true)
	@NotNull@IntegerValid
	private Integer revertDay;
	
	@Schema(title =  "合约地址",required = true)
	@NotNull
	private String contractAddress;

	@Schema(title = "加息数据",required = true)
	@Format(type = FormatType.JSONArray)
	private List<IncreaseInterestBean> increaseInterests;
	
	@Schema(title =  "是否结束")
	@BooleanValid
	private Boolean end;
	
	@Schema(title = "排序值值最小排最前",required = true)
	@NotNull
	@IntegerValid
	private Integer sort;
	
	@Schema(title =  "发布状态",required = true)
	@NotNull
	@BooleanValid
	private Boolean publish;
	
	@Schema(title =  "审计报告url")
	private String auditReportUrl;
	
	@Schema(title =  "最终年化收益率")
	private BigDecimal investRate;

	public CurrencyEnum getCurrency() {
		return currency;
	}

	public void setCurrency(CurrencyEnum currency) {
		this.currency = currency;
	}

	public Float getMinRate() {
		return minRate;
	}

	public void setMinRate(Float minRate) {
		this.minRate = minRate;
	}

	public Float getMaxRate() {
		return maxRate;
	}

	public void setMaxRate(Float maxRate) {
		this.maxRate = maxRate;
	}

	public BigDecimal getInvestAmount() {
		return investAmount;
	}

	public void setInvestAmount(BigDecimal investAmount) {
		this.investAmount = investAmount;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Integer getRaiseDay() {
		return raiseDay;
	}

	public void setRaiseDay(Integer raiseDay) {
		this.raiseDay = raiseDay;
	}

	public Integer getInvestDay() {
		return investDay;
	}

	public void setInvestDay(Integer investDay) {
		this.investDay = investDay;
	}

	public Integer getRevertDay() {
		return revertDay;
	}

	public void setRevertDay(Integer revertDay) {
		this.revertDay = revertDay;
	}

	public String getContractAddress() {
		return contractAddress;
	}

	public void setContractAddress(String contractAddress) {
		this.contractAddress = contractAddress;
	}

	public List<IncreaseInterestBean> getIncreaseInterests() {
		return increaseInterests;
	}

	public void setIncreaseInterests(List<IncreaseInterestBean> increaseInterests) {
		this.increaseInterests = increaseInterests;
	}
	
	public Boolean getEnd() {
		return end;
	}

	public void setEnd(Boolean end) {
		this.end = end;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Boolean getPublish() {
		return publish;
	}

	public void setPublish(Boolean publish) {
		this.publish = publish;
	}

	public String getAuditReportUrl() {
		return auditReportUrl;
	}

	public void setAuditReportUrl(String auditReportUrl) {
		this.auditReportUrl = auditReportUrl;
	}

	public BigDecimal getInvestRate() {
		return investRate;
	}

	public void setInvestRate(BigDecimal investRate) {
		this.investRate = investRate;
	}
}
