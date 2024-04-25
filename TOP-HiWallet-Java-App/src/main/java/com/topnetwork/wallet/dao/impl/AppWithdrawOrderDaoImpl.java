package com.topnetwork.wallet.dao.impl;

import org.springframework.stereotype.Repository;
import com.base.core.framework.sql.dao.impl.SqlBaseDaoImpl;

import com.topnetwork.wallet.dao.AppWithdrawOrderDao;
import com.topnetwork.wallet.entity.AppWithdrawOrder;

@Repository("appWithdrawOrderDao")
public class AppWithdrawOrderDaoImpl extends SqlBaseDaoImpl<AppWithdrawOrder,Long>implements AppWithdrawOrderDao {

	public AppWithdrawOrderDaoImpl() {
		super(AppWithdrawOrder.class);
	}

}
