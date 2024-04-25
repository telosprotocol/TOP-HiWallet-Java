package com.topnetwork.wallet.param.defi.system;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Format;
import com.gitee.magic.core.valid.annotation.Format.FormatType;
import com.gitee.magic.core.valid.annotation.Length;
import com.gitee.magic.core.valid.annotation.NotNull;
import com.gitee.magic.core.valid.annotation.number.LongValid;

public class RoleParam {

    @Schema(title="角色ID")
    @LongValid
    private Long roleId;

    @Schema(title="名称")
    @NotNull
    @Length(max=32)
    private String name;

    @Schema(title="授权列表")
    @Format(type=FormatType.JSONArrayLong)
    private List<Long> authIds;

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

	public List<Long> getAuthIds() {
		return authIds;
	}

	public void setAuthIds(List<Long> authIds) {
		this.authIds = authIds;
	}
	
}
