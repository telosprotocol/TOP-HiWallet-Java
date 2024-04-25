package com.topnetwork.wallet.service;

import com.topnetwork.wallet.common.enums.RewardFromType;
import com.topnetwork.wallet.common.enums.RewardType;
import com.topnetwork.wallet.entity.AppUser;
import com.topnetwork.wallet.entity.AppWithdrawOrder;
import com.topnetwork.wallet.param.wallet.appuser.ReceiveAwardParam;
import com.topnetwork.wallet.param.wallet.appuser.RewordHistParam;
import com.topnetwork.wallet.param.wallet.appuser.UserRewardParam;
import com.topnetwork.wallet.result.wallet.appuser.RewardHistResult;
import com.topnetwork.wallet.result.wallet.appuser.UserRewardResult;

import com.gitee.magic.framework.base.result.QueryResult;
import com.base.core.framework.sql.service.SqlBaseService;
import com.topnetwork.wallet.entity.AppActivityReward;

import java.math.BigDecimal;
import java.util.List;

public interface AppActivityRewardService extends SqlBaseService<AppActivityReward, Long> {

    List<AppActivityReward> getThisDayByType(RewardType behaviorType, Long uid, RewardFromType type);

    List<AppActivityReward> getThisDayEffectiveByType(RewardType behaviorType, Long uid, RewardFromType type);

    void saveList(List<AppActivityReward> rewardList);

    BigDecimal sumByUser(AppUser appUser);

    List<UserRewardResult> getUserReward(UserRewardParam param, AppUser appUser);

    QueryResult<List<RewardHistResult>> getRewardHistory(RewordHistParam param, AppUser appUser);

    void receiveAward(ReceiveAwardParam param, AppUser appUser);

    long saveReturnId(AppActivityReward appActivityReward);

    AppActivityReward findNewUserReward(AppUser appUser);

    void addWithdrawRecord(AppWithdrawOrder appWithdrawOrder);

    List<AppActivityReward> get7DaysReceiveReward(Long userId);
}
