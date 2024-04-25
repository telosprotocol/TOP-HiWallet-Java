package com.topnetwork.system.service;

import com.base.core.framework.sql.service.SqlBaseService;

import java.util.List;

import com.topnetwork.system.entity.UserPermission;

public interface UserPermissionService extends SqlBaseService<UserPermission, Long> {

    /**
     * 更新用户权限映射关系
     *
     * @param userId
     * @param permissionIds
     */
    void updatePermissionMapping(Long userId, List<Long> permissionIds);

}
