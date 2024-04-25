package com.topnetwork.wallet.dao.impl;

import org.springframework.stereotype.Repository;
import com.base.core.framework.sql.dao.impl.SqlBaseDaoImpl;

import com.topnetwork.wallet.dao.AppDiscoverDappDao;
import com.topnetwork.wallet.entity.AppDiscoverDapp;

@Repository("appDiscoverDappDao")
public class AppDiscoverDappDaoImpl extends SqlBaseDaoImpl<AppDiscoverDapp,Long>implements AppDiscoverDappDao {

	public AppDiscoverDappDaoImpl() {
		super(AppDiscoverDapp.class);
	}

}
