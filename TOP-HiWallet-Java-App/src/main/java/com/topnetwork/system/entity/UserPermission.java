package com.topnetwork.system.entity;

import com.base.core.framework.sql.entity.Base;
import com.gitee.magic.jdbc.persistence.annotation.Entity;
import com.gitee.magic.jdbc.persistence.annotation.Table;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.ColumnDef;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.Indexes;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.TableDef;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.indexes.Normal;

@Entity("userPermission")
@Table("sys_user_permission")
@TableDef(comment = "用户权限关系")
public class UserPermission extends Base {

    private static final long serialVersionUID = 1L;

    public UserPermission() {
    }

    @ColumnDef(indexes = @Indexes(normal = @Normal), comment = "用户ID")
    private Long userId;

    @ColumnDef(indexes = @Indexes(normal = @Normal), comment = "权限ID")
    private Long permissionId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

}
