package com.topnetwork.wallet.dao.impl;

import org.springframework.stereotype.Repository;
import com.base.core.framework.sql.dao.impl.SqlBaseDaoImpl;

import com.topnetwork.wallet.dao.AppUserDeviceDao;
import com.topnetwork.wallet.entity.AppUserDevice;

@Repository("appUserDeviceDao")
public class AppUserDeviceDaoImpl extends SqlBaseDaoImpl<AppUserDevice,Long>implements AppUserDeviceDao {

	public AppUserDeviceDaoImpl() {
		super(AppUserDevice.class);
	}

}
