package com.topnetwork.wallet.dao.impl;

import org.springframework.stereotype.Repository;
import com.base.core.framework.sql.dao.impl.SqlBaseDaoImpl;

import com.topnetwork.wallet.dao.AppHelpDao;
import com.topnetwork.wallet.entity.AppHelp;

@Repository("appHelpDao")
public class AppHelpDaoImpl extends SqlBaseDaoImpl<AppHelp,Long>implements AppHelpDao {

	public AppHelpDaoImpl() {
		super(AppHelp.class);
	}

}
