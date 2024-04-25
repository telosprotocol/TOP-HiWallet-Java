package com.topnetwork.recharge.dao.impl;

import org.springframework.stereotype.Repository;
import com.base.core.framework.sql.dao.impl.SqlBaseDaoImpl;

import com.topnetwork.recharge.dao.AppUserRechargeDao;
import com.topnetwork.recharge.entity.AppUserRecharge;

@Repository("appUserRechargeDao")
public class AppUserRechargeDaoImpl extends SqlBaseDaoImpl<AppUserRecharge,Long>implements AppUserRechargeDao {

	public AppUserRechargeDaoImpl() {
		super(AppUserRecharge.class);
	}

}
