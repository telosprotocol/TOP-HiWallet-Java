package com.topnetwork.wallet.controller;

import com.base.core.context.annotation.AuthenticationCheck;
import com.base.core.context.mvc.BaseController;
import com.topnetwork.system.entity.User;
import com.topnetwork.wallet.param.wallet.ResourceAddParam;
import com.topnetwork.wallet.param.wallet.ResourceListParam;
import com.topnetwork.wallet.result.wallet.GetResourceResult;
import com.topnetwork.wallet.service.AppResourceService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gitee.magic.framework.base.result.BaseResponse;
import com.gitee.magic.framework.base.result.PageResponse;

import java.util.List;

@RestController
@RequestMapping
@Tag(name = "钱包文件资源管理")
public class ResourceController extends BaseController {

    @Autowired
    private AppResourceService appResourceService;


    @Operation(summary= "查找资源列表")
    @AuthenticationCheck
    @PostMapping(value = "/resource/find_resource_list")
    public PageResponse<List<GetResourceResult>> findResourceList(@RequestBody ResourceListParam param) {
        return response(appResourceService.findResourceList(param));
    }

    @Operation(summary= "增加资源列表")
    @AuthenticationCheck
    @PostMapping(value = "/resource/add_resource")
    public BaseResponse addResource(@RequestBody ResourceAddParam param) {
        User user = getHttp().getCache(User.class);
        appResourceService.addResource(param, user);
        return response();
    }
}
