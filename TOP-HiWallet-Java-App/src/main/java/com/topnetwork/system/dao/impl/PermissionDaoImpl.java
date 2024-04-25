package com.topnetwork.system.dao.impl;

import org.springframework.stereotype.Repository;

import com.topnetwork.system.dao.PermissionDao;
import com.topnetwork.system.entity.Permission;

import com.base.core.framework.sql.dao.impl.SqlBaseDaoImpl;

@Repository("permissionDao")
public class PermissionDaoImpl extends SqlBaseDaoImpl<Permission, Long> implements PermissionDao {

    public PermissionDaoImpl() {
        super(Permission.class);
    }

}
