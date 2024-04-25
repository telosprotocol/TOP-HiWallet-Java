package com.topnetwork.wallet.service;

import com.topnetwork.wallet.param.wallet.changelly.CreateTransactionParam;
import com.topnetwork.wallet.param.wallet.changelly.GetOrderDetailParam;
import com.topnetwork.wallet.param.wallet.changelly.GetOrderPageParam;
import com.topnetwork.wallet.param.wallet.changelly.ReportTransResultParam;
import com.topnetwork.wallet.result.changelly.CreateTransactionResult;
import com.topnetwork.wallet.result.changelly.GetOrderDetailResult;
import com.topnetwork.wallet.result.changelly.GetOrderPageResult;
import com.gitee.magic.framework.base.result.QueryResult;
import com.base.core.framework.sql.service.SqlBaseService;
import com.topnetwork.wallet.entity.AppChangellyOrder;

import java.util.List;

public interface AppChangellyOrderService extends SqlBaseService<AppChangellyOrder,Long> {


    CreateTransactionResult createOrder(CreateTransactionParam param);

    QueryResult<List<GetOrderPageResult>> getOrderPage(GetOrderPageParam param);

    GetOrderDetailResult getOrderDetail(GetOrderDetailParam param);

    void reportTransferResults(ReportTransResultParam param);

    void updateChangellyOrder();
}
