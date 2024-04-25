package com.topnetwork.wallet.controller;

import com.base.core.context.annotation.LoginAuthorityCheck;
import com.base.core.context.annotation.SecretKeyAuthorityCheck;
import com.common.utils.LocationUtils;
import com.topnetwork.wallet.entity.AppUser;
import com.topnetwork.wallet.entity.SecretPlatform;
import com.topnetwork.wallet.param.wallet.activity.ActivityListParam;
import com.topnetwork.wallet.param.wallet.activity.RecSucTranParam;
import com.topnetwork.wallet.param.wallet.appuser.*;
import com.topnetwork.wallet.result.wallet.activity.ActivityListResult;
import com.topnetwork.wallet.result.wallet.appuser.*;
import com.topnetwork.wallet.service.AppActivityRewardService;
import com.topnetwork.wallet.service.AppActivityService;
import com.topnetwork.wallet.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.base.core.context.mvc.BaseController;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import com.gitee.magic.framework.base.result.BaseResponse;
import com.gitee.magic.framework.base.result.PageResponse;
import com.gitee.magic.framework.base.result.ResultResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping
@Tag(name = "新青年用户接口")
public class AppUserController extends BaseController {

    @Autowired
    private AppUserService appUserService;
    @Autowired
    private AppActivityRewardService appActivityRewardService;
    @Autowired
    private AppActivityService appActivityService;

    @Operation(summary= "用户注册登记")
    @SecretKeyAuthorityCheck
    @PostMapping(value = "/v1/app/user/register")
    public ResultResponse<UserRegisterResult> register(@RequestBody UserRegisterParam param, HttpServletRequest request) {
        SecretPlatform secretPlatform = getHttp().getCache(SecretPlatform.class);
        return response(appUserService.register(param, LocationUtils.getRequestIP(request), secretPlatform));
    }

    @Operation(summary= "删除重复用户")
    @SecretKeyAuthorityCheck
    @DeleteMapping(value = "/delete_user")
    public BaseResponse delete() {
        appUserService.delete();
        return response();
    }

    @Deprecated
    @Operation(summary= "用户信息获取")
    @LoginAuthorityCheck
    @PostMapping(value = "/v1/app/user/info")
    public ResultResponse<UserInfoResult> info() {
        AppUser appUser = getHttp().getCache(AppUser.class);
        return response(appUserService.getUserInfo(appUser));
    }

    @Operation(summary= "用户信息获取V2")
    @LoginAuthorityCheck
    @GetMapping(value = "/v2/app/user/info")
    public ResultResponse<UserInfoV2Result> infoV2() {
        AppUser appUser = getHttp().getCache(AppUser.class);
        return response(appUserService.getUserV2Info(appUser));
    }


    @Operation(summary= "用户24小时内生效奖励")
    @LoginAuthorityCheck
    @PostMapping(value = "/v1/app/user/reward")
    public ResultResponse<List<UserRewardResult>> reward(@RequestBody UserRewardParam param) {
        AppUser appUser = getHttp().getCache(AppUser.class);
        return response(appActivityRewardService.getUserReward(param, appUser));
    }

    @Operation(summary= "用户领取奖励记录查询")
    @LoginAuthorityCheck
    @PostMapping(value = "/v1/app/user/find_reward_history")
    public PageResponse<List<RewardHistResult>> findRewardHistory(@RequestBody RewordHistParam param) {
        AppUser appUser = getHttp().getCache(AppUser.class);
        return response(appActivityRewardService.getRewardHistory(param, appUser));
    }

    @Operation(summary= "用户领取奖励")
    @LoginAuthorityCheck
    @PostMapping(value = "/v1/app/user/receive_award")
    public BaseResponse receiveAward(@RequestBody ReceiveAwardParam param) {
        AppUser appUser = getHttp().getCache(AppUser.class);
        appActivityRewardService.receiveAward(param, appUser);
        return response();
    }

    @Operation(summary= "判断是否新用户")
    @SecretKeyAuthorityCheck
    @PostMapping(value = "/v1/app/user/is_new")
    public ResultResponse<IsNewResult> isNew(@RequestBody IsNewParam param) {
        return response(appUserService.isNew(param));
    }

    @Operation(summary= "用户证书编号获取")
    @LoginAuthorityCheck
    @PostMapping(value = "/v1/app/user/find_user_cert")
    public ResultResponse<UserCertResult> findUserCert() {
        AppUser appUser = getHttp().getCache(AppUser.class);
        return response(appUserService.findUserCert(appUser));
    }

    /**
     * 竞猜项目用
     *
     * @param param
     * @return
     */
    @Operation(summary= "获取登录用户信息")
    @SecretKeyAuthorityCheck(9)
    @PostMapping(value = "/v1/app/user/find_login_user")
    public ResultResponse<UserDataResult> loginUserData(@RequestBody UserDataParam param) {
        return response(appUserService.loginUserData(param));
    }


    @Operation(summary= "查询活动列表")
    @LoginAuthorityCheck
    @PostMapping(value = "/v1/app/activity/find_activity_list")
    public ResultResponse<List<ActivityListResult>> findActivityList(@RequestBody ActivityListParam param) {
        AppUser appUser = getHttp().getCache(AppUser.class);
        return response(appActivityService.getActivityList(param, appUser));
    }

    @Operation(summary= "接收交易成功")
    @LoginAuthorityCheck
    @PostMapping(value = "/v1/app/activity/rec_suc_tran")
    public BaseResponse recSucTran(@RequestBody RecSucTranParam param) {
        AppUser appUser = getHttp().getCache(AppUser.class);
        appActivityService.recSucTran(param, appUser);
        return response();
    }


}
