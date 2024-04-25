package com.topnetwork.wallet.param.system;

import com.topnetwork.wallet.common.CodeRes;

import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.NotNull;

public class ConfigureParam {

    @Schema(title = "配置键", required = true)
    @NotNull(CodeRes.CODE_12021)
    private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
