package com.topnetwork.wallet;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.topnetwork.wallet.param.wallet.*;
import com.topnetwork.wallet.result.ETHConfigResult;
import com.topnetwork.wallet.result.wallet.ETHTransactionResult;
import org.junit.Test;

import com.topnetwork.common.HttpServer;
import com.topnetwork.common.TestHeader;
import com.topnetwork.tokenview.param.BalanceParam;
import com.topnetwork.tokenview.result.BalanceResult;
import com.topnetwork.wallet.common.enums.ChainTypeEnum;
import com.topnetwork.wallet.common.enums.CurrencyUnitEnum;
import com.topnetwork.wallet.result.wallet.ETHQueryBalanceResult;
import com.topnetwork.wallet.result.wallet.EthGasResult;

import com.gitee.magic.framework.base.rest.RestfulTemplate;
import com.gitee.magic.context.RestConverterEditor;
import com.gitee.magic.core.json.JsonArray;
import com.gitee.magic.core.json.JsonObject;
import com.gitee.magic.core.utils.StringUtils;

public class CoinTest extends TestHeader {

    @Test
    public void test_my() throws Exception {
//		for (int i = 2; i <= 6; i++) {			
        List<CoinAddressBean> addressList = readFile(2);

        FileOutputStream fos = new FileOutputStream(new File("D:\\work\\doc\\wallet\\" + 2 + ".txt"));
        OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
        BufferedWriter bw = new BufferedWriter(osw);
//		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("E:/phsftp/evdokey/evdokey_201103221556.txt")), "UTF-8"));


        for (CoinAddressBean arr : addressList) {
            bw.write(arr.toString() + "\t\n");
        }
        bw.close();
        osw.close();
        fos.close();
//		}

    }

