package com.topnetwork.wallet.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.topnetwork.wallet.param.topgame.CheckWalletAddressParam;
import com.topnetwork.wallet.result.topgame.CheckWalletAddressResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.common.bean.CoinsAddress;
import com.topnetwork.coin.EthCoinService;
import com.topnetwork.wallet.common.enums.ChainTypeEnum;
import com.topnetwork.wallet.common.enums.PlatformEnum;
import com.topnetwork.wallet.dao.DeviceCoinsDao;
import com.topnetwork.wallet.entity.CoinSpecies;
import com.topnetwork.wallet.entity.Device;
import com.topnetwork.wallet.entity.DeviceCoins;
import com.topnetwork.wallet.param.wallet.ETHQueryBalanceParam;
import com.topnetwork.wallet.param.wallet.ETHQueryErc20BalanceParam;
import com.topnetwork.wallet.param.wallet.v2.DeviceCoinParam;
import com.topnetwork.wallet.param.wallet.v2.TokenParam;
import com.topnetwork.wallet.result.wallet.ETHQueryBalanceResult;
import com.topnetwork.wallet.result.wallet.statistics.AddressResult;
import com.topnetwork.wallet.result.wallet.statistics.EffectiveAddressResult;
import com.topnetwork.wallet.service.DeviceCoinsService;

import com.base.core.framework.sql.service.impl.SqlBaseServiceImpl;
import com.gitee.magic.jdbc.persistence.source.jdbc.sqlplus.conditions.query.QueryWrapper;
import com.gitee.magic.jdbc.persistence.source.jdbc.sqlplus.conditions.update.DeleteWrapper;

