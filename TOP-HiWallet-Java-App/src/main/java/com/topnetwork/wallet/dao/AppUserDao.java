package com.topnetwork.wallet.dao;

import com.base.core.framework.sql.dao.SqlBaseDao;
import com.topnetwork.wallet.entity.AppUser;

public interface AppUserDao extends SqlBaseDao<AppUser,Long> {

    Long saveReturnId(AppUser appUser);
}
