package com.topnetwork.wallet.dao.impl;

import org.springframework.stereotype.Repository;
import com.base.core.framework.sql.dao.impl.SqlBaseDaoImpl;

import com.topnetwork.wallet.dao.AppDiscoverDappI18nDao;
import com.topnetwork.wallet.entity.AppDiscoverDappI18n;

@Repository("appDiscoverDappI18nDao")
public class AppDiscoverDappI18nDaoImpl extends SqlBaseDaoImpl<AppDiscoverDappI18n,Long>implements AppDiscoverDappI18nDao {

	public AppDiscoverDappI18nDaoImpl() {
		super(AppDiscoverDappI18n.class);
	}

}
