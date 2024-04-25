package com.topnetwork.wallet.dao.impl;

import org.springframework.stereotype.Repository;
import com.base.core.framework.sql.dao.impl.SqlBaseDaoImpl;

import com.topnetwork.wallet.dao.AppDiscoverBannerNewI18nDao;
import com.topnetwork.wallet.entity.AppDiscoverBannerNewI18n;

@Repository("appDiscoverBannerNewI18nDao")
public class AppDiscoverBannerNewI18nDaoImpl extends SqlBaseDaoImpl<AppDiscoverBannerNewI18n,Long>implements AppDiscoverBannerNewI18nDao {

	public AppDiscoverBannerNewI18nDaoImpl() {
		super(AppDiscoverBannerNewI18n.class);
	}

}
