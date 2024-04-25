package com.topnetwork.topgame.controller;

import com.base.core.context.annotation.RestfulCheck;
import com.base.core.context.annotation.ValidRequestParam;
import com.base.core.context.mvc.BaseController;
import com.topnetwork.coin.BitCoinService;
import com.topnetwork.wallet.param.topgame.CheckWalletAddressParam;
import com.topnetwork.wallet.result.topgame.CheckWalletAddressResult;
import com.topnetwork.wallet.result.wallet.BTCTransactionResult;
import com.topnetwork.wallet.service.DeviceCoinsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.gitee.magic.framework.base.result.ResultResponse;

/**
 * @ClassName GameController
 * @Description
 * @Author bran
 * @Date 2020/8/12 11:52
 */
@RestController
@RequestMapping
@Tag(name = "top游戏相关接口")
public class GameController extends BaseController {

    @Autowired
    private DeviceCoinsService deviceCoinsService;

    @RestfulCheck
    @GetMapping("/top_game/wallet_address")
    @Operation(summary= "判断地址是否为钱包地址")
    public ResultResponse<CheckWalletAddressResult> isWalletAddress(@ValidRequestParam CheckWalletAddressParam param) {
        return response(deviceCoinsService.isWalletAddress(param));
    }

}
