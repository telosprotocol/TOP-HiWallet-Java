package com.topnetwork.system.entity;

import java.util.Date;

import com.topnetwork.wallet.common.enums.RoleEnum;

import com.base.core.framework.sql.entity.Base;
import com.gitee.magic.jdbc.persistence.annotation.Entity;
import com.gitee.magic.jdbc.persistence.annotation.Table;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.ColumnDef;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.Indexes;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.TableDef;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.indexes.Unique;

@Entity("user")
@Table("sys_user")
@TableDef(comment = "用户")
public class User extends Base {

    private static final long serialVersionUID = 1L;

    public User() {
    }

    @ColumnDef(indexes = @Indexes(unique = @Unique), length = 32, comment = "用户名")
    private String userName;

    @ColumnDef(length = 32, comment = "密码MD5")
    private String password;

    @ColumnDef(indexes = @Indexes(unique = @Unique), length = 32, comment = "手机号码")
    private String mobile;

    @ColumnDef(length = 32, comment = "姓名")
    private String name;

    @ColumnDef(comment = "角色")
    private RoleEnum role;

    @ColumnDef(comment = "创建时间")
    private Date createTime;

    @ColumnDef(comment = "是否禁用")
    private Boolean disable;

    @ColumnDef(comment = "是否删除")
    private Boolean del;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public Boolean getDel() {
        return del;
    }

    public void setDel(Boolean del) {
        this.del = del;
    }

}
