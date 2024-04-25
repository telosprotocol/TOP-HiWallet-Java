package com.topnetwork.wallet.dao.impl;

import org.springframework.stereotype.Repository;
import com.base.core.framework.sql.dao.impl.SqlBaseDaoImpl;

import com.topnetwork.wallet.dao.AppCoinConfigDao;
import com.topnetwork.wallet.entity.AppCoinConfig;

@Repository("appCoinConfigDao")
public class AppCoinConfigDaoImpl extends SqlBaseDaoImpl<AppCoinConfig,Long>implements AppCoinConfigDao {

	public AppCoinConfigDaoImpl() {
		super(AppCoinConfig.class);
	}

}
