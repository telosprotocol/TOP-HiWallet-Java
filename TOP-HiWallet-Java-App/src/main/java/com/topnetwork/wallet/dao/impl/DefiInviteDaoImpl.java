package com.topnetwork.wallet.dao.impl;

import org.springframework.stereotype.Repository;
import com.base.core.framework.sql.dao.impl.SqlBaseDaoImpl;

import com.topnetwork.wallet.dao.DefiInviteDao;
import com.topnetwork.wallet.entity.DefiInvite;

@Repository("defiInviteDao")
public class DefiInviteDaoImpl extends SqlBaseDaoImpl<DefiInvite,Long>implements DefiInviteDao {

	public DefiInviteDaoImpl() {
		super(DefiInvite.class);
	}

}
