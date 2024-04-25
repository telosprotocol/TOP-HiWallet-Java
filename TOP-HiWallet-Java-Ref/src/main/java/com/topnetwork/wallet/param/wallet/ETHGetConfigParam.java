package com.topnetwork.wallet.param.wallet;

import io.swagger.v3.oas.annotations.media.Schema;

public class ETHGetConfigParam {

    @Schema(title = "是否代币", required = false)
    private Boolean token;

	public Boolean getToken() {
		return token;
	}

	public void setToken(Boolean token) {
		this.token = token;
	}
    
}
