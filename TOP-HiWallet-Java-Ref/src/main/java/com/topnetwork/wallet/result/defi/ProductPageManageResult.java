package com.topnetwork.wallet.result.defi;

import java.math.BigDecimal;
import java.util.Date;

import com.topnetwork.wallet.common.converter.DateTimeStampEditoer;
import com.topnetwork.wallet.common.enums.CurrencyEnum;

import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.converter.PropertyConverter;
import com.gitee.magic.core.json.JsonArray;

public class ProductPageManageResult{
	
	@Schema(title =  "产品ID")
	private Long productId;
	
	@Schema(title = "产品名称")
	private String name;
	
	@Schema(title = "产品标签")
	private String[] tags;
	
	@Schema(title =  "投资币种")
	private CurrencyEnum currency;
	
	@Schema(title =  "年化利率范围最小值")
	private Float minRate;
	
	@Schema(title =  "年化利率范围最大值")
	private Float maxRate;
	
	@Schema(title =  "起投金额")
	private BigDecimal investAmount;

	@Schema(title =  "募集总资金")
	private BigDecimal totalAmount;
	
	@Schema(title =  "预热截止日期")
	@PropertyConverter(DateTimeStampEditoer.class)
	private Date startDate;
	
	@Schema(title =  "募集周期(天)")
	private Integer raiseDay;

	@Schema(title =  "项目周期(天)")
	private Integer investDay;

	@Schema(title =  "结算周期(天)")
	private Integer revertDay;
	
	@Schema(title =  "合约地址")
	private String contractAddress;

	@Schema(title = "加息数据")
	private JsonArray increaseInterests;
	
	@Schema(title = "排序值值最小排最前")
	private Integer sort;
	
	@Schema(title = "是否结束")
	private Boolean end;

	@Schema(title = "发布状态")
	private Boolean publish;
	
	@Schema(title = "审计报告url")
	private String auditReportUrl;
	
	@Schema(title = "最终年化收益率")
	private BigDecimal investRate;
	
	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String[] getTags() {
		return tags;
	}

	public void setTags(String[] tags) {
		this.tags = tags;
	}

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

	public JsonArray getIncreaseInterests() {
		return increaseInterests;
	}

	public void setIncreaseInterests(JsonArray increaseInterests) {
		this.increaseInterests = increaseInterests;
	}

	public Boolean getPublish() {
		return publish;
	}

	public void setPublish(Boolean publish) {
		this.publish = publish;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	public Boolean getEnd() {
		return end;
	}

	public void setEnd(Boolean end) {
		this.end = end;
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
