package com.topnetwork.wallet.dao.impl;

import org.springframework.stereotype.Repository;
import com.base.core.framework.sql.dao.impl.SqlBaseDaoImpl;

import com.topnetwork.wallet.dao.AppDiscoverLabelApplicationDao;
import com.topnetwork.wallet.entity.AppDiscoverLabelApplication;

@Repository("appDiscoverLabelApplicationDao")
public class AppDiscoverLabelApplicationDaoImpl extends SqlBaseDaoImpl<AppDiscoverLabelApplication,Long>implements AppDiscoverLabelApplicationDao {

	public AppDiscoverLabelApplicationDaoImpl() {
		super(AppDiscoverLabelApplication.class);
	}

}
