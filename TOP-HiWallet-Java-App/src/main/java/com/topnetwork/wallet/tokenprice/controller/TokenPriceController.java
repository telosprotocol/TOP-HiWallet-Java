package com.topnetwork.wallet.tokenprice.controller;

import com.base.core.context.annotation.SecretKeyAuthorityCheck;
import com.topnetwork.wallet.tokenprice.service.TokenPriceService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.base.core.context.mvc.BaseController;

import io.swagger.v3.oas.annotations.tags.Tag;
import com.gitee.magic.framework.base.result.ResultResponse;

import java.util.List;

@RestController
@RequestMapping("/token_price")
@Tag(name = "tokenview接口文档")
public class TokenPriceController extends BaseController {

    @Autowired
    private TokenPriceService tokenPriceService;


    @SecretKeyAuthorityCheck
    @GetMapping("/get_ids")
    @Operation(summary= "查找EOS节点列表")
    public ResultResponse<String> getIds() {
        return response(tokenPriceService.getAllIds());
    }


}
