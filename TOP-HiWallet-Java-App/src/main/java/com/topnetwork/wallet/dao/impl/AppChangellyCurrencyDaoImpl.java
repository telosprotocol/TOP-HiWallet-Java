package com.topnetwork.wallet.dao.impl;

import org.springframework.stereotype.Repository;
import com.base.core.framework.sql.dao.impl.SqlBaseDaoImpl;

import com.topnetwork.wallet.dao.AppChangellyCurrencyDao;
import com.topnetwork.wallet.entity.AppChangellyCurrency;

@Repository("appChangellyCurrencyDao")
public class AppChangellyCurrencyDaoImpl extends SqlBaseDaoImpl<AppChangellyCurrency,Long>implements AppChangellyCurrencyDao {

	public AppChangellyCurrencyDaoImpl() {
		super(AppChangellyCurrency.class);
	}

}
