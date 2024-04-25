package com.topnetwork.wyre.service;

import com.topnetwork.wallet.param.wyre.*;
import com.topnetwork.wallet.result.changelly.GetCoinListResult;
import com.topnetwork.wallet.result.wallet.ETHGetCoinPriceResult;
import com.topnetwork.wallet.result.wyre.GetCountryPageResult;
import com.topnetwork.wallet.result.wyre.GetInfoResult;
import com.topnetwork.wallet.result.wyre.GetReservationResult;
import com.topnetwork.wallet.result.wyre.GetReservationV2Result;
import com.gitee.magic.framework.base.result.QueryResult;
import com.base.core.framework.sql.service.SqlBaseService;
import com.topnetwork.wyre.entity.WyreCountry;

import java.util.List;

public interface WyreCountryService extends SqlBaseService<WyreCountry, Long> {

    GetInfoResult getInfo(String requestIP);

    QueryResult<List<GetCountryPageResult>> getCountryPage(GetCountryPageParam param);

    void deleteCountry(DelCountryParam param);

    void updateCountry(UpdateCountryParam param);

    void addCountry(AddCountryParam param);

    List<GetCoinListResult> getCoinList();

    List<String> getCoinNikeNamesByIp(String requestIp);

    List<WyreCountry> getSupportCountryByIp(String ip);

    ETHGetCoinPriceResult getCoinPrice(GetCoinPriceParam param);

    GetReservationResult getReservation();

    GetReservationV2Result getReservation(GetReservationParam param);
}
