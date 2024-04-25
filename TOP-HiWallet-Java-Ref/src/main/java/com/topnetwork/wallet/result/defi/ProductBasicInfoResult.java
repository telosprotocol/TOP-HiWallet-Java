package com.topnetwork.wallet.result.defi;

import com.base.core.head.converter.UploadKeyConverterEditor;

import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.converter.PropertyConverter;

public class ProductBasicInfoResult {
	
	@Schema(title = "产品简介")
	private String introduce;

	@Schema(title = "产品详情")
	private String description;
	
	@Schema(title = "资金流转图片MD5字符")
	@PropertyConverter(UploadKeyConverterEditor.class)
	private String capitalFlowImage;
	
	@Schema(title = "挖矿策略说明")
	private String miningStrategyExplain;
	
	@Schema(title = "资金安全说明")
	private String capitalSecurityExplain;
	
	@Schema(title = "风险管理说明")
	private String riskManagementExplain;

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCapitalFlowImage() {
		return capitalFlowImage;
	}

	public void setCapitalFlowImage(String capitalFlowImage) {
		this.capitalFlowImage = capitalFlowImage;
	}

	public String getMiningStrategyExplain() {
		return miningStrategyExplain;
	}

	public void setMiningStrategyExplain(String miningStrategyExplain) {
		this.miningStrategyExplain = miningStrategyExplain;
	}

	public String getCapitalSecurityExplain() {
		return capitalSecurityExplain;
	}

	public void setCapitalSecurityExplain(String capitalSecurityExplain) {
		this.capitalSecurityExplain = capitalSecurityExplain;
	}

	public String getRiskManagementExplain() {
		return riskManagementExplain;
	}

	public void setRiskManagementExplain(String riskManagementExplain) {
		this.riskManagementExplain = riskManagementExplain;
	}
	
}
