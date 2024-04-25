package com.topnetwork.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.base.core.context.annotation.AuthenticationCheck;
import com.base.core.context.mvc.BaseController;
import com.topnetwork.system.service.PermissionService;
import com.topnetwork.wallet.result.common.system.PermissionResult;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import com.gitee.magic.framework.base.result.ResultResponse;

@RestController
@RequestMapping("/permission")
@Tag(name = "权限")
public class PermissionController extends BaseController {

    @Autowired
    private PermissionService permissionService;

    @Operation(summary="权限列表")
    @AuthenticationCheck
    @PostMapping("/all_list")
    public ResultResponse<List<PermissionResult>> all_list() {
        return response(permissionService.getAllPermissionBusiness());
    }

}
