package com.topnetwork.system.dao.impl;

import org.springframework.stereotype.Repository;

import com.topnetwork.system.dao.UserPermissionDao;
import com.topnetwork.system.entity.UserPermission;

import com.base.core.framework.sql.dao.impl.SqlBaseDaoImpl;

@Repository("userPermissionDao")
public class UserPermissionDaoImpl extends SqlBaseDaoImpl<UserPermission, Long> implements UserPermissionDao {

    public UserPermissionDaoImpl() {
        super(UserPermission.class);
    }

}
