package com.topnetwork.common.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.utils.DateUtils;
import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.dao.DeviceCoinsDao;
import com.topnetwork.wallet.dao.DeviceDao;
import com.topnetwork.wallet.param.wallet.statistics.WalletDatePageParam;
import com.topnetwork.wallet.result.wallet.statistics.DeviceInfoResult;
import com.topnetwork.wallet.result.wallet.statistics.EffectiveAddressResult;
import com.topnetwork.wallet.result.wallet.statistics.WalletDatePageResult;
import com.topnetwork.wallet.result.wallet.statistics.WalletInfoResult;
import com.topnetwork.wallet.service.DeviceCoinsService;
import com.topnetwork.wallet.service.DeviceService;

import com.gitee.magic.framework.head.exception.BusinessException;

/**
 * @ClassName StatisticsService
 * @Description 统计相关service
 * @Author bran
 * @Date 2020/6/23 17:53
 */
@Service("statisticsService")
@Transactional
public class StatisticsService {

    @Autowired
    private DeviceCoinsService deviceCoinsService;
    @Autowired
    private DeviceCoinsDao deviceCoinsDao;
    @Autowired
    private DeviceService deviceService;
    @Autowired
    private DeviceDao deviceDao;

    public List<EffectiveAddressResult> getEffectiveAddress() {
        return deviceCoinsService.getEffectiveAddress();
    }

    public WalletInfoResult getWalletInfo() {
        String sql = "SELECT COUNT(DISTINCT(userId)) as mnemonicWords,COUNT(1) as device FROM wal_device";
        List<WalletInfoResult> walletInfoResultList = deviceService.result(WalletInfoResult.class, deviceDao.queryForMap(sql));
//        List<WalletInfoResult> walletInfoResultList = deviceService.queryForMap(WalletInfoResult.class, sql);
        WalletInfoResult walletInfoResult = walletInfoResultList.get(0);
        walletInfoResult.setAddress(deviceCoinsService.countAll());
        return walletInfoResult;
    }

    public List<DeviceInfoResult> getDeviceInfo() {
        String sql = "SELECT COUNT(clientId) as num,platform,`language` FROM wal_device GROUP BY platform,`language` ORDER BY platform,`language`";
        return deviceService.result(DeviceInfoResult.class, deviceDao.queryForMap(sql));
//        return deviceService.queryForMap(DeviceInfoResult.class, sql);
    }

    public List<WalletDatePageResult> getWalletDatePage(WalletDatePageParam param) {

        if (param.getEndTime() == null && param.getStartTime() == null) {
            param.setEndTime(System.currentTimeMillis());
            param.setStartTime(DateUtils.addDay(new Date(), -9).getTime());
        }
        if (param.getEndTime() == null || param.getStartTime() == null) {
            throw new BusinessException(CodeRes.CODE_21013);
        }
        Date start = DateUtils.getDayStart(new Date(param.getStartTime()));
        Date end = DateUtils.getDayEnd(new Date(param.getEndTime()));

        if (start.after(end)) {
            throw new BusinessException(CodeRes.CODE_21013);
        }
        String sql = "SELECT COUNT(DISTINCT(userId)) as mnemonicWords,COUNT(1) as device,cast(createTime as date) AS date FROM wal_device WHERE createTime BETWEEN '" + DateUtils.format(start, DateUtils.YYYYMMDDHHMMSS_F) + "' AND '" + DateUtils.format(end, DateUtils.YYYYMMDDHHMMSS_F) + "' GROUP BY cast(createTime as date) ";
        List<WalletDatePageResult> walletDatePageResultList = deviceService.result(WalletDatePageResult.class, deviceDao.queryForMap(sql));
//        List<WalletDatePageResult> walletDatePageResultList = deviceService.queryForMap(WalletDatePageResult.class, sql);
        String sql2 = "SELECT COUNT(1) as address,cast(createTime as date) AS date FROM wal_deviceCoins WHERE createTime BETWEEN '" + DateUtils.format(start, DateUtils.YYYYMMDDHHMMSS_F) + "' AND '" + DateUtils.format(end, DateUtils.YYYYMMDDHHMMSS_F) + "' GROUP BY cast(createTime as date) \n";
        List<WalletDatePageResult> walletCoinDatePageResultList = deviceCoinsService.result(WalletDatePageResult.class, deviceCoinsDao.queryForMap(sql2));
//        List<WalletDatePageResult> walletCoinDatePageResultList = deviceCoinsService.queryForMap(WalletDatePageResult.class, sql2);
        int dayDiff = DateUtils.getDayDiff(start, end);
        List<WalletDatePageResult> results = new ArrayList<>();
        for (int i = 0; i <= dayDiff; i++) {
            String date = DateUtils.format(DateUtils.addDay(start, i), DateUtils.YYYYMMDD_F);
            Integer mnemonicWords = 0;
            Integer device = 0;
            Integer address = 0;
            for (WalletDatePageResult walletDatePageResult : walletDatePageResultList) {
                if (date.equalsIgnoreCase(walletDatePageResult.getDate())) {
                    mnemonicWords = walletDatePageResult.getMnemonicWords();
                    device = walletDatePageResult.getDevice();
                    break;
                }
            }
            for (WalletDatePageResult walletDatePageResult : walletCoinDatePageResultList) {
                if (date.equalsIgnoreCase(walletDatePageResult.getDate())) {
                    address = walletDatePageResult.getAddress();
                    break;
                }
            }
            WalletDatePageResult walletDatePageResult = new WalletDatePageResult();
            walletDatePageResult.setDate(date);
            walletDatePageResult.setAddress(address);
            walletDatePageResult.setDevice(device);
            walletDatePageResult.setMnemonicWords(mnemonicWords);
            results.add(walletDatePageResult);
        }
        return results;
    }
}
