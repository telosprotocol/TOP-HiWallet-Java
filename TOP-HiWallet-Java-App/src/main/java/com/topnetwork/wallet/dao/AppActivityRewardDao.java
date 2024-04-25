package com.topnetwork.wallet.dao;

import com.base.core.framework.sql.dao.SqlBaseDao;
import com.topnetwork.wallet.entity.AppActivityReward;

public interface AppActivityRewardDao extends SqlBaseDao<AppActivityReward, Long> {

    boolean insertWithId(AppActivityReward appActivityReward);
}
