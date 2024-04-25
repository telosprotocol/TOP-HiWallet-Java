/**
 * 
 */
package com.topnetwork.wallet.result.defi.v2;

import java.math.BigDecimal;
import java.util.Date;

import com.base.core.head.converter.UploadKeyConverterEditor;
import com.topnetwork.wallet.common.converter.DateTimeStampEditoer;
import com.topnetwork.wallet.common.converter.NumberToBigdecimalConverterEditor;
import com.topnetwork.wallet.common.enums.defi.ContractProductStatusEnum;
import com.topnetwork.wallet.common.utils.TimeU;

import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.converter.PropertyConverter;

/**
 * @author jode
 * @date Nov 13, 2020
 */
public class MyInvestListV2Result {

	@Schema(title = "产品Id", required = true)
	private Long productId;

	@Schema(title = "聚合代投产品名称", required = true)
	private String name;

	@Schema(title = "年化利率范围最小值[0-100]", required = true)
	private Float minRate;

	@Schema(title = "年化利率范围最大值[0-100]", required = true)
	private Float maxRate;

	@Schema(title = "logo 在aws下的md5值")
	@PropertyConverter(UploadKeyConverterEditor.class)
	private String logo;

	@Schema(title = "当前地址总投资数额（usdt）", required = true)
	@PropertyConverter(NumberToBigdecimalConverterEditor.class)
	private BigDecimal totalAmount;
	
	@Schema(title =  "当前产品总资金")
	@PropertyConverter(NumberToBigdecimalConverterEditor.class)
	private BigDecimal currentTotalAmount;
	
	@Schema(title = "产品募集最低数额（usdt）", required = true)
	@PropertyConverter(NumberToBigdecimalConverterEditor.class)
	private BigDecimal raiseTotalAmount;


	@Schema(title = "总收益（usdt）", required = true)
	@PropertyConverter(NumberToBigdecimalConverterEditor.class)
	private BigDecimal totalIncome;

	@Schema(title = "昨日总收益（usdt）")
	@PropertyConverter(NumberToBigdecimalConverterEditor.class)
	private BigDecimal yesterdayIncome;

	@Schema(title = "锁定周期（天）")
	private Integer investDay;
	
	@Schema(title = "是否结束")
	private Boolean end;
	
	@Schema(title =  "参与日期")
	@PropertyConverter(DateTimeStampEditoer.class)
	private Date startDate;
	
	@Schema(title =  "参与完成")
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

	@Schema(title =  "结算周期(天)")
	private Integer revertDay;
	
	@Schema(title =  "募集完成时间")
	@PropertyConverter(DateTimeStampEditoer.class)
	private Date raiseDate;

	@Schema(title = "产品状态(INIT：预热中；RAISE_PROCESSING：募集中；RAISE_FULL：即将开始；MINING：挖矿中；SETTLEMENT_IN_PROGRESS：结算中；END：结束；CANCEL：取消", required = true)
	private ContractProductStatusEnum status;
	
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

	public Boolean getEnd() {
		return end;
	}

	public void setEnd(Boolean end) {
		this.end = end;
	}
	
	public Date getStartDate() {
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

	public Integer getRevertDay() {
		return revertDay;
	}

	public void setRevertDay(Integer revertDay) {
		this.revertDay = revertDay;
	}

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

	public ContractProductStatusEnum getStatus() {
		if(status==null) {
			if(getEnd()) {
				status=ContractProductStatusEnum.END;
				if(getCancelContractDate() != null) {//开启募集但是没有发起投资结束的
					status = ContractProductStatusEnum.CANCEL;
				}
			}else {
				status=ContractProductStatusEnum.RAISE_PROCESSING;//募集中开始，前面的状态不会存在
				long currentTime=System.currentTimeMillis();
				if(startDate.getTime()<currentTime) {
					if(getOpenContractDate() != null) {//开启募集
						status=ContractProductStatusEnum.RAISE_PROCESSING;
						if(getRaiseDate().getTime() < currentTime || getCurrentTotalAmount().compareTo(raiseTotalAmount) >=0) {
							status = ContractProductStatusEnum.RAISE_FULL;//即将挖矿
						}
					}
					if(getCancelContractDate() != null) {
						status = ContractProductStatusEnum.CANCEL;
					}else {						
						if(getPartDate()!=null) {
							if(getLiquidationContractDate() != null) {
								status = ContractProductStatusEnum.END;
							}else {								
								Date startInvestDate = getPartDate();
								Date endInterestDate=TimeU.getDate(startInvestDate, getInvestDay());
								if(startInvestDate.getTime()<currentTime&&currentTime<TimeU.getDate(startInvestDate, getInvestDay()).getTime()) {
									status=ContractProductStatusEnum.MINING;
								}else if(endInterestDate.getTime()<currentTime) {
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

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}
	
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	public BigDecimal getCurrentTotalAmount() {
		return currentTotalAmount;
	}

	public void setCurrentTotalAmount(BigDecimal currentTotalAmount) {
		this.currentTotalAmount = currentTotalAmount;
	}

	public BigDecimal getRaiseTotalAmount() {
		return raiseTotalAmount;
	}

	public void setRaiseTotalAmount(BigDecimal raiseTotalAmount) {
		this.raiseTotalAmount = raiseTotalAmount;
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

	public Integer getInvestDay() {
		return investDay;
	}

	public void setInvestDay(Integer investDay) {
		this.investDay = investDay;
	}

}
