package com.topnetwork.wechat.controller;

import com.base.core.context.annotation.SecretKeyAuthorityCheck;
import com.base.core.context.annotation.ValidRequestParam;
import com.topnetwork.wallet.param.wechat.SignatureParam;
import com.topnetwork.wallet.result.wechat.SignatureResult;
import com.topnetwork.wechat.service.ShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.base.core.context.mvc.BaseController;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import com.gitee.magic.framework.base.result.BaseResponse;
import com.gitee.magic.framework.base.result.ResultResponse;

@RestController
@RequestMapping("/share")
@Tag(name = "微信分享")
public class ShareController extends BaseController {
	@Autowired
	private ShareService shareService;
	
	@Operation(summary="微信获取签名")
	@GetMapping("/get_signature")
	@SecretKeyAuthorityCheck
	public ResultResponse<SignatureResult> getSignature(@ValidRequestParam SignatureParam signatureParam) {
		SignatureResult signatureResult = shareService.getSignature(signatureParam);
		return response(signatureResult);
	}
	
}
