package com.topnetwork.wallet.dao.impl;

import org.springframework.stereotype.Repository;
import com.base.core.framework.sql.dao.impl.SqlBaseDaoImpl;

import com.topnetwork.wallet.dao.AppUserBalanceDao;
import com.topnetwork.wallet.entity.AppUserBalance;

@Repository("appUserBalanceDao")
public class AppUserBalanceDaoImpl extends SqlBaseDaoImpl<AppUserBalance,Long>implements AppUserBalanceDao {

	public AppUserBalanceDaoImpl() {
		super(AppUserBalance.class);
	}

}
