package com.topnetwork.common.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.base.component.upload.service.BaseUploadService;
import com.base.core.context.annotation.SecretKeyAuthorityCheck;
import com.base.core.context.mvc.BaseController;
import com.base.core.head.vo.UploadStsVO;
import com.gitee.magic.core.utils.StringUtils;
import com.gitee.magic.framework.base.result.ResultResponse;
import com.topnetwork.wallet.result.wallet.config.UploadResult;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/aws")
@Tag(name = "云存储")
public class AwsController extends BaseController {
	
	@Autowired
	private BaseUploadService uploadService;

	@Operation(summary="申请文件Key")
	@SecretKeyAuthorityCheck
	@GetMapping("/applykey")
	public ResultResponse<UploadResult> applykey() {
		UploadResult result=new UploadResult();
		result.setUploadKey(StringUtils.random());
		return response(result);
	}

	@Operation(summary="获取STS授权")
	@SecretKeyAuthorityCheck
	@GetMapping("/sts")
	public ResultResponse<Map<String,String>> sts() {
		UploadStsVO vo=uploadService.sts("abc");
		Map<String,String> param=new HashMap<>();
		param.put("accessKeyId", vo.getAccessKeyId());
		param.put("secretAccessKey", vo.getAccessKeySecret());
		param.put("sessionToken", vo.getSecurityToken());
		return response(param);
	}
	
}