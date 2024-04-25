package com.topnetwork.wallet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.base.core.context.annotation.AuthenticationCheck;
import com.base.core.context.annotation.SecretKeyAuthorityCheck;
import com.base.core.context.annotation.ValidRequestParam;
import com.base.core.context.mvc.BaseController;
import com.base.service.base.system.service.ConfigureService;
import com.topnetwork.common.service.ConfigManagerService;
import com.topnetwork.wallet.common.enums.PlatformEnum;
import com.topnetwork.wallet.entity.SecretPlatform;
import com.topnetwork.wallet.param.system.ConfigureParam;
import com.topnetwork.wallet.param.wallet.CoinConfigParam;
import com.topnetwork.wallet.param.wallet.appuser.ConfigAddParam;
import com.topnetwork.wallet.param.wallet.appuser.ConfigPageParam;
import com.topnetwork.wallet.param.wallet.appuser.ConfigUpdParam;
import com.topnetwork.wallet.param.wallet.config.AppConfigParam;
import com.topnetwork.wallet.param.wallet.discover.DiscoverSwitchParam;
import com.topnetwork.wallet.param.wallet.v1.StakingSwitchV1Param;
import com.topnetwork.wallet.param.wallet.v2.StakingSwitchParam;
import com.topnetwork.wallet.result.wallet.CoinConfigResult;
import com.topnetwork.wallet.result.wallet.StakingSwitchResult;
import com.topnetwork.wallet.result.wallet.appuser.ConfigPageResult;
import com.topnetwork.wallet.result.wallet.config.AppConfigResult;
import com.topnetwork.wallet.result.wallet.config.AppConfigV2Result;
import com.topnetwork.wallet.result.wallet.discover.DiscoverSwitchResult;
import com.topnetwork.wallet.service.AppCoinConfigService;
import com.topnetwork.wallet.service.AppDiscoverGameService;
import com.topnetwork.wallet.service.AppVersionService;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import com.gitee.magic.framework.base.result.BaseResponse;
import com.gitee.magic.framework.base.result.PageResponse;
import com.gitee.magic.framework.base.result.ResultResponse;

/**
 * @ClassName ConfigController
 * @Description
 * @Author bran
 * @Date 2020/4/24 11:54
 */
@RestController
@RequestMapping
@Tag(name = "配置查询管理api")
public class AppConfigController extends BaseController {

    @Autowired
    private AppVersionService appVersionService;
    @Autowired
    private AppDiscoverGameService appDiscoverGameService;

    @Operation(summary= "查询钱包初始化配置v2")
    @SecretKeyAuthorityCheck
    @PostMapping(value = "/v2/app/config/find_app_config")
    public ResultResponse<AppConfigV2Result> findAppConfig(@RequestBody AppConfigParam param) {
        SecretPlatform secretPlatform = getHttp().getCache(SecretPlatform.class);
        return response(appVersionService.findAppV2Config(param, secretPlatform.getPlatform()));
    }

    @Deprecated
    @SecretKeyAuthorityCheck
    @PostMapping("/v1/app/discover/get_discover_switch")
    @Operation(summary= "获取app discover开关")
    public ResultResponse<DiscoverSwitchResult> getDiscoverSwitch(@RequestBody DiscoverSwitchParam param) {
        SecretPlatform secretPlatform = getHttp().getCache(SecretPlatform.class);
        return response(appDiscoverGameService.getDiscoverSwitch(param, secretPlatform.getPlatform()));
    }


    @Deprecated
    @SecretKeyAuthorityCheck
    @PostMapping("/v2/app/get_staking_switch")
    @Operation(summary= "获取app staking开关")
    public ResultResponse<StakingSwitchResult> getStakingSwitch(@RequestBody StakingSwitchParam param) {
        SecretPlatform secretPlatform = getHttp().getCache(SecretPlatform.class);
        return response(appVersionService.getStakingSwitch(param, secretPlatform.getPlatform()));
    }


    @Deprecated
    @Operation(summary= "查询活动列表v1")
    @SecretKeyAuthorityCheck
    @PostMapping(value = "/v1/app/config/find_app_config")
    public ResultResponse<AppConfigResult> findAppConfigV1(@RequestBody AppConfigParam param) {
        SecretPlatform secretPlatform = getHttp().getCache(SecretPlatform.class);
        return response(appVersionService.findAppConfig(param, secretPlatform.getPlatform()));
    }

    @Deprecated
    @SecretKeyAuthorityCheck
    @PostMapping("/v1/app/get_staking_switch")
    @Operation(summary= "获取app staking开关")
    public ResultResponse<StakingSwitchResult> getStakingSwitch(@RequestBody StakingSwitchV1Param param) {
        StakingSwitchParam stakingSwitchParamV2 = new StakingSwitchParam();
        stakingSwitchParamV2.setVersion(param.getVersion());
        PlatformEnum platformEnum = param.getPlatform() == 1 ? PlatformEnum.IOS : PlatformEnum.ANDROID;
        return response(appVersionService.getStakingSwitch(stakingSwitchParamV2, platformEnum));
    }

    @Autowired
    private AppCoinConfigService appCoinConfigService;

    @Operation(summary= "获取币种交易费配置")
    @SecretKeyAuthorityCheck
    @PostMapping(value = "/coin/fee/get_config")
    public ResultResponse<CoinConfigResult> getConfig(@RequestBody CoinConfigParam param) {
        return response(appCoinConfigService.getConfig(param));
    }


    @Autowired
    private ConfigManagerService configManagerService;

    @Operation(summary= "查找配置分页列表")
    @AuthenticationCheck
    @PostMapping(value = "/config/find_config_page")
    public PageResponse<List<ConfigPageResult>> findConfigPage(@RequestBody ConfigPageParam param) {
        return response(configManagerService.findConfigPage(param));
    }

    @Operation(summary= "修改配置")
    @AuthenticationCheck
    @PostMapping(value = "/config/update_config")
    public BaseResponse updateConfig(@RequestBody ConfigUpdParam param) {
        configManagerService.updateConfig(param);
        return response();
    }

    @Operation(summary= "增加配置")
    @AuthenticationCheck
    @PostMapping(value = "/config/add_config")
    public BaseResponse addConfig(@RequestBody ConfigAddParam param) {
        configManagerService.addConfig(param);
        return response();
    }

    @Autowired
    private ConfigureService configureService;

    @Operation(summary="获取配置值")
    @SecretKeyAuthorityCheck
    @GetMapping("/common/config")
    public ResultResponse<Object> configString(@ValidRequestParam ConfigureParam param) {
        return response(configureService.getValue(param.getCode()));
    }
}
