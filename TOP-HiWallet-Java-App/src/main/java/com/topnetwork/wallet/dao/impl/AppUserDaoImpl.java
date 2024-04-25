package com.topnetwork.wallet.dao.impl;

import org.springframework.stereotype.Repository;
import com.base.core.framework.sql.dao.impl.SqlBaseDaoImpl;

import com.topnetwork.wallet.dao.AppUserDao;
import com.topnetwork.wallet.entity.AppUser;

@Repository("appUserDao")
public class AppUserDaoImpl extends SqlBaseDaoImpl<AppUser,Long>implements AppUserDao {

	public AppUserDaoImpl() {
		super(AppUser.class);
	}

	@Override
	public Long saveReturnId(AppUser appUser) {
		Long id = getSnowflakeIdWorkerNextId();
		appUser.setId(id);
		long i = persist(appUser);
		return i > 0 ? id : null;
	}
}
