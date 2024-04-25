package com.topnetwork.wallet.dao.impl;

import org.springframework.stereotype.Repository;
import com.base.core.framework.sql.dao.impl.SqlBaseDaoImpl;

import com.topnetwork.wallet.dao.AppActivityRewardDao;
import com.topnetwork.wallet.entity.AppActivityReward;

@Repository("appActivityRewardDao")
public class AppActivityRewardDaoImpl extends SqlBaseDaoImpl<AppActivityReward, Long> implements AppActivityRewardDao {

    public AppActivityRewardDaoImpl() {
        super(AppActivityReward.class);
    }


    @Override
    public boolean insertWithId(AppActivityReward appActivityReward) {
        long i = persist(appActivityReward);
        return i > 0;
    }
}
