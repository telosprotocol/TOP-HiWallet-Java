package com.topnetwork.wallet.result.defi.system;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

public class UserDetailResult extends UserPageResult {

	@Schema(title="绑定的角色列表")
	private List<RoleResult> bindingRoles;

	public List<RoleResult> getBindingRoles() {
		return bindingRoles;
	}

	public void setBindingRoles(List<RoleResult> bindingRoles) {
		this.bindingRoles = bindingRoles;
	}
	
}
