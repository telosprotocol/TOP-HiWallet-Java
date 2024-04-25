package com.topnetwork.wallet.param.defi;

import com.base.core.head.ao.PageAO;
import com.topnetwork.wallet.common.enums.LanguageEnum;

import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Length;
import com.gitee.magic.core.valid.annotation.NotNull;

public class DefiLanguagePage extends PageAO{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2488758509977544225L;
	@Schema(title = "语言",required = true)
	@Length
	@NotNull
    private LanguageEnum language;

	public LanguageEnum getLanguage() {
		if(!language.equals(LanguageEnum.EN) && !language.equals(LanguageEnum.CN)) {
			return LanguageEnum.EN;
		}
		return language;
	}

	public void setLanguage(LanguageEnum language) {
		this.language = language;
	}
	
}