package com.topnetwork.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.base.core.context.annotation.AuthenticationCheck;
import com.base.core.context.annotation.SecretKeyAuthorityCheck;
import com.base.core.context.mvc.BaseController;
import com.base.service.base.logger.service.LoginLogService;
import com.topnetwork.system.entity.User;
import com.topnetwork.system.service.UserService;
import com.topnetwork.wallet.param.system.UserCreateParam;
import com.topnetwork.wallet.param.system.UserDetailParam;
import com.topnetwork.wallet.param.system.UserLoginParam;
import com.topnetwork.wallet.param.system.UserModifyParam;
import com.topnetwork.wallet.param.system.UserModifyPwdParam;
import com.topnetwork.wallet.param.system.UserPageParam;
import com.topnetwork.wallet.param.system.UserRestPwdParam;
import com.topnetwork.wallet.result.common.system.UserDetailResult;
import com.topnetwork.wallet.result.common.system.UserLoginResult;
import com.topnetwork.wallet.result.common.system.UserPageResult;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import com.gitee.magic.framework.base.result.BaseResponse;
import com.gitee.magic.framework.base.result.PageResponse;
import com.gitee.magic.framework.base.result.ResultResponse;

@RestController
@RequestMapping("/user")
@Tag(name = "管理后台用户")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;
    @Autowired
    private LoginLogService loginLogService;

    @Operation(summary="登录")
    @SecretKeyAuthorityCheck
    @PostMapping("/login")
    public ResultResponse<UserLoginResult> login(@RequestBody UserLoginParam param) {
        return response(userService.loginBusiness(param, getHttp().getRequestIp()));
    }

    @Operation(summary="用户管理")
    @AuthenticationCheck
    @PostMapping("/manager_page")
    public PageResponse<List<UserPageResult>> manager_page(@RequestBody UserPageParam param) {
        return response(userService.userPageBusiness(param));
    }

    @Operation(summary="创建用户")
    @AuthenticationCheck
    @PostMapping("/create")
    public BaseResponse create(@RequestBody UserCreateParam param) {
        userService.createBusiness(param);
        return response();
    }

    @Operation(summary="修改用户")
    @AuthenticationCheck
    @PostMapping("/modify")
    public BaseResponse modify(@RequestBody UserModifyParam param) {
        userService.modifyBusiness(param);
        return response();
    }

    @Operation(summary="用户详情")
    @AuthenticationCheck
    @PostMapping("/detail")
    public ResultResponse<UserDetailResult> detail(@RequestBody UserDetailParam param) {
        return response(userService.detailBusiness(param));
    }

    @Operation(summary="删除用户")
    @AuthenticationCheck
    @PostMapping("/delete")
    public BaseResponse delete(@RequestBody UserDetailParam param) {
        userService.deleteBusiness(param);
        return response();
    }

    @Operation(summary="修改密码")
    @AuthenticationCheck
    @PostMapping("/modify_pwd")
    public BaseResponse modify_pwd(@RequestBody UserModifyPwdParam param) {
        User user = requestCache(User.class);
        userService.modifyPwdBusiness(param, user);
        return response();
    }

    @Operation(summary="密码重置")
    @AuthenticationCheck
    @PostMapping("/rest_pwd")
    public BaseResponse rest_pwd(@RequestBody UserRestPwdParam param) {
        userService.restPwdBusiness(param);
        return response();
    }

    @Operation(summary="退出登录")
    @AuthenticationCheck
    @PostMapping("/logout")
    public BaseResponse logout() {
        loginLogService.logout(getHttp().getAccessId());
        return response();
    }

}
