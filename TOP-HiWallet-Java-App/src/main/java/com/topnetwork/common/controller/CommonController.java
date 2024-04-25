package com.topnetwork.common.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.base.core.context.mvc.BaseController;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/common")
@Tag(name = "公共")
public class CommonController extends BaseController {

//	@Autowired
//	private AwsService awsService;
//
//	@Deprecated
//	@Operation(summary="获取上传文件名")
//	@SecretKeyAuthorityCheck
//	@PostMapping("/get_upload_apply_result")
//	public ResultResponse<FileUploadKeyResult> getUploadApplyResult() {
//		return response(awsService.uploadKey());
//	}
//
//	@Deprecated
//	@Operation(summary="获取AWS上传文件许可")
//	@SecretKeyAuthorityCheck
//	@PostMapping("/get_aws_apply_result")
//	public ResultResponse<S3STSResult> getAwsApplyResult() {
//		return response(awsService.stsAuth());
//	}

}
