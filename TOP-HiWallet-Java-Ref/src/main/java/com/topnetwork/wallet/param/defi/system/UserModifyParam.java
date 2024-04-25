package com.topnetwork.wallet.param.defi.system;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Format;
import com.gitee.magic.core.valid.annotation.Format.FormatType;
import com.gitee.magic.core.valid.annotation.Length;
import com.gitee.magic.core.valid.annotation.NotNull;

/**
 * 用户创建更新
 *
 * @author Start
 */
public class UserModifyParam extends UserDetailParam {

    @Schema(title="姓名")
    @NotNull
    @Length(max = 10)
    private String name;

    @Schema(title="角色ID")
    @NotNull
    @Format(type = FormatType.JSONArray)
    private List<Long> roleId;

    @Schema(title="状态")
    @NotNull
    @Format(type = FormatType.BOOLEAN)
    private Boolean disable;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public List<Long> getRoleId() {
		return roleId;
	}

	public void setRoleId(List<Long> roleId) {
		this.roleId = roleId;
	}

	public Boolean getDisable() {
        return disable;
    }

    public void setDisable(Boolean disable) {
        this.disable = disable;
    }

}
