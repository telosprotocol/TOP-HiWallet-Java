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
public class UserModifyParam extends UserDetailParam {

    @Schema(title = "姓名", required = true)
    @NotNull(CodeRes.CODE_11008)
    @Length(max = 10, message = CodeRes.CODE_11008)
    private String name;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }

    public Boolean getDisable() {
        return disable;
    }

    public void setDisable(Boolean disable) {
        this.disable = disable;
    }

    public List<Long> getPermissionIds() {
        return permissionIds;
    }

    public void setPermissionIds(List<Long> permissionIds) {
        this.permissionIds = permissionIds;
    }

}
