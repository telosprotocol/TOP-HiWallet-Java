package com.topnetwork.wyre.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.base.core.mvc.business.CommonBusiness;
import com.common.utils.LocationUtils;
import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.dao.CoinSpeciesDao;
import com.topnetwork.wallet.entity.CoinSpecies;
import com.topnetwork.wallet.param.wyre.AddCountryParam;
import com.topnetwork.wallet.param.wyre.DelCountryParam;
import com.topnetwork.wallet.param.wyre.GetCoinPriceParam;
import com.topnetwork.wallet.param.wyre.GetCountryPageParam;
import com.topnetwork.wallet.param.wyre.GetReservationParam;
import com.topnetwork.wallet.param.wyre.UpdateCountryParam;
import com.topnetwork.wallet.result.changelly.GetCoinListResult;
import com.topnetwork.wallet.result.wallet.CoinMainListResult;
import com.topnetwork.wallet.result.wallet.ETHGetCoinPriceResult;
import com.topnetwork.wallet.result.wyre.CoinResult;
import com.topnetwork.wallet.result.wyre.Currency;
import com.topnetwork.wallet.result.wyre.GetCountryPageResult;
import com.topnetwork.wallet.result.wyre.GetInfoResult;
import com.topnetwork.wallet.result.wyre.GetReservationResult;
import com.topnetwork.wallet.result.wyre.GetReservationV2Result;
import com.topnetwork.wallet.service.CoinSpeciesService;
import com.topnetwork.wallet.tokenprice.entity.TokenPrice;
import com.topnetwork.wallet.tokenprice.service.TokenPriceService;
import com.topnetwork.wyre.dao.WyreCountryDao;
import com.topnetwork.wyre.entity.WyreCountry;
import com.topnetwork.wyre.entity.WyreCountryCoin;
import com.topnetwork.wyre.service.WyreCountryCoinService;
import com.topnetwork.wyre.service.WyreCountryService;

import com.gitee.magic.framework.head.exception.BusinessException;
import com.gitee.magic.framework.base.rest.HttpRequest;
import com.gitee.magic.framework.base.rest.HttpWrapper;
import com.gitee.magic.framework.base.rest.RequestMethodEnum;
import com.gitee.magic.framework.base.result.PageResponse;
import com.gitee.magic.framework.base.result.QueryResult;
import com.base.core.framework.sql.service.impl.SqlBaseServiceImpl;
import com.gitee.magic.jdbc.persistence.source.jdbc.sqlplus.conditions.query.QueryWrapper;
import com.gitee.magic.jdbc.persistence.source.jdbc.sqlplus.conditions.update.DeleteWrapper;
import com.gitee.magic.core.json.JsonObject;

