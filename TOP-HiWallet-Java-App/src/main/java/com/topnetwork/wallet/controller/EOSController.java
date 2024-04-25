package com.topnetwork.wallet.controller;

import com.base.core.context.annotation.AuthenticationCheck;
import com.base.core.context.annotation.SecretKeyAuthorityCheck;
import com.base.core.context.annotation.ValidRequestParam;
import com.base.core.context.mvc.BaseController;
import com.topnetwork.wallet.param.wallet.EOSAddNodeParam;
import com.topnetwork.wallet.param.wallet.EOSDelNodeParam;
import com.topnetwork.wallet.param.wallet.EOSGetNodesParam;
import com.topnetwork.wallet.param.wallet.EOSUpdNodeParam;
import com.topnetwork.wallet.param.wallet.v1.GetEOSNodeParam;
import com.topnetwork.wallet.result.wallet.EOSManageNodeResult;
import com.topnetwork.wallet.result.wallet.GetEOSNodeResult;
import com.topnetwork.wallet.service.EOSNodeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.gitee.magic.framework.base.result.BaseResponse;
import com.gitee.magic.framework.base.result.PageResponse;

import java.util.List;

/**
 * @ClassName EOSController
 * @Description
 * @Author bran
 * @Date 2020/4/24 11:52
 */
@RestController
@RequestMapping
@Tag(name = "eos相关api")
public class EOSController extends BaseController {

    @Autowired
    private EOSNodeService eosNodeService;

    @SecretKeyAuthorityCheck
    @GetMapping("/v1/app/eos/get_eos_nodes")
    @Operation(summary= "查找EOS节点列表")
    public PageResponse<List<GetEOSNodeResult>> get_eos_nodes(@ValidRequestParam GetEOSNodeParam param) {
        return response(eosNodeService.getEosNodes(param));
    }

    @AuthenticationCheck
    @GetMapping("/eos/get_eos_nodes")
    @Operation(summary= "查找EOS节点列表")
    public PageResponse<List<EOSManageNodeResult>> get_eos_nodes(@ValidRequestParam EOSGetNodesParam param) {
        return response(eosNodeService.getEosNodes(param));
    }

    @AuthenticationCheck
    @PostMapping("/eos/add_eos_node")
    @Operation(summary= "新增EOS节点列表")
    public BaseResponse addEosNode(@RequestBody EOSAddNodeParam param) {
        eosNodeService.addEosNode(param);
        return response();
    }

    @AuthenticationCheck
    @DeleteMapping("/eos/delete_eos_node")
    @Operation(summary= "删除EOS节点列表")
    public BaseResponse deleteEosNode(@RequestBody EOSDelNodeParam param) {
        eosNodeService.deleteEosNode(param);
        return response();
    }

    @AuthenticationCheck
    @PutMapping("/eos/update_eos_node")
    @Operation(summary= "修改EOS节点列表")
    public BaseResponse update_eos_node(@RequestBody EOSUpdNodeParam param) {
        eosNodeService.updateEosNode(param);
        return response();
    }
}
