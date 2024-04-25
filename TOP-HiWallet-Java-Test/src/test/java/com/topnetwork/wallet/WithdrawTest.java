//package com.topnetwork.wallet;
//
//import com.topnetwork.common.TestHeader;
//import com.topnetwork.ethexchange.enums.Status;
//import com.topnetwork.ethexchange.enums.TransactionTypeEnum;
//import com.topnetwork.ethexchange.param.NotifyParam;
//import com.topnetwork.wallet.common.enums.LanguageEnum;
//import com.topnetwork.wallet.common.enums.changelly.ExchangeStatus;
//import com.topnetwork.wallet.common.enums.changelly.GetListTypeEnum;
//import com.topnetwork.wallet.param.wallet.changelly.*;
//import com.topnetwork.wallet.param.wallet.withdraw.*;
//import com.topnetwork.wallet.param.wallet.withdraw.GetOrderDetailParam;
//import com.topnetwork.wallet.param.wallet.withdraw.GetOrderPageParam;
//import com.topnetwork.wallet.result.changelly.*;
//import com.topnetwork.wallet.result.wallet.invite.PhoneCodeResult;
//import com.topnetwork.wallet.result.wallet.withdraw.*;
//import com.topnetwork.wallet.result.wallet.withdraw.GetOrderDetailResult;
//import com.topnetwork.wallet.result.wallet.withdraw.GetOrderPageResult;
//import org.junit.Test;
//import start.framework.commons.rest.RESTfulTemplate;
//import com.gitee.magic.framework.base.result.BaseResponse;
//import com.gitee.magic.core.exception.ApplicationException;
//
//import java.math.BigDecimal;
//import java.util.Arrays;
//import java.util.concurrent.CountDownLatch;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
///**
// * @ClassName WithdrawTest
// * @Description
// * @Author bran
// * @Date 2020/6/15 16:47
// */
//public class WithdrawTest extends TestHeader {
//
//
//    @Test
//    public void test_get_order_page() throws Exception {
//        GetOrderPageParam param = new GetOrderPageParam();
//        param.setPageSize(142);
//        RESTfulTemplate template = new RESTfulTemplate(HOST, manageraccessid, manageraccesskey);
//        template.getForEntityPage("/withdraw/get_order_page", param, GetOrderPageResult.class);
//    }
//
//    @Test
//    public void test_rejected_orders() throws Exception {
//        ApprovedOrdersParam param = new ApprovedOrdersParam();
//        param.setOrderId(Arrays.asList(730820801581809664L, 730820889590890496L, 730820924286173184L));
//        RESTfulTemplate template = new RESTfulTemplate(HOST, manageraccessid, manageraccesskey);
//        template.postForEntityBase("/withdraw/rejected_orders", param);
//    }
//
//    @Test
//    public void test_get_order_detail() throws Exception {
//        GetOrderDetailParam param = new GetOrderDetailParam();
//        param.setOrderId(730820801581809664L);
//        RESTfulTemplate template = new RESTfulTemplate(HOST, manageraccessid, manageraccesskey);
//        GetOrderDetailResult getOrderDetailResult = template.getForEntityResult("/withdraw/get_order_detail", param, GetOrderDetailResult.class);
//        System.out.println(getOrderDetailResult.getAirDropAmount());
//    }
//
//    @Test
//    public void test_approved_orders() throws Exception {
//        ApprovedOrdersParam param = new ApprovedOrdersParam();
//        param.setOrderId(Arrays.asList(732553805748502528L,
//                731197314344943616L));
//        RESTfulTemplate template = new RESTfulTemplate(HOST, manageraccessid, manageraccesskey);
//        template.postForEntityBase("/withdraw/approved_orders", param);
//    }
//
//    @Test
//    public void test_get_airdrop_status() throws Exception {
//        RESTfulTemplate template = new RESTfulTemplate(HOST, clientaccessid, clientaccesskey);
//        template.getForEntityResult("/v1/app/airdrop/get_airdrop_status", null, GetAirdropStatusResult.class);
//    }
//
//
//    @Test
//    public void test_get_airdrop_info() throws Exception {
//        RESTfulTemplate template = new RESTfulTemplate(HOST, clientaccessid, clientaccesskey);
//        template.getForEntityResult("/v1/app/airdrop/get_airdrop_info", null, GetAirdropInfoResult.class);
//    }
//
//    @Test
//    public void test_get_phone_code() throws Exception {
//        PhoneCodeParam phoneCodeParam = new PhoneCodeParam();
//        phoneCodeParam.setLanguage(LanguageEnum.CN);
//        phoneCodeParam.setCode("sdfsd");
//        phoneCodeParam.setXaxis(BigDecimal.ZERO);
//        phoneCodeParam.setYaxis(BigDecimal.ZERO);
//        RESTfulTemplate template = new RESTfulTemplate(HOST, clientaccessid, clientaccesskey);
//        template.getForEntityResult("/v1/app/airdrop/get_phone_code", phoneCodeParam, PhoneCodeResult.class);
//    }
//
//    @Test
//    public void test_confirm_airdrop() throws Exception {
//        ConfirmAirdropParam param = new ConfirmAirdropParam();
//        param.setAddress("0x158BD57cA05a290D9D002D57d00c115b9807e2DC");
//        param.setAmount(new BigDecimal("899"));
//        param.setCode("000000");
//        RESTfulTemplate template = new RESTfulTemplate(HOST, clientaccessid, clientaccesskey);
//        template.postForEntityResult("/v1/app/airdrop/confirm_airdrop", param, AirdropDetailResult.class);
//    }
//
//    @Test
//    public void test_get_airdrop_page() throws Exception {
//        GetAirdropPageParam param = new GetAirdropPageParam();
//        RESTfulTemplate template = new RESTfulTemplate(HOST, clientaccessid, clientaccesskey);
//        template.getForEntityPage("/v1/app/airdrop/get_airdrop_page", param, AirdropSimpleResult.class);
//    }
//
//    @Test
//    public void test_get_airdrop_detail() throws Exception {
//        GetAirdropDetailParam param = new GetAirdropDetailParam();
//        param.setTranId("493abe55-7ddf-4eb0-aa42-54cdd29a3816");
//        RESTfulTemplate template = new RESTfulTemplate(HOST, clientaccessid, clientaccesskey);
//        template.getForEntityResult("/v1/app/airdrop/get_airdrop_detail", param, AirdropDetailResult.class);
//    }
//
//    ExecutorService service = Executors.newFixedThreadPool(20);
//
//    @Test
//    public void test_notify() throws Exception {
////        String[] ids = {"bfc20b56-3f31-4f76-ac06-0194b95125ab",
////                        "10c696ea-34ae-4417-a24f-0e14417bca7c",
////                        "cf3c2d40-1433-481e-9d8d-aadd937b2ad6",
////                        "493abe55-7ddf-4eb0-aa42-54cdd29a3816",
////                        "45ff0b50-4bd5-4d75-ba9c-8f2f4585e318",
////                        "60cc93fb-bf6f-45d2-8cb7-a9f07a86317e",
////                        "2cb08a6b-4c0e-4ae6-8124-c05a36bd055c",
////                        "97f34337-3248-4555-be8d-2d76b6438290",
////                        "97f34337-3248-4555-be8d-2d76b6438291",
////                        "97f34337-3248-4555-be8d-2d76b6438292"};
////        for (String id : ids) {
//        NotifyParam param = new NotifyParam();
//        param.setAmount(new BigDecimal("4700"));
//        param.setBlockNumber(2561L);
//        param.setClientOrderId("68b5f9a6-6e80-499c-80b7-b36678b8ea5b");
//        param.setFromAddress("0x158BD57cA05a290D9D002D57d00c115b9807e2DC");
//        param.setHash("0x158BD57cA05a290D9D002D57d00c115b9807e2DC");
//        param.setStatus(Status.SUCCESS);
//        param.setSymbol("TOP");
//        param.setToAddress("0x158BD57cA05a290D9D002D57d00c115b9807e2DC");
//        param.setTransactionType(TransactionTypeEnum.WITHDRAW);
//        param.setUserId("4F2317AE9B625A487B350D1E1DBB5130");
//        RESTfulTemplate template = new RESTfulTemplate(HOST, "ccc38b02c98ed57c586ac9cfbce40d64", "Y2NjMzhiMDJjOThlZDU3YzU4NmFjOWNmYmNlNDBkNjQ=");
//        template.postForEntityResult("/payment/notify", param, BaseResponse.class);
////        }
//
////        CountDownLatch latch = new CountDownLatch(5);
////        for (int i = 0; i <= 5; i++) {
////
////            service.submit(new Runnable() {
////                @Override
////                public void run() {
////                    try {
////                        NotifyParam param = new NotifyParam();
////                        param.setAmount(new BigDecimal("4700"));
////                        param.setBlockNumber(2561L);
////                        param.setClientOrderId("45ff0b50-4bd5-4d75-ba9c-8f2f4585e318");
////                        param.setFromAddress("0x158BD57cA05a290D9D002D57d00c115b9807e2DC");
////                        param.setHash("0x158BD57cA05a290D9D002D57d00c115b9807e2DC");
////                        param.setStatus(Status.SUCCESS);
////                        param.setSymbol("TOP");
////                        param.setToAddress("0x158BD57cA05a290D9D002D57d00c115b9807e2DC");
////                        param.setTransactionType(TransactionTypeEnum.WITHDRAW);
////                        param.setUserId("4F2317AE9B625A487B350D1E1DBB5130");
////                        RESTfulTemplate template = new RESTfulTemplate(HOST, "ccc38b02c98ed57c586ac9cfbce40d64", "Y2NjMzhiMDJjOThlZDU3YzU4NmFjOWNmYmNlNDBkNjQ=");
////                        template.postForEntityResult("/payment/notify", param, BaseResponse.class);
////                    } catch (Exception e) {
////                        e.printStackTrace();
////                    }finally {
////                        latch.countDown();
////                    }
////                }
////            });
////        }
////        try {
////            latch.await();
////        } catch (InterruptedException e) {
////            throw new ApplicationException(e);
////        }
//
//    }
//
//}
