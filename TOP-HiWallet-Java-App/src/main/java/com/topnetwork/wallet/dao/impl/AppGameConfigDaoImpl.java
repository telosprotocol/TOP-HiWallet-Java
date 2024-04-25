package com.topnetwork.wallet.dao.impl;

import org.springframework.stereotype.Repository;
import com.base.core.framework.sql.dao.impl.SqlBaseDaoImpl;

import com.topnetwork.wallet.dao.AppGameConfigDao;
import com.topnetwork.wallet.entity.AppGameConfig;

@Repository("appGameConfigDao")
public class AppGameConfigDaoImpl extends SqlBaseDaoImpl<AppGameConfig,Long>implements AppGameConfigDao {

	public AppGameConfigDaoImpl() {
		super(AppGameConfig.class);
	}

}
