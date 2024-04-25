package com.topnetwork.wallet;

import java.util.*;

import com.topnetwork.wallet.common.enums.LanguageEnum;
import com.topnetwork.wallet.param.wallet.ETHGetCoinPriceParam;
import com.topnetwork.wallet.param.wallet.v2.CoinHotParam;
import com.topnetwork.wallet.param.wallet.v2.CoinSpeciesParam;
import com.topnetwork.wallet.param.wallet.v2.StakingSwitchParam;
import com.topnetwork.wallet.param.wallet.v2.TransactionDetailParam;
import com.topnetwork.wallet.result.wallet.CoinSpeciesResult;
import com.topnetwork.wallet.result.wallet.ETHGetCoinPriceResult;
import org.junit.Test;

import com.topnetwork.common.HttpServer;
import com.topnetwork.common.TestHeader;
import com.topnetwork.common.HttpServer.Result;

import com.gitee.magic.framework.base.rest.RestfulTemplate;
import com.gitee.magic.core.json.JsonObject;

public class CoinSpeciesTest extends TestHeader {

    @Test
    public void test_query_tx() throws Exception {
        TransactionDetailParam param = new TransactionDetailParam();
        param.setHash("0xbf445910fe45f0033ff11f717e84f33d52a53dbd69c5480dc7fedb9e0edaca56");
        RestfulTemplate template = new RestfulTemplate(HOST, accessid, accesskey);
        template.postForEntityBase("/v1/app/query_eth_tx", param);
    }

    @Test
    public void test_get_coin_price() throws Exception {
//        for (int i = 0; i <= 3000; i++) {
//            Thread thread = new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    System.out.println(Thread.currentThread().getName());
//                    ETHGetCoinPriceParam param = new ETHGetCoinPriceParam();
//                    param.setVsCurrency("USD");
//                    param.setIds("BNB");
//                    RESTfulTemplate template = new RESTfulTemplate(HOST, accessid, accesskey);
//                    ETHGetCoinPriceResult ethGetCoinPriceResult = template.postForEntityResult("/v1/app/eth/get_coin_price", param, ETHGetCoinPriceResult.class);
//                    System.out.println(ethGetCoinPriceResult);
//                }
//            });
//
//            thread.start();
//        }
//
//        Thread.sleep(100000);

    }

    @Test
    public void test_coin_findCoinSpecies() throws Exception {
        CoinSpeciesParam param = new CoinSpeciesParam();
        List<String> chain = Arrays.asList("BTC", "ETH","TOP");
        param.setChainType(chain);
        param.setCoinName("U");
        param.setLanguage(LanguageEnum.CN);
        param.setPageIndex(1);
        param.setPageSize(30);
        RestfulTemplate template = new RestfulTemplate(HOST, accessid, accesskey);
        template.postForEntityBase("/v2/app/find_coin_species", param);
    }

    @Test
    public void test_find_coin_species_by_englishName() throws Exception {
        Map<String, Object> content = new HashMap<String, Object>();
        content.put("englishName", "ABYSS");
        content.put("language", 0);
        HttpServer server = new HttpServer();
        server.postRequest("/v1/app/find_coin_species_by_englishName", accessid, accesskey, content, new Result() {

            @Override
            public void success(JsonObject json) {
                System.out.println(json);
            }

        });
    }

    @Test
    public void test_find_hot_coin_species() throws Exception {

        CoinHotParam param = new CoinHotParam();
        List<String> chain = Arrays.asList("EOS");
        param.setChainType(chain);
        param.setLanguage(LanguageEnum.CN);
        param.setPageIndex(1);
        param.setPageSize(30);
        RestfulTemplate template = new RestfulTemplate(HOST, accessid, accesskey);
        template.postForEntityPage("/v2/app/find_hot_coin_species", param,CoinSpeciesResult.class);
    }

    @Test
    public void test_get_staking_switch() throws Exception {
        Map<String, Object> content = new HashMap<String, Object>();
        content.put("version", "1.0.1");
        content.put("platform", 1);
        HttpServer server = new HttpServer();
        server.postRequest("/v1/app/get_staking_switch", accessid, accesskey, content, new Result() {

            @Override
            public void success(JsonObject json) {
                System.out.println(json);
            }

        });
    }

    @Test
    public void test_staking_switch_v2() throws Exception {
        StakingSwitchParam param = new StakingSwitchParam();
        param.setVersion("1.3.2");
        RestfulTemplate template = new RestfulTemplate(HOST, accessid, accesskey);
        template.postForEntityBase("/v2/app/get_staking_switch", param);
    }

