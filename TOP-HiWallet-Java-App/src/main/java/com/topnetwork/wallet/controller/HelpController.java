package com.topnetwork.wallet.controller;

import com.base.core.context.annotation.AuthenticationCheck;
import com.base.core.context.annotation.SecretKeyAuthorityCheck;
import com.topnetwork.system.entity.User;
import com.topnetwork.wallet.param.wallet.*;
import com.topnetwork.wallet.result.wallet.*;
import com.topnetwork.wallet.service.AppHelpCategoryService;
import com.topnetwork.wallet.service.AppHelpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.base.core.context.mvc.BaseController;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import com.gitee.magic.framework.base.result.BaseResponse;
import com.gitee.magic.framework.base.result.PageResponse;
import com.gitee.magic.framework.base.result.ResultResponse;

import java.util.List;

@RestController
@RequestMapping()
@Tag(name = "帮助文档")
public class HelpController extends BaseController {

    @Autowired
    private AppHelpService appHelpService;
    @Autowired
    private AppHelpCategoryService appHelpCategoryService;

    @SecretKeyAuthorityCheck
    @PostMapping("/help/find_help_list")
    @Operation(summary= "获取帮助列表")
    public PageResponse<List<HelpListResult>> findHelpList(@RequestBody HelpListParam param) {
        return response(appHelpService.findHelpList(param));
    }

    @SecretKeyAuthorityCheck
    @PostMapping("/help/find_help_detail")
    @Operation(summary= "获取帮助详情")
    public ResultResponse<HelpDetailResult> findHelpDetail(@RequestBody HelpDetailParam param) {
        return response(appHelpService.findHelpDetail(param));
    }

    @Operation(summary= "更新帮助")
    @AuthenticationCheck
    @PostMapping("/help/update_help")
    public BaseResponse updateHelp(@RequestBody HelpUpdParam helpUpdParam) {
        User user = getHttp().getCache(User.class);
        appHelpService.updateHelp(helpUpdParam, user);
        return response();
    }

    @Operation(summary= "新增帮助")
    @AuthenticationCheck
    @PostMapping("/help/add_help")
    public BaseResponse addHelp(@RequestBody HelpAddParam helpAddParam) {
        User user = getHttp().getCache(User.class);
        appHelpService.addHelp(helpAddParam, user);
        return response();
    }

    @Operation(summary= "删除帮助")
    @AuthenticationCheck
    @PostMapping("/help/del_help")
    public BaseResponse delHelp(@RequestBody HelpDelParam param) {
        appHelpService.delHelp(param);
        return response();
    }

    @Operation(summary= "钱包官网根据一级类目id获取帮助标题列表")
    @SecretKeyAuthorityCheck
    @PostMapping(value = "/help/find_fist_help_for_wallet")
    public ResultResponse<List<HelpWalletFristResult>> findFistHelpForWallet(@RequestBody HelpWalletParam param) {
        return response(appHelpService.findFistHelpForWallet(param));
    }

    @Operation(summary= "钱包官网根据帮助id获取同级帮助列表")
    @SecretKeyAuthorityCheck
    @PostMapping(value = "/help/find_sec_help_for_wallet")
    public ResultResponse<HelpWalletSecResult> findSecHelpForWallet(@RequestBody HelpWalletParam param) {
        return response(appHelpService.findSecHelpForWallet(param));
    }


    @Operation(summary= "钱包官网搜索帮助列表")
    @SecretKeyAuthorityCheck
    @PostMapping(value = "/help/search")
    public PageResponse<List<HelpSearchResult>> search(@RequestBody HelpSearchParam param) {
        return response(appHelpService.search(param));
    }

    @SecretKeyAuthorityCheck
    @PostMapping("/help_category/find_category_list")
    @Operation(summary= "获取帮助类目")
    public ResultResponse<List<CategoryListResult>> findCategoryList(@RequestBody CategoryListParam param) {
        return response(appHelpCategoryService.findCategoryList(param));
    }

}
