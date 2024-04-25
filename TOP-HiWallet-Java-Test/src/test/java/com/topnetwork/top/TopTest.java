package com.topnetwork.top;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.topnetwork.common.TestHeader;
import com.topnetwork.wallet.param.wallet.top.GetBaseTokenParam;
import com.topnetwork.wallet.param.wallet.top.TransactionListParam;
import com.topnetwork.wallet.param.wallet.top.TransferParam;
import com.topnetwork.wallet.result.wallet.top.GasInfoResult;
import com.topnetwork.wallet.result.wallet.top.GetBalanceResult;
import com.topnetwork.wallet.result.wallet.top.GetTop2gasRatioResult;
import com.topnetwork.wallet.result.wallet.top.GetTxDepositResult;
import com.topnetwork.wallet.result.wallet.top.GetTxListResult;
import com.topnetwork.wallet.result.wallet.top.TransferGasResult;
import com.topnetwork.wallet.result.wallet.top.TransferResult;

import com.gitee.magic.framework.base.rest.RestfulTemplate;

/**
 * @ClassName EOSTest
 * @Description
 * @Author bran
 * @Date 2020/4/18 14:07
 */
public class TopTest extends TestHeader {

    @Test
    public void test_get_account() throws Exception {
        GetBaseTokenParam param = new GetBaseTokenParam();
        param.setToken("123");
        param.setAddress("T00000LhHKdV8rc9GHkgJUPS39XLCESGnJmJ2Zjg");
        RestfulTemplate template = new RestfulTemplate(HOST, accessid, accesskey);
        template.getForEntityBase("/v1/app/top/get_account", param);

    }

    @Test
    public void test_get_top2gas_ratio() throws Exception {
        RestfulTemplate template = new RestfulTemplate(HOST, accessid, accesskey);
        template.getForEntityResult("/v1/app/top/get_top2gas_ratio", GetTop2gasRatioResult.class);
    }

    @Test
    public void test_get_tx_deposit() throws Exception {
        RestfulTemplate template = new RestfulTemplate(HOST, accessid, accesskey);
        template.getForEntityResult("/v1/app/top/get_tx_deposit", GetTxDepositResult.class);

    }

    @Test
    public void test_get_transfer_gas() throws Exception {
        RestfulTemplate template = new RestfulTemplate(HOST, accessid, accesskey);
        GetBaseTokenParam param = new GetBaseTokenParam();
        param.setAddress("T00000LdVn7X4zs28KF2VUwrDbiuVAKrkGoimFmg");
        template.getForEntityResult("/v1/app/top/get_transfer_gas",param, TransferGasResult.class);

    }


    @Test
    public void test_get_tx_list() throws Exception {
        RestfulTemplate template = new RestfulTemplate(HOST, accessid, accesskey);
        TransactionListParam param = new TransactionListParam();
        param.setPageIndex(1);
        param.setPageSize(10);
        param.setAddress("T00000LdVn7X4zs28KF2VUwrDbiuVAKrkGoimFmg");
        template.getForEntityPage("/v1/app/top/get_tx_list", param, GetTxListResult.class);

    }

