package com.topnetwork.wallet.controller;

import com.base.core.context.annotation.AuthenticationCheck;
import com.base.core.context.annotation.SecretKeyAuthorityCheck;
import com.base.core.context.annotation.ValidRequestParam;
import com.base.core.context.mvc.BaseController;
import com.topnetwork.wallet.param.wallet.changelly.*;
import com.topnetwork.wallet.result.changelly.*;
import com.topnetwork.wallet.service.AppChangellyCurrencyService;
import com.topnetwork.wallet.service.AppChangellyOrderService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.gitee.magic.framework.base.result.BaseResponse;
import com.gitee.magic.framework.base.result.PageResponse;
import com.gitee.magic.framework.base.result.ResultResponse;

import java.util.List;

/**
 * @ClassName ChangellyController
 * @Description
 * @Author bran
 * @Date 2020/6/12 15:40
 */
@RestController
@RequestMapping
@Tag(name = "币币兑换相关api")
public class ChangellyController extends BaseController {

    @Autowired
    private AppChangellyCurrencyService appChangellyCurrencyService;
    @Autowired
    private AppChangellyOrderService appChangellyOrderService;

    @SecretKeyAuthorityCheck
    @PostMapping("/v1/app/changelly/get_currency_list")
    @Operation(summary= "查找支持币种列表")
    public ResultResponse<GetCurrencyAllResult> getCurrencyList(@RequestBody GetCurrenciesParam param) {
        return response(appChangellyCurrencyService.getCurrencyList(param));
    }

    @SecretKeyAuthorityCheck
    @GetMapping("/v1/app/changelly/get_pair_info")
    @Operation(summary= "获取币对信息")
    public ResultResponse<GetPairInfoResult> getPairInfo(@ValidRequestParam GetPairInfoParam param) {
        return response(appChangellyCurrencyService.getPairInfo(param));
    }

    @SecretKeyAuthorityCheck
    @GetMapping("/v1/app/changelly/check_currency")
    @Operation(summary= "检查币种是否支持买入或卖出，建议使用/v2/app/changelly/check_currency")
    @Deprecated
    public ResultResponse<CheckCurrencyResult> checkCurrency(@ValidRequestParam CheckCurrencyParam param) {
        return response(appChangellyCurrencyService.checkCurrency(param));
    }

    @SecretKeyAuthorityCheck
    @PostMapping("/v1/app/changelly/report_trans_result")
    @Operation(summary= "上报转账结果")
    public BaseResponse reportTransferResults(@RequestBody ReportTransResultParam param) {
        appChangellyOrderService.reportTransferResults(param);
        return response();
    }

    @SecretKeyAuthorityCheck
    @PostMapping("/v1/app/changelly/create_exchange")
    @Operation(summary= "创建交易")
    public ResultResponse<CreateTransactionResult> createOrder(@RequestBody CreateTransactionParam param) {
        return response(appChangellyOrderService.createOrder(param));
    }

    @SecretKeyAuthorityCheck
    @GetMapping("/v1/app/changelly/get_exchange_page")
    @Operation(summary= "查找兑换记录分页列表")
    public PageResponse<List<GetOrderPageResult>> getOrderPage(@ValidRequestParam GetOrderPageParam param) {
        return response(appChangellyOrderService.getOrderPage(param));
    }

    @SecretKeyAuthorityCheck
    @GetMapping("/v1/app/changelly/get_exchange_detail")
    @Operation(summary= "获取兑换记录详情")
    public ResultResponse<GetOrderDetailResult> getOrderDetail(@ValidRequestParam GetOrderDetailParam param) {
        return response(appChangellyOrderService.getOrderDetail(param));
    }

    /**
     * 管理后台
     */


    @AuthenticationCheck
    @GetMapping("/changelly/get_currency_page")
    @Operation(summary= "查找支持币种列表")
    public PageResponse<List<GetCurrencyPageResult>> getCurrencyPage(@ValidRequestParam GetCurrencyPageParam param) {
        return response(appChangellyCurrencyService.getCurrencyPage(param));
    }

    @AuthenticationCheck
    @PostMapping("/changelly/add_currency")
    @Operation(summary= "新增支持币种")
    public BaseResponse addCurrency(@RequestBody AddCurrencyParam param) {
        appChangellyCurrencyService.addCurrency(param);
        return response();
    }

    @AuthenticationCheck
    @DeleteMapping("/changelly/delete_currency")
    @Operation(summary= "删除支持币种")
    public BaseResponse deleteCurrency(@RequestBody DelCurrencyParam param) {
        appChangellyCurrencyService.deleteCurrency(param);
        return response();
    }

    @AuthenticationCheck
    @PutMapping("/changelly/update_currency")
    @Operation(summary= "修改支持币种")
    public BaseResponse updateCurrency(@RequestBody UpdCurrencyParam param) {
        appChangellyCurrencyService.updateCurrency(param);
        return response();
    }

    @AuthenticationCheck
    @GetMapping("/changelly/get_coin_list")
    @Operation(summary= "获取可选择币种列表")
    public ResultResponse<List<GetCoinListResult>> getCoinList() {
        return response(appChangellyCurrencyService.getCoinList());
    }
}
