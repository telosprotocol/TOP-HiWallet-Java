package com.topnetwork.wallet.dao.impl;

import org.springframework.stereotype.Repository;
import com.base.core.framework.sql.dao.impl.SqlBaseDaoImpl;

import com.topnetwork.wallet.dao.AppBehaviorConfigDao;
import com.topnetwork.wallet.entity.AppBehaviorConfig;

@Repository("appBehaviorConfigDao")
public class AppBehaviorConfigDaoImpl extends SqlBaseDaoImpl<AppBehaviorConfig,Long>implements AppBehaviorConfigDao {

	public AppBehaviorConfigDaoImpl() {
		super(AppBehaviorConfig.class);
	}

}