@Service("wyreCountryService")
public class WyreCountryServiceImpl extends SqlBaseServiceImpl<WyreCountry, Long>
        implements WyreCountryService {

    @SuppressWarnings("unused")
    private WyreCountryDao wyreCountryDao;
    @Autowired
    private WyreCountryCoinService wyreCountryCoinService;
    @Autowired
    private CoinSpeciesService coinSpeciesService;
    @Autowired
    private CoinSpeciesDao coinSpeciesDao;
    @Value("${wyre.account.id}")
    private String accountId;
    @Value("wyre.account.apiKey")
    private String apiKey;
    @Value("${wyre.account.secretKey}")
    private String secretKey;
    @Value("${wyre.account.baseUrl}")
    private String baseUrl;


    public WyreCountryServiceImpl(@Qualifier("wyreCountryDao") WyreCountryDao wyreCountryDao) {
        super(wyreCountryDao);
        this.wyreCountryDao = wyreCountryDao;
    }

    @Override
    public GetInfoResult getInfo(String requestIP) {
        GetInfoResult getInfoResult = new GetInfoResult();
        WyreCountry wyreCountry = get(getSupportCountryByIp(requestIP));
        if (wyreCountry == null) {
            throw new BusinessException(CodeRes.CODE_22006);
        }
        Currency currency = new Currency();
        currency.setCurrencies(wyreCountry.getCurrencies());
        currency.setCurrenciesIcon(wyreCountry.getCurrenciesIcon());
        List<Currency> currencyList = new ArrayList<>();
        currencyList.add(currency);
        getInfoResult.setCurrency(currencyList);
        List<CoinSpecies> supportCoinSpecies = getCountryCoin(wyreCountry);
        getInfoResult.setBuy(setCoinResult(supportCoinSpecies));
        return getInfoResult;
    }

    @Override
    public QueryResult<List<GetCountryPageResult>> getCountryPage(GetCountryPageParam param) {

        Map<String, Object> params = CommonBusiness.httpPageParam(param);
        QueryResult<List<GetCountryPageResult>> queryResult = new QueryResult<>();
        PageResponse.PageInfo pageInfo = new PageResponse.PageInfo();
        String countSql = "SELECT COUNT(1) FROM wal_wyre_country ";
        pageInfo.setTotalCount(wyreCountryDao.queryForInt(countSql));
        queryResult.setPageInfo(pageInfo);
        if (pageInfo.getTotalCount() > 0) {
            String selectSql = "SELECT w.*" +
                    " FROM wal_wyre_country w "
                    + " ORDER BY w.created_date DESC "
                    + " LIMIT " + params.get("pageIndex") + "," + params.get("pageSize");
            List<WyreCountry> list = wyreCountryDao.queryForList(selectSql);
            List<GetCountryPageResult> results = new ArrayList<>();

            for (WyreCountry wyreCountry : list) {
                GetCountryPageResult getCountryPageResult = new GetCountryPageResult();
                getCountryPageResult.setCountryCode(wyreCountry.getCountryCode());
                getCountryPageResult.setCountryId(wyreCountry.getId());
                getCountryPageResult.setCreateTime(wyreCountry.getCreatedDate().getTime());
                getCountryPageResult.setCurrencies(wyreCountry.getCurrencies());
                getCountryPageResult.setCurrenciesIcon(wyreCountry.getCurrenciesIcon());
                getCountryPageResult.setName(wyreCountry.getName());
                List<CoinResult> buy = setCoinResult(getCountryCoin(wyreCountry));
                getCountryPageResult.setCoinList(buy);
                results.add(getCountryPageResult);
            }
            queryResult.setResult(results);
        } else {
            queryResult.setResult(new ArrayList<>());
        }
        return queryResult;
    }

    private List<CoinSpecies> getCountryCoin(WyreCountry wyreCountry) {
        String coinSql = "SELECT s.* " +
                "FROM wal_coin_species s " +
                "WHERE id IN (" +
                "SELECT c.coinId FROM wyr_wyre_country_coin c LEFT JOIN wal_wyre_country country ON c.countryId = country.id  WHERE country.id = " + wyreCountry.getId() +
                ")";
        return coinSpeciesDao.queryForList(coinSql);
    }

    private List<CoinResult> setCoinResult(List<CoinSpecies> coinSpecies) {
        List<CoinResult> buy = new ArrayList<>();
        List<CoinMainListResult> mainCoinSelect = coinSpeciesService.findMainCoinSelect();
        for (CoinSpecies supportCoinSpecy : coinSpecies) {
            CoinResult coinResult = new CoinResult();
            if (supportCoinSpecy.getLevel() == 1) {
                coinResult.setChainType(supportCoinSpecy.getNickname());
            } else {
                for (CoinMainListResult coinMainListResult : mainCoinSelect) {
                    if (supportCoinSpecy.getFatherId().equals(coinMainListResult.getId())) {
                        coinResult.setChainType(coinMainListResult.getNickname());
                        break;
                    }
                }
            }
            coinResult.setContractAddress(supportCoinSpecy.getContractAddress());
            coinResult.setDecimals(supportCoinSpecy.getTokenDecimals());
            coinResult.setEnglishName(supportCoinSpecy.getEnglishName());
            coinResult.setIconUrl(supportCoinSpecy.getLogoUrl());
            coinResult.setId(supportCoinSpecy.getId());
            coinResult.setMainChain(supportCoinSpecy.getLevel() == 1);
            coinResult.setSymbol(supportCoinSpecy.getNickname());
            buy.add(coinResult);
        }
        return buy;
    }

    @Override
    public void deleteCountry(DelCountryParam param) {
        DeleteWrapper wrapper = new DeleteWrapper();
        wrapper.eq("id", param.getCountryId());
        wyreCountryDao.executeUpdate(wrapper);
        wyreCountryCoinService.deleteByCountryId(param.getCountryId());
    }

    @Override
    public void updateCountry(UpdateCountryParam param) {

        List<Long> coinId = param.getCoinId();
        if (coinId == null || coinId.size() == 0) {
            throw new BusinessException(CodeRes.CODE_22004);
        }
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("id", param.getCountryId());
        WyreCountry country = get(wyreCountryDao.queryForList(wrapper));
        if (country == null) {
            throw new BusinessException(CodeRes.CODE_22000);
        }
        wyreCountryCoinService.deleteByCountryId(param.getCountryId());
        country.setCountryCode(param.getCountryCode());
        country.setCurrencies(param.getCurrencies());
        country.setCurrenciesIcon(param.getCurrenciesIcon());
        country.setName(param.getName());
        country.setModifiedDate(new Date());
        save(country);
        saveCountryCoin(coinId, country.getId());
    }

    @Override
    public void addCountry(AddCountryParam param) {

        List<Long> coinId = param.getCoinId();
        if (coinId == null || coinId.size() == 0) {
            throw new BusinessException(CodeRes.CODE_22004);
        }
        WyreCountry country = new WyreCountry();
        country.setCountryCode(param.getCountryCode());
        country.setCurrencies(param.getCurrencies());
        country.setCurrenciesIcon(param.getCurrenciesIcon());
        country.setName(param.getName());
        country.setCreatedDate(new Date());
        Long id = wyreCountryDao.getSnowflakeIdWorkerNextId();
        country.setId(id);
        country.setModifiedDate(new Date());
        wyreCountryDao.persist(country);
        saveCountryCoin(coinId, id);
    }

    private void saveCountryCoin(List<Long> coinId, Long countryId) {
        List<WyreCountryCoin> countryCoins = new ArrayList<>();
        for (Long aLong : coinId) {
            WyreCountryCoin wyreCountryCoin = new WyreCountryCoin();
            wyreCountryCoin.setCoinId(aLong);
            wyreCountryCoin.setCountryId(countryId);
            wyreCountryCoin.setCreatedDate(new Date());
            wyreCountryCoin.setModifiedDate(new Date());
            countryCoins.add(wyreCountryCoin);
        }
        wyreCountryCoinService.saveBatch(countryCoins);
    }

    @Override
    public List<GetCoinListResult> getCoinList() {
        List<CoinSpecies> all = coinSpeciesService.queryForAll();
        List<GetCoinListResult> listResults = new ArrayList<>();
        for (CoinSpecies coinSpecies : all) {
            GetCoinListResult getCoinListResult = new GetCoinListResult();
            getCoinListResult.setLogoUrl(coinSpecies.getLogoUrl());
            getCoinListResult.setSymbol(coinSpecies.getNickname());
            getCoinListResult.setCoinId(coinSpecies.getId());
            listResults.add(getCoinListResult);
        }
        return listResults;
    }

    @Override
    public List<String> getCoinNikeNamesByIp(String requestIp) {
        WyreCountry country = get(getSupportCountryByIp(requestIp));
        List<String> stringList = new ArrayList<>();
        if (country == null) {
            return stringList;
        }
        List<CoinSpecies> supportCoinSpecies = getCountryCoin(country);
        for (CoinSpecies supportCoinSpecy : supportCoinSpecies) {
            stringList.add(supportCoinSpecy.getNickname());
        }
        return stringList;
    }

    @Override
    public List<WyreCountry> getSupportCountryByIp(String ip) {
//        ip = Config.isSystemFlag() ? ip : "54.241.114.144";
        String countryCode = LocationUtils.getLocationByIp(ip);
        if (StringUtils.isEmpty(countryCode)) {
            return null;
        }
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("countryCode", countryCode);
        List<WyreCountry> countryList = wyreCountryDao.queryForList(wrapper);
        return countryList;
    }

    @Autowired
    private TokenPriceService tokenPriceService;

    @Override
    public ETHGetCoinPriceResult getCoinPrice(GetCoinPriceParam param) {
        List<TokenPrice> tokenPrice = tokenPriceService.findByEnglishName(param.getEnglishName());
        ETHGetCoinPriceResult ethGetCoinPriceResult = new ETHGetCoinPriceResult();
        if (tokenPrice == null || tokenPrice.size() <= 0) {
            throw new BusinessException(CodeRes.CODE_22007);
        }
        ethGetCoinPriceResult.setCurrent_price(getPrice(tokenPrice.get(0).getIds(), param.getVsCurrency()));
        return ethGetCoinPriceResult;
    }

    public String getPrice(String ids, String currency) {

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("currencies", currency.toUpperCase());
        WyreCountry country = get(wyreCountryDao.queryForList(queryWrapper));
        if (country == null) {
            throw new BusinessException(CodeRes.CODE_22006);
        }
        String url = "https://api.coingecko.com/api/v3/simple/price?ids=" + ids.toLowerCase() + "&vs_currencies=" + currency.toLowerCase();
        HttpWrapper wrapper = new HttpWrapper();
        HttpRequest request = new HttpRequest(url, RequestMethodEnum.GET);
        String content = wrapper.start(request);
        JsonObject jsonObject = new JsonObject(content);
        JsonObject jsonObject2 = jsonObject.getJsonObject(ids.toLowerCase());
        return String.valueOf(jsonObject2.get(currency.toLowerCase()));
    }

    @Override
    public GetReservationResult getReservation() {
        GetReservationResult getReservationResult = new GetReservationResult();
        getReservationResult.setAccountId(accountId);
        getReservationResult.setReservation(String.valueOf(getWyreReservation("{\"referrerAccountId\":\"" + accountId + "\"}").get("reservation")));
        return getReservationResult;
    }

    @Override
    public GetReservationV2Result getReservation(GetReservationParam param) {

        if (param.getAmount().compareTo(BigDecimal.ZERO) != 1) {
            throw new BusinessException(CodeRes.CODE_15011);
        }

        String data = "{" +
                "  \"referrerAccountId\": \"" + accountId + "\"," +
                "  \"destCurrency\": \"" + param.getDestCurrency() + "\"," +
                "  \"sourceCurrency\" : \"" + param.getSourceCurrency() + "\"," +
                "  \"amount\" : \"" + param.getAmount().toString() + "\"," +
                "  \"dest\": \"" + param.getDest() + "\"," +
                "  \"paymentMethod\": \"" + param.getPaymentMethod() + "\"" +
                "}";
        JsonObject jsonObject = getWyreReservation(data);
        GetReservationV2Result getReservationResult = new GetReservationV2Result();
        getReservationResult.setUrl(String.valueOf(jsonObject.get("url")));
        return getReservationResult;
    }

    public JsonObject getWyreReservation(String body) {
        HttpWrapper wrapper = new HttpWrapper();
        HttpRequest request = new HttpRequest(baseUrl + "/v3/orders/reserve", RequestMethodEnum.POST);
        request.getHeaders().put("Authorization", "Bearer " + secretKey);
        request.getHeaders().put("Content-Type", "application/json");
        request.setBodyContent(body);
        String content = wrapper.start(request);
        JsonObject jsonObject = new JsonObject(content);
        return jsonObject;
    }

}
