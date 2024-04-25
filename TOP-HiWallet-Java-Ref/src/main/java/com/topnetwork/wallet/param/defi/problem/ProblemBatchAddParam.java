package com.topnetwork.wallet.param.defi.problem;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.NotEmpty;

public class ProblemBatchAddParam {
	
	@Schema(title =  "英文问题列表")
	@NotEmpty
	List<ProblemAddParam> problemENs;
	
	@Schema(title =  "中文问题列表")
	@NotEmpty
	List<ProblemAddParam> problemCNs;

	public List<ProblemAddParam> getProblemENs() {
		return problemENs;
	}

	public void setProblemENs(List<ProblemAddParam> problemENs) {
		this.problemENs = problemENs;
	}

	public List<ProblemAddParam> getProblemCNs() {
		return problemCNs;
	}

	public void setProblemCNs(List<ProblemAddParam> problemCNs) {
		this.problemCNs = problemCNs;
	}

}
