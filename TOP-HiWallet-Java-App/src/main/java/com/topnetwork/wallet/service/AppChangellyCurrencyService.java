package com.topnetwork.wallet.service;

import com.topnetwork.wallet.param.wallet.changelly.*;
import com.topnetwork.wallet.result.changelly.*;
import com.gitee.magic.framework.base.result.QueryResult;
import com.base.core.framework.sql.service.SqlBaseService;
import com.topnetwork.wallet.entity.AppChangellyCurrency;

import java.util.List;

public interface AppChangellyCurrencyService extends SqlBaseService<AppChangellyCurrency, Long> {

    GetCurrencyAllResult getCurrencyList(GetCurrenciesParam param);

    QueryResult<List<GetCurrencyPageResult>> getCurrencyPage(GetCurrencyPageParam param);

    void addCurrency(AddCurrencyParam param);

    void deleteCurrency(DelCurrencyParam param);

    void updateCurrency(UpdCurrencyParam param);

    GetPairInfoResult getPairInfo(GetPairInfoParam param);

    List<GetCoinListResult> getCoinList();

    CheckCurrencyResult checkCurrency(CheckCurrencyParam param);

    CheckCurrencyV2Result checkCurrency(CheckCurrencyParam param, String requestIp);
}
