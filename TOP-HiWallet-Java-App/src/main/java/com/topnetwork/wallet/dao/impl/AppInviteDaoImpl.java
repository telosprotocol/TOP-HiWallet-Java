package com.topnetwork.wallet.dao.impl;

import org.springframework.stereotype.Repository;
import com.base.core.framework.sql.dao.impl.SqlBaseDaoImpl;

import com.topnetwork.wallet.dao.AppInviteDao;
import com.topnetwork.wallet.entity.AppInvite;

@Repository("appInviteDao")
public class AppInviteDaoImpl extends SqlBaseDaoImpl<AppInvite,Long>implements AppInviteDao {

	public AppInviteDaoImpl() {
		super(AppInvite.class);
	}

}
