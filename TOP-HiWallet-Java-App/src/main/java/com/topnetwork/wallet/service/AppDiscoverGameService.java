package com.topnetwork.wallet.service;

import com.topnetwork.wallet.common.enums.PlatformEnum;
import com.topnetwork.wallet.entity.SecretPlatform;
import com.topnetwork.wallet.param.wallet.discover.*;
import com.topnetwork.wallet.result.wallet.discover.*;
import com.gitee.magic.framework.base.result.QueryResult;
import com.base.core.framework.sql.service.SqlBaseService;
import com.topnetwork.wallet.entity.AppDiscoverGame;

import java.util.List;

public interface AppDiscoverGameService extends SqlBaseService<AppDiscoverGame, Long> {

    List<RecommendGamesResult> getRecommendGames(RecommendGamesParam param, Boolean showNewYoung);

    QueryResult<List<GameResult>> getDiscoverGamesPage(RecommendPageParam param, Boolean showNewYoung);

    DiscoverSwitchResult getDiscoverSwitch(DiscoverSwitchParam param, PlatformEnum platform);

    QueryResult<List<GameListResult>> findGameList(GameListParam param);

    void updateGame(GameUpdateParam param);

    void addGame(GameAddParam param);

    List<RecommendGamesV3Result> getRecommendV3Games(RecommendGamesV3Param param, SecretPlatform secretPlatform, String ip);

    QueryResult<List<GameResult>> getDiscoverGamesV3Page(RecommendPageV3Param param, SecretPlatform secretPlatform, String ip);

    void addUsedHistory(AddUsedHistoryParam param);
}
