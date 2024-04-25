package com.topnetwork.wallet.service;

import com.topnetwork.wallet.common.enums.ChainTypeEnum;
import com.topnetwork.wallet.entity.Device;
import com.topnetwork.wallet.param.topgame.CheckWalletAddressParam;
import com.topnetwork.wallet.param.wallet.v2.DeviceCoinParam;
import com.topnetwork.wallet.result.topgame.CheckWalletAddressResult;
import com.topnetwork.wallet.result.wallet.statistics.EffectiveAddressResult;
import com.gitee.magic.framework.base.context.Http;
import com.base.core.framework.sql.service.SqlBaseService;
import com.topnetwork.wallet.entity.DeviceCoins;

import java.util.List;

public interface DeviceCoinsService extends SqlBaseService<DeviceCoins, Long> {

    List<DeviceCoins> findByAccount(String account, ChainTypeEnum chainType,String contract);

    boolean removeByDeviceId(Long id);

    boolean removeToken(DeviceCoinParam param, Device device);

    boolean saveNoRepetition(DeviceCoins deviceCoins);

    void removeDeviceCoin(Device device);

    List<EffectiveAddressResult> getEffectiveAddress();
    
    /**
     * 获取有效用户的地址列表
     * @return
     */
    List<DeviceCoins> getEffectiveUserDeviceCoinsList();
    
    /**
     * 标记存币地址
     */
    void markerHoldingCoins() ;

    Integer countAll();

    CheckWalletAddressResult isWalletAddress(CheckWalletAddressParam param);

    /**
     * 批量获取用户设备地址
     * @param addressList
     * @param chainTypeEnum
     * @return
     */
    List<DeviceCoins> findByAccountBatch(List<String> addressList, ChainTypeEnum chainTypeEnum);
}
