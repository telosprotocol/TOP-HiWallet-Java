package com.topnetwork.coin.top;

import org.springframework.util.StringUtils;
import org.topj.account.Account;
import org.topj.core.Topj;
import org.topj.methods.response.AccountInfoResponse;
import org.topj.methods.response.ResponseBase;
import org.topj.methods.response.tx.XTransactionResponse;
import org.topj.procotol.http.HttpService;
import org.topj.tx.NoOpProcessor;

import java.io.IOException;
import java.math.BigInteger;

/**
 * @ClassName Test
 * @Description
 * @Author bran
 * @Date 2020/10/13 17:33
 */
public class Test {
    static Topj instance;
    static HttpService httpService;
    public static void main(String[] args) throws IOException {

        Boolean b = null;
        if(b){
            System.out.println("aaa");
        }
        httpService = new HttpService("http://157.230.84.88:19081");
        instance = Topj.build(httpService);
        instance.setTransactionReceiptProcessor(new NoOpProcessor());
//        Account account1 = new Account("0x5b9c1831f415570be4f8b0a17350476f4167e9265fb1c03e64a8022f99ee1e7f");
//       for(int i = 0;i<100;i++){
           Account account1 = new Account();
           account1.setIdentityToken("123");
           instance.getAccount(account1);
           System.out.println(account1.getBalance());
           System.out.println(account1.getAddress());
//       }

//        ResponseBase<XTransactionResponse> aaa = instance.transfer(account1, "T-0-LWX1sLGaMp6Li5k7DfFjLubGM3rCFVmWPk", new BigInteger("20000000000"), "测试");
//        System.out.println(aaa.getData().getOriginalTxInfo().getTxHash());
    }
}
