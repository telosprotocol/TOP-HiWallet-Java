package com.topnetwork.wallet.service;

import com.topnetwork.wallet.common.enums.RewardType;
import com.topnetwork.wallet.entity.AppUser;
import com.topnetwork.wallet.param.wallet.activity.*;
import com.topnetwork.wallet.result.wallet.activity.*;
import com.gitee.magic.framework.base.result.QueryResult;
import com.base.core.framework.sql.service.SqlBaseService;
import com.topnetwork.wallet.entity.AppActivity;

import java.util.List;

public interface AppActivityService extends SqlBaseService<AppActivity, Long> {

    List<ActivityListResult> getActivityList(ActivityListParam param, AppUser appUser);

    void recSucTran(RecSucTranParam param, AppUser appUser);

    RecQuestionResult recQuestionResult(RecQuestionParam param, AppUser appUser);

    void recGuessResult(GuessReportParam param, AppUser appUser);

    RecBrickResult recBrickResult(RecBrickParam param, AppUser appUser);


    AppActivity findActivityByType(RewardType rewardType);

    void start(ActivityConfParam param, AppUser appUser);

    AppActivity findActivityById(Long aid);

    void reset(ActivityConfParam param, AppUser appUser);

    void end(ActivityBaseParam param, AppUser appUser);

    void awardDaily();

    void countGuess();

    void saveBtcPrice();

    QueryResult<List<ActivityPageResult>> getActivityPage(ActivityPageParam param);

    void updateActivity(ActivityUpdParam param);

    void addActivity(ActivityAddParam param);

    QueryResult<List<GameConfigPageResult>> findGameConfigPage(GameConfigPageParam param);

    QueryResult<List<RewardPageResult>> findRewardPage(RewardPageParam param);

    QueryResult<List<BehaviorPageResult>> findBehaviorPage(BehaviorPageParam param);

    ActivityTicketResult findActivityTicket(ActivityTicketParam param, AppUser appUser);

    void registerDaily();

    void partInGuess(PartInGuessParam param);

    void reward(RewardGuessParam param);

}
