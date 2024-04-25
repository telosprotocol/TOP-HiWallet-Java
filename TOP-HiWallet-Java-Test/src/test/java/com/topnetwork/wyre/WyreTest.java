package com.topnetwork.wyre;

import com.topnetwork.common.TestHeader;
import com.topnetwork.wallet.common.enums.LanguageEnum;
import com.topnetwork.wallet.common.enums.changelly.ExchangeStatus;
import com.topnetwork.wallet.common.enums.changelly.GetListTypeEnum;
import com.topnetwork.wallet.param.wallet.ETHGetCoinPriceV2Param;
import com.topnetwork.wallet.param.wallet.changelly.*;
import com.topnetwork.wallet.param.wyre.*;
import com.topnetwork.wallet.result.changelly.*;
import com.topnetwork.wallet.result.wallet.ETHGetCoinPriceResult;
import com.topnetwork.wallet.result.wyre.GetCountryPageResult;
import com.topnetwork.wallet.result.wyre.GetInfoResult;
import com.topnetwork.wallet.result.wyre.GetReservationResult;
import com.topnetwork.wallet.result.wyre.GetReservationV2Result;
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
public class WyreTest extends TestHeader {

    @Test
    public void test_add_country() throws Exception {
        RestfulTemplate template = new RestfulTemplate(HOST, manageraccessid, manageraccesskey);
        AddCountryParam param = new AddCountryParam();
        param.setCountryCode("US");
        param.setCurrencies("USD");
        param.setCurrenciesIcon("74e76c154d51de040ef1f6967dc7a3b0");
        param.setName("美国");
        List<Long> longList = Arrays.asList(1L, 310L);
        param.setCoinId(longList);
        template.postForEntityBase("/wyre/add_country", param);
    }

    @Test
    public void test_update_country() throws Exception {
        RestfulTemplate template = new RestfulTemplate(HOST, manageraccessid, manageraccesskey);
        UpdateCountryParam param = new UpdateCountryParam();
        param.setCountryCode("US");
        param.setCurrencies("USD");
        param.setCurrenciesIcon("74e76c154d51de040ef1f6967dc7a3b0");
        param.setName("美国2");
        List<Long> longList = Arrays.asList(1L, 310L);
        param.setCoinId(longList);
        param.setCountryId(743799815099383808L);
        template.postForEntityBase("/wyre/update_country", param);
    }

    @Test
    public void test_get_country_page() throws Exception {
        RestfulTemplate template = new RestfulTemplate(HOST, manageraccessid, manageraccesskey);
        GetCountryPageParam param = new GetCountryPageParam();
        template.getForEntityPage("/wyre/get_country_page", param, GetCountryPageResult.class);
    }

    @Test
    public void test_delete_country() throws Exception {
        RestfulTemplate template = new RestfulTemplate(HOST, manageraccessid, manageraccesskey);
        DelCountryParam param = new DelCountryParam();
        param.setCountryId(743799815099383808L);
        template.deleteForEntityBase("/wyre/delete_country", param);
    }

    @Test
    public void test_get_coin_list() throws Exception {
        RestfulTemplate template = new RestfulTemplate(HOST, manageraccessid, manageraccesskey);
        template.getForEntityListResult("/wyre/get_coin_list", GetCoinListResult.class);
    }

    @Test
    public void test_info() throws Exception {
        RestfulTemplate template = new RestfulTemplate(HOST, accessid, accesskey);
        template.getForEntityResult("/v1/app/wyre/info", GetInfoResult.class);
    }

    @Test
    public void test_get_reservation() throws Exception {
        RestfulTemplate template = new RestfulTemplate(HOST, accessid, accesskey);
        template.getForEntityResult("/v1/app/wyre/get_reservation", GetReservationResult.class);
    }

    @Test
    public void test_get_reservation2() throws Exception {
        RestfulTemplate template = new RestfulTemplate(HOST, accessid, accesskey);
        GetReservationParam param = new GetReservationParam();
        param.setAmount(new BigDecimal("2.5"));
        param.setDest("0xa7D0F6E2349B3a003b48a4A979776C6710b99dB8");
        param.setDestCurrency("ETH");
        param.setPaymentMethod("apple-pay");
        param.setSourceCurrency("USD");
        template.getForEntityResult("/v2/app/wyre/get_reservation", param, GetReservationV2Result.class);
    }


    @Test
    public void test_check_currency() throws Exception {
        RestfulTemplate template = new RestfulTemplate(HOST, accessid, accesskey);
        CheckCurrencyParam param = new CheckCurrencyParam();
        param.setType(GetListTypeEnum.buy);
        param.setSymbol("BBB");
        template.getForEntityResult("/v2/app/changelly/check_currency", param, CheckCurrencyV2Result.class);
    }

    @Test
    public void test_get_coin_price() throws Exception {
        RestfulTemplate template = new RestfulTemplate(HOST, accessid, accesskey);
        GetCoinPriceParam param = new GetCoinPriceParam();
        param.setEnglishName("bitcoin");
        param.setVsCurrency("BBB");
        template.getForEntityResult("/v1/app/wyre/get_coin_price", param, ETHGetCoinPriceResult.class);
    }

}