    @Test
    public void test_transfer() throws Exception {
        RestfulTemplate template = new RestfulTemplate(HOST, accessid, accesskey);
        TransferParam param = new TransferParam();
        param.setRequestModel("f9049bb904557b2276657273696f6e223a22312e30222c227461726765745f6163636f756e745f61646472223a225430303030304c5943574239704d4c68755170647375616367456e64453862393839386854773274222c22746f6b656e223a22222c226d6574686f64223a2273656e645472616e73616374696f6e222c2273657175656e63655f6964223a313632363734373134373832332c22626f6479223a227b22706172616d73223a7b2274785f74797065223a342c2274785f6c656e223a302c2274785f7374727563747572655f76657273696f6e223a302c22746f5f6c65646765725f6964223a302c2266726f6d5f6c65646765725f6964223a302c2274785f6465706f736974223a3130303030302c2274785f6578706972655f6475726174696f6e223a3130302c2273656e645f74696d657374616d70223a313632363734373134382c2274785f72616e646f6d5f6e6f6e6365223a302c227072656d69756d5f7072696365223a302c226c6173745f74785f6e6f6e6365223a31302c226c6173745f74785f68617368223a22307834326333373466396636333063616530222c2274785f68617368223a22307835343262396164343062636264623239343538633631633132346139663831303532386361623637306662653365353338343737363730316232626239656130222c22657874223a22222c226e6f7465223a227472616e736665722074657374222c226368616c6c656e67655f70726f6f66223a22222c22617574686f72697a6174696f6e223a22222c2273656e6465725f616374696f6e223a7b22616374696f6e5f68617368223a302c22616374696f6e5f74797065223a302c22616374696f6e5f73697a65223a302c2274785f73656e6465725f6163636f756e745f61646472223a225430303030304c5943574239704d4c68755170647375616367456e64453862393839386854773274222c22616374696f6e5f6e616d65223a22222c22616374696f6e5f706172616d223a223078303030303030303030613030303030303030303030303030222c22616374696f6e5f657874223a22222c22616374696f6e5f617574686f72697a6174696f6e223a22227d2c2272656365697665725f616374696f6e223a7b22616374696f6e5f68617368223a302c22616374696f6e5f74797065223a362c22616374696f6e5f73697a65223a302c2274785f72656365697665725f6163636f756e745f61646472223a2254383030303064353066353037663464383165356266663637353938313564633338393264386132393039303938222c22616374696f6e5f6e616d65223a22222c22616374696f6e5f706172616d223a223078303030303030303030613030303030303030303030303030222c22616374696f6e5f657874223a22222c22616374696f6e5f617574686f72697a6174696f6e223a22227d2c227075626c69635f6b6579223a22222c22636f6e6669726d5f756e69745f696e666f223a7b22657865635f737461747573223a307d7d7d227db84100f3bc179bb2c8c3c8d2a0d365a84b6c02ccce0ba022621d765b385e0ef3ec78c60b0e2398fab6d6aa98bb4bd8a15396f4630462a520e95005173933f47a2c2f48");
        param.setToken("");
        param.setAddress("T00000LYCWB9pMLhuQpdsuacgEndE8b9898hTw2t");
        template.postForEntityResult("/v1/app/top/transfer", param, TransferResult.class);

    }

    //T-0-LgPHeCbBua5UYM3avUzDFHYePYaUXQK2DX

    @Test
    public void test_get_balance() throws Exception {
//        for (int i = 0; i <= 100; i++) {
            RestfulTemplate template = new RestfulTemplate(HOST, accessid, accesskey);
            GetBaseTokenParam param = new GetBaseTokenParam();
            param.setToken("");
            param.setAddress("T00000LhHKdV8rc9GHkgJUPS39XLCESGnJmJ2Zjg");
            template.getForEntityResult("/v1/app/top/get_balance", param, GetBalanceResult.class);

//        }

    }

    public String getAccount() {
        List<String> accounts = Arrays.asList("T-0-LKQHYYQXcpzFLdhdudtnJhdKDM7ZifsEYz", "T-0-LPURdcLCoAa5P5ThmADgDPobmM8eebxdmP ");
        int i = (int) (Math.random() * accounts.size());
        return accounts.get(i);
    }

    @Test
    public void test_get_gas_info() throws Exception {
        RestfulTemplate template = new RestfulTemplate(HOST, accessid, accesskey);
        GetBaseTokenParam param = new GetBaseTokenParam();
        param.setToken("");
        param.setAddress("T00000LdVn7X4zs28KF2VUwrDbiuVAKrkGoimFmg");
        template.getForEntityResult("/v1/app/top/get_gas_info", param, GasInfoResult.class);
    }

}
