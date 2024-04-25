package com.topnetwork.wallet.param.defi;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.NotEmpty;
import com.gitee.magic.core.valid.annotation.NotNull;
import com.gitee.magic.core.valid.annotation.number.LongValid;

public class ProductLanguageByAddParam {
	
	@Schema(title =  "产品ID")
	@NotNull
	@LongValid
	private Long productId;
	
	@Schema(title =  "产品国际化语言数据")
	@NotNull
	@NotEmpty
	private List<ProductLanguageUpdateParam> languages;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public List<ProductLanguageUpdateParam> getLanguages() {
		return languages;
	}

	public void setLanguages(List<ProductLanguageUpdateParam> languages) {
		this.languages = languages;
	}
	
}
