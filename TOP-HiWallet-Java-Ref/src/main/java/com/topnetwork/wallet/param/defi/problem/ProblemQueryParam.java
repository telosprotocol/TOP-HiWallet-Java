package com.topnetwork.wallet.param.defi.problem;

import com.topnetwork.wallet.common.enums.LanguageEnum;
import com.topnetwork.wallet.common.enums.defi.ProblemTypeEnum;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Enum;

@Schema(title = "问题列表参数")
public class ProblemQueryParam {
	
	@Schema(title = "问题类型[DEFI_PRODUCT defi产品问题，COMMON常见问题]")
	@Enum
	private ProblemTypeEnum problemType;
	
	@Schema(title = "语言")
	@Enum
	private LanguageEnum language;
	
	@Schema(title = "产品Id")
	private Long targetId;

	public ProblemTypeEnum getProblemType() {
		return problemType;
	}

	public void setProblemType(ProblemTypeEnum problemType) {
		this.problemType = problemType;
	}
	
	public LanguageEnum getLanguage() {
		return language;
	}

	public void setLanguage(LanguageEnum language) {
		this.language = language;
	}

	public Long getTargetId() {
		return targetId;
	}

	public void setTargetId(Long targetId) {
		this.targetId = targetId;
	}
	
	
}
