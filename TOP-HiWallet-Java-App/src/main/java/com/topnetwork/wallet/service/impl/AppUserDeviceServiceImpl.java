package com.topnetwork.wallet.service.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import com.base.core.framework.sql.service.impl.SqlBaseServiceImpl;
import com.topnetwork.wallet.dao.AppUserDeviceDao;
import com.topnetwork.wallet.entity.AppUserDevice;
import com.topnetwork.wallet.service.AppUserDeviceService;
import com.gitee.magic.jdbc.persistence.source.jdbc.sqlplus.conditions.query.QueryWrapper;

import java.util.Date;
import java.util.List;

@Service("appUserDeviceService")
@Transactional
public class AppUserDeviceServiceImpl extends SqlBaseServiceImpl<AppUserDevice, Long>
        implements AppUserDeviceService {

    @SuppressWarnings("unused")
    private AppUserDeviceDao appUserDeviceDao;

    public AppUserDeviceServiceImpl(@Qualifier("appUserDeviceDao") AppUserDeviceDao appUserDeviceDao) {
        super(appUserDeviceDao);
        this.appUserDeviceDao = appUserDeviceDao;
    }

    @Override
    public void saveUserDevice(String deviceId, Long uid) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("uid", uid).eq("deviceId", deviceId);
        List<AppUserDevice> list = appUserDeviceDao.queryForList(wrapper);
        if (list == null || list.isEmpty()) {
            AppUserDevice appUserDevice = new AppUserDevice();
            appUserDevice.setCreateTime(new Date());
            appUserDevice.setDeviceId(deviceId);
            appUserDevice.setUid(uid);
            save(appUserDevice);
        }
    }

    @Override
    public List<AppUserDevice> getByDeviceId(String deviceId) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("deviceId", deviceId);
        return appUserDeviceDao.queryForList(wrapper);
    }
}
