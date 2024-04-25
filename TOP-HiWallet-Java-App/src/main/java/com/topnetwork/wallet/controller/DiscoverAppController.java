package com.topnetwork.wallet.controller;

import com.base.core.context.annotation.SecretKeyAuthorityCheck;
import com.base.core.context.annotation.ValidRequestParam;
import com.base.core.context.mvc.BaseController;
import com.topnetwork.wallet.entity.SecretPlatform;
import com.topnetwork.wallet.param.wallet.discover.*;
import com.topnetwork.wallet.result.wallet.discover.*;
import com.topnetwork.wallet.service.AppDiscoverBannerNewService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.gitee.magic.framework.base.result.BaseResponse;
import com.gitee.magic.framework.base.result.PageResponse;
import com.gitee.magic.framework.base.result.ResultResponse;

import java.util.List;

/**
 * @ClassName DiscoverAppController
 * @Description
 * @Author bran
 * @Date 2020/5/21 11:36
 */
@RestController
@RequestMapping
@Tag(name = "新版发现钱包api")
public class DiscoverAppController extends BaseController {

    @Autowired
    private AppDiscoverBannerNewService appDiscoverBannerNewService;

    @Operation(summary= "1、获取banner图")
    @SecretKeyAuthorityCheck
    @GetMapping(value = "/v1/app/discover_new/get_banners")
    public ResultResponse<List<GetNewBannersResult>> getBanners(@ValidRequestParam GetBannersV4Param param) {
        SecretPlatform secretPlatform = getHttp().getCache(SecretPlatform.class);
        return response(appDiscoverBannerNewService.getBanners(param, secretPlatform));
    }

    @Operation(summary= "2、获取分组应用接口")
    @SecretKeyAuthorityCheck
    @GetMapping(value = "/v1/app/discover_new/get_recommend_list")
    public ResultResponse<List<RecommendNewResult>> getRecommendList(@ValidRequestParam GetBannersV4Param param) {
        SecretPlatform secretPlatform = getHttp().getCache(SecretPlatform.class);
        return response(appDiscoverBannerNewService.getRecommendList(param, secretPlatform, getHttp().getRequestIp()));
    }

    @Operation(summary= "3、上报用户使用历史")
    @SecretKeyAuthorityCheck
    @PostMapping(value = "/v1/app/discover_new/add_used_history")
    public BaseResponse addUsedHistory(@RequestBody AddNewUsedHistoryParam param) {
        appDiscoverBannerNewService.addUsedHistory(param);
        return response();
    }

    @Operation(summary= "6、移除用户使用历史")
    @SecretKeyAuthorityCheck
    @DeleteMapping(value = "/v1/app/discover_new/remove_used_history")
    public BaseResponse deleteUsedHistory(@RequestBody AddNewUsedHistoryParam param) {
        appDiscoverBannerNewService.deleteUsedHistory(param);
        return response();
    }

    @Operation(summary= "4、查找某分组下应用接口-分页（会返回应用详情） get")
    @SecretKeyAuthorityCheck
    @GetMapping(value = "/v1/app/discover_new/get_discover_all")
    public PageResponse<List<GetNewBannersResult>> getDiscoverGamesPage(@ValidRequestParam RecommendPageNewParam param) {
        SecretPlatform secretPlatform = getHttp().getCache(SecretPlatform.class);
        return response(appDiscoverBannerNewService.getDiscoverGamesPage(param, secretPlatform, getHttp().getRequestIp()));
    }

    @Operation(summary= "5、查找应用详情接口-备用 get")
    @SecretKeyAuthorityCheck
    @GetMapping(value = "/v1/app/discover_new/get_app_detail")
    public ResultResponse<GetAppDetailResult> getAppDetail(@ValidRequestParam GetAppDetailParam param) {
        SecretPlatform secretPlatform = getHttp().getCache(SecretPlatform.class);
        return response(appDiscoverBannerNewService.getAppDetail(param, secretPlatform));
    }
}
