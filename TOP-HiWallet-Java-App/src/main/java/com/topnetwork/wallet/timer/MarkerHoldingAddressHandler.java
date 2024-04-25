package com.topnetwork.wallet.timer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.base.core.context.utils.LoggerUtils;
import com.topnetwork.wallet.service.DeviceCoinsService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;

import com.gitee.magic.core.utils.StackTraceInfo;

/**
 * 标记存币地址
 */
@Component
public class MarkerHoldingAddressHandler {

    @Autowired
    private DeviceCoinsService deviceCoinsService;

    @XxlJob("markerHoldingAddressJob")
    public ReturnT<String> markerHoldingAddressJob(String param) {
    	try {
    		deviceCoinsService.markerHoldingCoins();
    	}catch(Exception e) {
    		LoggerUtils.printErrorLog(StackTraceInfo.getTraceInfo(), e);
			return ReturnT.FAIL;
    	}
    	return ReturnT.SUCCESS;
    }
}
