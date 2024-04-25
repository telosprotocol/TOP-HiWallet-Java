package com.topnetwork.wallet.dao.impl;

import org.springframework.stereotype.Repository;
import com.base.core.framework.sql.dao.impl.SqlBaseDaoImpl;

import com.topnetwork.wallet.dao.AppVersionDao;
import com.topnetwork.wallet.entity.AppVersion;

@Repository("appVersionDao")
public class AppVersionDaoImpl extends SqlBaseDaoImpl<AppVersion,Long>implements AppVersionDao {

	public AppVersionDaoImpl() {
		super(AppVersion.class);
	}

}
