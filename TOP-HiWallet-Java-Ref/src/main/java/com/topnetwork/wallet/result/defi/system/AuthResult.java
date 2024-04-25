package com.topnetwork.wallet.result.defi.system;

import io.swagger.v3.oas.annotations.media.Schema;

public class AuthResult {

    @Schema(title="授权ID")
	private Long authId;

    @Schema(title="接口名称")
	private String name;

    @Schema(title="接口地址")
	private String action;

    @Schema(title="请求方法")
	private String method;

    @Schema(title="授权值")
	private Integer value;

	public Long getAuthId() {
		return authId;
	}

	public void setAuthId(Long authId) {
		this.authId = authId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

}
