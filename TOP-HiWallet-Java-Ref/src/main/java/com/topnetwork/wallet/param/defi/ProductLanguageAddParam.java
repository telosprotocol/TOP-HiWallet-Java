package com.topnetwork.wallet.param.defi;

import com.base.core.head.valid.UploadKeyValid;
import com.topnetwork.wallet.common.enums.LanguageEnum;

import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Custom;
import com.gitee.magic.core.valid.annotation.Enum;
import com.gitee.magic.core.valid.annotation.Format;
import com.gitee.magic.core.valid.annotation.Format.FormatType;
import com.gitee.magic.core.valid.annotation.Length;
import com.gitee.magic.core.valid.annotation.NotNull;

public class ProductLanguageAddParam{
	
	@Schema(title = "语言")
	@NotNull
	@Enum
	private LanguageEnum language;

	@Schema(title = "logo在aws下的md5值")
	@NotNull
	@Custom(UploadKeyValid.class)
	private String logo;

	@Schema(title = "产品名称")
	@NotNull
	@Length
	private String name;

	@Schema(title = "产品标签")
	@NotNull
	@Format(type = FormatType.JSONArray)
	private String[] tags;

	@Schema(title = "产品简介")
	@NotNull
	private String introduce;

	@Schema(title = "产品详情")
	@NotNull
	private String description;
	
	@Schema(title = "审计报告图片")
	@Custom(UploadKeyValid.class)
	private String auditReportImage;
	
	@Schema(title = "产品优势图片MD5字符")
	@Format(type = FormatType.JSONArray)
	private String[] advantageImages;
	
	@Schema(title = "资金流转图片MD5字符")
	@Custom(UploadKeyValid.class)
	private String capitalFlowImage;
	
	@Schema(title = "管理费用文案描述")
	@NotNull
	private String managementFeeText;
	
	@Schema(title = "管理费用示例说明")
	@NotNull
	private String managementFeeExplain;
	
	@Schema(title = "风险须知")
	@NotNull
	private String riskExplain;
	
	@Schema(title = "风险管理说明")
	@NotNull
	private String riskManagementExplain;
	
	@Schema(title = "挖矿策略说明")
	@NotNull
	private String miningStrategyExplain;
	
	@Schema(title = "资金安全说明")
	@NotNull
	private String capitalSecurityExplain;
	
	@Schema(title = "赎回规则")
	@NotNull
	private String redeemRuleExplain;
	
	@Schema(title = "赎回费用文案")
	@NotNull
	private String redeemFeeText;

	public LanguageEnum getLanguage() {
		return language;
	}

	public void setLanguage(LanguageEnum language) {
		this.language = language;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
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

	public String getAuditReportImage() {
		return auditReportImage;
	}

	public void setAuditReportImage(String auditReportImage) {
		this.auditReportImage = auditReportImage;
	}

	public String[] getAdvantageImages() {
		return advantageImages;
	}

	public void setAdvantageImages(String[] advantageImages) {
		this.advantageImages = advantageImages;
	}

	public String getCapitalFlowImage() {
		return capitalFlowImage;
	}

	public void setCapitalFlowImage(String capitalFlowImage) {
		this.capitalFlowImage = capitalFlowImage;
	}

	public String getManagementFeeText() {
		return managementFeeText;
	}

	public void setManagementFeeText(String managementFeeText) {
		this.managementFeeText = managementFeeText;
	}

	public String getManagementFeeExplain() {
		return managementFeeExplain;
	}

	public void setManagementFeeExplain(String managementFeeExplain) {
		this.managementFeeExplain = managementFeeExplain;
	}

	public String getRiskExplain() {
		return riskExplain;
	}

	public void setRiskExplain(String riskExplain) {
		this.riskExplain = riskExplain;
	}

	public String getRiskManagementExplain() {
		return riskManagementExplain;
	}

	public void setRiskManagementExplain(String riskManagementExplain) {
		this.riskManagementExplain = riskManagementExplain;
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

	public String getRedeemRuleExplain() {
		return redeemRuleExplain;
	}

	public void setRedeemRuleExplain(String redeemRuleExplain) {
		this.redeemRuleExplain = redeemRuleExplain;
	}

	public String getRedeemFeeText() {
		return redeemFeeText;
	}

	public void setRedeemFeeText(String redeemFeeText) {
		this.redeemFeeText = redeemFeeText;
	}
	
}
