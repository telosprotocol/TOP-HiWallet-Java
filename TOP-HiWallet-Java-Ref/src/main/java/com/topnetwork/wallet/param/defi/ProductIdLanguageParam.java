package com.topnetwork.wallet.param.defi;

import com.base.core.head.ao.IDAO;
import com.gitee.magic.core.valid.annotation.Enum;
import com.gitee.magic.core.valid.annotation.NotNull;
import com.topnetwork.wallet.common.enums.LanguageEnum;

import io.swagger.v3.oas.annotations.media.Schema;

public class ProductIdLanguageParam extends IDAO{
	
	@Schema(title = "语言")
	@NotNull
	@Enum
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
