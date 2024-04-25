package com.topnetwork.wallet.param.defi.problem;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.number.LongValid;

@Schema(title = "问题主键参数")
public class ProblemIdParam {
	
	@Schema(title =  "问题ID")
	@LongValid
	private Long problemId;

	public Long getProblemId() {
		return problemId;
	}

	public void setProblemId(Long problemId) {
		this.problemId = problemId;
	}

}
