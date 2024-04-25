package com.topnetwork.wallet.dao.impl;

import org.springframework.stereotype.Repository;
import com.base.core.framework.sql.dao.impl.SqlBaseDaoImpl;

import com.topnetwork.wallet.dao.AppDiscoverBannerDao;
import com.topnetwork.wallet.entity.AppDiscoverBanner;

@Repository("appDiscoverBannerDao")
public class AppDiscoverBannerDaoImpl extends SqlBaseDaoImpl<AppDiscoverBanner,Long>implements AppDiscoverBannerDao {

	public AppDiscoverBannerDaoImpl() {
		super(AppDiscoverBanner.class);
	}

}
