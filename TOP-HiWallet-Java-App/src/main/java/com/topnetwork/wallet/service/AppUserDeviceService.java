package com.topnetwork.wallet.service;

import com.base.core.framework.sql.service.SqlBaseService;
import com.topnetwork.wallet.entity.AppUserDevice;

import java.util.List;

public interface AppUserDeviceService extends SqlBaseService<AppUserDevice, Long> {

    void saveUserDevice(String deviceId, Long uid);

    List<AppUserDevice> getByDeviceId(String deviceId);
}
