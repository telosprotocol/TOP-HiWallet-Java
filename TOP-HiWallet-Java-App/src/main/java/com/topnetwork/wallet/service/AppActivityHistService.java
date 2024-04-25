package com.topnetwork.wallet.service;

import com.topnetwork.wallet.entity.AppUser;
import com.topnetwork.wallet.param.wallet.activity.GameHistParam;
import com.topnetwork.wallet.param.wallet.activity.GuessConfigParam;
import com.topnetwork.wallet.result.wallet.activity.GameHistResult;
import com.topnetwork.wallet.result.wallet.activity.GuessConfigResult;
import com.gitee.magic.framework.base.result.QueryResult;
import com.base.core.framework.sql.service.SqlBaseService;
import com.topnetwork.wallet.entity.AppGameHist;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

public interface AppActivityHistService extends SqlBaseService<AppGameHist, Long> {

    BigDecimal countRewardByUidAndAid(Long uid, Long aid);

    BigInteger countTimeByAid(Long aid);

    boolean isHasExit(String hash, Long uid, Long aid);

    List<AppGameHist> findByAidAndUid(Long aid, Long uid);

    AppGameHist findWaitHist(Long uid, Long aid);

    AppGameHist findWaitHist(Long uid, Long aid, String phase);

    List<AppGameHist> findWaitHist(Long aid, String phase);

    QueryResult<List<GameHistResult>> findGameHist(GameHistParam param, AppUser appUser);

    GuessConfigResult findGuessGameConfig(GuessConfigParam param, AppUser appUser);
}
