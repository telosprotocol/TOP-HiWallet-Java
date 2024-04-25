package com.topnetwork.wallet.dao.impl;

import org.springframework.stereotype.Repository;
import com.base.core.framework.sql.dao.impl.SqlBaseDaoImpl;

import com.topnetwork.wallet.dao.SecretPlatformDao;
import com.topnetwork.wallet.entity.SecretPlatform;

@Repository("secretPlatformDao")
public class SecretPlatformDaoImpl extends SqlBaseDaoImpl<SecretPlatform,Long>implements SecretPlatformDao {

	public SecretPlatformDaoImpl() {
		super(SecretPlatform.class);
	}

}
