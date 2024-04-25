//package com.topnetwork.wallet.controller;
//
//import com.base.core.context.annotation.SecretKeyAuthorityCheck;
//import com.base.core.context.annotation.ValidRequestParam;
//import com.base.core.context.mvc.BaseController;
//import com.topnetwork.coin.EthCoinService;
//import com.topnetwork.tokenview.common.enums.CurrencyUnitEnum;
//import com.topnetwork.tokenview.param.TokenPriceParam;
//import com.topnetwork.tokenview.result.TokenPriceResult;
//import com.topnetwork.wallet.entity.CoinSpecies;
//import com.topnetwork.wallet.param.wallet.ETHGetCoinPriceParam;
//import com.topnetwork.wallet.param.wallet.v1.GetEOSNodeParam;
//import com.topnetwork.wallet.result.wallet.ETHGetCoinPriceResult;
//import com.topnetwork.wallet.result.wallet.GetEOSNodeResult;
//import com.topnetwork.wallet.service.CoinSpeciesService;
//import io.swagger.v3.oas.annotations.Operation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//import start.framework.commons.rest.RESTfulTemplate;
//import com.gitee.magic.framework.base.result.BaseResponse;
//import com.gitee.magic.framework.base.result.PageResponse;
//
//import java.util.List;
//
///**
// * @ClassName TestController
// * @Description
// * @Author bran
// * @Date 2020/4/26 15:24
// */
//@RestController
//public class TestController extends BaseController {
//
//    @Autowired
//    private CoinSpeciesService coinSpeciesService;
//    @Autowired
//    EthCoinService ethCoinService;
//
//
//    @SecretKeyAuthorityCheck
//    @GetMapping("/test_price")
//    @Operation(summary= "查找EOS节点列表")
//    public BaseResponse testPrice() {
//        List<CoinSpecies> all = coinSpeciesService.findAll();
//        for (CoinSpecies coinSpecies : all) {
//            try {
//                ETHGetCoinPriceParam ethGetCoinPriceParam = new ETHGetCoinPriceParam();
//                ethGetCoinPriceParam.setIds(coinSpecies.getEnglishName().replaceAll(" ", "-").replaceAll("-", " "));
//                ethGetCoinPriceParam.setVsCurrency("CNY");
//                ETHGetCoinPriceResult coinPrice = ethCoinService.getCoinPrice(ethGetCoinPriceParam);
//                System.out.println(coinSpecies.getEnglishName() + "........." + coinPrice.getCurrent_price());
//            } catch (Exception e) {
//                System.out.println(coinSpecies.getEnglishName() + "..........报错");
//            }
//        }
//        return response();
//    }
//}