@Service("deviceCoinsService")
@Transactional
public class DeviceCoinsServiceImpl extends SqlBaseServiceImpl<DeviceCoins, Long>
        implements DeviceCoinsService {

    @SuppressWarnings("unused")
    private DeviceCoinsDao deviceCoinsDao;

    @Autowired
    private EthCoinService ethCoinService;

    public DeviceCoinsServiceImpl(@Qualifier("deviceCoinsDao") DeviceCoinsDao deviceCoinsDao) {
        super(deviceCoinsDao);
        this.deviceCoinsDao = deviceCoinsDao;
    }

    @Override
    public List<DeviceCoins> findByAccount(String account, ChainTypeEnum chainType, String contract) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("address", account).eq("chainType", chainType.toString()).eq(!StringUtils.isEmpty(contract), "contract", contract);
        return deviceCoinsDao.queryForList(wrapper);
    }

    @Override
    public boolean removeByDeviceId(Long id) {
        DeleteWrapper deleteWrapper = new DeleteWrapper();
        deleteWrapper.eq("deviceId", id);
        return deviceCoinsDao.executeUpdate(deleteWrapper) > 0;
    }

    @Override
    public boolean removeToken(DeviceCoinParam param, Device device) {
        for (TokenParam tokenParam : param.getToken()) {
            DeleteWrapper deleteWrapper = new DeleteWrapper();
            deleteWrapper.eq("deviceId", device.getId());
            deleteWrapper.eq("chainType", tokenParam.getChainType().toString());
            deleteWrapper.eq(!StringUtils.isEmpty(tokenParam.getContract()), "contract", tokenParam.getContract());
            deleteWrapper.eq("address", tokenParam.getAddress());
            deviceCoinsDao.executeUpdate(deleteWrapper);
        }
        return true;
    }

    @Override
    public void removeDeviceCoin(Device device) {
        DeleteWrapper deleteWrapper = new DeleteWrapper();
        deleteWrapper.eq("deviceId", device.getId());
        deviceCoinsDao.executeUpdate(deleteWrapper);
    }

    @Override
    public boolean saveNoRepetition(DeviceCoins deviceCoins) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("address", deviceCoins.getAddress()).eq("chainType", deviceCoins.getChainType().toString())
                .eq("deviceId", deviceCoins.getDeviceId()).eq("contract", deviceCoins.getContract());
        DeviceCoins deviceCoins1 = get(deviceCoinsDao.queryForList(wrapper), false);
        if (deviceCoins1 != null) {
            return false;
        }
        return save(deviceCoins) > 0;
    }

    @Autowired
    private com.topnetwork.wallet.service.CoinSpeciesService coinSpeciesService;

    @Override
    public List<EffectiveAddressResult> getEffectiveAddress() {
        String sqlList = "SELECT c.chainType,c.address,c.contract,c.effectiveAddress,d.platform FROM wal_app_user wau,wal_deviceCoins c LEFT JOIN wal_device d ON c.deviceId = d.id WHERE wau.uid= d.userId AND wau.`disable`='false'";
        List<CoinsAddress> coinsAddresses = result(CoinsAddress.class, deviceCoinsDao.queryForMap(sqlList));
        CoinSpecies USDT = coinSpeciesService.findContractBySymbol("USDT");
        CoinSpecies TOP = coinSpeciesService.findContractBySymbol("TOP");
        List<EffectiveAddressResult> list = new ArrayList<>();
        Map<String, AddressResult> all = new HashMap<>();
        Map<String, AddressResult> effective = new HashMap<>();

        for (CoinsAddress coinsAddress : coinsAddresses) {
            if (coinsAddress.getPlatform() == null) continue;
            String name = "";
            if (USDT != null && USDT.getContractAddress().equalsIgnoreCase(coinsAddress.getContract())) {
                //usdt
                name = "USDT-ERC20";
            } else if (TOP != null && TOP.getContractAddress().equalsIgnoreCase(coinsAddress.getContract())) {
                name = "TOP-ERC20";
            } else {
                name = coinsAddress.getChainType().toString();
            }

            AddressResult addressResultAll = all.get(name) == null ? new AddressResult() : all.get(name);
            addCount(addressResultAll, coinsAddress);
            all.put(name, addressResultAll);
            if (coinsAddress.getEffectiveAddress()) {
                AddressResult addressResultEffective = effective.get(name) == null ? new AddressResult() : effective.get(name);
                addCount(addressResultEffective, coinsAddress);
                effective.put(name, addressResultEffective);
            }
        }
        for (String key : all.keySet()) {
            EffectiveAddressResult effectiveAddressResult = new EffectiveAddressResult();
            effectiveAddressResult.setName(key);
            effectiveAddressResult.setAll(all.get(key) == null ? new AddressResult() : all.get(key));
            effectiveAddressResult.setEffective(effective.get(key) == null ? new AddressResult() : effective.get(key));
            list.add(effectiveAddressResult);
        }
        return list;
    }

    private void addCount(AddressResult addressResultAll, CoinsAddress coinsAddress) {
        Integer all = addressResultAll.getAll() == null ? 0 : addressResultAll.getAll();
        Integer android = addressResultAll.getAndroid() == null ? 0 : addressResultAll.getAndroid();
        Integer ios = addressResultAll.getIos() == null ? 0 : addressResultAll.getIos();
        addressResultAll.setAll(all + 1);
        if (PlatformEnum.IOS.equals(coinsAddress.getPlatform())) {
            addressResultAll.setIos(ios + 1);
            addressResultAll.setAndroid(android);
        } else if (PlatformEnum.ANDROID.equals(coinsAddress.getPlatform())) {
            addressResultAll.setAndroid(android + 1);
            addressResultAll.setIos(ios);
        } else {
            addressResultAll.setIos(ios);
            addressResultAll.setAndroid(android);
        }
    }

    /**
     * 1.查询出有效用户的地址
     * 2.根据地址类型分别去eth，btc等链查询是否有余额>0的地址
     * 3.将余额>0 的地址标记为存币地址
     */
    @Override
    public void markerHoldingCoins() {
        List<DeviceCoins> dcList = getEffectiveUserDeviceCoinsList();
        if (null == dcList || dcList.isEmpty()) {
            return;
        }
        List<DeviceCoins> upadteList = new ArrayList<DeviceCoins>();
        dcList.stream().forEach(dc -> {
            try {
                if (dc.getChainType().equals(ChainTypeEnum.BTC) || dc.getChainType().equals(ChainTypeEnum.BCH)
                        || dc.getChainType().equals(ChainTypeEnum.EOS) || dc.getChainType().equals(ChainTypeEnum.DASH)
                        || dc.getChainType().equals(ChainTypeEnum.LTC)) {
                    BigDecimal btcB = new BigDecimal(ethCoinService.getUtxoBalance(dc.getChainType().toString(), dc.getAddress(), dc.getContract()));
                    if (btcB.compareTo(BigDecimal.ZERO) > 0) {
                        dc.setEffectiveAddress(true);
                        upadteList.add(dc);
                    }
                } else if (dc.getChainType().equals(ChainTypeEnum.ETH)) {
                    if (StringUtils.isEmpty(dc.getContract())) {
                        ETHQueryBalanceParam qParam = new ETHQueryBalanceParam();
                        qParam.setAddress(dc.getAddress());
                        ETHQueryBalanceResult ethBalance = ethCoinService.queryBalance(qParam);
                        BigDecimal b = new BigDecimal(ethBalance.getBalance());
                        if (b.compareTo(BigDecimal.ZERO) > 0) {
                            dc.setEffectiveAddress(true);
                            upadteList.add(dc);
                        }
                    } else {
                        ETHQueryErc20BalanceParam qerc20Param = new ETHQueryErc20BalanceParam();
                        ETHQueryBalanceResult balance = ethCoinService.queryErc20Balance(qerc20Param);
                        BigDecimal b = new BigDecimal(balance.getBalance());
                        if (b.compareTo(BigDecimal.ZERO) > 0) {
                            dc.setEffectiveAddress(true);
                            upadteList.add(dc);
                        }
                    }
                }

            } catch (Exception e) {
                //不处理
            }
        });
        if (upadteList != null && !upadteList.isEmpty()) {
            deviceCoinsDao.mergeBatch(upadteList);
        }

    }

    @Override
    public List<DeviceCoins> getEffectiveUserDeviceCoinsList() {
        return deviceCoinsDao.queryForListMapper("getEffectiveUserDeviceCoinsList");
    }

    @Override
    public Integer countAll() {
        return deviceCoinsDao.queryForInt("SELECT COUNT(1) FROM wal_deviceCoins", null);
    }

    @Override
    public CheckWalletAddressResult isWalletAddress(CheckWalletAddressParam param) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("chainType", param.getChainType().toString()).eq("address", param.getAddress());
        List<DeviceCoins> deviceCoinsList = deviceCoinsDao.queryForList(wrapper);
        CheckWalletAddressResult checkWalletAddressResult = new CheckWalletAddressResult();
        if (deviceCoinsList != null && deviceCoinsList.size() > 0) {
            checkWalletAddressResult.setWalletAddress(true);
        } else {
            checkWalletAddressResult.setWalletAddress(false);
        }
        return checkWalletAddressResult;
    }

    @Override
    public List<DeviceCoins> findByAccountBatch(List<String> addressList, ChainTypeEnum chainTypeEnum) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.in("address", addressList).eq("chainType", chainTypeEnum.toString());
        return deviceCoinsDao.queryForList(wrapper);
    }
}
