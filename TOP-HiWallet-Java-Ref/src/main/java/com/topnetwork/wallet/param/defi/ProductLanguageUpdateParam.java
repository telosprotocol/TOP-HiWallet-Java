package com.topnetwork.wallet.param.defi;

import java.util.List;

import com.topnetwork.wallet.param.defi.problem.ProblemAddParam;

import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.NotEmpty;
import com.gitee.magic.core.valid.annotation.NotNull;
import com.gitee.magic.core.valid.annotation.number.LongValid;

public class ProductLanguageUpdateParam extends ProductLanguageAddParam{
	
	@Schema(title =  "语言ID")
	@LongValid
	private Long languageId;
	
	@Schema(title =  "问题国际化语言数据")
	@NotNull
	@NotEmpty
	private List<ProblemAddParam> problems;

	public Long getLanguageId() {
		return languageId;
	}

	public void setLanguageId(Long languageId) {
		this.languageId = languageId;
	}

	public List<ProblemAddParam> getProblems() {
		return problems;
	}

	public void setProblems(List<ProblemAddParam> problems) {
		this.problems = problems;
	}
	
}
