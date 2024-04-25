package com.topnetwork.wallet.dao.impl;

import org.springframework.stereotype.Repository;
import com.base.core.framework.sql.dao.impl.SqlBaseDaoImpl;

import com.topnetwork.wallet.dao.AppDiscoverAppDao;
import com.topnetwork.wallet.entity.AppDiscoverApp;

@Repository("appDiscoverAppDao")
public class AppDiscoverAppDaoImpl extends SqlBaseDaoImpl<AppDiscoverApp,Long>implements AppDiscoverAppDao {

	public AppDiscoverAppDaoImpl() {
		super(AppDiscoverApp.class);
	}

}
