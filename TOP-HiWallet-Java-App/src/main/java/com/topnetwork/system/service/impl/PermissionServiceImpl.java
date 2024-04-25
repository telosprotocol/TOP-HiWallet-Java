package com.topnetwork.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.topnetwork.system.dao.PermissionDao;
import com.topnetwork.system.entity.Permission;
import com.topnetwork.system.service.PermissionService;
import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.result.common.system.PermissionResult;

import com.gitee.magic.framework.head.exception.BusinessException;
import com.base.core.framework.sql.service.impl.SqlBaseServiceImpl;
import com.gitee.magic.jdbc.persistence.source.jdbc.sqlplus.conditions.query.QueryWrapper;

@Service("permissionService")
public class PermissionServiceImpl extends SqlBaseServiceImpl<Permission, Long>
        implements PermissionService {

    private PermissionDao permissionDao;

    public PermissionServiceImpl(@Qualifier("permissionDao") PermissionDao permissionDao) {
        super(permissionDao);
        this.permissionDao = permissionDao;
    }

    @Override
    public void authorityCheck(Long userId, String url) {
//        List<PermissionResult> permissions = getPermissionWithUserId(userId);
//        for (PermissionResult p : permissions) {
//            if (p.getUrl().equals(url)) {
//                //校验当前权限
//                return;
//            }
//        }
//        throw new BusinessException(CodeRes.CODE_11006);
    }

    @Override
    public List<PermissionResult> getAllPermissionBusiness() {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("disable", String.valueOf(false)).orderByAsc("sort");
        List<Permission> permissions = permissionDao.queryForList(wrapper);
        if (permissions.isEmpty()) {
            throw new BusinessException(CodeRes.CODE_204);
        }
        List<PermissionResult> results = new ArrayList<>();
        for (Permission p : permissions) {
            PermissionResult permission = new PermissionResult();
            permission.setPermissionId(p.getId());
            permission.setName(p.getName());
            permission.setUrl(p.getUrl());
            permission.setModule(p.getModule());
            results.add(permission);
        }
        return results;
    }

    @Override
    public List<Permission> getPermissionInIds(List<Long> ids) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.in("id", ids);
        return permissionDao.queryForList(wrapper);
    }

    @Override
    public List<PermissionResult> getPermissionWithUserId(Long userId) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        return queryForMapMapper(PermissionResult.class, "getPermissionWithUserId", params);
    }

}
