package com.topnetwork.wallet.dao.impl;

import org.springframework.stereotype.Repository;
import com.base.core.framework.sql.dao.impl.SqlBaseDaoImpl;

import com.topnetwork.wallet.dao.AppDiscoverHtmlDao;
import com.topnetwork.wallet.entity.AppDiscoverHtml;

@Repository("appDiscoverHtmlDao")
public class AppDiscoverHtmlDaoImpl extends SqlBaseDaoImpl<AppDiscoverHtml,Long>implements AppDiscoverHtmlDao {

	public AppDiscoverHtmlDaoImpl() {
		super(AppDiscoverHtml.class);
	}

}
