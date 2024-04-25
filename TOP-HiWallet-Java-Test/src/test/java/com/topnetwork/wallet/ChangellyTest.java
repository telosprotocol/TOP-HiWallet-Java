package com.topnetwork.wallet;

import com.topnetwork.common.TestHeader;
import com.topnetwork.wallet.common.enums.LanguageEnum;
import com.topnetwork.wallet.common.enums.changelly.ExchangeStatus;
import com.topnetwork.wallet.common.enums.changelly.GetListTypeEnum;
import com.topnetwork.wallet.param.wallet.changelly.*;
import com.topnetwork.wallet.result.changelly.*;
import org.junit.Test;
import com.gitee.magic.framework.base.rest.RestfulTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName ChangellyTest
 * @Description
 * @Author bran
 * @Date 2020/6/15 16:47
 */
public class ChangellyTest extends TestHeader {

    @Test
    public void test_get_coin_list() throws Exception {
        RestfulTemplate template = new RestfulTemplate(HOST, manageraccessid, manageraccesskey);
        template.getForEntityListResult("/changelly/get_coin_list", null, GetCoinListResult.class);
    }

    @Test
    public void test_add_currency() throws Exception {
        RestfulTemplate template = new RestfulTemplate(HOST, manageraccessid, manageraccesskey);
        AddCurrencyParam param = new AddCurrencyParam();
        param.setCoinId(1L);
        param.setCustomized(false);
        param.setDisable(false);
        param.setMaxSellAmount(new BigDecimal("20"));
//        param.setMinSellAmount(new BigDecimal("1"));
        template.postForEntityBase("/changelly/add_currency", param);
    }

    @Test
    public void test_update_currency() throws Exception {
        RestfulTemplate template = new RestfulTemplate(HOST, manageraccessid, manageraccesskey);
        UpdCurrencyParam param = new UpdCurrencyParam();
        param.setCurrencyId(722829012983873536L);
        param.setCoinId(2L);
        param.setCustomized(true);
        param.setDisable(false);
        param.setMaxSellAmount(new BigDecimal("10"));
        param.setMinSellAmount(new BigDecimal("-1"));
        template.putForEntityBase("/changelly/update_currency", param);
    }

    @Test
    public void test_delete_currency() throws Exception {
        RestfulTemplate template = new RestfulTemplate(HOST, manageraccessid, manageraccesskey);
        DelCurrencyParam param = new DelCurrencyParam();
        param.setCurrencyId(722758522835566592L);
        template.deleteForEntityBase("/changelly/delete_currency", param);
    }

    @Test
    public void test_get_currency_page() throws Exception {
        RestfulTemplate template = new RestfulTemplate(HOST, manageraccessid, manageraccesskey);
        GetCurrencyPageParam param = new GetCurrencyPageParam();
        param.setPageIndex(1);
        param.setPageSize(10);
        template.getForEntityPage("/changelly/get_currency_page", param, GetCurrencyPageResult.class);
    }

    @Test
    public void test_get_currency_list() throws Exception {
        RestfulTemplate template = new RestfulTemplate(HOST, accessid, accesskey);
        GetCurrenciesParam param = new GetCurrenciesParam();
        String[] chain = {"BTC", "ETH"};
        param.setLanguage(LanguageEnum.CN);
        param.setChainTypes(Arrays.asList(chain));
        template.postForEntityResult("/v1/app/changelly/get_currency_list", param, GetCurrencyAllResult.class);
    }

    @Test
    public void test_get_pair_info() throws Exception {
        RestfulTemplate template = new RestfulTemplate(HOST, accessid, accesskey);
        GetPairInfoParam param = new GetPairInfoParam();
        param.setFrom("ETH");
        param.setTo("USDT");
        template.getForEntityResult("/v1/app/changelly/get_pair_info", param, GetPairInfoResult.class);
    }

    @Test
    public void test_check_currency() throws Exception {
        RestfulTemplate template = new RestfulTemplate(HOST, accessid, accesskey);
        CheckCurrencyParam param = new CheckCurrencyParam();
        param.setSymbol("aaaa");
        param.setType(GetListTypeEnum.sell);
        template.getForEntityResult("/v1/app/changelly/check_currency", param, CheckCurrencyResult.class);
    }

    @Test
    public void test_get_exchange_page() throws Exception {
        RestfulTemplate template = new RestfulTemplate(HOST, accessid, accesskey);
        GetOrderPageParam param = new GetOrderPageParam();
        param.setUid("aaa");
        param.setPageIndex(1);
        param.setPageSize(10);
        template.getForEntityPage("/v1/app/changelly/get_exchange_page", param, GetOrderPageResult.class);
    }

    @Test
    public void test_get_exchange_detail() throws Exception {
        RestfulTemplate template = new RestfulTemplate(HOST, accessid, accesskey);
        GetOrderDetailParam param = new GetOrderDetailParam();
        param.setExchangeId("54rn76e69ouk5i2u");
        template.getForEntityResult("/v1/app/changelly/get_exchange_detail", param, GetOrderDetailResult.class);
    }


    @Test
    public void test_create_exchange() throws Exception {
        RestfulTemplate template = new RestfulTemplate(HOST, accessid, accesskey);
        CreateTransactionParam param = new CreateTransactionParam();
        param.setUid("39f306c907c3e886f216e9269e48e0967679b86c2b30005a7b94c7517833c935");
        param.setAmount(new BigDecimal("0.00075"));
        param.setFrom("BTC");
        param.setFromAddress("1HhdzZNfAdpdtbGnRNkat9YSc5BN6TKenG");
        param.setTo("ETH");
        param.setToAddress("0x7AB38077ab29DeE6Bc2dD87CB0eFDf06D18962Dd");
        template.postForEntityResult("/v1/app/changelly/create_exchange", param, CreateTransactionResult.class);
    }

    @Test
    public void test_report_trans_result() throws Exception {
        RestfulTemplate template = new RestfulTemplate(HOST, accessid, accesskey);
        ReportTransResultParam param = new ReportTransResultParam();
        param.setExchangeId("18q88xn9w9f8l3k9");
        param.setStatus(ExchangeStatus.cancel);
        template.postForEntityBase("/v1/app/changelly/report_trans_result", param);
    }
}
