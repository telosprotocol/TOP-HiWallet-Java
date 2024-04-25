package com.topnetwork.wallet.dao.impl;

import org.springframework.stereotype.Repository;
import com.base.core.framework.sql.dao.impl.SqlBaseDaoImpl;

import com.topnetwork.wallet.dao.AppActivityDao;
import com.topnetwork.wallet.entity.AppActivity;

@Repository("appActivityDao")
public class AppActivityDaoImpl extends SqlBaseDaoImpl<AppActivity,Long>implements AppActivityDao {

	public AppActivityDaoImpl() {
		super(AppActivity.class);
	}

}
