package com.topnetwork.wallet.param.system;

import java.util.List;

import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.common.enums.RoleEnum;

import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Enum;
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

    @Schema(title = "账号", required = true)
    @NotNull(CodeRes.CODE_11009)
    @Length(max = 32, message = CodeRes.CODE_11009)
    private String userName;

    @Schema(title = "姓名", required = true)
    @NotNull(CodeRes.CODE_11008)
    @Length(max = 32, message = CodeRes.CODE_11008)
    private String name;

    @Schema(title = "密码MD5值", required = true)
    @NotNull(CodeRes.CODE_11016)
    @Format(type = FormatType.MD5, message = CodeRes.CODE_11016)
    private String password;

    @Schema(title = "手机号码", required = true)
    @NotNull(CodeRes.CODE_11013)
    @Format(type = FormatType.MOBILE, message = CodeRes.CODE_11013)
    private String mobile;

    @Schema(title = "角色", required = true)
    @NotNull(CodeRes.CODE_11010)
    @Enum(CodeRes.CODE_11010)
    private RoleEnum role;

    @Schema(title = "状态", required = true)
    @NotNull(CodeRes.CODE_11012)
    @Format(type = FormatType.BOOLEAN, message = CodeRes.CODE_11012)
    private Boolean disable;

    @Schema(title = "权限ID列表", required = true)
    @NotNull(CodeRes.CODE_11011)
    @Format(type = FormatType.JSONArrayLong, message = CodeRes.CODE_11011)
    private List<Long> permissionIds;

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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Boolean getDisable() {
        return disable;
    }

    public void setDisable(Boolean disable) {
        this.disable = disable;
    }

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }

    public List<Long> getPermissionIds() {
        return permissionIds;
    }

    public void setPermissionIds(List<Long> permissionIds) {
        this.permissionIds = permissionIds;
    }

}
