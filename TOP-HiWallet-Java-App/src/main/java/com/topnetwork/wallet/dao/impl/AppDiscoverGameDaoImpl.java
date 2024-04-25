package com.topnetwork.wallet.dao.impl;

import org.springframework.stereotype.Repository;
import com.base.core.framework.sql.dao.impl.SqlBaseDaoImpl;

import com.topnetwork.wallet.dao.AppDiscoverGameDao;
import com.topnetwork.wallet.entity.AppDiscoverGame;

@Repository("appDiscoverGameDao")
public class AppDiscoverGameDaoImpl extends SqlBaseDaoImpl<AppDiscoverGame,Long>implements AppDiscoverGameDao {

	public AppDiscoverGameDaoImpl() {
		super(AppDiscoverGame.class);
	}

}