    @Test
    public void test_find_main_coin_list() throws Exception {
        Map<String, Object> content = new HashMap<String, Object>();
        content.put("pageIndex", 1);
        content.put("pageSize", 10);
        // content.put("isHide", 0);
        //content.put("name", "aaa");
        HttpServer server = new HttpServer();
        server.postRequest("/coin/find_main_coin_list", accessid, accesskey, content, new Result() {

            @Override
            public void success(JsonObject json) {
                System.out.println(json);
            }

        });
    }

    public void test_find_main_coin_select() throws Exception {
        HttpServer server = new HttpServer();
        server.postRequest("/coin/find_main_coin_select", accessid, accesskey, null, new Result() {
            @Override
            public void success(JsonObject json) {
                System.out.println(json);
            }
        });
    }

    @Test
    public void test_find_token_list() throws Exception {
        Map<String, Object> content = new HashMap<String, Object>();
        content.put("pageIndex", 1);
        content.put("pageSize", 10);
        content.put("fid", 1);
        //content.put("name", "aaa");
        HttpServer server = new HttpServer();
        server.postRequest("/coin/find_token_list", accessid, accesskey, content, new Result() {

            @Override
            public void success(JsonObject json) {
                System.out.println(json);
            }

        });
    }

    @Test
    public void test_find_coin_detail() throws Exception {
        Map<String, Object> content = new HashMap<String, Object>();
        content.put("cid", 626734058533027840L);
        //content.put("name", "aaa");
        HttpServer server = new HttpServer();
        server.postRequest("/coin/find_coin_detail", accessid, accesskey, content, new Result() {

            @Override
            public void success(JsonObject json) {
                System.out.println(json);
            }

        });
    }

    @Test
    public void test_add_coin_species() throws Exception {
        Map<String, Object> content = new HashMap<String, Object>();
        content.put("englishName", "sss");
        content.put("chineseName", "测试");
        content.put("nickname", "text");
        content.put("tokenOrder", 1);
        content.put("isHide", 0);
        content.put("logoUrl", "zilliqa__ZIL.png");
        content.put("fatherId", 1);
        content.put("contractAddress", "0x05f4a42e251f2d52b8ed15e9fedaacfcef1fad27");
        content.put("tokenDescriptionUrl", "https://etherscan.io/token/0x05f4a42e251f2d52b8ed15e9fedaacfcef1fad27");
        content.put("chineseDescription", "买点水果能打得过");
        content.put("englishDescription", "dsgfdsdsfsdsdffsdfdsfdsfdf");
        content.put("isHot", 1);
        content.put("level", 2);
        content.put("tokenDecimals", 6);
        content.put("showDecimals", 6);
        HttpServer server = new HttpServer();
        server.postRequest("/coin/add_coin_species", loginaccessid, loginaccesskey, content, new Result() {

            @Override
            public void success(JsonObject json) {
                System.out.println(json);
            }

        });
    }

    @Test
    public void test_upd_coin_species() throws Exception {
        Map<String, Object> content = new HashMap<String, Object>();
        content.put("id", 625743791181004800L);
        content.put("englishName", "sss");
        content.put("chineseName", "测试");
        content.put("nickname", "text1111");
        content.put("tokenOrder", 1);
        content.put("isHide", 0);
        content.put("logoUrl", "zilliqa__ZIL.png");
        content.put("fatherId", 1);
        content.put("contractAddress", "0x05f4a42e251f2d52b8ed15e9fedaacfcef1fad27");
        content.put("tokenDescriptionUrl", "https://etherscan.io/token/0x05f4a42e251f2d52b8ed15e9fedaacfcef1fad27");
        content.put("chineseDescription", "买点水果能打得过");
        content.put("englishDescription", "dsgfdsdsfsdsdffsdfdsfdsfdf");
        content.put("isHot", 1);
        content.put("level", 2);
        content.put("tokenDecimals", 6);
        content.put("showDecimals", 6);
        HttpServer server = new HttpServer();
        server.postRequest("/coin/upd_coin_species", loginaccessid, loginaccesskey, content, new Result() {

            @Override
            public void success(JsonObject json) {
                System.out.println(json);
            }

        });
    }

    @Test
    public void test_del_coin_detail() throws Exception {
        Map<String, Object> content = new HashMap<String, Object>();
        content.put("cid", 625743791181004800L);
        HttpServer server = new HttpServer();
        server.postRequest("/coin/del_coin_detail", loginaccessid, loginaccesskey, content, new Result() {

            @Override
            public void success(JsonObject json) {
                System.out.println(json);
            }

        });
    }

    @Test
    public void test_up() throws Exception {
        HttpServer server = new HttpServer();
        server.postRequest("/v1/app/coin/up", accessid, accesskey, null, new Result() {

            @Override
            public void success(JsonObject json) {
                System.out.println(json);
            }

        });
    }
}
