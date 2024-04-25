package com.topnetwork.wallet.controller;

import com.base.core.context.annotation.AuthenticationCheck;
import com.base.core.context.annotation.SecretKeyAuthorityCheck;
import com.base.core.context.annotation.ValidRequestParam;
import com.topnetwork.wallet.param.wallet.invite.*;
import com.topnetwork.wallet.result.wallet.invite.*;
import com.topnetwork.wallet.service.AppInviteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.base.core.context.mvc.BaseController;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import com.gitee.magic.framework.base.result.BaseResponse;
import com.gitee.magic.framework.base.result.PageResponse;
import com.gitee.magic.framework.base.result.ResultResponse;

import java.util.List;

@RestController
@RequestMapping("/invite")
@Tag(name = "邀请相关接口文档")
public class AppInviteController extends BaseController {

    @Autowired
    private AppInviteService appInviteService;

    @Operation(summary= "1、邀请好友页面-邀请成果")
    @SecretKeyAuthorityCheck
    @GetMapping(value = "/get_user_info")
    public ResultResponse<UserInfoResult> getInfo(@ValidRequestParam UserInfoParam param) {
        return response(appInviteService.getInfo(param));
    }


    @Operation(summary= "2、邀请好友页面-邀请历史")
    @SecretKeyAuthorityCheck
    @GetMapping(value = "/get_list")
    public PageResponse<List<UserListResult>> getList(@ValidRequestParam UserListParam param) {
        return response(appInviteService.getList(param));
    }

    @Operation(summary= "3、邀请好友页面-获取邀请码")
    @SecretKeyAuthorityCheck
    @GetMapping(value = "/get_invite_code")
    public ResultResponse<InviteCodeResult> getInviteCode(@ValidRequestParam InviteCodeParam param) {
        return response(appInviteService.getInviteCode(param));
    }

    @Operation(summary= "5、注册落地页面-验证手机号")
    @SecretKeyAuthorityCheck
    @GetMapping(value = "/verify_phone_code")
    public BaseResponse getPhoneCode(@ValidRequestParam PhoneVerifyParam param) {
        appInviteService.verifyPhoneCode(param);
        return response();
    }

    @Operation(summary= "5、注册落地页面-获取验证码")
    @SecretKeyAuthorityCheck
    @GetMapping(value = "/get_phone_code")
    public ResultResponse<PhoneCodeResult> getPhoneCode(@ValidRequestParam PhoneCodeParam param) {
        return response(appInviteService.getPhoneCode(param));
    }

    @Operation(summary= "6、注册落地页面-接收邀请")
    @SecretKeyAuthorityCheck
    @PostMapping(value = "/rec_invite")
    public BaseResponse recInvite(@RequestBody RecInviteParam param) {
        appInviteService.recInvite(param);
        return response();
    }

    @Operation(summary= "7、注册落地页面-获取接收邀请奖励数值")
    @SecretKeyAuthorityCheck
    @GetMapping(value = "/get_reward")
    public ResultResponse<RewardNumberResult> getRewardNumber() {
        return response(appInviteService.getRewardNumber());
    }

    @Operation(summary= "8、app绑定手机号")
    @SecretKeyAuthorityCheck
    @PutMapping(value = "/app_bind_phone")
    public ResultResponse<BindPhoneResult> appBindPhone(@RequestBody BindPhoneParam param) {
        return response(appInviteService.appBindPhone(param));
    }


    @Operation(summary= "10、注册落地页面-验证手机号")
    @SecretKeyAuthorityCheck
    @GetMapping(value = "/verify_phone_code_bind")
    public BaseResponse verifyPhoneCodeBind(@ValidRequestParam PhoneVerifyParam param) {
        appInviteService.verifyPhoneCodeBind(param);
        return response();
    }
    @Operation(summary= "10、绑定手机号码页面-获取验证码")
    @SecretKeyAuthorityCheck
    @GetMapping(value = "/get_phone_code_bind")
    public ResultResponse<PhoneCodeResult> getPhoneCodeBind(@ValidRequestParam PhoneCodeParam param) {
        return response(appInviteService.getPhoneCodeBind(param));
    }

    @Operation(summary= "9、注册落地页面-上传用户code")
    @SecretKeyAuthorityCheck
    @PutMapping(value = "/put_code")
    public BaseResponse putCode(@RequestBody PutCodeParam param) {
        appInviteService.putCode(param);
        return response();
    }

    @Operation(summary= "11、管理员根据手机号获取已发送验证码")
    @AuthenticationCheck
    @GetMapping(value = "/get_phone_code_manager")
    public ResultResponse<ManagerPhoneCodeResult> getPhoneCodeForManager(@ValidRequestParam ManagerPhoneCodeParam param) {
        return response(appInviteService.getPhoneCodeForManager(param));
    }

    @Operation(summary= "12、获取短信发送支持国家码")
    @SecretKeyAuthorityCheck
    @GetMapping(value = "/get_country_code")
    public ResultResponse<List<Integer>> getCountryCodes() {
        return response(appInviteService.getCountryCodes());
    }

}
