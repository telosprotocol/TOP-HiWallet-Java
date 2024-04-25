package com.topnetwork.recharge.controller;

import com.base.core.context.annotation.AuthenticationCheck;
import com.base.core.context.annotation.ValidRequestParam;
import com.topnetwork.recharge.service.AppUserRechargeService;
import com.topnetwork.system.entity.User;
import com.topnetwork.wallet.param.recharge.GetRechargeHistoryParam;
import com.topnetwork.wallet.param.recharge.RechargeListParam;
import com.topnetwork.wallet.result.recharge.GetRechargeHistoryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.base.core.context.mvc.BaseController;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import com.gitee.magic.framework.base.result.BaseResponse;
import com.gitee.magic.framework.base.result.PageResponse;

import java.util.List;

@RestController
@RequestMapping
@Tag(name = "新青年充值相关接口")
public class AppUserRechargeController extends BaseController {

    @Autowired
    private AppUserRechargeService appUserRechargeService;

    @AuthenticationCheck
    @GetMapping("/recharge/get_recharge_history")
    @Operation(summary= "查找充值记录")
    public PageResponse<List<GetRechargeHistoryResult>> getRechargeHistory(@ValidRequestParam GetRechargeHistoryParam param) {
        return response(appUserRechargeService.getRechargeHistory(param));
    }


    @AuthenticationCheck
    @PostMapping("/recharge/recharge")
    @Operation(summary= "充值打币")
    public BaseResponse recharge(@RequestBody RechargeListParam param) {
        User user = getHttp().getCache(User.class);
        appUserRechargeService.recharge(param, user);
        return response();
    }

    @Operation(summary= "获取验证码")
    @AuthenticationCheck
    @GetMapping(value = "/recharge/get_phone_code")
    public BaseResponse getPhoneCode() {
        appUserRechargeService.getPhoneCode();
        return response();
    }

}
