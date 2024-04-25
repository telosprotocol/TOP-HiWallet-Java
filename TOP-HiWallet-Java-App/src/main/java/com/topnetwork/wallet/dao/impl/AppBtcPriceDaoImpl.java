package com.topnetwork.wallet.dao.impl;

import org.springframework.stereotype.Repository;
import com.base.core.framework.sql.dao.impl.SqlBaseDaoImpl;

import com.topnetwork.wallet.dao.AppBtcPriceDao;
import com.topnetwork.wallet.entity.AppBtcPrice;

@Repository("appBtcPriceDao")
public class AppBtcPriceDaoImpl extends SqlBaseDaoImpl<AppBtcPrice,Long>implements AppBtcPriceDao {

	public AppBtcPriceDaoImpl() {
		super(AppBtcPrice.class);
	}

}
