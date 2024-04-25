package com.topnetwork.wallet.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.base.component.aws.s3.UploadS3ServiceImpl;
import com.base.core.framework.sql.service.impl.SqlBaseServiceImpl;
import com.base.core.mvc.business.CommonBusiness;
import com.base.service.base.system.service.ConfigureService;
import com.gitee.magic.core.json.JsonObject;
import com.gitee.magic.framework.base.result.PageResponse;
import com.gitee.magic.framework.base.result.QueryResult;
import com.gitee.magic.framework.head.exception.BusinessException;
import com.gitee.magic.jdbc.persistence.source.jdbc.sqlplus.conditions.query.QueryWrapper;
import com.gitee.magic.jdbc.persistence.source.jdbc.sqlplus.conditions.update.DeleteWrapper;
import com.topnetwork.changelly.ChangellyService;
import com.topnetwork.changelly.CurrencyInfo;
import com.topnetwork.changelly.GetAmountResult;
import com.topnetwork.changelly.GetCurrenciesFullResult;
import com.topnetwork.changelly.GetCurrenciesResult;
import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.common.enums.changelly.GetListTypeEnum;
import com.topnetwork.wallet.dao.AppChangellyCurrencyDao;
import com.topnetwork.wallet.dao.CoinSpeciesDao;
import com.topnetwork.wallet.entity.AppChangellyCurrency;
import com.topnetwork.wallet.entity.CoinSpecies;
import com.topnetwork.wallet.param.wallet.changelly.AddCurrencyParam;
import com.topnetwork.wallet.param.wallet.changelly.CheckCurrencyParam;
import com.topnetwork.wallet.param.wallet.changelly.DelCurrencyParam;
import com.topnetwork.wallet.param.wallet.changelly.GetCurrenciesParam;
import com.topnetwork.wallet.param.wallet.changelly.GetCurrencyPageParam;
import com.topnetwork.wallet.param.wallet.changelly.GetPairInfoParam;
import com.topnetwork.wallet.param.wallet.changelly.UpdCurrencyParam;
import com.topnetwork.wallet.result.changelly.CheckCurrencyResult;
import com.topnetwork.wallet.result.changelly.CheckCurrencyV2Result;
import com.topnetwork.wallet.result.changelly.GetCoinListResult;
import com.topnetwork.wallet.result.changelly.GetCurrencyAllResult;
import com.topnetwork.wallet.result.changelly.GetCurrencyPageResult;
import com.topnetwork.wallet.result.changelly.GetCurrencyResult;
import com.topnetwork.wallet.result.changelly.GetPairInfoResult;
import com.topnetwork.wallet.result.wallet.CoinMainListResult;
import com.topnetwork.wallet.service.AppChangellyCurrencyService;
import com.topnetwork.wallet.service.CoinSpeciesService;
import com.topnetwork.wyre.service.WyreCountryService;

