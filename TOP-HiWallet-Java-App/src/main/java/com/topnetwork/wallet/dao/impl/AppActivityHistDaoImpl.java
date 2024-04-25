package com.topnetwork.wallet.dao.impl;

import org.springframework.stereotype.Repository;
import com.base.core.framework.sql.dao.impl.SqlBaseDaoImpl;

import com.topnetwork.wallet.dao.AppActivityHistDao;
import com.topnetwork.wallet.entity.AppGameHist;

@Repository("appActivityHistDao")
public class AppActivityHistDaoImpl extends SqlBaseDaoImpl<AppGameHist,Long>implements AppActivityHistDao {

	public AppActivityHistDaoImpl() {
		super(AppGameHist.class);
	}

}
