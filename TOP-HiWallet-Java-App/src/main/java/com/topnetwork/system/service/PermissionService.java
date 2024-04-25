package com.topnetwork.system.service;

import java.util.List;

import com.topnetwork.system.entity.Permission;
import com.topnetwork.wallet.result.common.system.PermissionResult;

import com.base.core.framework.sql.service.SqlBaseService;

public interface PermissionService extends SqlBaseService<Permission, Long> {

    /**
     * 授权检测
     *
     * @param userId
     * @param url
     */
    void authorityCheck(Long userId, String url);

    /**
     * 获取所有权限
     *
     * @param http
     * @return
     */
    List<PermissionResult> getAllPermissionBusiness();

    /**
     * 根据权限ids获取权限列表
     *
     * @param ids
     * @return
     */
    List<Permission> getPermissionInIds(List<Long> ids);

    /**
     * 根据用户ID获取权限列表
     *
     * @param userId
     * @return
     */
    List<PermissionResult> getPermissionWithUserId(Long userId);

}
