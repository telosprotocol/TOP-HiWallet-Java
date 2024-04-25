package com.topnetwork.wallet.dao.impl;

import org.springframework.stereotype.Repository;
import com.base.core.framework.sql.dao.impl.SqlBaseDaoImpl;

import com.topnetwork.wallet.dao.AppCoinFeeDao;
import com.topnetwork.wallet.entity.AppCoinFee;

@Repository("appCoinFeeDao")
public class AppCoinFeeDaoImpl extends SqlBaseDaoImpl<AppCoinFee,Long>implements AppCoinFeeDao {

	public AppCoinFeeDaoImpl() {
		super(AppCoinFee.class);
	}

}
