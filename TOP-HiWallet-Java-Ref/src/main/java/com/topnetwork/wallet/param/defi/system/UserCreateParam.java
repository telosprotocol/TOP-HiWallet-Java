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
public class UserCreateParam {

    @Schema(title="账号")
    @NotNull
    @Length(max = 32)
    private String userName;

    @Schema(title="姓名")
    @NotNull
    @Length(max = 32)
    private String name;

    @Schema(title="密码")
    @NotNull
    @Format(type = FormatType.MD5)
    private String password;

    @Schema(title="角色ID")
    @NotNull
    @Format(type = FormatType.JSONArray)
    private List<Long> roleId;

    @Schema(title="状态")
    @NotNull
    @Format(type = FormatType.BOOLEAN)
    private Boolean disable;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getDisable() {
        return disable;
    }

    public void setDisable(Boolean disable) {
        this.disable = disable;
    }

	public List<Long> getRoleId() {
		return roleId;
	}

	public void setRoleId(List<Long> roleId) {
		this.roleId = roleId;
	}

}
