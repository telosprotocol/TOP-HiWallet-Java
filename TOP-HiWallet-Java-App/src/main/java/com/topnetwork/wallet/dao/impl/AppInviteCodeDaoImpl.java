package com.topnetwork.wallet.dao.impl;

import org.springframework.stereotype.Repository;
import com.base.core.framework.sql.dao.impl.SqlBaseDaoImpl;

import com.topnetwork.wallet.dao.AppInviteCodeDao;
import com.topnetwork.wallet.entity.AppInviteCode;

@Repository("appInviteCodeDao")
public class AppInviteCodeDaoImpl extends SqlBaseDaoImpl<AppInviteCode,Long>implements AppInviteCodeDao {

	public AppInviteCodeDaoImpl() {
		super(AppInviteCode.class);
	}

}
