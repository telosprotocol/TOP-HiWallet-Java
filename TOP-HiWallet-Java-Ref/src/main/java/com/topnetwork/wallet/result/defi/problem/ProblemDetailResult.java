package com.topnetwork.wallet.result.defi.problem;

import com.topnetwork.wallet.common.enums.defi.ProblemTypeEnum;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(title = "问题详情返回参数")
public class ProblemDetailResult extends ProblemLanguageResult{
	
	@Schema(title = "问题Id",required = true)
	private Long problemId;
	
	@Schema(title = "业务ID[比如产品ID]",required = false)
	private Long targetId;
	
	@Schema(title = "问题分类[DEFI_PRODUCT defi产品问题，COMMON常见问题]",required = true)
	private ProblemTypeEnum problemType;
	
	public Long getProblemId() {
		return problemId;
	}

	public void setProblemId(Long problemId) {
		this.problemId = problemId;
	}

	public Long getTargetId() {
		return targetId;
	}

	public void setTargetId(Long targetId) {
		this.targetId = targetId;
	}

	public ProblemTypeEnum getProblemType() {
		return problemType;
	}

	public void setProblemType(ProblemTypeEnum problemType) {
		this.problemType = problemType;
	}
	
}
