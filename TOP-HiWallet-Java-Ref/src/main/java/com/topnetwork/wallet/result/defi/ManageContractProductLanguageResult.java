package com.topnetwork.wallet.result.defi;

import com.base.core.head.converter.UploadKeyConverterEditor;

import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.framework.head.converter.Base64ConverterEditor;
import com.gitee.magic.core.converter.PropertyConverter;


public class ManageContractProductLanguageResult {

	@Schema(title = "语言", required = true)
	private String language;

	@Schema(title = "名称", required = true)
	private String name;

	@Schema(title = "logo URL地址")
	@PropertyConverter(UploadKeyConverterEditor.class)
	private String logo;

	@Schema(title = "标签逗号分割",required = true)
	private String tags;

	@Schema(title = "产品简介")
	@PropertyConverter(Base64ConverterEditor.class)
	private String introduce;

	@Schema(title = "产品详情")
	@PropertyConverter(Base64ConverterEditor.class)
	private String description;

	public ManageContractProductLanguageResult(String language, String name, String logo, String tags, String introduce, String description) {
		this.language = language;
		this.name = name;
		this.logo = logo;
		this.tags = tags;
		this.introduce = introduce;
		this.description = description;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
