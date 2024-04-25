package com.topnetwork.filecoin.controller;

import com.base.core.context.annotation.RestfulCheck;
import com.topnetwork.filecoin.service.TempDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.base.core.context.mvc.BaseController;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import com.gitee.magic.framework.base.result.BaseResponse;

@RestController
@RequestMapping
@Tag(name = "接口文档")
public class TempDataController extends BaseController {

    @Autowired
    private TempDataService tempDataService;


    @Operation(summary="存储cid")
    @RestfulCheck
    @GetMapping("/filecoin/save/{cid}/{type}/{size}")
    public BaseResponse saveCid(@PathVariable String cid,
                                @PathVariable String type,
                                @PathVariable String size,
                                @RequestParam String fileName) {
        tempDataService.saveCid(cid, type, size, fileName);
        return response();
    }

}
