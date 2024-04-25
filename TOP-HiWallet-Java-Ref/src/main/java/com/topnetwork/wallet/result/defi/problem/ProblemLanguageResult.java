package com.topnetwork.wallet.result.defi.problem;

import com.topnetwork.wallet.common.enums.LanguageEnum;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(title = "问题返回参数")
public class ProblemLanguageResult{
	
	@Schema(title = "问题排序",required = true)
	private Integer sort;
	
	@Schema(title = "问题",required = true)
	private String question;
	
	@Schema(title = "问题回答",required = true)
	private String answer;
	
	@Schema(title = "语言",required = true)
	private LanguageEnum language;
	
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

	public LanguageEnum getLanguage() {
		return language;
	}

	public void setLanguage(LanguageEnum language) {
		this.language = language;
	}

}
