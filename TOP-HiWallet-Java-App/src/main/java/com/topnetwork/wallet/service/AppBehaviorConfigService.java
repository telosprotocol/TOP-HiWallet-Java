package com.topnetwork.wallet.service;

import com.topnetwork.wallet.common.enums.RewardType;
import com.topnetwork.wallet.param.wallet.activity.BehaviorUpdateParam;
import com.base.core.framework.sql.service.SqlBaseService;
import com.topnetwork.wallet.entity.AppBehaviorConfig;

public interface AppBehaviorConfigService extends SqlBaseService<AppBehaviorConfig,Long> {

    AppBehaviorConfig findByAid(Long aid);

    void updateBehavior(BehaviorUpdateParam param);

    AppBehaviorConfig findByActivityType(RewardType invite);
}
