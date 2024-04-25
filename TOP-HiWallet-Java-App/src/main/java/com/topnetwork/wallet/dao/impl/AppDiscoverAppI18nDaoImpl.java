package com.topnetwork.wallet.dao.impl;

import org.springframework.stereotype.Repository;
import com.base.core.framework.sql.dao.impl.SqlBaseDaoImpl;

import com.topnetwork.wallet.dao.AppDiscoverAppI18nDao;
import com.topnetwork.wallet.entity.AppDiscoverAppI18n;

@Repository("appDiscoverAppI18nDao")
public class AppDiscoverAppI18nDaoImpl extends SqlBaseDaoImpl<AppDiscoverAppI18n,Long>implements AppDiscoverAppI18nDao {

	public AppDiscoverAppI18nDaoImpl() {
		super(AppDiscoverAppI18n.class);
	}

}
