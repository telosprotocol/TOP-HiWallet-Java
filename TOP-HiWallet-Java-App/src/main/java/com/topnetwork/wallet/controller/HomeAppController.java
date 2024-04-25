package com.topnetwork.wallet.controller;

import com.base.core.context.annotation.AuthenticationCheck;
import com.base.core.context.annotation.SecretKeyAuthorityCheck;
import com.base.core.context.annotation.ValidRequestParam;
import com.topnetwork.wallet.entity.SecretPlatform;
import com.topnetwork.wallet.param.wallet.home.*;
import com.topnetwork.wallet.result.wallet.discover.GetNewBannersResult;
import com.topnetwork.wallet.result.wallet.home.AppListResult;
import com.topnetwork.wallet.result.wallet.home.AppSelectListResult;
import com.topnetwork.wallet.service.HomeAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.base.core.context.mvc.BaseController;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import com.gitee.magic.framework.base.result.BaseResponse;
import com.gitee.magic.framework.base.result.ResultResponse;

import java.util.List;

@RestController
@RequestMapping
@Tag(name = "主页app相关接口")
public class HomeAppController extends BaseController {

    @Autowired
    private HomeAppService homeAppService;

    @Operation(summary= "0、查询配置好的app列表")
    @AuthenticationCheck
    @GetMapping(value = "/home/get_app_list")
    public ResultResponse<List<AppListResult>> getAppList() {
        return response(homeAppService.getAppList());
    }

    @Operation(summary= "1、根据类型查询可选择的app列表")
    @AuthenticationCheck
    @GetMapping(value = "/home/get_select_app_list")
    public ResultResponse<List<AppSelectListResult>> getSelectAppList(@ValidRequestParam GetSelectAppParam param) {
        return response(homeAppService.getSelectAppList(param));
    }

    @Operation(summary= "2、增加显示的app")
    @AuthenticationCheck
    @PostMapping(value = "/home/add_app")
    public BaseResponse addApp(@RequestBody AddAppParam addAppParam) {
        homeAppService.addApp(addAppParam);
        return response();
    }

    @Operation(summary= "3、修改显示的app")
    @AuthenticationCheck
    @PutMapping(value = "/home/upd_app")
    public BaseResponse updateApp(@RequestBody UpdAppParam param) {
        homeAppService.updateApp(param);
        return response();
    }

    @Operation(summary= "4、删除要显示的app")
    @AuthenticationCheck
    @DeleteMapping(value = "/home/del_app")
    public BaseResponse delApp(@RequestBody DelAppParam delAppParam) {
        homeAppService.delApp(delAppParam);
        return response();
    }

    @Operation(summary= "5、查找首页可显示用用")
    @SecretKeyAuthorityCheck
    @GetMapping(value = "/v1/app/home/get_app_list")
    public ResultResponse<List<GetNewBannersResult>> getDiscoverApp(@ValidRequestParam GetAppListParam param) {
        SecretPlatform secretPlatform = getHttp().getCache(SecretPlatform.class);
        return response(homeAppService.getDiscoverApp(param, secretPlatform, getHttp().getRequestIp()));
    }

}
