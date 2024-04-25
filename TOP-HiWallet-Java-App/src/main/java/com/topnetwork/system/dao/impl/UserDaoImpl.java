package com.topnetwork.system.dao.impl;

import org.springframework.stereotype.Repository;

import com.topnetwork.system.dao.UserDao;
import com.topnetwork.system.entity.User;

import com.base.core.framework.sql.dao.impl.SqlBaseDaoImpl;

@Repository("userDao")
public class UserDaoImpl extends SqlBaseDaoImpl<User, Long> implements UserDao {

    public UserDaoImpl() {
        super(User.class);
    }

}
