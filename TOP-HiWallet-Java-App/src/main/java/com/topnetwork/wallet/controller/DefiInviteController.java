package com.topnetwork.wallet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.base.core.context.annotation.SecretKeyAuthorityCheck;
import com.base.core.context.mvc.BaseController;
import com.topnetwork.wallet.param.wallet.defi.BindDefiInviteParam;
import com.topnetwork.wallet.param.wallet.defi.GenerateDefiInviteParam;
import com.topnetwork.wallet.result.defi.DefiInviteResult;
import com.topnetwork.wallet.service.DefiInviteService;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import com.gitee.magic.framework.base.result.BaseResponse;
import com.gitee.magic.framework.base.result.ResultResponse;

@RestController
@RequestMapping("/defi_invite")
@Tag(name = "defi业务邀请接口文档")
public class DefiInviteController extends BaseController {
	
	@Autowired
	private DefiInviteService defiInviteService;
	
	@Operation(summary="生成邀请码[存在直接返回信息]")
	@SecretKeyAuthorityCheck
	@PostMapping("/generate")
	public ResultResponse<DefiInviteResult> generate(@RequestBody GenerateDefiInviteParam params) {
		return response(defiInviteService.generate(params));
	}
	
	@Operation(summary="绑定好友邀请码")
	@SecretKeyAuthorityCheck
	@PostMapping("/bind")
	public BaseResponse bind(@RequestBody BindDefiInviteParam params) {
		defiInviteService.bind(params);
		return response();
	}
}
