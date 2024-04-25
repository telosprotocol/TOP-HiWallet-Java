//package com.topnetwork.wallet.controller;
//
//import com.base.core.context.annotation.AuthenticationCheck;
//import com.base.core.context.annotation.LoginAuthorityCheck;
//import com.base.core.context.annotation.SecretKeyAuthorityCheck;
//import com.base.core.context.annotation.ValidRequestParam;
//import com.topnetwork.ethexchange.param.NotifyParam;
//import com.topnetwork.system.entity.User;
//import com.topnetwork.wallet.entity.AppUser;
//import com.topnetwork.wallet.param.wallet.withdraw.*;
//import com.topnetwork.wallet.result.wallet.withdraw.*;
//import com.topnetwork.wallet.result.wallet.invite.PhoneCodeResult;
//import com.topnetwork.wallet.service.AppWithdrawOrderService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import com.base.core.context.mvc.BaseController;
//
//import io.swagger.v3.oas.annotations.tags.Tag;
//import io.swagger.v3.oas.annotations.Operation;
//import com.gitee.magic.framework.base.result.BaseResponse;
//import com.gitee.magic.framework.base.result.PageResponse;
//import com.gitee.magic.framework.base.result.ResultResponse;
//
//import java.util.List;
//
//@RestController
//@RequestMapping
//@Tag(name = "提现接口相关文档")
//public class AppWithdrawOrderController extends BaseController {
//    @Autowired
//    private AppWithdrawOrderService appWithdrawOrderService;
//
//    @Operation(summary= "1、查询提币订单列表")
//    @AuthenticationCheck
//    @GetMapping(value = "/withdraw/get_order_page")
//    public PageResponse<List<GetOrderPageResult>> getOrderPage(@ValidRequestParam GetOrderPageParam param) {
//        return response(appWithdrawOrderService.getOrderPage(param));
//    }
//
//
//    @Operation(summary= "0、查询提币订单详情")
//    @AuthenticationCheck
//    @GetMapping(value = "/withdraw/get_order_detail")
//    public ResultResponse<GetOrderDetailResult> getOrderDetail(@ValidRequestParam GetOrderDetailParam param) {
//        return response(appWithdrawOrderService.getOrderDetail(param));
//    }
//
//    @Operation(summary= "2、订单通过审核")
//    @AuthenticationCheck
//    @PostMapping(value = "/withdraw/approved_orders")
//    public ResultResponse<List<Long>> approvedOrders(@RequestBody ApprovedOrdersParam param) {
//        User user = getHttp().getCache(User.class);
//        return response(appWithdrawOrderService.approvedOrders(param, user));
//    }
//
//    @Operation(summary= "3、订单通未过审核")
//    @AuthenticationCheck
//    @PostMapping(value = "/withdraw/rejected_orders")
//    public BaseResponse rejectedOrders(@RequestBody ApprovedOrdersParam param) {
//        User user = getHttp().getCache(User.class);
//        appWithdrawOrderService.rejectedOrders(param, user);
//        return response();
//    }
//
//
//    @Operation(summary= "4、获取提现开关状态")
//    @LoginAuthorityCheck
//    @GetMapping(value = "/v1/app/airdrop/get_airdrop_status")
//    public ResultResponse<GetAirdropStatusResult> getWithdrawStatus() {
//        AppUser appUser = getHttp().getCache(AppUser.class);
//        return response(appWithdrawOrderService.getWithdrawStatus(appUser));
//    }
//
//    @Operation(summary= "5、获取提现基本信息")
//    @LoginAuthorityCheck
//    @GetMapping(value = "/v1/app/airdrop/get_airdrop_info")
//    public ResultResponse<GetAirdropInfoResult> getWithdrawInfo() {
//        AppUser appUser = getHttp().getCache(AppUser.class);
//        return response(appWithdrawOrderService.getWithdrawInfo(appUser));
//    }
//
//
//    @Operation(summary= "6、获取验证码")
//    @LoginAuthorityCheck
//    @GetMapping(value = "/v1/app/airdrop/get_phone_code")
//    public ResultResponse<PhoneCodeResult> getPhoneCode(@ValidRequestParam PhoneCodeParam param) {
//        AppUser appUser = getHttp().getCache(AppUser.class);
//        return response(appWithdrawOrderService.getPhoneCode(param, appUser));
//    }
//
//    @Operation(summary= "7、确认提现")
//    @LoginAuthorityCheck
//    @PostMapping(value = "/v1/app/airdrop/confirm_airdrop")
//    public ResultResponse<AirdropDetailResult> confirmAirdrop(@RequestBody ConfirmAirdropParam param) {
//        AppUser appUser = getHttp().getCache(AppUser.class);
//        return response(appWithdrawOrderService.confirmAirdrop(param, appUser));
//    }
//
//
//    @Operation(summary= "8、查询提币记录分页列表")
//    @LoginAuthorityCheck
//    @GetMapping(value = "/v1/app/airdrop/get_airdrop_page")
//    public PageResponse<List<AirdropSimpleResult>> getAirdropPage(@ValidRequestParam GetAirdropPageParam param) {
//        AppUser appUser = getHttp().getCache(AppUser.class);
//        return response(appWithdrawOrderService.getAirdropPage(appUser, param));
//    }
//
//    @Operation(summary= "9、查询提币记录详情")
//    @LoginAuthorityCheck
//    @GetMapping(value = "/v1/app/airdrop/get_airdrop_detail")
//    public ResultResponse<AirdropDetailResult> getAirdropDetail(@ValidRequestParam GetAirdropDetailParam param) {
//        AppUser appUser = getHttp().getCache(AppUser.class);
//        return response(appWithdrawOrderService.getAirdropDetail(appUser, param));
//    }
//
//    @Operation(summary="支付回调通知接口")
//    @PostMapping("/payment/notify")
//    @SecretKeyAuthorityCheck(9)
//    public ResultResponse<BaseResponse> notifyCallback(@RequestBody NotifyParam notifyParam) {
//        appWithdrawOrderService.notifyCallback(notifyParam);
//        BaseResponse baseResponse = new BaseResponse();
//        baseResponse.setCode(200);
//        return response(baseResponse);
//    }
//
//}
