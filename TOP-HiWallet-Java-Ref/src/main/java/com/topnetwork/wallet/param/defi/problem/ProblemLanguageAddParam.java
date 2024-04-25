package com.topnetwork.wallet.param.defi.problem;

import com.topnetwork.wallet.common.enums.LanguageEnum;

import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Enum;
import com.gitee.magic.core.valid.annotation.NotNull;

public class ProblemLanguageAddParam{
	
	@Schema(title = "语言",required = true)
	@NotNull
	@Enum
	private LanguageEnum language;
	
	@Schema(title = "问题",required = true)
	@NotNull
	private String question;
	
	@Schema(title = "问题回答",required = true)
	@NotNull
	private String answer;
	
	public LanguageEnum getLanguage() {
		return language;
	}

	public void setLanguage(LanguageEnum language) {
		this.language = language;
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
