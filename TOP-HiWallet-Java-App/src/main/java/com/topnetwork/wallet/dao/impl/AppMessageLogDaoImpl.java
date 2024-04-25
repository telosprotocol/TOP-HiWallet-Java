package com.topnetwork.wallet.dao.impl;

import org.springframework.stereotype.Repository;
import com.base.core.framework.sql.dao.impl.SqlBaseDaoImpl;

import com.topnetwork.wallet.dao.AppMessageLogDao;
import com.topnetwork.wallet.entity.AppMessageLog;

@Repository("appMessageLogDao")
public class AppMessageLogDaoImpl extends SqlBaseDaoImpl<AppMessageLog,Long>implements AppMessageLogDao {

	public AppMessageLogDaoImpl() {
		super(AppMessageLog.class);
	}

}
