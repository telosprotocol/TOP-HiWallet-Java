package com.topnetwork.wallet.dao.impl;

import org.springframework.stereotype.Repository;
import com.base.core.framework.sql.dao.impl.SqlBaseDaoImpl;

import com.topnetwork.wallet.dao.AppResourceDao;
import com.topnetwork.wallet.entity.AppResource;

@Repository("appResourceDao")
public class AppResourceDaoImpl extends SqlBaseDaoImpl<AppResource,Long>implements AppResourceDao {

	public AppResourceDaoImpl() {
		super(AppResource.class);
	}

}
