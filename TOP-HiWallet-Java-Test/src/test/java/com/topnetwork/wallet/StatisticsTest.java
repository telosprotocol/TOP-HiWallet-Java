package com.topnetwork.wallet;

import com.topnetwork.common.TestHeader;
import com.topnetwork.wallet.param.wallet.statistics.WalletDatePageParam;
import com.topnetwork.wallet.result.wallet.statistics.DeviceInfoResult;
import com.topnetwork.wallet.result.wallet.statistics.EffectiveAddressResult;
import com.topnetwork.wallet.result.wallet.statistics.WalletDatePageResult;
import com.topnetwork.wallet.result.wallet.statistics.WalletInfoResult;
import org.junit.Test;
import com.gitee.magic.framework.base.rest.RestfulTemplate;

/**
 * @ClassName StatisticsTest
 * @Description 统计测试类
 * @Author bran
 * @Date 2020/6/22 15:54
 */
public class StatisticsTest extends TestHeader {

    @Test
    public void test_get_effective_address() throws Exception {
        RestfulTemplate template = new RestfulTemplate(HOST, manageraccessid, manageraccesskey);
        template.getForEntityListResult("/statistics/get_effective_address", EffectiveAddressResult.class);
    }

    @Test
    public void test_get_wallet_info() throws Exception {
        RestfulTemplate template = new RestfulTemplate(HOST, manageraccessid, manageraccesskey);
        template.getForEntityResult("/statistics/get_wallet_info", WalletInfoResult.class);
    }

    @Test
    public void test_get_device_info() throws Exception {
        RestfulTemplate template = new RestfulTemplate(HOST, manageraccessid, manageraccesskey);
        template.getForEntityListResult("/statistics/get_device_info", DeviceInfoResult.class);
    }

    @Test
    public void test_get_wallet_date_page() throws Exception {
        RestfulTemplate template = new RestfulTemplate(HOST, manageraccessid, manageraccesskey);
        WalletDatePageParam pageParam = new WalletDatePageParam();
        pageParam.setStartTime(1592539994000L);
        pageParam.setEndTime(1592967159000L);
        template.getForEntityListResult("/statistics/get_wallet_date_page", pageParam, WalletDatePageResult.class);
    }
}
