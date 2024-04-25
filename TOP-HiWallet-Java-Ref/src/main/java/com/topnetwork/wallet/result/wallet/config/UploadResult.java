package com.topnetwork.wallet.result.wallet.config;

import io.swagger.v3.oas.annotations.media.Schema;

public class UploadResult {

    @Schema(title = "上传文件")
	private String uploadKey;

	public String getUploadKey() {
		return uploadKey;
	}

	public void setUploadKey(String uploadKey) {
		this.uploadKey = uploadKey;
	}
	
}
