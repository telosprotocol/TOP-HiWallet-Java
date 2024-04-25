package com.topnetwork.wallet.param.defi;

import com.base.core.head.ao.PageAO;
import com.topnetwork.wallet.common.enums.LanguageEnum;

import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Enum;
import com.gitee.magic.core.valid.annotation.Format;
import com.gitee.magic.core.valid.annotation.Format.FormatType;
import com.gitee.magic.core.valid.annotation.NotNull;

public class ProductPageParam extends PageAO{
	
	@Schema(title = "语言")
	@NotNull
	@Enum
	private LanguageEnum language;

	@Schema(title =  "发布状态")
	@Format(type = FormatType.BOOLEAN)
	private Boolean publish;

	public LanguageEnum getLanguage() {
		if(!language.equals(LanguageEnum.EN) && !language.equals(LanguageEnum.CN)) {
			return LanguageEnum.EN;
		}
		return language;
	}

	public void setLanguage(LanguageEnum language) {
		this.language = language;
	}

	public Boolean getPublish() {
		return publish;
	}

	public void setPublish(Boolean publish) {
		this.publish = publish;
	}

}
