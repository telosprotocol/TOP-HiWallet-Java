package com.topnetwork.wallet.service;

import com.topnetwork.wallet.param.wallet.activity.BtcPriceListParam;
import com.topnetwork.wallet.result.wallet.activity.BtcPriceListResult;
import com.gitee.magic.framework.base.result.QueryResult;
import com.base.core.framework.sql.service.SqlBaseService;
import com.topnetwork.wallet.entity.AppBtcPrice;

import java.util.List;

public interface AppBtcPriceService extends SqlBaseService<AppBtcPrice,Long> {

    AppBtcPrice getByPhase(String phase);

    QueryResult<List<BtcPriceListResult>> findInWeekList(BtcPriceListParam param);
}
