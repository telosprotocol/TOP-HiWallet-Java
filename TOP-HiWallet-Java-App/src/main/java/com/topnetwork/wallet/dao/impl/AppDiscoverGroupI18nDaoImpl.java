package com.topnetwork.wallet.dao.impl;

import org.springframework.stereotype.Repository;
import com.base.core.framework.sql.dao.impl.SqlBaseDaoImpl;

import com.topnetwork.wallet.dao.AppDiscoverGroupI18nDao;
import com.topnetwork.wallet.entity.AppDiscoverGroupI18n;

@Repository("appDiscoverGroupI18nDao")
public class AppDiscoverGroupI18nDaoImpl extends SqlBaseDaoImpl<AppDiscoverGroupI18n,Long>implements AppDiscoverGroupI18nDao {

	public AppDiscoverGroupI18nDaoImpl() {
		super(AppDiscoverGroupI18n.class);
	}

}
