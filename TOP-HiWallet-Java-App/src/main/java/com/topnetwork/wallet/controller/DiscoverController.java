package com.topnetwork.wallet.controller;

import com.base.core.context.annotation.AuthenticationCheck;
import com.base.core.context.annotation.SecretKeyAuthorityCheck;
import com.base.core.context.annotation.ValidRequestParam;
import com.base.core.context.mvc.BaseController;
import com.topnetwork.wallet.entity.SecretPlatform;
import com.topnetwork.wallet.param.wallet.discover.*;
import com.topnetwork.wallet.result.wallet.discover.*;
import com.topnetwork.wallet.service.AppDiscoverBannerService;
import com.topnetwork.wallet.service.AppDiscoverGameClassifyService;
import com.topnetwork.wallet.service.AppDiscoverGameService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.gitee.magic.framework.base.result.BaseResponse;
import com.gitee.magic.framework.base.result.PageResponse;
import com.gitee.magic.framework.base.result.ResultResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ClassName DiscoverController
 * @Description
 * @Author bran
 * @Date 2020/4/24 11:40
 */
@RestController
@RequestMapping
@Tag(name = "发现api")
public class DiscoverController extends BaseController {

    @Autowired
    private AppDiscoverBannerService appDiscoverBannerService;

    @Deprecated
    @Operation(summary= "获取banner图V3")
    @SecretKeyAuthorityCheck
    @GetMapping(value = "/v3/app/discover/get_banners")
    public ResultResponse<List<GetBannersV2Result>> getBanners(@ValidRequestParam GetBannersV3Param param) {
        SecretPlatform secretPlatform = getHttp().getCache(SecretPlatform.class);
        return response(appDiscoverBannerService.getV3Banners(param, secretPlatform));
    }

    @Operation(summary= "获取banner图V4")
    @SecretKeyAuthorityCheck
    @GetMapping(value = "/v4/app/discover/get_banners")
    public ResultResponse<List<GetBannersV2Result>> getBanners(@ValidRequestParam GetBannersV4Param param) {
        SecretPlatform secretPlatform = getHttp().getCache(SecretPlatform.class);
        return response(appDiscoverBannerService.getV4Banners(param, secretPlatform, getHttp().getRequestIp()));
    }

    @Autowired
    private AppDiscoverGameService appDiscoverGameService;

    @Operation(summary= "获取发现首页游戏列表V3")
    @SecretKeyAuthorityCheck
    @GetMapping(value = "/v3/app/discover/get_recommend_list")
    public ResultResponse<List<RecommendGamesV3Result>> getRecommendGames(@ValidRequestParam RecommendGamesV3Param param) {
        SecretPlatform secretPlatform = getHttp().getCache(SecretPlatform.class);
        return response(appDiscoverGameService.getRecommendV3Games(param, secretPlatform, getHttp().getRequestIp()));
    }

    @Operation(summary= "获取全部内容分页列表V3")
    @SecretKeyAuthorityCheck
    @GetMapping(value = "/v3/app/discover/get_discover_all")
    public PageResponse<List<GameResult>> getDiscoverGamesPage(@ValidRequestParam RecommendPageV3Param param) {
        SecretPlatform secretPlatform = getHttp().getCache(SecretPlatform.class);
        return response(appDiscoverGameService.getDiscoverGamesV3Page(param, secretPlatform, getHttp().getRequestIp()));
    }

    @Operation(summary= "上报用户使用历史")
    @SecretKeyAuthorityCheck
    @PostMapping(value = "/v3/app/discover/add_used_history")
    public BaseResponse addUsedHistory(@RequestBody AddUsedHistoryParam param) {
        appDiscoverGameService.addUsedHistory(param);
        return response();
    }

    @Deprecated
    @Operation(summary= "获取banner图V2")
    @SecretKeyAuthorityCheck
    @PostMapping(value = "/v2/app/discover/get_banners")
    public ResultResponse<List<GetBannersV2Result>> getBanners(@RequestBody GetBannersParam param) {
        return response(appDiscoverBannerService.getV2Banners(param));
    }

