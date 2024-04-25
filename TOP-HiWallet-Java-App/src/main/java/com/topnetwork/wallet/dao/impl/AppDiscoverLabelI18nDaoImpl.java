package com.topnetwork.wallet.dao.impl;

import org.springframework.stereotype.Repository;
import com.base.core.framework.sql.dao.impl.SqlBaseDaoImpl;

import com.topnetwork.wallet.dao.AppDiscoverLabelI18nDao;
import com.topnetwork.wallet.entity.AppDiscoverLabelI18n;

@Repository("appDiscoverLabelI18nDao")
public class AppDiscoverLabelI18nDaoImpl extends SqlBaseDaoImpl<AppDiscoverLabelI18n,Long>implements AppDiscoverLabelI18nDao {

	public AppDiscoverLabelI18nDaoImpl() {
		super(AppDiscoverLabelI18n.class);
	}

}
