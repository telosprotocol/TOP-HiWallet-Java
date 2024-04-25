package com.topnetwork.wallet.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.base.core.mvc.business.CommonBusiness;
import com.common.utils.LocationUtils;
import com.common.utils.RedisUtils;
import com.base.service.base.system.service.ConfigureService;
import com.topnetwork.common.service.IconService;
import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.common.enums.LanguageEnum;
import com.topnetwork.wallet.common.enums.PlatformEnum;
import com.topnetwork.wallet.common.enums.discover.DappShowType;
import com.topnetwork.wallet.common.enums.discover.DiscoverDataFrom;
import com.topnetwork.wallet.common.enums.discover.GameType;
import com.topnetwork.wallet.dao.AppDiscoverGameDao;
import com.topnetwork.wallet.entity.AppDiscoverGame;
import com.topnetwork.wallet.entity.AppDiscoverGameClassify;
import com.topnetwork.wallet.entity.AppVersion;
import com.topnetwork.wallet.entity.SecretPlatform;
import com.topnetwork.wallet.param.wallet.discover.AddUsedHistoryParam;
import com.topnetwork.wallet.param.wallet.discover.DiscoverSwitchParam;
import com.topnetwork.wallet.param.wallet.discover.GameAddParam;
import com.topnetwork.wallet.param.wallet.discover.GameListParam;
import com.topnetwork.wallet.param.wallet.discover.GameUpdateParam;
import com.topnetwork.wallet.param.wallet.discover.RecommendGamesParam;
import com.topnetwork.wallet.param.wallet.discover.RecommendGamesV3Param;
import com.topnetwork.wallet.param.wallet.discover.RecommendPageParam;
import com.topnetwork.wallet.param.wallet.discover.RecommendPageV3Param;
import com.topnetwork.wallet.result.wallet.discover.DiscoverSwitchResult;
import com.topnetwork.wallet.result.wallet.discover.GameClassifyResult;
import com.topnetwork.wallet.result.wallet.discover.GameListResult;
import com.topnetwork.wallet.result.wallet.discover.GameResult;
import com.topnetwork.wallet.result.wallet.discover.RecommendGamesResult;
import com.topnetwork.wallet.result.wallet.discover.RecommendGamesV3Result;
import com.topnetwork.wallet.service.AppDiscoverGameClassifyService;
import com.topnetwork.wallet.service.AppDiscoverGameService;
import com.topnetwork.wallet.service.AppVersionService;

import com.gitee.magic.framework.head.exception.BusinessException;
import com.gitee.magic.framework.base.result.QueryResult;
import com.base.core.framework.sql.service.impl.SqlBaseServiceImpl;
import com.gitee.magic.jdbc.persistence.source.jdbc.sqlplus.conditions.query.QueryWrapper;
import com.gitee.magic.core.json.JsonObject;

