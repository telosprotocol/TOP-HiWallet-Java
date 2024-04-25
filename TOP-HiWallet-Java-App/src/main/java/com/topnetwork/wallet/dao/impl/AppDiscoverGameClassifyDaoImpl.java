package com.topnetwork.wallet.dao.impl;

import org.springframework.stereotype.Repository;
import com.base.core.framework.sql.dao.impl.SqlBaseDaoImpl;

import com.topnetwork.wallet.dao.AppDiscoverGameClassifyDao;
import com.topnetwork.wallet.entity.AppDiscoverGameClassify;

@Repository("appDiscoverGameClassifyDao")
public class AppDiscoverGameClassifyDaoImpl extends SqlBaseDaoImpl<AppDiscoverGameClassify,Long>implements AppDiscoverGameClassifyDao {

	public AppDiscoverGameClassifyDaoImpl() {
		super(AppDiscoverGameClassify.class);
	}

}
