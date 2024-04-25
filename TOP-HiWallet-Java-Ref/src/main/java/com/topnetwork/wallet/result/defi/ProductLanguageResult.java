package com.topnetwork.wallet.result.defi;

import java.util.List;

import com.base.core.head.converter.UploadKeyArrayConverterEditor;
import com.base.core.head.converter.UploadKeyConverterEditor;
import com.topnetwork.wallet.common.enums.LanguageEnum;
import com.topnetwork.wallet.result.defi.problem.ProblemDetailResult;

import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.converter.PropertyConverter;

public class ProductLanguageResult{
	
	@Schema(title =  "语言ID")
	private Long languageId;

	@Schema(title =  "产品ID")
	private Long productId;
	
	@Schema(title = "语言")
	private LanguageEnum language;

	@Schema(title = "logo在aws下的md5值")
	@PropertyConverter(UploadKeyConverterEditor.class)
	private String logo;

	@Schema(title = "产品名称")
	private String name;

	@Schema(title = "产品标签")
	private String[] tags;

	@Schema(title = "产品简介")
	private String introduce;

	@Schema(title = "产品详情")
	private String description;
	
	@Schema(title = "审计报告图片")
	@PropertyConverter(UploadKeyConverterEditor.class)
	private String auditReportImage;
	
	@Schema(title = "产品优势图片MD5字符")
	@PropertyConverter(UploadKeyArrayConverterEditor.class)
	private String[] advantageImages;
	
	@Schema(title = "资金流转图片MD5字符")
	@PropertyConverter(UploadKeyConverterEditor.class)
	private String capitalFlowImage;
	
	@Schema(title = "管理费用文案描述")
	private String managementFeeText;
	
	@Schema(title = "管理费用示例说明")
	private String managementFeeExplain;
	
	@Schema(title = "风险须知")
	private String riskExplain;
	
	@Schema(title = "风险管理说明")
	private String riskManagementExplain;
	
	@Schema(title = "挖矿策略说明")
	private String miningStrategyExplain;
	
	@Schema(title = "资金安全说明")
	private String capitalSecurityExplain;
	
	@Schema(title = "赎回规则说明")
	private String redeemRuleExplain;
	
	@Schema(title = "赎回费用文案")
	private String redeemFeeText;
	
	@Schema(title = "取消原因")
	private String cancelReason;
	
	@Schema(title = "产品问题列表")
	private List<ProblemDetailResult> problems;
	
	public Long getLanguageId() {
		return languageId;
	}

	public void setLanguageId(Long languageId) {
		this.languageId = languageId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

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

	public String getRiskManagementExplain() {
		return riskManagementExplain;
	}

	public void setRiskManagementExplain(String riskManagementExplain) {
		this.riskManagementExplain = riskManagementExplain;
	}

	public String getManagementFeeText() {
		return managementFeeText;
	}

	public void setManagementFeeText(String managementFeeText) {
		this.managementFeeText = managementFeeText;
	}

	public List<ProblemDetailResult> getProblems() {
		return problems;
	}

	public void setProblems(List<ProblemDetailResult> problems) {
		this.problems = problems;
	}

	public String getRedeemFeeText() {
		return redeemFeeText;
	}

	public void setRedeemFeeText(String redeemFeeText) {
		this.redeemFeeText = redeemFeeText;
	}

	public String getCancelReason() {
		return cancelReason;
	}

	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}
	
}
