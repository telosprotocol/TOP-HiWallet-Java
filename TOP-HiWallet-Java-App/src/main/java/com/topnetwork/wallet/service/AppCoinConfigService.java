package com.topnetwork.wallet.service;

import com.topnetwork.wallet.param.wallet.CoinConfigParam;
import com.topnetwork.wallet.result.wallet.CoinConfigResult;
import com.base.core.framework.sql.service.SqlBaseService;
import com.topnetwork.wallet.entity.AppCoinConfig;

public interface AppCoinConfigService extends SqlBaseService<AppCoinConfig,Long> {

    CoinConfigResult getConfig(CoinConfigParam param);
}