    private List<CoinAddressBean> readFile(int i) {
        List<CoinAddressBean> addressList = null;
        try {
            String fileStr = readFileByBytes("D:\\work\\doc\\wallet\\" + i + ".json");
            List<CoinAddressBean> ls = RestConverterEditor.converterList(CoinAddressBean.class, new JsonArray(fileStr));
            addressList = new ArrayList<CoinAddressBean>();
            for (CoinAddressBean coinAddressBean : ls) {
                if (coinAddressBean.getChainType().equals("BTC")) {
                    try {
                        //调用tokenview
                        String host = "https://www.hiwallet.org/tokenview";
                        RestfulTemplate template = new RestfulTemplate(host, "8412f654f8662d033111fc453edc5b63", "c4jWPrC8mdRC+Nt3ftdwDDi564O3L0+FqMjRRHwHigBwmmoSZB7Pug==");
                        BalanceParam params = new BalanceParam();
                        params.setAddress(coinAddressBean.getAddress());
                        params.setChainType(coinAddressBean.getChainType());
                        params.setContractAddress(params.getContractAddress());
                        BalanceResult btbResult = template.getForEntityResult("/v1/utxo/balance", params, BalanceResult.class);
                        coinAddressBean.setBalance(btbResult.getBalance());
                        addressList.add(coinAddressBean);
                    } catch (Exception e) {
                        System.out.println("异常了" + e);
                        continue;
                    }
                } else if (coinAddressBean.getChainType().equals("ETH")) {
                    try {
                        if (!StringUtils.isBlank(coinAddressBean.getContractAddress())) {
                            ETHQueryErc20BalanceParam param = new ETHQueryErc20BalanceParam();
                            param.setContract(coinAddressBean.getContractAddress());
                            param.setAddress(coinAddressBean.getAddress());
                            RestfulTemplate template = new RestfulTemplate(HOST, accessid, accesskey);
                            ETHQueryBalanceResult balanceR = template.postForEntityResult("/v1/app/eth/query_erc20_balance", param, ETHQueryBalanceResult.class);
                            coinAddressBean.setBalance(balanceR.getBalance());
                        } else {
                            ETHQueryBalanceParam param = new ETHQueryBalanceParam();
                            param.setAddress(coinAddressBean.getAddress());
                            RestfulTemplate template = new RestfulTemplate(HOST, accessid, accesskey);
                            ETHQueryBalanceResult balanceR = template.postForEntityResult("/v1/app/eth/query_balance", param, ETHQueryBalanceResult.class);
                            coinAddressBean.setBalance(balanceR.getBalance());
                        }
                        addressList.add(coinAddressBean);
                    } catch (Exception e) {
                        System.out.println("异常了" + e);
                        continue;
                    }
                } else {
                    continue;
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return addressList;
    }

    private String readFileByBytes(String fileName) throws IOException {
        File file = new File(fileName);
        InputStream in = null;
        StringBuffer sb = new StringBuffer();
        if (file.isFile() && file.exists()) { //判断文件是否存在
            System.out.println("以字节为单位读取文件内容，一次读多个字节：");
            byte[] tempbytes = new byte[1024];
            int byteread = 0;
            in = new FileInputStream(file);
            while ((byteread = in.read(tempbytes)) != -1) {
                String str = new String(tempbytes, 0, byteread);
                sb.append(str);
            }
        } else {
            System.out.println("找不到指定的文件，请确认文件路径是否正确");
        }
        return sb.toString();
    }

    @Test
    public void test_query_erc20_balance() throws Exception {
        ETHQueryErc20BalanceParam param = new ETHQueryErc20BalanceParam();
        param.setContract("0xdcd85914b8ae28c1e62f1c488e1d968d5aaffe2b");
        param.setAddress("0x7AB38077ab29DeE6Bc2dD87CB0eFDf06D18962Dd");
        RestfulTemplate template = new RestfulTemplate(HOST, accessid, accesskey);
        template.postForEntityBase("/v1/app/eth/query_erc20_balance", param);
    }

    @Test
    public void test_query_price() throws Exception {
        RestfulTemplate template = new RestfulTemplate(HOST, accessid, accesskey);
        template.getForEntityBase("/test_price", null);
    }

    @Test
    public void test_token_price() throws Exception {
        RestfulTemplate template = new RestfulTemplate(HOST, accessid, accesskey);
        template.getForEntityBase("/token_price/get_ids", null);
    }


    @Test
    public void test_get_coin_price() throws Exception {
        ETHGetCoinPriceParam param = new ETHGetCoinPriceParam();
        param.setVsCurrency("GBP");
        param.setIds("EOS");
        param.setTest(true);
        RestfulTemplate template = new RestfulTemplate(HOST, accessid, accesskey);
        template.postForEntityBase("/v1/app/eth/get_coin_price", param);
    }

    @Test
    public void test_get_eth_config() throws Exception {
        RestfulTemplate template = new RestfulTemplate(HOST, accessid, accesskey);
        ETHGetConfigParam param = new ETHGetConfigParam();
        param.setToken(false);
        template.getForEntityResult("/v1/app/eth/get_eth_config", param, ETHConfigResult.class);
    }

    @Test
    public void test_get_coin_price_v2() throws Exception {
        for (int i = 1; i < 400; i++) {
            ETHGetCoinPriceV2Param param = new ETHGetCoinPriceV2Param();
            param.setVsCurrency(CurrencyUnitEnum.CNY);
            param.setCoinId(Long.valueOf(i));
            RestfulTemplate template = new RestfulTemplate(HOST, accessid, accesskey);
            template.postForEntityBase("/v2/app/eth/get_coin_price", param);
        }

    }

    public void test_query_balance() throws Exception {
//
//        for(int i= 0;i<=100000000;i++){
//            BigDecimal bigDecimal = new BigDecimal("0.0535826");
//            String s = bigDecimal.toString();
//
//            try {
//                Double d = Double.valueOf(s);
//            } catch (Exception e) {
//                System.out.println(s);
//            }
//        }
        ETHQueryBalanceParam param = new ETHQueryBalanceParam();
        param.setAddress("0xC8CE40F24C546af9101Ae7bd40fe4D19D4fB123D");
        RestfulTemplate template = new RestfulTemplate(HOST, accessid, accesskey);
        template.postForEntityBase("/v1/app/eth/query_balance", param);
    }

    @Test
    public void test_get_eth_nonce() throws Exception {
        HttpServer server = new HttpServer();
        Map<String, Object> content = new HashMap<String, Object>();
        content.put("address", "0x739e9f93566d7fb10697cce9b34c23f4f4e3cca7");
        server.postRequest("/v1/app/eth/get_eth_nonce", accessid, accesskey, content, new HttpServer.Result() {
            @Override
            public void success(JsonObject json) {
                System.out.println(json);
            }
        });
    }

    @Test
    public void test_get_eth_transaction_list_by_account() throws Exception {

        ETHTransactionListParam content = new ETHTransactionListParam();
        content.setAddress("0x9cbe0cfdaea7a9830977cb41f97e40dad543ccb8");
        content.setPageIndex(1);
        content.setPageSize(20);

        RestfulTemplate template = new RestfulTemplate(HOST, accessid, accesskey);
        List<ETHTransactionResult> ethGasResult = template.postForEntityListResult("/v1/app/eth/get_eth_transaction_list_by_account", content, ETHTransactionResult.class);
    }

    @Test
    public void test_get_gas_list() throws Exception {
        RestfulTemplate template = new RestfulTemplate(HOST, accessid, accesskey);
        EthGasResult ethGasResult = template.postForEntityResult("/v1/app/eth/get_gas_list", null, EthGasResult.class);
    }
    @Test
    public void test_get_gas_list_new() throws Exception {
        RestfulTemplate template = new RestfulTemplate(HOST, accessid, accesskey);
        EthGasResult ethGasResult = template.postForEntityResult("/v1/app/eth/get_gas_list/new", null, EthGasResult.class);
    }

    public void test_get_config() throws Exception {
        CoinConfigParam param = new CoinConfigParam();
        param.setChainType(ChainTypeEnum.TOP);
        RestfulTemplate template = new RestfulTemplate(HOST, accessid, accesskey);
        template.postForEntityBase("/coin/fee/get_config", param);
    }

    public void test_get_btc_fee() throws Exception {
        RestfulTemplate template = new RestfulTemplate(HOST, accessid, accesskey);
        template.postForEntityBase("/v1/app/btc/get_btc_fee", null);
    }


    public void test_send_transaction() throws Exception {
        ETHSendTransactionParam param = new ETHSendTransactionParam();
        param.setHexValue("eyJoZXhWYWx1ZSI6IjB4Zjg2ZjgyMDMwMjg1MDEyYTA1ZjIwMDgzMDE4NmEwOTQ3NzhmZjE0OTZkY2E1ZTI0MjczODYzNjYxY2ZhNzdkMTI0NTEzZTY3ODgwNjJmYzE2MGUyZGIxYzAwODAxY2EwZDliY2Q1YjdmMjNkOWM4ODQ3YzNiMmNlMDE4NTZjYzI1YTNlOGVjMmRkMjQ4OTNkZDAzZTg2OTFhZWEzM2JiOGEwMTM4NTAyNDcwNGRhODk2NTg0MjFlMTlkNjYxMDVhOGMxYmZmY2VkMWE4M2Y1YjBhZTdlNGFiYWU1NmY3MDkxOSJ9");
        RestfulTemplate template = new RestfulTemplate(HOST, accessid, accesskey);
        template.postForEntityBase("/v1/app/eth/send_transaction", param);
    }
}
