package com.topnetwork.wallet.controller;

import com.base.core.context.annotation.AuthenticationCheck;
import com.base.core.context.annotation.ValidRequestParam;
import com.base.core.context.mvc.BaseController;
import com.topnetwork.common.service.StatisticsService;
import com.topnetwork.wallet.common.enums.LanguageEnum;
import com.topnetwork.wallet.param.wallet.statistics.WalletDatePageParam;
import com.topnetwork.wallet.result.wallet.statistics.DeviceInfoResult;
import com.topnetwork.wallet.result.wallet.statistics.EffectiveAddressResult;
import com.topnetwork.wallet.result.wallet.statistics.WalletDatePageResult;
import com.topnetwork.wallet.result.wallet.statistics.WalletInfoResult;
import com.topnetwork.wallet.service.DeviceCoinsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gitee.magic.framework.base.result.PageResponse;
import com.gitee.magic.framework.base.result.ResultResponse;

import java.util.List;

/**
 * @ClassName StatisticsController
 * @Description
 * @Author bran
 * @Date 2020/6/22 14:19
 */
@RestController
@RequestMapping("/statistics")
@Tag(name = "统计相关接口")
public class StatisticsController extends BaseController {

    @Autowired
    private StatisticsService statisticsService;


    @Operation(summary= "查询存币账户")
    @AuthenticationCheck
    @GetMapping(value = "/get_effective_address")
    public ResultResponse<List<EffectiveAddressResult>> getEffectiveAddress() {
        return response(statisticsService.getEffectiveAddress());
    }

    @Operation(summary= "查询钱包信息")
    @AuthenticationCheck
    @GetMapping(value = "/get_wallet_info")
    public ResultResponse<WalletInfoResult> getWalletInfo() {
        return response(statisticsService.getWalletInfo());
    }

    @Operation(summary= "根据日期查询钱包助记词，设备，地址新增数量信息")
    @AuthenticationCheck
    @GetMapping(value = "/get_wallet_date_page")
    public ResultResponse<List<WalletDatePageResult>> getWalletDatePage(@ValidRequestParam WalletDatePageParam param) {
        return response(statisticsService.getWalletDatePage(param));
    }


    @Operation(summary= "查询设备统计信息")
    @AuthenticationCheck
    @GetMapping(value = "/get_device_info")
    public ResultResponse<List<DeviceInfoResult>> getDeviceInfo() {
        return response(statisticsService.getDeviceInfo());
    }


}
