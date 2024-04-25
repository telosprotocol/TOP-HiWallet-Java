package com.topnetwork.wallet.controller;

import com.base.core.context.annotation.SecretKeyAuthorityCheck;
import com.base.core.context.mvc.BaseController;
import com.topnetwork.coin.BitCoinService;
import com.topnetwork.wallet.param.wallet.v2.TransactionDetailParam;
import com.topnetwork.wallet.result.wallet.BTCFeeResult;
import com.topnetwork.wallet.result.wallet.BTCTransactionResult;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gitee.magic.framework.base.result.ResultResponse;

/**
 * @ClassName BTCController
 * @Description
 * @Author bran
 * @Date 2020/4/24 11:52
 */
@RestController
@RequestMapping
@Tag(name = "btc相关api")
public class BTCController extends BaseController {

    @Autowired
    private BitCoinService bitCoinService;

    @SecretKeyAuthorityCheck
    @PostMapping("/v2/app/query_btc_tx")
    @Operation(summary= "获取btc交易详情")
    public ResultResponse<BTCTransactionResult> queryBTCTx(@RequestBody TransactionDetailParam param) {
        return response(bitCoinService.getTransactionByHash(param.getHash()));
    }

    @SecretKeyAuthorityCheck
    @PostMapping("/v1/app/btc/get_btc_fee")
    @Operation(summary= "获取btc交易费列表")
    public ResultResponse<BTCFeeResult> getBtcFee() {
        return response(bitCoinService.getBTCFee());
    }
}
