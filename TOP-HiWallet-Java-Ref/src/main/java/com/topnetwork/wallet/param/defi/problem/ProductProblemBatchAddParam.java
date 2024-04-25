package com.topnetwork.wallet.param.defi.problem;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.NotEmpty;

public class ProductProblemBatchAddParam {
	
	@Schema(title =  "问题列表")
	@NotEmpty
	List<ProblemAddParam> problems;

	public List<ProblemAddParam> getProblems() {
		return problems;
	}

	public void setProblems(List<ProblemAddParam> problems) {
		this.problems = problems;
	}
	
}
