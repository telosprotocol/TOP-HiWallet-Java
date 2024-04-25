package com.topnetwork.wallet.service;

import java.util.List;

import com.topnetwork.system.entity.User;
import com.topnetwork.wallet.common.enums.ChainTypeEnum;
import com.topnetwork.wallet.entity.CoinSpecies;
import com.topnetwork.wallet.param.wallet.*;
import com.topnetwork.wallet.param.wallet.v2.CoinHotParam;
import com.topnetwork.wallet.param.wallet.v2.CoinSpeciesDetailParam;
import com.topnetwork.wallet.param.wallet.v2.CoinSpeciesParam;
import com.topnetwork.wallet.result.wallet.CoinDetailListResult;
import com.topnetwork.wallet.result.wallet.CoinMainListResult;
import com.topnetwork.wallet.result.wallet.CoinSpeciesDetailResult;
import com.topnetwork.wallet.result.wallet.CoinSpeciesResult;

import com.gitee.magic.framework.base.result.QueryResult;
import com.base.core.framework.sql.service.SqlBaseService;

public interface CoinSpeciesService extends SqlBaseService<CoinSpecies, Long> {

    QueryResult<List<CoinSpeciesResult>> findCoinSpeciesByWalletCoinSpecies(CoinSpeciesParam param);

    CoinSpeciesDetailResult findCoinSpeciesByEnglishName(CoinSpeciesDetailParam param);

    QueryResult<List<CoinSpeciesResult>> findHotCoinSpecies(CoinHotParam param);

    QueryResult<List<CoinMainListResult>> findMainCoinList(CoinMainListParam param);

    List<CoinMainListResult> findMainCoinSelect();

    QueryResult<List<CoinMainListResult>> findTokenList(CoinTokenListParam param);

    CoinDetailListResult findCoinDetail(CoinDetailListParam param);

    void delCoinDetail(CoinDetailListParam param);

    void addCoinSpecies(CoinAddParam param, User user);

    void updCoinSpecies(CoinUpdParam param, User user);

    void up();

    List<ChainTypeEnum> getChainType();

    List<CoinSpecies> findByChainType(List<String> chainTypes);

    CoinSpecies findContractBySymbol(String symbol);
}
