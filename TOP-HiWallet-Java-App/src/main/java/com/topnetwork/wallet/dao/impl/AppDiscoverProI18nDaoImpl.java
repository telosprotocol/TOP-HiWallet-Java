package com.topnetwork.wallet.dao.impl;

import org.springframework.stereotype.Repository;
import com.base.core.framework.sql.dao.impl.SqlBaseDaoImpl;

import com.topnetwork.wallet.dao.AppDiscoverProI18nDao;
import com.topnetwork.wallet.entity.AppDiscoverProI18n;

@Repository("appDiscoverProI18nDao")
public class AppDiscoverProI18nDaoImpl extends SqlBaseDaoImpl<AppDiscoverProI18n,Long>implements AppDiscoverProI18nDao {

	public AppDiscoverProI18nDaoImpl() {
		super(AppDiscoverProI18n.class);
	}

}
