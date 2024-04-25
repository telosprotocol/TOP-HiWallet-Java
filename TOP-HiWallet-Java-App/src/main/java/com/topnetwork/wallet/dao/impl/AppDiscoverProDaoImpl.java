package com.topnetwork.wallet.dao.impl;

import org.springframework.stereotype.Repository;
import com.base.core.framework.sql.dao.impl.SqlBaseDaoImpl;

import com.topnetwork.wallet.dao.AppDiscoverProDao;
import com.topnetwork.wallet.entity.AppDiscoverPro;

@Repository("appDiscoverProDao")
public class AppDiscoverProDaoImpl extends SqlBaseDaoImpl<AppDiscoverPro,Long>implements AppDiscoverProDao {

	public AppDiscoverProDaoImpl() {
		super(AppDiscoverPro.class);
	}

}