    @Deprecated
    @Operation(summary= "获取发现首页游戏列表V2")
    @SecretKeyAuthorityCheck
    @PostMapping(value = "/v2/app/discover/get_recommend_games")
    public ResultResponse<List<RecommendGamesResult>> getRecommendGames(@RequestBody RecommendGamesParam param) {
        return response(appDiscoverGameService.getRecommendGames(param, true));
    }

    @Deprecated
    @Operation(summary= "获取分页游戏列表V2")
    @SecretKeyAuthorityCheck
    @PostMapping(value = "/v2/app/discover/get_discover_games_page")
    public PageResponse<List<GameResult>> getDiscoverGamesPage(@RequestBody RecommendPageParam param) {
        return response(appDiscoverGameService.getDiscoverGamesPage(param, true));
    }


    @Deprecated
    @Operation(summary= "获取banner图v1")
    @SecretKeyAuthorityCheck
    @PostMapping(value = "/v1/app/discover/get_banners")
    public ResultResponse<List<GetBannersResult>> getBannersV1(@RequestBody GetBannersParam param) {
        return response(appDiscoverBannerService.getBanners(param));
    }


    @Deprecated
    @Operation(summary= "获取发现首页游戏列表V1")
    @SecretKeyAuthorityCheck
    @PostMapping(value = "/v1/app/discover/get_recommend_games")
    public ResultResponse<List<RecommendGamesResult>> getRecommendGamesV1(@RequestBody RecommendGamesParam param) {
        return response(appDiscoverGameService.getRecommendGames(param, false));
    }

    @Deprecated
    @Operation(summary= "获取分页游戏列表V1")
    @SecretKeyAuthorityCheck
    @PostMapping(value = "/v1/app/discover/get_discover_games_page")
    public PageResponse<List<GameResult>> getDiscoverGamesPageV1(@RequestBody RecommendPageParam param) {
        return response(appDiscoverGameService.getDiscoverGamesPage(param, false));
    }

    @AuthenticationCheck
    @PostMapping("/discover_banner/find_banner_list")
    @Operation(summary= "获取banner列表")
    public PageResponse<List<BannerListResult>> findBannerList(@RequestBody BannerListParam param) {
        return response(appDiscoverBannerService.findBannerList(param));
    }

    @AuthenticationCheck
    @PostMapping("/discover_banner/update_banner")
    @Operation(summary= "更新banner属性")
    public BaseResponse updateBanner(@RequestBody BannerUpdateParam param) {
        appDiscoverBannerService.updateBanner(param);
        return response();
    }

    @AuthenticationCheck
    @PostMapping("/discover_banner/add_banner")
    @Operation(summary= "新增banner")
    public BaseResponse addBanner(@RequestBody BannerAddParam param) {
        appDiscoverBannerService.addBanner(param);
        return response();
    }


    @Autowired
    private AppDiscoverGameClassifyService appDiscoverGameClassifyService;

    @AuthenticationCheck
    @PostMapping("/discover_game/find_game_list")
    @Operation(summary= "获取游戏列表")
    public PageResponse<List<GameListResult>> findGameList(@RequestBody GameListParam param) {
        return response(appDiscoverGameService.findGameList(param));
    }

    @AuthenticationCheck
    @PostMapping("/discover_game/find_game_classify_list")
    @Operation(summary= "获取游戏类别列表")
    public ResultResponse<List<GameClassifyResult>> findGameClassifyList() {
        return response(appDiscoverGameClassifyService.findGameClassifyList());
    }

    @AuthenticationCheck
    @PostMapping("/discover_game/update_game")
    @Operation(summary= "更新游戏属性")
    public BaseResponse updateGame(@RequestBody GameUpdateParam param) {
        appDiscoverGameService.updateGame(param);
        return response();
    }

    @AuthenticationCheck
    @PostMapping("/discover_game/add_game")
    @Operation(summary= "新增游戏")
    public BaseResponse addGame(@RequestBody GameAddParam param) {
        appDiscoverGameService.addGame(param);
        return response();
    }
}
