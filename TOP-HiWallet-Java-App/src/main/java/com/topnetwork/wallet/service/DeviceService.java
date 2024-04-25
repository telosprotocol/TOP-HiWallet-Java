package com.topnetwork.wallet.service;

import com.topnetwork.wallet.common.enums.PlatformEnum;
import com.topnetwork.wallet.param.wallet.commonpush.CommonPushMessageParam;
import com.topnetwork.wallet.param.wallet.defijpush.DefiPushMessageParam;
import com.topnetwork.wallet.param.wallet.v2.*;
import com.base.core.framework.sql.service.SqlBaseService;
import com.topnetwork.wallet.entity.Device;

import java.util.List;

public interface DeviceService extends SqlBaseService<Device,Long> {

    void reportDevice(DeviceBaseParam param, PlatformEnum platformEnum);

    void removeToken(DeviceCoinParam param);

    void recEthTranInfo(RecTranInfoParam param);

    void tokenReport(DeviceCoinParam param);

    void removeUser(DeviceDelUserParam param);

    List<Device> findDevice(PlatformEnum ios);

    /**
     * 发送推送
     * @param param
     */
    void defiInfo(DefiPushMessageParam param);
    /**
     * Description: 跳转发送推送
     * @param param
     * @return: void
     * @Author: Tyrone
     * @date: 2021/1/15
     **/
    void routeInfo(CommonPushMessageParam param);
}
