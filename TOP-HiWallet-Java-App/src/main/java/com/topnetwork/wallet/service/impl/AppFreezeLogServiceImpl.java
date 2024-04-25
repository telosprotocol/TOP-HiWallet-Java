package com.topnetwork.wallet.service.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.base.core.framework.sql.service.impl.SqlBaseServiceImpl;
import com.topnetwork.wallet.dao.AppFreezeLogDao;
import com.topnetwork.wallet.entity.AppFreezeLog;
import com.topnetwork.wallet.service.AppFreezeLogService;

@Service("appFreezeLogService")
public class AppFreezeLogServiceImpl extends SqlBaseServiceImpl<AppFreezeLog,Long> 
implements AppFreezeLogService {

	@SuppressWarnings("unused")
	private AppFreezeLogDao appFreezeLogDao;
	
	public AppFreezeLogServiceImpl(@Qualifier("appFreezeLogDao")AppFreezeLogDao appFreezeLogDao) {
		super(appFreezeLogDao);
		this.appFreezeLogDao=appFreezeLogDao;
	}

}
