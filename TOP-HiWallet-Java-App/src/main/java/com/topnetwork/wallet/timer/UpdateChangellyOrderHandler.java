package com.topnetwork.wallet.timer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.base.core.context.utils.LoggerUtils;
import com.topnetwork.wallet.service.AppChangellyOrderService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;

import com.gitee.magic.core.utils.StackTraceInfo;

/**
 * @ClassName UpdateChangellyOrderHandler
 * @Description 更新changelly订单状态
 * @Author bran
 * @Date 2020/6/19 10:59
 */
@Component
public class UpdateChangellyOrderHandler {

    @Autowired
    private AppChangellyOrderService appChangellyOrderService;

    @XxlJob("updateChangellyOrder")
    public ReturnT<String> execute(String param) {
    	try {
    		appChangellyOrderService.updateChangellyOrder();
    	}catch(Exception e) {
    		LoggerUtils.printErrorLog(StackTraceInfo.getTraceInfo(), e);
			return ReturnT.FAIL;
    	}
        return ReturnT.SUCCESS;
    }
}
