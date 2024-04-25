package com.topnetwork.wallet.result.defi.v2;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

import com.topnetwork.wallet.common.converter.DateTimeStampEditoer;
import com.topnetwork.wallet.common.converter.NumberToBigdecimalConverterEditor;
import com.topnetwork.wallet.common.enums.CurrencyEnum;
import com.topnetwork.wallet.common.enums.defi.ContractProductStatusEnum;
import com.topnetwork.wallet.common.utils.TimeU;
import com.topnetwork.wallet.result.defi.ProductLanguageResult;

import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.converter.PropertyConverter;
import com.gitee.magic.core.json.JsonArray;

public class ProductPageV2Result extends ProductLanguageResult{
	
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
	
	@Schema(title =  " 预热开始时间")
	@PropertyConverter(DateTimeStampEditoer.class)
	private Date publishTime;
	
	@Schema(title =  "启动参与日期")
	@PropertyConverter(DateTimeStampEditoer.class)
	private Date startDate;
	
	@Schema(title =  "合约投资完成时间")
	@PropertyConverter(DateTimeStampEditoer.class)
	private Date partDate;
	
	@Schema(title =  "取消合约时间")
	@PropertyConverter(DateTimeStampEditoer.class)
	private Date cancelContractDate;
	
	@Schema(title =  "启动合约时间")
	@PropertyConverter(DateTimeStampEditoer.class)
	private Date openContractDate;
	
	@Schema(title =  "清算合约时间")
	@PropertyConverter(DateTimeStampEditoer.class)
	private Date liquidationContractDate;
	
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
	
	@Schema(title =  "当前总资金")
	@PropertyConverter(NumberToBigdecimalConverterEditor.class)
	private BigDecimal currentTotalAmount;
	
	@Schema(title = "TopStaking数量")
	private BigDecimal topNum;
	
	@Schema(title =  "top staking 额外加息[0-100]")
	private BigDecimal rateInc;

	@Schema(title = "Staking标签")
	private String topStakingTip;

	@Schema(title = "Staking描述")
	private String topStakingRemark;
	
	@Schema(title = "审计报告url")
	private String auditReportUrl;
	
	@Schema(title = "是否参与该产品")
	private Boolean participate;
	
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
//		if(getOpenContractDate() != null) {
//			startDate = getOpenContractDate();
//		}
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	public Date getPartDate() {
		return partDate;
	}

	public void setPartDate(Date partDate) {
		this.partDate = partDate;
	}
	
	public Date getCancelContractDate() {
		return cancelContractDate;
	}

	public void setCancelContractDate(Date cancelContractDate) {
		this.cancelContractDate = cancelContractDate;
	}

	public Date getOpenContractDate() {
		return openContractDate;
	}

	public void setOpenContractDate(Date openContractDate) {
		this.openContractDate = openContractDate;
	}

	public Date getLiquidationContractDate() {
		return liquidationContractDate;
	}

