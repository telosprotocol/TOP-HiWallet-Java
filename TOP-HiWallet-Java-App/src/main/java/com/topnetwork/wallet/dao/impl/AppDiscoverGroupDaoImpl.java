package com.topnetwork.wallet.dao.impl;

import org.springframework.stereotype.Repository;
import com.base.core.framework.sql.dao.impl.SqlBaseDaoImpl;

import com.topnetwork.wallet.dao.AppDiscoverGroupDao;
import com.topnetwork.wallet.entity.AppDiscoverGroup;

@Repository("appDiscoverGroupDao")
public class AppDiscoverGroupDaoImpl extends SqlBaseDaoImpl<AppDiscoverGroup,Long>implements AppDiscoverGroupDao {

	public AppDiscoverGroupDaoImpl() {
		super(AppDiscoverGroup.class);
	}

}
