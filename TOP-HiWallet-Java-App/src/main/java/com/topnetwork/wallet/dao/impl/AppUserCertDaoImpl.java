package com.topnetwork.wallet.dao.impl;

import org.springframework.stereotype.Repository;
import com.base.core.framework.sql.dao.impl.SqlBaseDaoImpl;

import com.topnetwork.wallet.dao.AppUserCertDao;
import com.topnetwork.wallet.entity.AppUserCert;

@Repository("appUserCertDao")
public class AppUserCertDaoImpl extends SqlBaseDaoImpl<AppUserCert,Long>implements AppUserCertDao {

	public AppUserCertDaoImpl() {
		super(AppUserCert.class);
	}

}
