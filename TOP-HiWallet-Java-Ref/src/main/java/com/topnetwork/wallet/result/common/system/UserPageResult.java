package com.topnetwork.wallet.result.common.system;

import java.util.Date;

import com.topnetwork.wallet.common.enums.RoleEnum;

import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.framework.head.converter.DateTimeConverterEditor;
import com.gitee.magic.core.converter.PropertyConverter;

/**
 * 用户管理页
 *
 * @author Start
 */
public class UserPageResult {

    @Schema(title = "用户ID", required = true)
    private Long userId;

    @Schema(title = "账号", required = true)
    private String userName;

    @Schema(title = "姓名", required = true)
    private String name;

    @Schema(title = "手机号码", required = true)
    private String mobile;

    @Schema(title = "角色", required = true)
    private RoleEnum role;

    @Schema(title = "创建时间", required = true)
    @PropertyConverter(DateTimeConverterEditor.class)
    private Date createTime;

    @Schema(title = "是否禁用", required = true)
    private Boolean disable;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Boolean getDisable() {
        return disable;
    }

    public void setDisable(Boolean disable) {
        this.disable = disable;
    }

}
