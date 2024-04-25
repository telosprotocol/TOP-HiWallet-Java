package com.topnetwork.wallet.service;

import com.topnetwork.wallet.common.enums.ChainTypeEnum;
import com.topnetwork.wallet.result.wallet.CoinFeeResult;
import com.base.core.framework.sql.service.SqlBaseService;
import com.topnetwork.wallet.entity.AppCoinFee;

import java.util.List;

public interface AppCoinFeeService extends SqlBaseService<AppCoinFee,Long> {

    List<CoinFeeResult> queryByChainType(ChainTypeEnum chainType);
}
