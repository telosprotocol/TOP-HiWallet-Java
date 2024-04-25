package com.topnetwork.wyre.controller;

import com.base.core.context.annotation.AuthenticationCheck;
import com.base.core.context.annotation.SecretKeyAuthorityCheck;
import com.base.core.context.annotation.ValidRequestParam;
import com.topnetwork.wallet.param.wallet.changelly.CheckCurrencyParam;
import com.topnetwork.wallet.param.wyre.*;
import com.topnetwork.wallet.result.changelly.CheckCurrencyV2Result;
import com.topnetwork.wallet.result.changelly.GetCoinListResult;
import com.topnetwork.wallet.result.wallet.ETHGetCoinPriceResult;
import com.topnetwork.wallet.result.wyre.GetCountryPageResult;
import com.topnetwork.wallet.result.wyre.GetInfoResult;
import com.topnetwork.wallet.result.wyre.GetReservationResult;
import com.topnetwork.wallet.result.wyre.GetReservationV2Result;
import com.topnetwork.wallet.service.AppChangellyCurrencyService;
import com.topnetwork.wyre.service.WyreCountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.base.core.context.mvc.BaseController;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import com.gitee.magic.framework.base.result.BaseResponse;
import com.gitee.magic.framework.base.result.PageResponse;
import com.gitee.magic.framework.base.result.ResultResponse;

import java.util.List;

@RestController
@RequestMapping
@Tag(name = "法币交易接口文档")
public class WyreCountryController extends BaseController {

    @Autowired
    private WyreCountryService wyreCountryService;
    @Autowired
    private AppChangellyCurrencyService appChangellyCurrencyService;


    @SecretKeyAuthorityCheck
    @GetMapping("/v1/app/wyre/info")
    @Operation(summary= "获取币对信息")
    public ResultResponse<GetInfoResult> getInfo() {
        return response(wyreCountryService.getInfo(getHttp().getRequestIp()));
    }

    @SecretKeyAuthorityCheck
    @GetMapping("/v1/app/wyre/get_coin_price")
    @Operation(summary= "查询币价")
    public ResultResponse<ETHGetCoinPriceResult> getCoinPrice(@ValidRequestParam GetCoinPriceParam param) {
        return response(wyreCountryService.getCoinPrice(param));
    }

    @SecretKeyAuthorityCheck
    @Deprecated
    @GetMapping("/v1/app/wyre/get_reservation")
    @Operation(summary= "获取账户id和reservationId 推荐使用/v2/app/wyre/get_reservation")
    public ResultResponse<GetReservationResult> getReservation() {
        return response(wyreCountryService.getReservation());
    }

    @SecretKeyAuthorityCheck
    @GetMapping("/v2/app/wyre/get_reservation")
    @Operation(summary= "获取账户id和reservationId")
    public ResultResponse<GetReservationV2Result> getReservation(@ValidRequestParam GetReservationParam param) {
        return response(wyreCountryService.getReservation(param));
    }


    @SecretKeyAuthorityCheck
    @GetMapping("/v2/app/changelly/check_currency")
    @Operation(summary= "检查币种是否支持买入或卖出")
    public ResultResponse<CheckCurrencyV2Result> checkV2Currency(@ValidRequestParam CheckCurrencyParam param) {
        return response(appChangellyCurrencyService.checkCurrency(param, getHttp().getRequestIp()));
    }


    /**
     * 管理后台接口
     */


    @AuthenticationCheck
    @GetMapping("/wyre/get_country_page")
    @Operation(summary= "查找支持国家列表")
    public PageResponse<List<GetCountryPageResult>> getCountryPage(@ValidRequestParam GetCountryPageParam param) {
        return response(wyreCountryService.getCountryPage(param));
    }

    @AuthenticationCheck
    @DeleteMapping("/wyre/delete_country")
    @Operation(summary= "删除支持国家")
    public BaseResponse deleteCountry(@RequestBody DelCountryParam param) {
        wyreCountryService.deleteCountry(param);
        return response();
    }

    @AuthenticationCheck
    @PostMapping("/wyre/update_country")
    @Operation(summary= "修改支持国家")
    public BaseResponse updateCountry(@RequestBody UpdateCountryParam param) {
        wyreCountryService.updateCountry(param);
        return response();
    }

    @AuthenticationCheck
    @PostMapping("/wyre/add_country")
    @Operation(summary= "增加支持国家")
    public BaseResponse addCountry(@RequestBody AddCountryParam param) {
        wyreCountryService.addCountry(param);
        return response();
    }

    @AuthenticationCheck
    @GetMapping("/wyre/get_coin_list")
    @Operation(summary= "获取可选择币种列表")
    public ResultResponse<List<GetCoinListResult>> getCoinList() {
        return response(wyreCountryService.getCoinList());
    }

}
