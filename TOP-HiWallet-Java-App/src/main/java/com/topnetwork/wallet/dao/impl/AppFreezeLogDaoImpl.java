package com.topnetwork.wallet.dao.impl;

import org.springframework.stereotype.Repository;
import com.base.core.framework.sql.dao.impl.SqlBaseDaoImpl;

import com.topnetwork.wallet.dao.AppFreezeLogDao;
import com.topnetwork.wallet.entity.AppFreezeLog;

@Repository("appFreezeLogDao")
public class AppFreezeLogDaoImpl extends SqlBaseDaoImpl<AppFreezeLog,Long>implements AppFreezeLogDao {

	public AppFreezeLogDaoImpl() {
		super(AppFreezeLog.class);
	}

}
