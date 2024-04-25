package com.topnetwork.filecoin.controller;

import com.base.core.context.annotation.SecretKeyAuthorityCheck;
import com.topnetwork.filecoin.service.FiledRecordsService;
import com.topnetwork.wallet.param.filecoin.AddRecordParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.base.core.context.mvc.BaseController;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import com.gitee.magic.framework.base.result.BaseResponse;

@RestController
@RequestMapping
@Tag(name = "filecoin申请单接口")
public class FiledRecordsController extends BaseController {

	@Autowired
	private FiledRecordsService filedRecordsService;

	@Operation(summary="增加申请记录")
	@PostMapping("/filecoin_records/add")
	@SecretKeyAuthorityCheck
	public BaseResponse addFileCoinRecords(@RequestBody AddRecordParam param) {
		filedRecordsService.addFileCoinRecords(param);
		return response();
	}
	
}
