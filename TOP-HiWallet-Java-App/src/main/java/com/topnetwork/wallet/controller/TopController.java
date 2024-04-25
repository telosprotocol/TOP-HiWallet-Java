package com.topnetwork.wallet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.topj.methods.response.AccountInfoResponse;

import com.base.core.context.annotation.SecretKeyAuthorityCheck;
import com.base.core.context.annotation.ValidRequestParam;
import com.base.core.context.mvc.BaseController;
import com.topnetwork.coin.TopCoinService;
import com.topnetwork.wallet.param.wallet.top.GetBaseTokenParam;
import com.topnetwork.wallet.param.wallet.top.StakeGasParam;
import com.topnetwork.wallet.param.wallet.top.TopBaseParam;
import com.topnetwork.wallet.param.wallet.top.TransactionListParam;
import com.topnetwork.wallet.param.wallet.top.TransferParam;
import com.topnetwork.wallet.result.wallet.top.GasInfoResult;
import com.topnetwork.wallet.result.wallet.top.GetBalanceResult;
import com.topnetwork.wallet.result.wallet.top.GetTokenResult;
import com.topnetwork.wallet.result.wallet.top.GetTop2gasRatioResult;
import com.topnetwork.wallet.result.wallet.top.GetTxDepositResult;
import com.topnetwork.wallet.result.wallet.top.GetTxListResult;
import com.topnetwork.wallet.result.wallet.top.TransferGasFreeGasResult;
import com.topnetwork.wallet.result.wallet.top.TransferGasResult;
import com.topnetwork.wallet.result.wallet.top.TransferResult;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import com.gitee.magic.framework.base.result.PageResponse;
import com.gitee.magic.framework.base.result.ResultResponse;

/**
 * @ClassName TopController
 * @Description
 * @Author bran
 * @Date 2020/10/10 16:55
 */
@RestController
@RequestMapping
@Tag(name = "top相关api")
public class TopController extends BaseController {
    @Autowired
    private TopCoinService topCoinService;


    @Operation(summary= "获取top余额")
    @SecretKeyAuthorityCheck
    @GetMapping(value = "/v1/app/top/get_balance")
    public ResultResponse<GetBalanceResult> getTopBalance(@ValidRequestParam GetBaseTokenParam param) {
        return response(topCoinService.getTopBalance(param));
    }

    @Operation(summary= "获取token")
    @SecretKeyAuthorityCheck
    @GetMapping(value = "/v1/app/top/get_token")
    public ResultResponse<GetTokenResult> getToken(@ValidRequestParam TopBaseParam param) {
        return response(topCoinService.getToken(param));
    }

    @Operation(summary= "获取账户信息")
    @SecretKeyAuthorityCheck
    @GetMapping(value = "/v1/app/top/get_account")
    public ResultResponse<AccountInfoResponse> getAccount(@ValidRequestParam GetBaseTokenParam param) {
        return response(topCoinService.getAccount(param));
    }

    @Operation(summary= "获取交易列表")
    @SecretKeyAuthorityCheck
    @GetMapping(value = "/v1/app/top/get_tx_list")
    public PageResponse<List<GetTxListResult>> getTransactionList(@ValidRequestParam TransactionListParam param) {
        return response(topCoinService.getTransactionList(param));
    }

    @Operation(summary= "获取gas资源信息")
    @SecretKeyAuthorityCheck
    @GetMapping(value = "/v1/app/top/get_gas_info")
    public ResultResponse<GasInfoResult> getGasInfo(@ValidRequestParam GetBaseTokenParam param) {
        return response(topCoinService.getGasInfo(param));
    }
    
    @SecretKeyAuthorityCheck
    @GetMapping("/v2/app/top/get_transfer_gas")
    @Operation(summary= "交易获取手续费")
    public ResultResponse<TransferGasFreeGasResult> getGasFee(@ValidRequestParam GetBaseTokenParam param) {
        return response(topCoinService.getGasFee(param));
    }

    @SecretKeyAuthorityCheck
    @GetMapping("/v1/app/top/get_transfer_gas")
    @Operation(summary= "交易获取手续费")
    public ResultResponse<TransferGasResult> getGasFee() {
        return response(topCoinService.getGasFee());
    }

    @Operation(summary= "获取top兑换gas比例")
    @SecretKeyAuthorityCheck
    @GetMapping(value = "/v1/app/top/get_top2gas_ratio")
    public ResultResponse<GetTop2gasRatioResult> getTop2gasRatio() {
        return response(topCoinService.getTop2gasRatio());
    }
    @Operation(summary= "获取交易质押保证金")
    @SecretKeyAuthorityCheck
    @GetMapping(value = "/v1/app/top/get_tx_deposit")
    public ResultResponse<GetTxDepositResult> getTxDeposit() {
        return response(topCoinService.getTxDeposit());
    }

    @Operation(summary= "转账")
    @SecretKeyAuthorityCheck
    @PostMapping(value = "/v1/app/top/transfer")
    public ResultResponse<TransferResult> transfer(@RequestBody TransferParam param) {
        return response(topCoinService.transfer(param));
    }

    @Operation(summary= "质押gas")
    @SecretKeyAuthorityCheck
    @PostMapping(value = "/v1/app/top/stake_gas")
    public ResultResponse<TransferResult> stakeGas(@RequestBody StakeGasParam param) {
        return response(topCoinService.stakeGas(param));
    }

    @Operation(summary= "赎回gas")
    @SecretKeyAuthorityCheck
    @PostMapping(value = "/v1/app/top/un_stake_gas")
    public ResultResponse<TransferResult> unStakeGas(@RequestBody StakeGasParam param) {
        return response(topCoinService.unStakeGas(param));
    }
}
