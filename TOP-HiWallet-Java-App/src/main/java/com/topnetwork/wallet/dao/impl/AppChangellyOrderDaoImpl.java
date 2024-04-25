package com.topnetwork.wallet.dao.impl;

import org.springframework.stereotype.Repository;
import com.base.core.framework.sql.dao.impl.SqlBaseDaoImpl;

import com.topnetwork.wallet.dao.AppChangellyOrderDao;
import com.topnetwork.wallet.entity.AppChangellyOrder;

@Repository("appChangellyOrderDao")
public class AppChangellyOrderDaoImpl extends SqlBaseDaoImpl<AppChangellyOrder,Long>implements AppChangellyOrderDao {

	public AppChangellyOrderDaoImpl() {
		super(AppChangellyOrder.class);
	}

}
