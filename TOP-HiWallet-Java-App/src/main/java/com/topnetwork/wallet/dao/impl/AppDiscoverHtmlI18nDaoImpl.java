package com.topnetwork.wallet.dao.impl;

import org.springframework.stereotype.Repository;
import com.base.core.framework.sql.dao.impl.SqlBaseDaoImpl;

import com.topnetwork.wallet.dao.AppDiscoverHtmlI18nDao;
import com.topnetwork.wallet.entity.AppDiscoverHtmlI18n;

@Repository("appDiscoverHtmlI18nDao")
public class AppDiscoverHtmlI18nDaoImpl extends SqlBaseDaoImpl<AppDiscoverHtmlI18n,Long>implements AppDiscoverHtmlI18nDao {

	public AppDiscoverHtmlI18nDaoImpl() {
		super(AppDiscoverHtmlI18n.class);
	}

}
