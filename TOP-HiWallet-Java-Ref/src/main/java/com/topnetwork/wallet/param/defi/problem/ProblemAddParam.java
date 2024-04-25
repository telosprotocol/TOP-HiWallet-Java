package com.topnetwork.wallet.param.defi.problem;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.NotNull;

@Schema(title = "添加问题参数")
public class ProblemAddParam {
	
	@Schema(title = "问题排序",required = true)
	private Integer sort;
	
	@Schema(title = "问题",required = true)
	@NotNull
	private String question;
	
	@Schema(title = "问题回答",required = true)
	@NotNull
	private String answer;

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
}
