package com.topnetwork.wallet.dao.impl;

import org.springframework.stereotype.Repository;
import com.base.core.framework.sql.dao.impl.SqlBaseDaoImpl;

import com.topnetwork.wallet.dao.HomeAppDao;
import com.topnetwork.wallet.entity.HomeApp;

@Repository("homeAppDao")
public class HomeAppDaoImpl extends SqlBaseDaoImpl<HomeApp,Long>implements HomeAppDao {

	public HomeAppDaoImpl() {
		super(HomeApp.class);
	}

}
