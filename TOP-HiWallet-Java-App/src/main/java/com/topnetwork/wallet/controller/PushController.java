package com.topnetwork.wallet.controller;

import com.base.core.context.annotation.AuthenticationCheck;
import com.base.core.context.annotation.SecretKeyAuthorityCheck;
import com.base.core.context.mvc.BaseController;
import com.topnetwork.system.entity.User;
import com.topnetwork.wallet.entity.SecretPlatform;
import com.topnetwork.wallet.param.wallet.*;
import com.topnetwork.wallet.param.wallet.commonpush.CommonPushMessageParam;
import com.topnetwork.wallet.param.wallet.defijpush.DefiPushMessageParam;
import com.topnetwork.wallet.param.wallet.v2.*;
import com.topnetwork.wallet.result.wallet.NoticeDetailResult;
import com.topnetwork.wallet.result.wallet.NoticeDetailV2Result;
import com.topnetwork.wallet.result.wallet.NoticeListResult;
import com.topnetwork.wallet.result.wallet.NoticeListV2Result;
import com.topnetwork.wallet.service.AppNoticeService;
import com.topnetwork.wallet.service.DeviceService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gitee.magic.framework.base.result.BaseResponse;
import com.gitee.magic.framework.base.result.PageResponse;
import com.gitee.magic.framework.base.result.ResultResponse;

import java.util.List;

/**
 * @ClassName PushController
 * @Description
 * @Author bran
 * @Date 2020/4/24 12:00
 */
@RestController
@RequestMapping
@Tag(name = "推送相关api")
public class PushController extends BaseController {

    @Autowired
    private DeviceService deviceService;
    @Autowired
    private AppNoticeService appNoticeService;

    @Operation(summary= "设备上报接口")
    @SecretKeyAuthorityCheck
    @PostMapping(value = "/v2/app/device/device_report")
    public BaseResponse deviceReport(@RequestBody DeviceBaseParam param) {
        SecretPlatform secretPlatform = getHttp().getCache(SecretPlatform.class);
        deviceService.reportDevice(param, secretPlatform.getPlatform());
        return response();
    }

    @Operation(summary= "代币上报接口")
    @SecretKeyAuthorityCheck
    @PostMapping(value = "/v2/app/device/token_report")
    public BaseResponse tokenReport(@RequestBody DeviceCoinParam param) {
        deviceService.tokenReport(param);
        return response();
    }

    @Operation(summary= "设备移除token")
    @SecretKeyAuthorityCheck
    @PostMapping(value = "/v2/app/device/remove_token")
    public BaseResponse removeToken(@RequestBody DeviceCoinParam param) {
        deviceService.removeToken(param);
        return response();
    }

    @Operation(summary= "设备移除身份")
    @SecretKeyAuthorityCheck
    @PostMapping(value = "/v2/app/device/remove_user")
    public BaseResponse removeUser(@RequestBody DeviceDelUserParam param) {
        deviceService.removeUser(param);
        return response();
    }

    @Operation(summary= "接收交易推送")
    @SecretKeyAuthorityCheck
    @PostMapping(value = "/v2/app/eth/rec_tran_info")
    public BaseResponse recTranInfo(@RequestBody RecTranInfoParam param) {
        deviceService.recEthTranInfo(param);
        return response();
    }

    @Operation(summary= "获取推送详情")
    @SecretKeyAuthorityCheck
    @PostMapping("/v2/app/find_notice_detail")
    public ResultResponse<NoticeDetailV2Result> findNoticeDetail(@RequestBody NoticeDetailV2Param param) {
        return response(appNoticeService.findNoticeDetail(param));
    }


    @Operation(summary= "获取推送列表")
    @SecretKeyAuthorityCheck
    @PostMapping("/v2/app/find_notice_list")
    public ResultResponse<NoticeListV2Result> findNoticeList(@RequestBody NoticeListV2Param param) {
        return response(appNoticeService.findNoticeListForApp(param));
    }


    @Operation(summary= "获取推送列表")
    @AuthenticationCheck
    @PostMapping("/notice/find_notice_list")
    public PageResponse<List<NoticeListResult>> findNoticeList(@RequestBody NoticeListParam param) {
        return response(appNoticeService.findNoticeList(param));
    }

    @Operation(summary= "获取推送详情")
    @AuthenticationCheck
    @PostMapping("/notice/find_notice_detail")
    public ResultResponse<NoticeDetailResult> findNoticeDetail(@RequestBody NoticeDetailParam param) {
        return response(appNoticeService.findNoticeDetail(param));
    }

    @Operation(summary= "删除推送")
    @AuthenticationCheck
    @PostMapping("/notice/del_notice")
    public BaseResponse delNotice(@RequestBody NoticeDelParam param) {
        appNoticeService.delNotice(param);
        return response();
    }

    @Operation(summary= "更新推送")
    @AuthenticationCheck
    @PostMapping("/notice/update_notice")
    public BaseResponse updateNotice(@RequestBody NoticeUpdParam param) {
        User user = getHttp().getCache(User.class);
        appNoticeService.updateNotice(param, user);
        return response();
    }

    @Operation(summary= "新增推送")
    @AuthenticationCheck
    @PostMapping("/notice/add_notice")
    public BaseResponse addNotice(@RequestBody NoticeAddParam param) {
        User user = getHttp().getCache(User.class);
        appNoticeService.addNotice(param, user);
        return response();
    }

    @Operation(summary= "接收defi消息推送")
    @SecretKeyAuthorityCheck
    @PostMapping(value = "/jpush/defi_info")
    public BaseResponse defiInfo(@RequestBody DefiPushMessageParam param) {
        deviceService.defiInfo(param);
        return response();
    }

    @Operation(summary= "接收Route消息推送")
    @SecretKeyAuthorityCheck
    @PostMapping(value = "/jpush/route_info")
    public BaseResponse routeInfo(@RequestBody CommonPushMessageParam param) {
        deviceService.routeInfo(param);
        return response();
    }
}
