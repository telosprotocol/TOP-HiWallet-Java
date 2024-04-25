package com.topnetwork.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.topnetwork.system.dao.UserPermissionDao;
import com.topnetwork.system.entity.Permission;
import com.topnetwork.system.entity.UserPermission;
import com.topnetwork.system.service.PermissionService;
import com.topnetwork.system.service.UserPermissionService;

import com.base.core.framework.sql.service.impl.SqlBaseServiceImpl;
import com.gitee.magic.jdbc.persistence.source.jdbc.sqlplus.conditions.update.DeleteWrapper;

@Service("userPermissionService")
public class UserPermissionServiceImpl extends SqlBaseServiceImpl<UserPermission, Long>
        implements UserPermissionService {

    private UserPermissionDao userPermissionDao;
    @Autowired
    private PermissionService permissionService;

    public UserPermissionServiceImpl(@Qualifier("userPermissionDao") UserPermissionDao userPermissionDao) {
        super(userPermissionDao);
        this.userPermissionDao = userPermissionDao;
    }

    @Transactional
    @Override
    public void updatePermissionMapping(Long userId, List<Long> permissionIds) {
        //删除用户已映射的权限对应关系
        DeleteWrapper wrapper = new DeleteWrapper();
        wrapper.eq("userId", userId);
        userPermissionDao.executeUpdate(wrapper);
        //重新更新权限对应关系
        List<Permission> permissions = permissionService.getPermissionInIds(permissionIds);
        List<UserPermission> ups = new ArrayList<>();
        for (Permission per : permissions) {
            UserPermission up = new UserPermission();
            up.setUserId(userId);
            up.setPermissionId(per.getId());
            ups.add(up);
        }
        saveBatch(ups);
    }

}
