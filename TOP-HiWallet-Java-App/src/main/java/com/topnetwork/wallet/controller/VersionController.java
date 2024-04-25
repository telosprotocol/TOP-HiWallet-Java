package com.topnetwork.wallet.controller;

import com.base.core.context.annotation.AuthenticationCheck;
import com.base.core.context.annotation.SecretKeyAuthorityCheck;
import com.base.core.context.mvc.BaseController;
import com.topnetwork.system.entity.User;
import com.topnetwork.wallet.common.enums.PlatformEnum;
import com.topnetwork.wallet.entity.SecretPlatform;
import com.topnetwork.wallet.param.wallet.VersionAddParam;
import com.topnetwork.wallet.param.wallet.VersionDelParam;
import com.topnetwork.wallet.param.wallet.VersionListParam;
import com.topnetwork.wallet.param.wallet.VersionUpdateParam;
import com.topnetwork.wallet.param.wallet.v1.VersionCheckV1Param;
import com.topnetwork.wallet.param.wallet.v3.VersionCheckParam;
import com.topnetwork.wallet.result.wallet.VersionCheckResult;
import com.topnetwork.wallet.result.wallet.VersionCheckV4Result;
import com.topnetwork.wallet.result.wallet.VersionListResult;
import com.topnetwork.wallet.service.AppVersionService;
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
 * @ClassName AppVersionController
 * @Description 版本管理
 * @Author bran
 * @Date 2020/4/24 11:31
 */
@RestController
@RequestMapping
@Tag(name = "版本管理api")
public class VersionController extends BaseController {

    @Autowired
    private AppVersionService appVersionService;

    @Operation(summary= "检查版本更新v4")
    @SecretKeyAuthorityCheck
    @PostMapping(value = "/v4/app/check_update")
    public ResultResponse<VersionCheckV4Result> checkUpdateV4(@RequestBody VersionCheckParam param) {
        SecretPlatform secretPlatform = getHttp().getCache(SecretPlatform.class);
        return response(appVersionService.checkUpdateV4(param, secretPlatform.getPlatform()));
    }

//    @Deprecated
//    @Operation(summary= "检查版本更新v3")
//    @SecretKeyAuthorityCheck
//    @PostMapping(value = "/v3/app/check_update")
//    public ResultResponse<VersionCheckResult> checkUpdateV3(@RequestBody VersionCheckParam param) {
//        SecretPlatform secretPlatform = getHttp().getCache(SecretPlatform.class);
//        return response(appVersionService.checkUpdate(param, secretPlatform.getPlatform()));
//    }
//
//    @Deprecated
//    @Operation(summary= "检查版本更新v2")
//    @SecretKeyAuthorityCheck
//    @PostMapping(value = "/v2/app/check_update")
//    public ResultResponse<VersionCheckResult> checkUpdateV2(@RequestBody com.topnetwork.wallet.param.wallet.v2.VersionCheckParam param) {
//        SecretPlatform secretPlatform = getHttp().getCache(SecretPlatform.class);
//        com.topnetwork.wallet.param.wallet.v3.VersionCheckParam versionCheckParamV3 = new com.topnetwork.wallet.param.wallet.v3.VersionCheckParam();
//        versionCheckParamV3.setVersion(param.getVersion());
//        versionCheckParamV3.setLanguage(param.getLanguage());
//        return response(appVersionService.checkUpdate(versionCheckParamV3, secretPlatform.getPlatform()));
//    }
//
//    @Deprecated
//    @Operation(summary= "检查版本更新v1")
//    @SecretKeyAuthorityCheck
//    @PostMapping(value = "/v1/app/check_update")
//    public ResultResponse<VersionCheckResult> checkUpdateV1(@RequestBody VersionCheckV1Param param) {
//        PlatformEnum platformEnum = param.getPlatform() == 1 ? PlatformEnum.IOS : PlatformEnum.ANDROID;
//        VersionCheckParam versionCheckParamV3 = new VersionCheckParam();
//        versionCheckParamV3.setVersion(param.getVersion());
//        return response(appVersionService.checkUpdate(versionCheckParamV3, platformEnum));
//    }

    @Operation(summary= "增加版本更新")
    @AuthenticationCheck
    @PostMapping(value = "/version/add_version_update")
    public BaseResponse addVersionUpdate(@RequestBody VersionAddParam param) {
        User user = getHttp().getCache(User.class);
        appVersionService.addVersionUpdate(param, user);
        return response();
    }

    @Operation(summary= "更新版本信息")
    @AuthenticationCheck
    @PostMapping(value = "/version/update_app_version")
    public BaseResponse updateAppVersion(@RequestBody VersionUpdateParam param) {
        appVersionService.updateAppVersion(param);
        return response();
    }

    @Operation(summary= "删除版本信息")
    @AuthenticationCheck
    @PostMapping(value = "/version/del_app_version")
    public BaseResponse delAppVersion(@RequestBody VersionDelParam param) {
        appVersionService.delAppVersion(param);
        return response();
    }

    @Operation(summary= "查找更新记录列表")
    @AuthenticationCheck
    @PostMapping(value = "/version/find_app_version_list")
    public PageResponse<List<VersionListResult>> findAppVersionList(@RequestBody VersionListParam param) {
        return response(appVersionService.findAppVersionList(param));
    }

    @Operation(summary= "查找更新详情")
    @AuthenticationCheck
    @PostMapping(value = "/version/find_app_version_detail")
    public ResultResponse<VersionListResult> findAppVersionDetail(@RequestBody VersionDelParam param) {
        VersionListResult versionListResult = appVersionService.findAppVersionDetail(param);
        return response(versionListResult);
    }
}
