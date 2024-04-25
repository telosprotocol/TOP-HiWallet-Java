package com.topnetwork.wallet.service.impl;

import com.topnetwork.jpush.PushMessage;
import com.topnetwork.jpush.PushService;
import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.common.enums.ChainTypeEnum;
import com.topnetwork.wallet.common.enums.LanguageEnum;
import com.topnetwork.wallet.common.enums.PlatformEnum;
import com.topnetwork.wallet.dao.DeviceDao;
import com.topnetwork.wallet.entity.Device;
import com.topnetwork.wallet.entity.DeviceCoins;
import com.topnetwork.wallet.param.wallet.RecTransactionData;
import com.topnetwork.wallet.param.wallet.commonpush.CommonPushMessageParam;
import com.topnetwork.wallet.param.wallet.defijpush.DefiPushMessageParam;
import com.topnetwork.wallet.param.wallet.v2.*;
import com.topnetwork.wallet.service.DeviceCoinsService;
import com.topnetwork.wallet.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.gitee.magic.framework.head.exception.BusinessException;
import com.base.core.framework.sql.service.impl.SqlBaseServiceImpl;
import com.gitee.magic.jdbc.persistence.source.jdbc.sqlplus.conditions.query.QueryWrapper;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@Service("deviceService")
@Transactional
public class DeviceServiceImpl extends SqlBaseServiceImpl<Device, Long>
        implements DeviceService {

    @SuppressWarnings("unused")
    private DeviceDao deviceDao;

    @Autowired
    private DeviceCoinsService deviceCoinsService;
    @Autowired
    private PushService pushService;

    ExecutorService executorService = Executors.newFixedThreadPool(20);

    public DeviceServiceImpl(@Qualifier("deviceDao") DeviceDao deviceDao) {
        super(deviceDao);
        this.deviceDao = deviceDao;
    }

    @Override
    public void reportDevice(DeviceBaseParam param, PlatformEnum platformEnum) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("clientId", param.getClientId());
        Device device = get(deviceDao.queryForList(wrapper));
        if (device == null) {
            device = new Device();
            device.setLanguage(param.getLanguage());
            device.setRegistrationId(param.getRegistrationId());
            device.setClientId(param.getClientId());
            device.setUserId(param.getUserId());
            device.setPlatform(platformEnum);
            device.setRegion(param.getRegion());
            device.setUpdateTime(new Date());
            device.setCreateTime(new Date());
            save(device);
            //调用服务，修改该设备所属标签
            //pushService.setDeviceTag(device);
            return;
        }
        if (!device.getRegistrationId().equals(param.getRegistrationId()) || !device.getUserId().equals(param.getUserId())) {
            //证明是卸载重装或者换了用户

            //1、删除之前的设备币种关系
            deviceCoinsService.removeByDeviceId(device.getId());
            //2、更新设备信息
            device.setRegistrationId(param.getRegistrationId());
            device.setUserId(param.getUserId());
        }
        if (!param.getLanguage().equals(device.getLanguage())) {
            device.setLanguage(param.getLanguage());
            //调用服务，修改该设备所属标签
            //pushService.setDeviceTag(device);
        }
        if (!param.getRegion().equals(device.getRegion())) {
            device.setRegion(param.getRegion());
        }
        device.setUpdateTime(new Date());
        save(device);
    }

    @Override
    public void tokenReport(DeviceCoinParam param) {

        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("clientId", param.getClientId()).eq("userId", param.getUserId());
        Device device = get(deviceDao.queryForList(wrapper));
        if (device == null) {
            throw new BusinessException(CodeRes.CODE_15017);
        }
        //新加币种关系
        List<TokenParam> coins = param.getToken();
        for (TokenParam tokenParam : coins) {
            DeviceCoins deviceCoins = new DeviceCoins();
            deviceCoins.setAddress(tokenParam.getAddress());
            deviceCoins.setChainType(tokenParam.getChainType());
            deviceCoins.setContract(tokenParam.getContract());
            deviceCoins.setDeviceId(device.getId());
            deviceCoins.setEffectiveAddress(false);
            deviceCoins.setCreateTime(new Date());
            deviceCoinsService.saveNoRepetition(deviceCoins);
        }
    }

    @Override
    public void removeToken(DeviceCoinParam param) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("clientId", param.getClientId()).eq("userId", param.getUserId());
        Device device = get(deviceDao.queryForList(wrapper));
        if (device == null) {
            return;
        }
        deviceCoinsService.removeToken(param, device);
    }

    @Override
    public void removeUser(DeviceDelUserParam param) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("clientId", param.getClientId()).eq("userId", param.getUserId());
        Device device = get(deviceDao.queryForList(wrapper));
        if (device != null) {
            remove(device.getId());
            deviceCoinsService.removeDeviceCoin(device);
        }
    }

    @Override
    public void recEthTranInfo(RecTranInfoParam param) {
        for (RecTransactionData transactionData : param.getTransactions()) {
            executorService.submit(new SendMessage(transactionData, deviceDao, deviceCoinsService, pushService));
        }
    }

    @Override
    public List<Device> findDevice(PlatformEnum platformEnum) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("platform", platformEnum.toString());
        return deviceDao.queryForList(wrapper);
    }

    @Override
    public void defiInfo(DefiPushMessageParam param) {
        List<String> addressList = param.getAddressList();
        //取出所有的DeviceCoins对应关系
        List<DeviceCoins> fromDeviceList = deviceCoinsService.findByAccountBatch(addressList, ChainTypeEnum.ETH);
        //根据设备id查询Device对象
        List<Long> idList = fromDeviceList.stream().map(DeviceCoins::getDeviceId).collect(Collectors.toList());
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.in("id", idList);
        List<Device> devices = deviceDao.queryForList(queryWrapper);
        for (Device device : devices) {
            LanguageEnum language = device.getLanguage();
            //封装推送消息对象
            PushMessage pushMessage = PushMessage.buildFromDefiMessage(param,language);
            //发推送
            pushService.transactionPush(pushMessage, device);
        }
    }

    @Override
    public void routeInfo(CommonPushMessageParam param) {
        List<String> addressList = param.getAddressList();
        //取出所有的DeviceCoins对应关系
        List<DeviceCoins> fromDeviceList = deviceCoinsService.findByAccountBatch(addressList, ChainTypeEnum.ETH);
        //根据设备id查询Device对象
        List<Long> idList = fromDeviceList.stream().map(DeviceCoins::getDeviceId).collect(Collectors.toList());
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.in("id", idList);
        List<Device> devices = deviceDao.queryForList(queryWrapper);
        for (Device device : devices) {
            LanguageEnum language = device.getLanguage();
            //封装推送消息对象
            PushMessage pushMessage = PushMessage.buildFromRouteMessage(param,language);
            //发推送
            pushService.transactionPush(pushMessage, device);
        }
    }

    public static class SendMessage implements Runnable {

        private RecTransactionData transactionData;
        private DeviceDao deviceDao;

        private DeviceCoinsService deviceCoinsService;
        private PushService pushService;

        public SendMessage(RecTransactionData transactionData, DeviceDao deviceDao,
                           DeviceCoinsService deviceCoinsService, PushService pushService) {
            this.transactionData = transactionData;
            this.deviceDao = deviceDao;
            this.deviceCoinsService = deviceCoinsService;
            this.pushService = pushService;
        }

        @Override
        public void run() {
            String from = transactionData.getFromAccount();
            String to = transactionData.getToAccount();
            String contract = transactionData.getContract();

            List<DeviceCoins> fromDeviceList = deviceCoinsService.findByAccount(from, ChainTypeEnum.ETH, contract);
            for (DeviceCoins deviceCoins : fromDeviceList) {
                QueryWrapper queryWrapper = new QueryWrapper();
                queryWrapper.eq("id", deviceCoins.getDeviceId());
                Device device = deviceDao.queryForList(queryWrapper).get(0);
                //new push add
                //封装推送消息对象
                PushMessage pushMessage = PushMessage.buildFromTranMessage(transactionData);
                //发推送
                pushService.transactionPush(pushMessage, device);
            }

            List<DeviceCoins> toDeviceList = deviceCoinsService.findByAccount(to, ChainTypeEnum.ETH, contract);
            for (DeviceCoins deviceCoins : toDeviceList) {
                QueryWrapper queryWrapper = new QueryWrapper();
                queryWrapper.eq("id", deviceCoins.getDeviceId());
                Device device = deviceDao.queryForList(queryWrapper).get(0);
                //封装推送消息对象
                PushMessage pushMessage = PushMessage.buildToTranMessage(transactionData);
                //发推送
                pushService.transactionPush(pushMessage, device);
            }
        }
    }


}