@Service("appChangellyCurrencyService")
@Transactional
public class AppChangellyCurrencyServiceImpl extends SqlBaseServiceImpl<AppChangellyCurrency, Long>
        implements AppChangellyCurrencyService {

    @SuppressWarnings("unused")
    private AppChangellyCurrencyDao appChangellyCurrencyDao;

    public AppChangellyCurrencyServiceImpl(@Qualifier("appChangellyCurrencyDao") AppChangellyCurrencyDao appChangellyCurrencyDao) {
        super(appChangellyCurrencyDao);
        this.appChangellyCurrencyDao = appChangellyCurrencyDao;
    }

    @Autowired
    private ChangellyService changellyService;
    @Autowired
    private CoinSpeciesDao coinSpeciesDao;
    @Autowired
    private CoinSpeciesService coinSpeciesService;
	@Autowired
	private UploadS3ServiceImpl uploadS3Service;

    @Override
    public GetCurrencyAllResult getCurrencyList(GetCurrenciesParam param) {
        List<String> chainTypes = param.getChainTypes();
        GetCurrencyAllResult getCurrencyAllResult = new GetCurrencyAllResult();
        List<GetCurrencyResult> buy = new ArrayList<>();
        List<GetCurrencyResult> sell = new ArrayList<>();
        List<Long> list = new ArrayList<>();
        if (chainTypes != null && chainTypes.size() > 0) {
            List<CoinSpecies> mainCoinSpecies = coinSpeciesService.findByChainType(chainTypes);
            for (CoinSpecies mainCoinSpecy : mainCoinSpecies) {
                list.add(mainCoinSpecy.getId());
            }
        }
        String sqlList = "SELECT coin.* FROM wal_app_changelly_currency cur LEFT JOIN wal_coin_species coin ON coin.id = cur.coinId WHERE cur.`disable`='false'";
        List<CoinSpecies> supportCoinSpecies = coinSpeciesDao.queryForList(sqlList);
        GetCurrenciesFullResult currenciesFull = changellyService.getCurrenciesFull();
        List<CoinMainListResult> mainCoinSelect = coinSpeciesService.findMainCoinSelect();
        for (CoinSpecies supportCoinSpecy : supportCoinSpecies) {

            if (list.size() > 0) {
                //判断是否主币，所属主币或者本身是否在所传支持主币列表中
                if (supportCoinSpecy.getLevel() == 1 && !list.contains(supportCoinSpecy.getId())) {
                    continue;
                }
                if (supportCoinSpecy.getLevel() != 1 && !list.contains(supportCoinSpecy.getFatherId())) {
                    continue;
                }
            }
            String symbol = changellyService.converterCurrency(supportCoinSpecy.getNickname());
            GetCurrencyResult getCurrencyResult = new GetCurrencyResult();
            if (supportCoinSpecy.getLevel() == 1) {
                getCurrencyResult.setChainType(supportCoinSpecy.getNickname());
            } else {
                for (CoinMainListResult coinMainListResult : mainCoinSelect) {
                    if (supportCoinSpecy.getFatherId().equals(coinMainListResult.getId())) {
                        getCurrencyResult.setChainType(coinMainListResult.getNickname());
                        break;
                    }
                }
            }

            getCurrencyResult.setContractAddress(supportCoinSpecy.getContractAddress());
            getCurrencyResult.setDecimals(supportCoinSpecy.getTokenDecimals());
            getCurrencyResult.setEnglishName(supportCoinSpecy.getEnglishName());
            String logoUrl = uploadS3Service.getUrl(supportCoinSpecy.getLogoUrl(), false);
            getCurrencyResult.setIconUrl(logoUrl);
            getCurrencyResult.setId(supportCoinSpecy.getId());
            getCurrencyResult.setMainChain(supportCoinSpecy.getLevel() == 1);
            getCurrencyResult.setSymbol(supportCoinSpecy.getNickname());

            for (CurrencyInfo currencyInfo : currenciesFull.getResult()) {
                if (symbol.equalsIgnoreCase(currencyInfo.getName())) {
                    if (currencyInfo.getEnabledFrom()) {
                        sell.add(getCurrencyResult);
                    }
                    if (currencyInfo.getEnabledTo()) {
                        buy.add(getCurrencyResult);
                    }
                    break;
                }
            }
        }
        getCurrencyAllResult.setBuy(buy);
        getCurrencyAllResult.setSell(sell);
        return getCurrencyAllResult;
    }

    @Override
    public QueryResult<List<GetCurrencyPageResult>> getCurrencyPage(GetCurrencyPageParam param) {
        Map<String, Object> params = CommonBusiness.httpPageParam(param);
        QueryResult<List<GetCurrencyPageResult>> queryResult = new QueryResult<>();
        PageResponse.PageInfo pageInfo = new PageResponse.PageInfo();
        String sqlCount = "SELECT COUNT(1) FROM wal_app_changelly_currency";
        pageInfo.setTotalCount(appChangellyCurrencyDao.queryForInt(sqlCount));
        queryResult.setPageInfo(pageInfo);
        if (pageInfo.getTotalCount() > 0) {
            String sqlList = "SELECT " +
                    "cur.id AS currencyId, " +
                    "cur.coinId," +
                    "cur.`disable`," +
                    "cur.customized," +
                    "cur.maxSellAmount," +
                    "cur.minSellAmount," +
                    "UNIX_TIMESTAMP( cur.modified_date ) AS updateTime," +
                    "coin.nickname AS symbol " +
                    "FROM wal_app_changelly_currency cur " +
                    "LEFT JOIN wal_coin_species coin ON coin.id = cur.coinId " +
                    "ORDER BY cur.id ASC " +
                    "LIMIT " + params.get("pageIndex") + "," + params.get("pageSize");
            queryResult.setResult(queryForMap(GetCurrencyPageResult.class,appChangellyCurrencyDao.queryForMap(sqlList)));
        } else {
            queryResult.setResult(new ArrayList<>());
        }
        return queryResult;
    }

    @Override
    public void addCurrency(AddCurrencyParam param) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("coinId", param.getCoinId());
        AppChangellyCurrency appChangellyCurrency1 = get(appChangellyCurrencyDao.queryForList(wrapper));
        if (appChangellyCurrency1 != null) {
            throw new BusinessException(CodeRes.CODE_21011);
        }
        AppChangellyCurrency appChangellyCurrency = new AppChangellyCurrency();
        BeanUtils.copyProperties(param, appChangellyCurrency);
        checkValue(appChangellyCurrency);
        save(appChangellyCurrency);
    }

    private void checkValue(AppChangellyCurrency appChangellyCurrency) {
        if (appChangellyCurrency.getCustomized() && appChangellyCurrency.getMinSellAmount() == null) {
            throw new BusinessException(CodeRes.CODE_21001);
        }
        if (appChangellyCurrency.getMaxSellAmount().compareTo(new BigDecimal("0")) <= 0) {
            throw new BusinessException(CodeRes.CODE_21001);
        }
        if (!appChangellyCurrency.getCustomized()) {
            appChangellyCurrency.setMinSellAmount(null);
        }
        if (appChangellyCurrency.getMinSellAmount() != null) {
            if (appChangellyCurrency.getMinSellAmount().compareTo(new BigDecimal("0")) <= 0) {
                throw new BusinessException(CodeRes.CODE_21001);
            }
            if (appChangellyCurrency.getMinSellAmount().compareTo(appChangellyCurrency.getMaxSellAmount()) >= 0) {
                throw new BusinessException(CodeRes.CODE_21001);
            }
        }
    }

    @Override
    public void deleteCurrency(DelCurrencyParam param) {
        DeleteWrapper deleteWrapper = new DeleteWrapper();
        deleteWrapper.eq("id", param.getCurrencyId());
        appChangellyCurrencyDao.executeUpdate(deleteWrapper);
    }

    @Override
    public void updateCurrency(UpdCurrencyParam param) {
        AppChangellyCurrency appChangellyCurrency = new AppChangellyCurrency();
        BeanUtils.copyProperties(param, appChangellyCurrency);
        appChangellyCurrency.setId(param.getCurrencyId());
        checkValue(appChangellyCurrency);
        save(appChangellyCurrency);
    }

    @Override
    public GetPairInfoResult getPairInfo(GetPairInfoParam param) {

        if (param.getFrom().equalsIgnoreCase(param.getTo())) {
            throw new BusinessException(CodeRes.CODE_21004);
        }
        AppChangellyCurrency appChangellyCurrency = getBySymbol(param.getFrom());
        if (appChangellyCurrency == null) {
            throw new BusinessException(CodeRes.CODE_21004);
        }
        /**
         * 以买入为基准
         */
        GetAmountResult minAmount = changellyService.getMinAmount(param.getFrom(), param.getTo());

        GetAmountResult exchangeAmount = changellyService.getExchangeAmount(param.getFrom(), param.getTo(), minAmount.getResult());

        GetPairInfoResult getPairInfoResult = new GetPairInfoResult();
        getPairInfoResult.setMaxAmount(appChangellyCurrency.getMaxSellAmount());
        if (appChangellyCurrency.getCustomized()) {
            getPairInfoResult.setMinAmount(appChangellyCurrency.getMinSellAmount());
        } else {
            getPairInfoResult.setMinAmount(minAmount.getResult());
        }

        BigDecimal rate = minAmount.getResult().divide(exchangeAmount.getResult(), 8, RoundingMode.HALF_DOWN);

        getPairInfoResult.setRate(rate);

        return getPairInfoResult;
    }

    private AppChangellyCurrency getBySymbol(String from) {
        String sqlList = "SELECT cur.* " +
                "FROM wal_app_changelly_currency cur " +
                "LEFT JOIN wal_coin_species coin ON coin.id = cur.coinId " +
                "WHERE  coin.nickname='" + from + "' AND cur.`disable` = 'false' ";
        List<AppChangellyCurrency> list = appChangellyCurrencyDao.queryForList(sqlList);
        return (list == null || list.isEmpty()) ? null : list.get(0);
    }


    @Override
    public CheckCurrencyResult checkCurrency(CheckCurrencyParam param) {
        CheckCurrencyResult checkCurrencyResult = new CheckCurrencyResult();
        AppChangellyCurrency appChangellyCurrency = getBySymbol(param.getSymbol());
        if (appChangellyCurrency == null) {
            checkCurrencyResult.setEnabled(false);
            return checkCurrencyResult;
        }
        GetCurrenciesFullResult currenciesFull = changellyService.getCurrenciesFull();
        String symbol = changellyService.converterCurrency(param.getSymbol());
        for (CurrencyInfo currencyInfo : currenciesFull.getResult()) {
            if (symbol.equalsIgnoreCase(currencyInfo.getName())) {
                if (GetListTypeEnum.buy.equals(param.getType())) {
                    checkCurrencyResult.setEnabled(currencyInfo.getEnabledTo());
                } else {
                    checkCurrencyResult.setEnabled(currencyInfo.getEnabledFrom());
                }
                break;
            }
        }
        return checkCurrencyResult;
    }

    @Autowired
    private WyreCountryService wyreCountryService;
    @Autowired
    private ConfigureService configureService;

    @Override
    public CheckCurrencyV2Result checkCurrency(CheckCurrencyParam param, String requestIp) {
//        CheckCurrencyResult checkCurrency = checkCurrency(param);
        CheckCurrencyV2Result checkCurrencyV2Result = new CheckCurrencyV2Result();
        checkCurrencyV2Result.setSwapConfig(false);//2022-07-27 start要求暂时关闭
        JsonObject jsonObject_buy = (JsonObject) configureService.getValue("buycryptoConfig");
        boolean buySwitch = Boolean.parseBoolean(String.valueOf(jsonObject_buy.get("switch")));
        if (!buySwitch) {
            checkCurrencyV2Result.setGetCoinConfig(false);
        } else {
            List<String> nikename = wyreCountryService.getCoinNikeNamesByIp(requestIp);
            if (nikename.contains(param.getSymbol().toUpperCase())) {
                checkCurrencyV2Result.setGetCoinConfig(true);
            } else {
                checkCurrencyV2Result.setGetCoinConfig(false);
            }
        }
        return checkCurrencyV2Result;
    }

    @Override
    public List<GetCoinListResult> getCoinList() {
        List<GetCoinListResult> listResults = new ArrayList<>();
        GetCurrenciesResult currencies = changellyService.getCurrencies();
        List<CoinSpecies> coinSpecies = coinSpeciesService.queryForAll();
        for (String currency : currencies.getResult()) {
            for (CoinSpecies coinSpecy : coinSpecies) {
                if (currency.equalsIgnoreCase(coinSpecy.getNickname())) {
                    GetCoinListResult getCoinListResult = new GetCoinListResult();
                    getCoinListResult.setCoinId(coinSpecy.getId());
                    getCoinListResult.setSymbol(coinSpecy.getNickname());
                    getCoinListResult.setLogoUrl(coinSpecy.getLogoUrl());
                    listResults.add(getCoinListResult);
                }
            }
        }
        return listResults;
    }
}
