package com.topnetwork.wallet;

import com.topnetwork.common.TestHeader;
import com.topnetwork.wallet.common.enums.ChainTypeEnum;
import com.topnetwork.wallet.common.enums.LanguageEnum;
import com.topnetwork.wallet.common.enums.PushType;
import com.topnetwork.wallet.common.enums.RegionEnum;
import com.topnetwork.wallet.param.wallet.*;
import com.topnetwork.wallet.param.wallet.defijpush.DefiPushMessageParam;
import com.topnetwork.wallet.param.wallet.v2.*;
import org.junit.Test;
import com.gitee.magic.framework.base.rest.RestfulTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class PushTest extends TestHeader {


    @Test
    public void test_device_report() throws Exception {
        DeviceReportParam param = new DeviceReportParam();
        param.setClientId("clientId-安卓");
        param.setLanguage(LanguageEnum.CN);
        param.setRegistrationId("190e35f7e0d71cebef4");
        //param.setRegistrationId("<50ac2fb3 db2216d6 9ab8245e da7a64ab d0d112c0 c77b1860 4d51cdbb 4978bf5d>");
        param.setUserId("uniqueHash-安卓");
        param.setRegion(RegionEnum.OVERSEAS);
        RestfulTemplate template = new RestfulTemplate(HOST, accessid, accesskey);
        //RESTfulTemplate template = new RESTfulTemplate(HOST, "92bfca8adf52f522f418d052ceec74ff", "KAMAhTqZFy2hgt2i0CCAd/XYsB8dK9gYVNHdPh+RYJWmxJIyt0ofXA==");

        template.postForEntityBase("/v2/app/device/device_report", param);

    }

    @Test
    public void test_rec_tran_info() throws Exception {
        RecTranInfoParam param = new RecTranInfoParam();

        List<RecTransactionData> transactions = new ArrayList<>();

        RecTransactionData recTransactionData = new RecTransactionData();
        recTransactionData.setAmount(new BigDecimal("10000"));
        recTransactionData.setComment("转账");
        recTransactionData.setFromAccount("fdsfd");
        recTransactionData.setHash("ssssssssss");
        recTransactionData.setGas("123");
        recTransactionData.setToAccount("toAccount");

        transactions.add(recTransactionData);

        param.setTransactions(transactions);
        RestfulTemplate template = new RestfulTemplate(HOST, accessid, accesskey);
        template.postForEntityBase("/v2/app/eth/rec_tran_info", param);

    }

    @Test
    public void test_token_report() throws Exception {
        DeviceCoinParam param = new DeviceCoinParam();

        List<TokenParam> token = new ArrayList<>();
        TokenParam tokenParam = new TokenParam();
        tokenParam.setAddress("fdsfd3");
        tokenParam.setChainType(ChainTypeEnum.ETH);
        tokenParam.setContract("contract3");
        token.add(tokenParam);

        TokenParam tokenParam2 = new TokenParam();
        tokenParam2.setAddress("fdsfd5");
        tokenParam2.setChainType(ChainTypeEnum.ETH);
        tokenParam2.setContract("contract5");
        token.add(tokenParam2);

        param.setToken(token);
        param.setClientId("EACE8DC8-F343-4262-AF83-E0338D938827");
        param.setUserId("3008c706bb0fa5fde8db85dae82319b645a6de4ce5dc05425410eaf28cc44422");

        RestfulTemplate template = new RestfulTemplate(HOST, accessid, accesskey);
        template.postForEntityBase("/v2/app/device/token_report", param);

    }


    @Test
    public void test_find_notice_detail() throws Exception {
        NoticeDetailV2Param param = new NoticeDetailV2Param();
        param.setId(658618905148784640L);
        param.setLanguage(LanguageEnum.CN);
        RestfulTemplate template = new RestfulTemplate(HOST, accessid, accesskey);
        template.postForEntityBase("/v2/app/find_notice_detail", param);
    }

    @Test
    public void test_find_notice_list() throws Exception {
        NoticeListV2Param param = new NoticeListV2Param();
        param.setDate(new Date());
        param.setLanguage(LanguageEnum.CN);
        RestfulTemplate template = new RestfulTemplate(HOST, accessid, accesskey);
        template.postForEntityBase("/v2/app/find_notice_list", param);
    }

    @Test
    public void test_token_remove() throws Exception {
        DeviceCoinParam param = new DeviceCoinParam();

        List<TokenParam> token = new ArrayList<>();
        TokenParam tokenParam = new TokenParam();
        tokenParam.setAddress("fdsfd3");
        tokenParam.setChainType(ChainTypeEnum.ETH);
        tokenParam.setContract("contract3");
        token.add(tokenParam);

        TokenParam tokenParam2 = new TokenParam();
        tokenParam2.setAddress("fdsfd2222111");
        tokenParam2.setChainType(ChainTypeEnum.ETH);
        tokenParam2.setContract("contract111");
        token.add(tokenParam2);

        param.setToken(token);
        param.setClientId("EACE8DC8-F343-4262-AF83-E0338D938827");
        param.setUserId("3008c706bb0fa5fde8db85dae82319b645a6de4ce5dc05425410eaf28cc44422");

        RestfulTemplate template = new RestfulTemplate(HOST, accessid, accesskey);
        template.postForEntityBase("/v2/app/device/remove_token", param);

    }

    @Test
    public void test_user_remove() throws Exception {
        DeviceDelUserParam param = new DeviceDelUserParam();

        param.setClientId("clientId-安卓");
        param.setUserId("uniqueHash-安卓");

        RestfulTemplate template = new RestfulTemplate(HOST, accessid, accesskey);
        template.postForEntityBase("/v2/app/device/remove_user", param);
    }

    @Test
    public void test_defi_info() throws Exception {
        DefiPushMessageParam defiPushMessage = new DefiPushMessageParam();
        defiPushMessage.setContent("测试content,测试字符串长度111111111111111111111111111111111111111111");
        defiPushMessage.setTitle("测试title");
        defiPushMessage.setEnglishContent("test content");
        defiPushMessage.setEnglishTitle("test title");
        ArrayList<String> list = new ArrayList<>();
        list.add("0xf6cCD9f7FD1Af6F1f5b2d8763F5d3a4Ae57a36DC");
//        list.add("0xC724c86A3336cd421a221B0bd9D47ca36b0769dB");
        defiPushMessage.setAddressList(list);
        RestfulTemplate template = new RestfulTemplate(HOST, accessid, accesskey);
        template.postForEntityBase("/jpush/defi_info", defiPushMessage);
    }
}