@Service("appDiscoverGameService")
@Transactional
public class AppDiscoverGameServiceImpl extends SqlBaseServiceImpl<AppDiscoverGame, Long>
        implements AppDiscoverGameService {

    @SuppressWarnings("unused")
    private AppDiscoverGameDao appDiscoverGameDao;
    @Autowired
    private AppDiscoverGameClassifyService appDiscoverGameClassifyService;
    @Autowired
    private ConfigureService configureService;
    @Autowired
    @Qualifier("iconService")
    private IconService iconService;

    public AppDiscoverGameServiceImpl(@Qualifier("appDiscoverGameDao") AppDiscoverGameDao appDiscoverGameDao) {
        super(appDiscoverGameDao);
        this.appDiscoverGameDao = appDiscoverGameDao;
    }

    @Override
    public List<RecommendGamesResult> getRecommendGames(RecommendGamesParam param, Boolean showNewYoung) {
        List<RecommendGamesResult> result = new ArrayList<>();
        List<AppDiscoverGameClassify> listClassify = appDiscoverGameClassifyService.findAllByApiVersion(2);

        for (AppDiscoverGameClassify appDiscoverGameClassify : listClassify) {
            RecommendGamesResult recommendGamesResult = new RecommendGamesResult();
            recommendGamesResult.setClassifyId(appDiscoverGameClassify.getId());
            recommendGamesResult.setGames(getGamesByClassifyIdAndShowToHome(appDiscoverGameClassify.getId(), true, param.getLanguage(), showNewYoung, true));
            if (LanguageEnum.CN.equals(param.getLanguage())) {
                recommendGamesResult.setTitle(appDiscoverGameClassify.getTitle());
            } else {
                recommendGamesResult.setTitle(appDiscoverGameClassify.getEnglishTitle());
            }

            result.add(recommendGamesResult);
        }
        return result;
    }

    @Override
    public QueryResult<List<GameResult>> getDiscoverGamesPage(RecommendPageParam param, Boolean showNewYoung) {
        QueryResult<List<GameResult>> queryResult = new QueryResult<>();
        Map<String, Object> params = CommonBusiness.httpPageParam(param);
        QueryResult<List<AppDiscoverGame>> queryResultSource = queryForPage(AppDiscoverGame.class, "findDiscoverGameByClassify", params);
        if(ObjectUtils.isEmpty(queryResultSource)) {
        	return queryResult;
        }
        queryResult.setPageInfo(queryResultSource.getPageInfo());
        queryResult.setResult(tran(queryResultSource.getResult(), param.getLanguage(), showNewYoung));
        return queryResult;
    }

    @Autowired
    private AppVersionService appVersionService;

    @Override
    public List<RecommendGamesV3Result> getRecommendV3Games(RecommendGamesV3Param param, SecretPlatform secretPlatform, String ip) {
        AppVersion appVersion = appVersionService.getVersion(param.getVersion(), param.getRegion(), secretPlatform);
        if (appVersion == null) {
            throw new BusinessException(CodeRes.CODE_14019);
        }
        List<RecommendGamesV3Result> result = new ArrayList<>();
        List<AppDiscoverGameClassify> listClassify = appDiscoverGameClassifyService.findAllByApiVersion(3);

        for (AppDiscoverGameClassify appDiscoverGameClassify : listClassify) {
            RecommendGamesV3Result recommendGamesResult = new RecommendGamesV3Result();
            recommendGamesResult.setClassifyId(appDiscoverGameClassify.getId());
            List<GameResult> list;
            if (appDiscoverGameClassify.getDataFrom().equals(DiscoverDataFrom.mysql)) {
                list = filterWithIp(getGamesByClassifyIdAndShowToHome(appDiscoverGameClassify.getId(), true, param.getLanguage(), true, appVersion.getApproved()), ip);
            } else {
                list = filterWithIp(tran(redisTemplate.opsForList().range(RedisUtils.getKey("dapp_history:" + param.getUid()), 0, -1), param.getLanguage(), true), ip);
            }
            if (list == null || list.size() <= 0) {
                continue;
            }
            recommendGamesResult.setItems(list);
            if (LanguageEnum.CN.equals(param.getLanguage())) {
                recommendGamesResult.setTitle(appDiscoverGameClassify.getTitle());
            } else {
                recommendGamesResult.setTitle(appDiscoverGameClassify.getEnglishTitle());
            }
            recommendGamesResult.setShowMore(appDiscoverGameClassify.getShowMore());

            result.add(recommendGamesResult);
        }
        return result;
    }

    /**
     * 过滤新青年dapp
     *
     * @param list
     * @param ip
     * @return
     */
    private List<GameResult> filterWithIp(List<GameResult> list, String ip) {
        if (list == null || list.size() <= 0) {
            return list;
        }
        String countryCodes = String.valueOf(configureService.getValue("forbidCountryCodes"));
        if (StringUtils.isEmpty(countryCodes) || LocationUtils.isForbid(ip, countryCodes)) {
            JsonObject jsonObject = (JsonObject) configureService.getValue("forbidResourceIds");
            String youngDappId = String.valueOf(jsonObject.get("youngDappId"));
            for (int i = 0; i < list.size(); i++) {
                GameResult gameResult = list.get(i);
                if (gameResult.getId().equals(Long.valueOf(youngDappId))) {
                    list.remove(gameResult);
                }
            }
        }

        return list;
    }

    @Override
    public QueryResult<List<GameResult>> getDiscoverGamesV3Page(RecommendPageV3Param param, SecretPlatform secretPlatform, String ip) {
        AppVersion appVersion = appVersionService.getVersion(param.getVersion(), param.getRegion(), secretPlatform);
        if (appVersion == null) {
            throw new BusinessException(CodeRes.CODE_14019);
        }
        QueryResult<List<GameResult>> queryResult = new QueryResult<>();
        Map<String, Object> params = CommonBusiness.httpPageParam(param);
        params.put("approved", appVersion.getApproved() ? "1" : "0");
        QueryResult<List<AppDiscoverGame>> queryResultSource = queryForPage(AppDiscoverGame.class, "findDiscoverGameByClassify", params);
        if(ObjectUtils.isEmpty(queryResultSource)) {
        	return queryResult;
        }
        queryResult.setPageInfo(queryResultSource.getPageInfo());
        queryResult.setResult(filterWithIp(tran(queryResultSource.getResult(), param.getLanguage(), true), ip));
        return queryResult;
    }

    @Autowired
    private RedisTemplate<String, AppDiscoverGame> redisTemplate;

    /**
     * 只保存最近使用的5个到redis
     *
     * @param param
     */
    @Override
    public void addUsedHistory(AddUsedHistoryParam param) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", param.getDappId());
        AppDiscoverGame appDiscoverGame = get(appDiscoverGameDao.queryForList(queryWrapper));
        if (appDiscoverGame == null) {
            return;
        }
        saveHistoryToRedis(appDiscoverGame, param);
    }

    private synchronized void saveHistoryToRedis(AppDiscoverGame appDiscoverGame, AddUsedHistoryParam param) {
        List<AppDiscoverGame> list = redisTemplate.opsForList().range(RedisUtils.getKey("dapp_history:" + param.getUid()), 0, -1);
        List<AppDiscoverGame> newList = new ArrayList<>();
        newList.add(appDiscoverGame);
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                AppDiscoverGame discoverGame = list.get(i);
                if (discoverGame.getId().equals(param.getDappId())) {
                    list.remove(discoverGame);
                }
            }
            if (list.size() <= 4) {
                newList.addAll(list);
            } else {
                for (int i = 0; i < 4; i++) {
                    newList.add(list.get(i));
                }
            }
        }
        redisTemplate.delete(RedisUtils.getKey("dapp_history:" + param.getUid()));
        redisTemplate.opsForList().rightPushAll(RedisUtils.getKey("dapp_history:" + param.getUid()), newList);
    }

    @Override
    public DiscoverSwitchResult getDiscoverSwitch(DiscoverSwitchParam param, PlatformEnum platform) {
        String value = String.valueOf(configureService.getValue("discoverSwitch"));
        DiscoverSwitchResult discoverSwitchResult = new DiscoverSwitchResult();
        discoverSwitchResult.setOpen(Boolean.valueOf(value));
        return discoverSwitchResult;
    }


    @Override
    public QueryResult<List<GameListResult>> findGameList(GameListParam param) {
        QueryResult<List<GameListResult>> queryResult = new QueryResult<>();
        Map<String, Object> params = CommonBusiness.httpPageParam(param);
        QueryResult<List<AppDiscoverGame>> queryResultSource = queryForPage(AppDiscoverGame.class, "findDiscoverGame", params);
        if(ObjectUtils.isEmpty(queryResultSource)) {
        	return queryResult;
        }
        queryResult.setPageInfo(queryResultSource.getPageInfo());
        queryResult.setResult(tran(queryResultSource.getResult()));
        return queryResult;
    }

    @Override
    public void updateGame(GameUpdateParam param) {
        if (param.getType().equals(GameType.DECENTRALIAPP) && param.getChainType() == null) {
            throw new BusinessException(CodeRes.CODE_15044);
        }
        if ((param.getType().equals(GameType.DECENTRALIAPP) || param.getType().equals(GameType.HTML))
                && param.getActivityUrl() == null) {
            throw new BusinessException(CodeRes.CODE_15046);
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", param.getId());
        AppDiscoverGame appDiscoverGame = get(appDiscoverGameDao.queryForList(queryWrapper));
        if (appDiscoverGame == null) {
            throw new BusinessException(CodeRes.CODE_15041);
        }
        appDiscoverGame.setActivityUrl(param.getActivityUrl());
        appDiscoverGame.setChainType(param.getChainType());
        appDiscoverGame.setClassifyId(param.getClassifyId());
        appDiscoverGame.setChineseDesc(param.getDesc());
        appDiscoverGame.setEnglishDesc(param.getEnglishDesc());
        appDiscoverGame.setTitle(param.getTitle());
        appDiscoverGame.setEnglishTitle(param.getEnglishTitle());
        appDiscoverGame.setGameOrder(param.getGameOrder());
        appDiscoverGame.setGameShow(param.getGameShow());
        appDiscoverGame.setIconUrl(param.getIconUrl());
        appDiscoverGame.setShowType(param.getShowType());
        appDiscoverGame.setUpdateTime(new Date());
        appDiscoverGame.setShowToHome(param.getShowToHome());
        appDiscoverGame.setType(param.getType());
        save(appDiscoverGame);
    }

    @Override
    public void addGame(GameAddParam param) {
        if (param.getType().equals(GameType.DECENTRALIAPP) && param.getChainType() == null) {
            throw new BusinessException(CodeRes.CODE_15044);
        }
        if ((param.getType().equals(GameType.DECENTRALIAPP) || param.getType().equals(GameType.HTML))
                && param.getActivityUrl() == null) {
            throw new BusinessException(CodeRes.CODE_15046);
        }
        AppDiscoverGame appDiscoverGame = new AppDiscoverGame();
        appDiscoverGame.setActivityUrl(param.getActivityUrl());
        appDiscoverGame.setChainType(param.getChainType());
        appDiscoverGame.setClassifyId(param.getClassifyId());
        appDiscoverGame.setChineseDesc(param.getDesc());
        appDiscoverGame.setEnglishDesc(param.getEnglishDesc());
        appDiscoverGame.setGameShow(param.getGameShow());
        appDiscoverGame.setTitle(param.getTitle());
        appDiscoverGame.setEnglishTitle(param.getEnglishTitle());
        appDiscoverGame.setGameOrder(param.getGameOrder());
        appDiscoverGame.setIconUrl(param.getIconUrl());
        appDiscoverGame.setShowType(param.getShowType());
        appDiscoverGame.setCreateTime(new Date());
        appDiscoverGame.setShowToHome(param.getShowToHome());
        appDiscoverGame.setType(param.getType());
        save(appDiscoverGame);
    }

    private List<GameResult> getGamesByClassifyIdAndShowToHome(Long ClassifyId, Boolean showToHome, LanguageEnum language, Boolean showNewYoung, Boolean approved) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("gameShow", "true");
        queryWrapper.eq("ClassifyId", ClassifyId);
        queryWrapper.eq(showToHome != null, "showToHome", showToHome.toString());
        queryWrapper.ne(approved, "showType", DappShowType.examine.toString());
        queryWrapper.ne(!approved, "showType", DappShowType.normal.toString());
        queryWrapper.orderByAsc("gameOrder");
        List<AppDiscoverGame> list = appDiscoverGameDao.queryForList(queryWrapper);
        return tran(list, language, showNewYoung);
    }

    private List<GameResult> tran(List<AppDiscoverGame> list, LanguageEnum language, Boolean showNewYoung) {
        List<GameResult> results = new ArrayList<>();
        for (AppDiscoverGame appDiscoverGame : list) {
            if (!showNewYoung) {
                if (appDiscoverGame.getType().equals(GameType.CENTRALIAPP)) {
                    continue;
                }
            }
            GameResult gameResult = new GameResult();
            gameResult.setActivityUrl(appDiscoverGame.getActivityUrl());
            if (appDiscoverGame.getIconUrl().contains("http")) {
                gameResult.setIconUrl(appDiscoverGame.getIconUrl());
            } else {
                String logoUrl = iconService.buildURL(appDiscoverGame.getIconUrl());
                gameResult.setIconUrl(logoUrl);
            }
            gameResult.setId(appDiscoverGame.getId());
            if (LanguageEnum.CN.equals(language)) {
                gameResult.setTitle(appDiscoverGame.getTitle());
                gameResult.setDesc(appDiscoverGame.getChineseDesc());
            } else {
                gameResult.setTitle(appDiscoverGame.getEnglishTitle());
                gameResult.setDesc(appDiscoverGame.getEnglishDesc());
            }
            gameResult.setType(appDiscoverGame.getType());
            results.add(gameResult);
        }
        return results;
    }

    private List<GameListResult> tran(List<AppDiscoverGame> list) {
        List<GameListResult> results = new ArrayList<>();
        for (AppDiscoverGame appDiscoverGame : list) {
            GameListResult gameResult = new GameListResult();
            gameResult.setId(appDiscoverGame.getId());
            gameResult.setChainType(appDiscoverGame.getChainType());
            gameResult.setGameShow(appDiscoverGame.getGameShow());
            gameResult.setOrder(appDiscoverGame.getGameOrder());
            gameResult.setActivityUrl(appDiscoverGame.getActivityUrl());
            gameResult.setDesc(appDiscoverGame.getChineseDesc());
            gameResult.setEnglishDesc(appDiscoverGame.getEnglishDesc());
            gameResult.setType(appDiscoverGame.getType());
            gameResult.setTitle(appDiscoverGame.getTitle());
            gameResult.setShowType(appDiscoverGame.getShowType());
            gameResult.setEnglishTitle(appDiscoverGame.getEnglishTitle());
            if (appDiscoverGame.getIconUrl().contains("http")) {
                gameResult.setIconUrl(appDiscoverGame.getIconUrl());
            } else {
                String logoUrl = iconService.buildURL(appDiscoverGame.getIconUrl());
                gameResult.setIconUrl(logoUrl);
            }
            gameResult.setShowToHome(appDiscoverGame.getShowToHome());
            AppDiscoverGameClassify appDiscoverGameClassify = appDiscoverGameClassifyService.findById(appDiscoverGame.getClassifyId());
            if (appDiscoverGameClassify != null) {
                GameClassifyResult gameClassifyResult = new GameClassifyResult();
                gameClassifyResult.setEnglishTitle(appDiscoverGameClassify.getEnglishTitle());
                gameClassifyResult.setTitle(appDiscoverGameClassify.getTitle());
                gameClassifyResult.setId(appDiscoverGameClassify.getId());
                gameResult.setClassify(gameClassifyResult);
            }
            results.add(gameResult);
        }
        return results;
    }

}
