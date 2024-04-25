package com.topnetwork.wallet.result.defi.problem;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(title = "问题返回参数")
public class ProblemListResult{
	
	@Schema(title =  "英文问题列表",required = true)
	List<ProblemLanguageResult> problemENs;
	
	@Schema(title =  "中文问题列表",required = true)
	List<ProblemLanguageResult> problemCNs;

	public List<ProblemLanguageResult> getProblemENs() {
		return problemENs;
	}

	public void setProblemENs(List<ProblemLanguageResult> problemENs) {
		this.problemENs = problemENs;
	}

	public List<ProblemLanguageResult> getProblemCNs() {
		return problemCNs;
	}

	public void setProblemCNs(List<ProblemLanguageResult> problemCNs) {
		this.problemCNs = problemCNs;
	}
	
	
	

}
