package com.topnetwork.wallet.controller;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.base.core.context.annotation.RestfulCheck;
import com.base.core.context.annotation.SecretKeyAuthorityCheck;
import com.base.core.context.annotation.ValidRequestParam;
import com.base.core.context.mvc.BaseController;
import com.topnetwork.coin.EthCoinService;
import com.topnetwork.wallet.param.wallet.ETHGetCoinPriceParam;
import com.topnetwork.wallet.param.wallet.ETHGetCoinPriceV2Param;
import com.topnetwork.wallet.param.wallet.ETHGetConfigParam;
import com.topnetwork.wallet.param.wallet.ETHQueryBalanceParam;
import com.topnetwork.wallet.param.wallet.ETHQueryErc20BalanceParam;
import com.topnetwork.wallet.param.wallet.ETHSendTransactionParam;
import com.topnetwork.wallet.param.wallet.ETHTokenTransactionListParam;
import com.topnetwork.wallet.param.wallet.ETHTransactionListParam;
import com.topnetwork.wallet.param.wallet.v2.TransactionDetailParam;
import com.topnetwork.wallet.result.ETHConfigResult;
import com.topnetwork.wallet.result.wallet.ETHGetCoinPriceResult;
import com.topnetwork.wallet.result.wallet.ETHQueryBalanceResult;
import com.topnetwork.wallet.result.wallet.ETHQueryNonceResult;
import com.topnetwork.wallet.result.wallet.ETHSendTransactionResult;
import com.topnetwork.wallet.result.wallet.ETHTransactionResult;
import com.topnetwork.wallet.result.wallet.EthGasResult;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import com.gitee.magic.framework.base.result.ResultResponse;

/**
 * @ClassName ETHController
 * @Description
 * @Author bran
 * @Date 2020/4/24 11:51
 */
@RestController
@RequestMapping
@Tag(name = "eth相关api")
public class ETHController extends BaseController {

    @Autowired
    private EthCoinService ethCoinService;

    @SecretKeyAuthorityCheck
    @PostMapping("/v2/app/query_eth_tx")
    @Operation(summary= "获取eth交易详情")
    public ResultResponse<ETHTransactionResult> queryETHTx(@RequestBody TransactionDetailParam param) {
        return response(ethCoinService.getTransactionDetail(param.getHash()));
    }

    @SecretKeyAuthorityCheck
    @PostMapping("/v1/app/eth/query_balance")
    @Operation(summary= "获取以太坊余额,通过web3J调用")
    public ResultResponse<ETHQueryBalanceResult> queryBalance(@RequestBody ETHQueryBalanceParam queryETHBalanceParam) {
        return response(ethCoinService.queryBalance(queryETHBalanceParam));
    }

    @SecretKeyAuthorityCheck
    @PostMapping("/v1/app/eth/query_erc20_balance")
    @Operation(summary= "获取指定地址和合约 ERC20 余额")
    public ResultResponse<ETHQueryBalanceResult> queryErc20Balance(@RequestBody ETHQueryErc20BalanceParam queryETHBalanceParam) {
        return response(ethCoinService.queryErc20Balance(queryETHBalanceParam));
    }

    @SecretKeyAuthorityCheck
    @PostMapping("/v1/app/eth/get_eth_nonce")
    @Operation(summary= "获取指定的地址的 Nonce")
    public ResultResponse<ETHQueryNonceResult> getEthNonce(@RequestBody ETHQueryBalanceParam queryETHBalanceParam) {
        return response(ethCoinService.getEthNonce(queryETHBalanceParam));
    }

    @SecretKeyAuthorityCheck
    @PostMapping("/v1/app/eth/send_transaction")
    @Operation(summary= "发送交易")
    public ResultResponse<ETHSendTransactionResult> sendTransaction(@RequestBody ETHSendTransactionParam ethSendTransactionParam) {
        return response(ethCoinService.sendTransaction(ethSendTransactionParam));
    }

    @SecretKeyAuthorityCheck
    @PostMapping("/v1/app/eth/get_eth_transaction_list_by_account")
    @Operation(summary= "获取交易历史")
    public ResultResponse<List<ETHTransactionResult>> getEthTransactionListByAccount(@RequestBody ETHTransactionListParam ethTransactionListParam) {
        return response(ethCoinService.getEthTransactionListByAccount(ethTransactionListParam));
    }

    @SecretKeyAuthorityCheck
    @PostMapping("/v1/app/eth/get_gas_list")
    @Operation(summary= "获取手续费列表")
    public ResultResponse<EthGasResult> getGasList() {
        return response(ethCoinService.getGasList());
    }

    @SecretKeyAuthorityCheck
    @PostMapping("/v1/app/eth/get_gas_list/new")
    @Operation(summary= "获取手续费列表(新)")
    public ResultResponse<EthGasResult> getGasListNew() {
        return response(ethCoinService.getGasListNew());
    }
    
    @RestfulCheck
    @GetMapping("/v1/app/eth/getGasPrice")
    @Operation(summary= "获取gasPrice")
    public ResultResponse<BigInteger> getPrice() {
        return response(ethCoinService.getGasPrice());
    }

    @SecretKeyAuthorityCheck
    @PostMapping("/v1/app/eth/query_tx_list")
    @Operation(summary= "获取以太坊代币（ETH Token）的交易历史数据")
    public ResultResponse<List<ETHTransactionResult>> queryTxList(@RequestBody ETHTokenTransactionListParam ethTokenTransactionListParam) {
        return response(ethCoinService.queryTxList(ethTokenTransactionListParam));
    }

    @SecretKeyAuthorityCheck
    @PostMapping("/v1/app/eth/get_coin_price")
    @Operation(summary= "根据app端传回的参数查询币价")
    public ResultResponse<ETHGetCoinPriceResult> getCoinPrice(@RequestBody ETHGetCoinPriceParam ethGetCoinPriceParam) {
        return response(ethCoinService.getCoinPrice(ethGetCoinPriceParam));
    }

//    @SecretKeyAuthorityCheck
    @RestfulCheck
    @PostMapping("/v2/app/eth/get_coin_price")
    @Operation(summary= "根据app端传回的参数查询币价V2")
    public ResultResponse<ETHGetCoinPriceResult> getCoinPriceV2(@RequestBody ETHGetCoinPriceV2Param ethGetCoinPriceParam) {
        return response(ethCoinService.getCoinPrice(ethGetCoinPriceParam));
    }

    @Operation(summary= "eth gas获取")
    @SecretKeyAuthorityCheck
    @GetMapping(value = "/v1/app/eth/get_eth_config")
    public ResultResponse<ETHConfigResult> getETHConfig(@ValidRequestParam ETHGetConfigParam param) {
        return response(ethCoinService.getETHConfig(param));
    }
}
