package com.topnetwork.wallet.result.wallet.verify;

import io.swagger.v3.oas.annotations.media.Schema;

public class SecurityMarkImageResult {
    
    @Schema(title = "安全Code", required = true)
    private String code;

    @Schema(title = "底图Base64格式", required = true)
    private String orgImage;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getOrgImage() {
		return orgImage;
	}

	public void setOrgImage(String orgImage) {
		this.orgImage = orgImage;
	}

}
