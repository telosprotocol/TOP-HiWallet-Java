package com.topnetwork.wallet.result.defi.system;

import io.swagger.v3.oas.annotations.media.Schema;

public class RoleResult {

    @Schema(title="角色ID")
    private Long roleId;

    @Schema(title="名称")
    private String name;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