	public void setLiquidationContractDate(Date liquidationContractDate) {
		this.liquidationContractDate = liquidationContractDate;
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

	public BigDecimal getCurrentTotalAmount() {
		return currentTotalAmount;
	}

	public void setCurrentTotalAmount(BigDecimal currentTotalAmount) {
		this.currentTotalAmount = currentTotalAmount;
	}

	public BigDecimal getTopNum() {
		return topNum;
	}

	public void setTopNum(BigDecimal topNum) {
		this.topNum = topNum;
	}
	
	///////////////////////
	
	@Schema(title =  "募集完成时间")
	@PropertyConverter(DateTimeStampEditoer.class)
	private Date raiseDate;
	
	@Schema(title =  "开始计息")
	@PropertyConverter(DateTimeStampEditoer.class)
	private Date startInterestDate;
	
	@Schema(title =  "停止计息")
	@PropertyConverter(DateTimeStampEditoer.class)
	private Date endInterestDate;
	
	@Schema(title =  "本息到账")
	@PropertyConverter(DateTimeStampEditoer.class)
	private Date investEndDate;
	
	@Schema(title =  "系统时间")
	@PropertyConverter(DateTimeStampEditoer.class)
	private Date systemDate;
	
	@Schema(title =  "产品状态(INIT：预热中；RAISE_PROCESSING：募集中；RAISE_FULL：即将开始；MINING：挖矿中；SETTLEMENT_IN_PROGRESS：结算中；END：结束；CANCEL：取消)")
	private ContractProductStatusEnum status;
	
	@Schema(title = "产品规则中时间进度【1-100】")
	private BigDecimal timeProgress;
	
	public Date getRaiseDate() {
		if(raiseDate==null) {
			if(getPartDate()!=null) {
				raiseDate=getPartDate();
			}else {
				raiseDate=TimeU.getDate(getStartDate(), getRaiseDay());
			}
		}
		return raiseDate;
	}

	public void setRaiseDate(Date raiseDate) {
		this.raiseDate = raiseDate;
	}

	public Date getStartInterestDate() {
		if(startInterestDate == null) {
			startInterestDate = getRaiseDate();
		}
		return startInterestDate;
	}

	public void setStartInterestDate(Date startInterestDate) {
		this.startInterestDate = startInterestDate;
	}

	public Date getEndInterestDate() {
		if(endInterestDate==null) {
			if(getLiquidationContractDate() != null) {
				endInterestDate = getLiquidationContractDate();
			}else {				
				endInterestDate=TimeU.getDate(getStartInterestDate(), getInvestDay());
			}
		}
		return endInterestDate;
	}

	public void setEndInterestDate(Date endInterestDate) {
		this.endInterestDate = endInterestDate;
	}

	public Date getInvestEndDate() {
		if(investEndDate == null) {			
			if(getLiquidationContractDate() != null) {
				investEndDate = getLiquidationContractDate();
			}else {
				investEndDate=TimeU.getDate(getEndInterestDate(), getRevertDay());
			}
		}
		return investEndDate;
	}

	public void setInvestEndDate(Date investEndDate) {
		this.investEndDate = investEndDate;
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

	public ContractProductStatusEnum getStatus() {
		if(status==null&&getCurrentTotalAmount()!=null) {
			long currentTime=System.currentTimeMillis();
			if(getEnd()) {
				status=ContractProductStatusEnum.END;
				if(getCancelContractDate() != null) {//开启募集但是没有发起投资结束的
					status = ContractProductStatusEnum.CANCEL;
				}
			}else {
				status=ContractProductStatusEnum.INIT;
				if(startDate.getTime()<currentTime) {
					if(getOpenContractDate() != null) {//开启募集
						status=ContractProductStatusEnum.RAISE_PROCESSING;
						if(getRaiseDate().getTime() < currentTime || getCurrentTotalAmount().compareTo(totalAmount) >=0) {
							status = ContractProductStatusEnum.RAISE_FULL;//即将挖矿
						}
					}
					if(getCancelContractDate() != null) {
						status = ContractProductStatusEnum.CANCEL;
					}else {						
						if(getPartDate()!=null) {//投资操作后的状态
							if(getLiquidationContractDate() != null) {
								status = ContractProductStatusEnum.END;
							}else {							
								if(getStartInterestDate().getTime()<currentTime&&currentTime<getEndInterestDate().getTime()) {
									status=ContractProductStatusEnum.MINING;
								}else if(getEndInterestDate().getTime()<currentTime) {
									status=ContractProductStatusEnum.SETTLEMENT_IN_PROGRESS;
								}
							}
						}
					}
				}
			}
		}
		return status;
	}

	public void setStatus(ContractProductStatusEnum status) {
		this.status = status;
	}

	public String getTopStakingTip() {
		return topStakingTip;
	}

	public void setTopStakingTip(String topStakingTip) {
		this.topStakingTip = topStakingTip;
	}

	public String getTopStakingRemark() {
		return topStakingRemark;
	}

	public void setTopStakingRemark(String topStakingRemark) {
		this.topStakingRemark = topStakingRemark;
	}

	public Date getSystemDate() {
		if(systemDate==null) {
			systemDate=new Date();
		}
		return systemDate;
	}

	public void setSystemDate(Date systemDate) {
		this.systemDate = systemDate;
	}

	public BigDecimal getRateInc() {
		return rateInc;
	}

	public void setRateInc(BigDecimal rateInc) {
		this.rateInc = rateInc;
	}

	public String getAuditReportUrl() {
		return auditReportUrl;
	}

	public void setAuditReportUrl(String auditReportUrl) {
		this.auditReportUrl = auditReportUrl;
	}

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public Boolean getParticipate() {
		return participate;
	}

	public void setParticipate(Boolean participate) {
		this.participate = participate;
	}
	
	public BigDecimal getTimeProgress() {
		long curr_time = System.currentTimeMillis();
		long totalDuration = 0L;
		long duration = 0L;
		if(curr_time > getPublishTime().getTime() && curr_time <= getStartDate().getTime()) {//预热-》募集
			totalDuration = getStartDate().getTime() - getPublishTime().getTime();
			duration = curr_time - getPublishTime().getTime();
		}
		if(curr_time > getStartDate().getTime() && curr_time <= getRaiseDate().getTime()) {//募集->挖矿
			totalDuration = getRaiseDate().getTime() - getStartDate().getTime();
			duration = curr_time - getStartDate().getTime();
		}
		if(curr_time > getRaiseDate().getTime() && curr_time < getInvestEndDate().getTime()) {//挖矿->本息返还
			totalDuration = getInvestEndDate().getTime() - getRaiseDate().getTime();
			duration = curr_time - getRaiseDate().getTime();
		}
		timeProgress = BigDecimal.ZERO;
		if(totalDuration > 0 && duration >0) {
			timeProgress = new  BigDecimal(duration).divide(new BigDecimal(totalDuration),4, RoundingMode.HALF_DOWN).multiply(BigDecimal.TEN.pow(2)).stripTrailingZeros();
		}
		return timeProgress;
	}

	public void setTimeProgress(BigDecimal timeProgress) {
		this.timeProgress = timeProgress;
	}
	
}
