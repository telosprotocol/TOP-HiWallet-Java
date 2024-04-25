package com.topnetwork.wallet.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.base.component.aws.s3.UploadS3ServiceImpl;
import com.base.core.framework.sql.service.impl.SqlBaseServiceImpl;
import com.base.core.mvc.business.CommonBusiness;
import com.gitee.magic.core.utils.StringUtils;
import com.gitee.magic.framework.base.result.QueryResult;
import com.gitee.magic.framework.head.exception.BusinessException;
import com.gitee.magic.jdbc.persistence.source.jdbc.sqlplus.conditions.query.QueryWrapper;
import com.gitee.magic.jdbc.persistence.source.jdbc.sqlplus.conditions.update.DeleteWrapper;
import com.topnetwork.system.entity.User;
import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.common.enums.ChainTypeEnum;
import com.topnetwork.wallet.common.enums.LanguageEnum;
import com.topnetwork.wallet.dao.CoinSpeciesDao;
import com.topnetwork.wallet.entity.CoinSpecies;
import com.topnetwork.wallet.param.wallet.CoinAddParam;
import com.topnetwork.wallet.param.wallet.CoinDetailListParam;
import com.topnetwork.wallet.param.wallet.CoinMainListParam;
import com.topnetwork.wallet.param.wallet.CoinTokenListParam;
import com.topnetwork.wallet.param.wallet.CoinUpdParam;
import com.topnetwork.wallet.param.wallet.v2.CoinHotParam;
import com.topnetwork.wallet.param.wallet.v2.CoinSpeciesDetailParam;
import com.topnetwork.wallet.param.wallet.v2.CoinSpeciesParam;
import com.topnetwork.wallet.result.wallet.CoinDetailListResult;
import com.topnetwork.wallet.result.wallet.CoinMainListResult;
import com.topnetwork.wallet.result.wallet.CoinSpeciesDetailResult;
import com.topnetwork.wallet.result.wallet.CoinSpeciesResult;
import com.topnetwork.wallet.service.CoinSpeciesService;
import com.topnetwork.wallet.tokenprice.entity.TokenPrice;
import com.topnetwork.wallet.tokenprice.service.TokenPriceService;

