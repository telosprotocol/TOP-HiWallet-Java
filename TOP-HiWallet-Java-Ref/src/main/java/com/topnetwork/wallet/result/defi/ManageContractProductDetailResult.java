package com.topnetwork.wallet.result.defi;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigInteger;
import java.util.List;

public class ManageContractProductDetailResult {

	@Schema(title = "投资币种", required = true)
	private String currency;

	@Schema(title = "起投金额", required = true)
	private Integer minInvestAmount;

	@Schema(title = "项目周期（天）", required = true)
	private Integer lockDay;

	@Schema(title = "募集时间（天）", required = true)
	private Integer raiseDay;

	@Schema(title = "募集金额", required = true)
	private BigInteger minTotalAmount;

	@Schema(title = "年化利率范围最小值[0-100]", required = true)
	private Integer minRate;

	@Schema(title = "年化利率范围最大值[0-100]", required = true)
	private Integer maxRate;

	@Schema(title = "产品合约地址", required = true)
	private String contractAddress;

	@Schema(title = "是否加息 1是 0否", required = true)
	private Integer staking;

	@Schema(title = "加息配置")
	private List<IncreaseInterestResult> increaseInterestList;

	@Schema(title = "项目介绍", required = true)
	private List<ManageContractProductLanguageResult> languageList;

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Integer getMinInvestAmount() {
		return minInvestAmount;
	}

	public void setMinInvestAmount(Integer minInvestAmount) {
		this.minInvestAmount = minInvestAmount;
	}

	public Integer getLockDay() {
		return lockDay;
	}

	public void setLockDay(Integer lockDay) {
		this.lockDay = lockDay;
	}

	public Integer getRaiseDay() {
		return raiseDay;
	}

	public void setRaiseDay(Integer raiseDay) {
		this.raiseDay = raiseDay;
	}

	public BigInteger getMinTotalAmount() {
		return minTotalAmount;
	}

	public void setMinTotalAmount(BigInteger minTotalAmount) {
		this.minTotalAmount = minTotalAmount;
	}

	public Integer getMinRate() {
		return minRate;
	}

	public void setMinRate(Integer minRate) {
		this.minRate = minRate;
	}

	public Integer getMaxRate() {
		return maxRate;
	}

	public void setMaxRate(Integer maxRate) {
		this.maxRate = maxRate;
	}

	public String getContractAddress() {
		return contractAddress;
	}

	public void setContractAddress(String contractAddress) {
		this.contractAddress = contractAddress;
	}

	public Integer getStaking() {
		return staking;
	}

	public void setStaking(Integer staking) {
		this.staking = staking;
	}

	public List<IncreaseInterestResult> getIncreaseInterestList() {
		return increaseInterestList;
	}

	public void setIncreaseInterestList(List<IncreaseInterestResult> increaseInterestList) {
		this.increaseInterestList = increaseInterestList;
	}

	public List<ManageContractProductLanguageResult> getLanguageList() {
		return languageList;
	}

	public void setLanguageList(List<ManageContractProductLanguageResult> languageList) {
		this.languageList = languageList;
	}
}
