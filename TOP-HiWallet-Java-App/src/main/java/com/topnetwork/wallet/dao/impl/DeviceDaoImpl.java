package com.topnetwork.wallet.dao.impl;

import org.springframework.stereotype.Repository;
import com.base.core.framework.sql.dao.impl.SqlBaseDaoImpl;

import com.topnetwork.wallet.dao.DeviceDao;
import com.topnetwork.wallet.entity.Device;

@Repository("deviceDao")
public class DeviceDaoImpl extends SqlBaseDaoImpl<Device,Long>implements DeviceDao {

	public DeviceDaoImpl() {
		super(Device.class);
	}

}
