package com.topnetwork.wallet.result.defi;

import io.swagger.v3.oas.annotations.media.Schema;

public class DefiLoginResult {

	@Schema(title = "访问ID", required = true)
    private String accessid;

    @Schema(title = "访问KEY", required = true)
    private String accesskey;

	public String getAccessid() {
		return accessid;
	}

	public void setAccessid(String accessid) {
		this.accessid = accessid;
	}

	public String getAccesskey() {
		return accesskey;
	}

	public void setAccesskey(String accesskey) {
		this.accesskey = accesskey;
	}
    
}