@Service("coinSpeciesService")
@Transactional
public class CoinSpeciesServiceImpl extends SqlBaseServiceImpl<CoinSpecies, Long>
        implements CoinSpeciesService {

    private CoinSpeciesDao coinSpeciesDao;

    @Value("${wallet.static.img.base.url}")
    private String imgBaseUrl;

	@Autowired
	private UploadS3ServiceImpl uploadS3Service;

    public CoinSpeciesServiceImpl(@Qualifier("coinSpeciesDao") CoinSpeciesDao coinSpeciesDao) {
        super(coinSpeciesDao);
        this.coinSpeciesDao = coinSpeciesDao;
    }

    @Override
    public QueryResult<List<CoinSpeciesResult>> findCoinSpeciesByWalletCoinSpecies(CoinSpeciesParam param) {
        List<String> mainCoinNames = param.getChainType();

        //支持主币列表默认以太坊
        if (mainCoinNames == null || mainCoinNames.isEmpty()) {
            mainCoinNames = new ArrayList<>();
            mainCoinNames.add("ETH");

        }

        //查找所有可显示主币
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("level", 1).eq("isHide", 0);
        List<CoinSpecies> list_main = coinSpeciesDao.queryForList(queryWrapper);

        //查找所有可显示代币
        param.setChainType(null);
        Boolean isMainChain = param.getMainChain();
        param.setMainChain(null);
        Map<String, Object> params = CommonBusiness.httpPageParam(param);
        params.put("chainType", mainCoinNames);
        params.put("isHide", 0);
        if (isMainChain != null) {
            params.put("level", isMainChain ? 1 : 2);
        }
        QueryResult<List<CoinSpecies>> queryResult = queryForPage(CoinSpecies.class, "findCoinSpeciesByWalletCoinSpecies", params);

        QueryResult<List<CoinSpeciesResult>> result = new QueryResult<>();
        if(ObjectUtils.isEmpty(queryResult)) {
        	return result;
        }
        result.setPageInfo(queryResult.getPageInfo());
        result.setResult(transformation(queryResult.getResult(), list_main, param.getLanguage()));
        return result;
    }

    private List<CoinSpeciesResult> transformation(List<CoinSpecies> result, List<CoinSpecies> list_main, LanguageEnum language) {
        List<CoinSpeciesResult> coinSpeciesResultList = new ArrayList<>();
        for (CoinSpecies coinSpecies : result) {
            CoinSpeciesResult coinSpeciesResult = new CoinSpeciesResult();
            coinSpeciesResult.setId(coinSpecies.getId());
            coinSpeciesResult.setEnglishName(coinSpecies.getEnglishName());
            coinSpeciesResult.setChineseName(coinSpecies.getChineseName());
            coinSpeciesResult.setDecimals(coinSpecies.getTokenDecimals());
            coinSpeciesResult.setSymbol(coinSpecies.getNickname());
            coinSpeciesResult.setMainChain(coinSpecies.getLevel() == 1);
            coinSpeciesResult.setContractAddress(coinSpecies.getContractAddress());
            //获取url
            String logoUrl = uploadS3Service.getUrl(coinSpecies.getLogoUrl(), false);
            coinSpeciesResult.setIconUrl(logoUrl);
            coinSpeciesResult.setLogoUrl(coinSpecies.getLogoUrl());
            coinSpeciesResult.setInfoURL(coinSpecies.getTokenDescriptionUrl());
            if (language == null || language.equals(LanguageEnum.CN)) {
                coinSpeciesResult.setIntroduce(coinSpecies.getChineseDescription());
            } else {
                coinSpeciesResult.setIntroduce(coinSpecies.getEnglishDescription());
            }
            if (coinSpecies.getLevel() == 2) {
                for (CoinSpecies coinSpecies1 : list_main) {
                    if (coinSpecies.getFatherId().equals(coinSpecies1.getId())) {
                        coinSpeciesResult.setChainType(coinSpecies1.getNickname());
                    }
                }
                if (StringUtils.isEmpty(coinSpeciesResult.getChainType())) {
                    continue;
                }
            } else {
                coinSpeciesResult.setChainType(coinSpeciesResult.getSymbol());
            }
            coinSpeciesResultList.add(coinSpeciesResult);
        }
        return coinSpeciesResultList;
    }

    private List<CoinMainListResult> transformation(List<CoinSpecies> result, List<CoinSpecies> list_main) {

        List<CoinMainListResult> coinSpeciesResultList = new ArrayList<>();
        for (CoinSpecies coinSpecies : result) {
            CoinMainListResult coinSpeciesResult = new CoinMainListResult();
            coinSpeciesResult.setId(coinSpecies.getId());
            coinSpeciesResult.setEnglishName(coinSpecies.getEnglishName());
            coinSpeciesResult.setChineseName(coinSpecies.getChineseName());
            coinSpeciesResult.setNickname(coinSpecies.getNickname());
            coinSpeciesResult.setTokenOrder(coinSpecies.getTokenOrder());
            coinSpeciesResult.setIsHide(coinSpecies.getIsHide());
            //获取url
            String logoUrl = uploadS3Service.getUrl(coinSpecies.getLogoUrl(), false);
            coinSpeciesResult.setIconUrl(logoUrl);
            coinSpeciesResult.setLogoUrl(coinSpecies.getLogoUrl());
            if (coinSpecies.getLevel() == 1) {
                QueryWrapper queryWrapper = new QueryWrapper();
                queryWrapper.select("count(1) ").eq("fatherId", coinSpecies.getId());
                coinSpeciesResult.setTokenSize(coinSpeciesDao.queryForInt(queryWrapper));
            } else {
                for (CoinSpecies coinSpecies1 : list_main) {
                    if (coinSpecies.getFatherId().equals(coinSpecies1.getId())) {
                        coinSpeciesResult.setFatherId(coinSpecies1.getId());
                        coinSpeciesResult.setFatherName(coinSpecies1.getEnglishName());
                    }
                }
            }
            coinSpeciesResultList.add(coinSpeciesResult);
        }
        return coinSpeciesResultList;
    }

    @Override
    public CoinSpeciesDetailResult findCoinSpeciesByEnglishName(CoinSpeciesDetailParam param) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("englishName", param.getEnglishName()).eq("isHide", 0);
        CoinSpecies coin = get(coinSpeciesDao.queryForList(wrapper));
        if (coin == null) {
            throw new BusinessException(CodeRes.CODE_12001);
        }
        CoinSpeciesDetailResult result = new CoinSpeciesDetailResult();
        if (param.equals(LanguageEnum.CN)) {
            result.setIntroduce(coin.getChineseDescription());
        } else {
            result.setIntroduce(coin.getEnglishDescription());
        }
        return result;
    }

    @Override
    public QueryResult<List<CoinSpeciesResult>> findHotCoinSpecies(CoinHotParam param) {

        List<String> mainCoinNames = param.getChainType();

        //支持主币列表默认以太坊
        if (mainCoinNames == null || mainCoinNames.isEmpty()) {
            mainCoinNames = new ArrayList<>();
            mainCoinNames.add("ETH");

        }

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("level", 1).eq("isHide", 0);
        List<CoinSpecies> list_main = coinSpeciesDao.queryForList(queryWrapper);

        param.setChainType(null);
        Map<String, Object> params = CommonBusiness.httpPageParam(param);
        params.put("chainType", mainCoinNames);
        params.put("isHide", 0);
        params.put("isHot", 1);
        QueryResult<List<CoinSpecies>> QueryResult = queryForPage(CoinSpecies.class, "findCoinSpeciesByWalletCoinSpecies", params);
        QueryResult<List<CoinSpeciesResult>> result = new QueryResult<>();
        if(ObjectUtils.isEmpty(QueryResult)) {
        	return result;
        }
        result.setPageInfo(QueryResult.getPageInfo());
        result.setResult(transformation(QueryResult.getResult(), list_main, param.getLanguage()));
        return result;
    }

    @Override
    public QueryResult<List<CoinMainListResult>> findMainCoinList(CoinMainListParam param) {
        Map<String, Object> params = CommonBusiness.httpPageParam(param);
        params.put("level", 1);
        QueryResult<List<CoinSpecies>> queryResult = queryForPage(CoinSpecies.class, "findCoinSpecies", params);
        QueryResult<List<CoinMainListResult>> result = new QueryResult<>();
        if(ObjectUtils.isEmpty(queryResult)) {
        	return result;
        }
        result.setPageInfo(queryResult.getPageInfo());
        if (queryResult.getPageInfo().getTotalCount() == 0) {
            result.setResult(new ArrayList<>());
        } else {
            result.setResult(transformation(queryResult.getResult(), null));
        }
        return result;
    }

    @Override
    public QueryResult<List<CoinMainListResult>> findTokenList(CoinTokenListParam param) {
        Map<String, Object> params = CommonBusiness.httpPageParam(param);
        params.put("level", 2);
        QueryResult<List<CoinSpecies>> queryResult = queryForPage(CoinSpecies.class, "findCoinSpecies", params);
        QueryResult<List<CoinMainListResult>> result = new QueryResult<>();
        if(ObjectUtils.isEmpty(queryResult)) {
        	return result;
        }
        result.setPageInfo(queryResult.getPageInfo());
        if (queryResult.getPageInfo().getTotalCount() == 0) {
            result.setResult(new ArrayList<>());
        } else {
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("level", 1);
            List<CoinSpecies> list = coinSpeciesDao.queryForList(queryWrapper);
            result.setResult(transformation(queryResult.getResult(), list));
        }
        return result;
    }

    @Override
    public List<CoinMainListResult> findMainCoinSelect() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("level", 1).orderByAsc("tokenOrder");
        List<CoinSpecies> list = coinSpeciesDao.queryForList(queryWrapper);
        List<CoinMainListResult> listResults = new ArrayList<>();
        for (CoinSpecies coinSpecies : list) {
            CoinMainListResult mainCoinListResult = new CoinMainListResult();
            mainCoinListResult.setNickname(coinSpecies.getNickname());
            mainCoinListResult.setId(coinSpecies.getId());
            listResults.add(mainCoinListResult);
        }
        return listResults;
    }

    @Override
   public CoinSpecies findContractBySymbol(String symbol){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("nickname", symbol);
       return get(coinSpeciesDao.queryForList(queryWrapper));
    }

    @Autowired
    private TokenPriceService tokenPriceService;

    @Override
    public CoinDetailListResult findCoinDetail(CoinDetailListParam param) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", param.getCid());
        CoinSpecies coinSpecies = get(coinSpeciesDao.queryForList(queryWrapper));
        if (coinSpecies == null) return null;
        CoinDetailListResult coinDetailListResult = new CoinDetailListResult();
        BeanUtils.copyProperties(coinSpecies, coinDetailListResult);
        /**
         * 获取币种ids
         */
        List<TokenPrice> byCoinId = tokenPriceService.findByCoinId(param.getCid());
        if (byCoinId != null && byCoinId.size() > 0) {
            coinDetailListResult.setIds(byCoinId.get(0).getIds());
        }
        //获取url
        String logoUrl = uploadS3Service.getUrl(coinDetailListResult.getLogoUrl(), false);
        coinDetailListResult.setIconUrl(logoUrl);
        return coinDetailListResult;
    }

    @Override
    public void delCoinDetail(CoinDetailListParam param) {
        DeleteWrapper wrapper = new DeleteWrapper();
        wrapper.eq("id", param.getCid());
        coinSpeciesDao.executeUpdate(wrapper);
    }


    @Override
    public void addCoinSpecies(CoinAddParam param, User user) {

        String ids = param.getIds();
        //检查文件上传的key
//        awsService.checkUploadApplyKey(param.getLogoUrl());
        CoinSpecies coinSpecies = new CoinSpecies();
        BeanUtils.copyProperties(param, coinSpecies);
        coinSpecies.setCreateId(user.getId());
        coinSpecies.setCreateTime(new Date());
        Long coinId = coinSpeciesDao.getSnowflakeIdWorkerNextId();
        coinSpecies.setId(coinId);
        coinSpeciesDao.saveWithId(coinSpecies);
        TokenPrice tokenPrice = new TokenPrice();
        tokenPrice.setIds(ids);
        tokenPrice.setEnglishName(coinSpecies.getEnglishName());
        tokenPrice.setCoinId(coinId);
        tokenPriceService.save(tokenPrice);
    }

    @Override
    public void updCoinSpecies(CoinUpdParam param, User user) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", param.getId());
        CoinSpecies coinSpecies_db = get(coinSpeciesDao.queryForList(queryWrapper));
        CoinSpecies coinSpecies = new CoinSpecies();
        BeanUtils.copyProperties(param, coinSpecies);
        coinSpecies.setUpdateId(user.getId());
        coinSpecies.setUpdateTime(new Date());
        coinSpecies.setCreateTime(coinSpecies_db.getCreateTime());
        coinSpecies.setCreateId(coinSpecies_db.getCreateId());
        save(coinSpecies);
        List<TokenPrice> byCoinId = tokenPriceService.findByCoinId(param.getId());
        if (byCoinId == null || byCoinId.size() == 0) {
            TokenPrice tokenPrice = new TokenPrice();
            tokenPrice.setIds(param.getIds());
            tokenPrice.setEnglishName(coinSpecies.getEnglishName());
            tokenPrice.setCoinId(param.getId());
            tokenPrice.setApiId(null);
            tokenPriceService.save(tokenPrice);
        } else {
            for (TokenPrice tokenPrice : byCoinId) {
                tokenPrice.setIds(param.getIds());
            }
            tokenPriceService.saveBatch(byCoinId);
        }
    }

    @Override
    public void up() {
        QueryWrapper queryWrapper = new QueryWrapper();
        List<CoinSpecies> coinSpecies_list = coinSpeciesDao.queryForList(queryWrapper);
        File file = new File("C:\\Users\\pc\\Desktop\\images");
        File[] tempList = file.listFiles();
        for (CoinSpecies coinSpecies : coinSpecies_list) {
            if (coinSpecies.getLogoUrl().contains("png") || coinSpecies.getLogoUrl().contains("jpg")) {
                for (int i = 0; i < tempList.length; i++) {
                    if (tempList[i].isFile()) {
                        String filename = tempList[i].getName();
                        if (filename.equals(coinSpecies.getLogoUrl())) {
                        	String key = StringUtils.random();
//                            String key = awsService.uploadKey().getUploadKey();
                        	uploadS3Service.putObject(key, tempList[i]);
                            coinSpecies.setLogoUrl(key);
                            save(coinSpecies);
                        }
                    }
                }
            }
        }
    }

    @Override
    public List<ChainTypeEnum> getChainType() {
        ChainTypeEnum[] values = ChainTypeEnum.values();
        return new ArrayList<>(Arrays.asList(values));
    }

    @Override
    public List<CoinSpecies> findByChainType(List<String> chainTypes) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.in("nickname", chainTypes).eq("level", 1);
        return coinSpeciesDao.queryForList(wrapper);
    }
}
