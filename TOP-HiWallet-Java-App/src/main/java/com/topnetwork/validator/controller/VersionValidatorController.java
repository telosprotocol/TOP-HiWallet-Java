package com.topnetwork.validator.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.base.core.context.annotation.AuthenticationCheck;
import com.base.core.context.annotation.SecretKeyAuthorityCheck;
import com.base.core.context.annotation.ValidRequestParam;
import com.base.core.context.mvc.BaseController;
import com.gitee.magic.framework.base.result.BaseResponse;
import com.gitee.magic.framework.base.result.PageResponse;
import com.gitee.magic.framework.base.result.ResultResponse;
import com.topnetwork.validator.service.VersionService;
import com.topnetwork.wallet.param.wallet.ValidatorUpdateParam;
import com.topnetwork.wallet.param.wallet.VersionDelParam;
import com.topnetwork.wallet.param.wallet.VersionListParam;
import com.topnetwork.wallet.param.wallet.validator.ValidatorAddParam;
import com.topnetwork.wallet.param.wallet.validator.ValidatorStatusParam;
import com.topnetwork.wallet.result.wallet.validator.TableBarConfigResult;
import com.topnetwork.wallet.result.wallet.validator.ValidatorListResult;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping
@Tag(name = "提审版本操作")
public class VersionValidatorController extends BaseController {


    @Autowired
    private VersionService versionService;

    @Operation(summary= "查询版本审核状态")
    @SecretKeyAuthorityCheck
    @GetMapping(value = "/v1/conf/tab_bar")
    public ResultResponse<TableBarConfigResult> findValidatorStatus(@ValidRequestParam ValidatorStatusParam param) {
        TableBarConfigResult status =  versionService.findValidatorStatus(param);
        return response(status);
    }



    @Operation(summary= "增加版本记录")
    @AuthenticationCheck
    @PostMapping(value = "/validator/add_version")
    public BaseResponse addVersionUpdate(@RequestBody ValidatorAddParam param) {
        versionService.addVersionUpdate(param);
        return response();
    }

    @Operation(summary= "更新版本信息")
    @AuthenticationCheck
    @PostMapping(value = "/validator/update_version")
    public BaseResponse updateAppVersion(@RequestBody ValidatorUpdateParam param) {
        versionService.updateAppVersion(param);
        return response();
    }

    @Operation(summary= "删除版本信息")
    @AuthenticationCheck
    @PostMapping(value = "/validator/del_version")
    public BaseResponse delAppVersion(@RequestBody VersionDelParam param) {
        versionService.delAppVersion(param);
        return response();
    }

    @Operation(summary= "查找更新记录列表")
    @AuthenticationCheck
    @PostMapping(value = "/validator/find_version_list")
    public PageResponse<List<ValidatorListResult>> findAppVersionList(@RequestBody VersionListParam param) {
        return response(versionService.findAppVersionList(param));
    }

    @Operation(summary= "查找更新详情")
    @AuthenticationCheck
    @PostMapping(value = "/validator/find_version_detail")
    public ResultResponse<ValidatorListResult> findAppVersionDetail(@RequestBody VersionDelParam param) {
        ValidatorListResult validatorResult = versionService.findAppVersionDetail(param);
        return response(validatorResult);
    }


}
