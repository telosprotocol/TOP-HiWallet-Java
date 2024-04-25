package com.topnetwork.wallet.dao.impl;

import org.springframework.stereotype.Repository;
import com.base.core.framework.sql.dao.impl.SqlBaseDaoImpl;

import com.topnetwork.wallet.dao.DeviceCoinsDao;
import com.topnetwork.wallet.entity.DeviceCoins;

@Repository("deviceCoinsDao")
public class DeviceCoinsDaoImpl extends SqlBaseDaoImpl<DeviceCoins,Long>implements DeviceCoinsDao {

	public DeviceCoinsDaoImpl() {
		super(DeviceCoins.class);
	}

}
