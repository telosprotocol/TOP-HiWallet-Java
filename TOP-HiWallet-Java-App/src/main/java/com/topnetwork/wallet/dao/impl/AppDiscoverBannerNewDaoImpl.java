package com.topnetwork.wallet.dao.impl;

import org.springframework.stereotype.Repository;
import com.base.core.framework.sql.dao.impl.SqlBaseDaoImpl;

import com.topnetwork.wallet.dao.AppDiscoverBannerNewDao;
import com.topnetwork.wallet.entity.AppDiscoverBannerNew;

@Repository("appDiscoverBannerNewDao")
public class AppDiscoverBannerNewDaoImpl extends SqlBaseDaoImpl<AppDiscoverBannerNew,Long>implements AppDiscoverBannerNewDao {

	public AppDiscoverBannerNewDaoImpl() {
		super(AppDiscoverBannerNew.class);
	}

}
