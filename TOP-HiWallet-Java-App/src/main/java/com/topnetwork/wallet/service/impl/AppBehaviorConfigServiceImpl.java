package com.topnetwork.wallet.service.impl;

import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.common.enums.RewardType;
import com.topnetwork.wallet.param.wallet.activity.BehaviorUpdateParam;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import com.gitee.magic.framework.head.exception.BusinessException;
import com.base.core.framework.sql.service.impl.SqlBaseServiceImpl;
import com.topnetwork.wallet.dao.AppBehaviorConfigDao;
import com.topnetwork.wallet.entity.AppBehaviorConfig;
import com.topnetwork.wallet.service.AppBehaviorConfigService;
import com.gitee.magic.jdbc.persistence.source.jdbc.sqlplus.conditions.query.QueryWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("appBehaviorConfigService")
@Transactional
public class AppBehaviorConfigServiceImpl extends SqlBaseServiceImpl<AppBehaviorConfig, Long>
        implements AppBehaviorConfigService {

    @SuppressWarnings("unused")
    private AppBehaviorConfigDao appBehaviorConfigDao;

    public AppBehaviorConfigServiceImpl(@Qualifier("appBehaviorConfigDao") AppBehaviorConfigDao appBehaviorConfigDao) {
        super(appBehaviorConfigDao);
        this.appBehaviorConfigDao = appBehaviorConfigDao;
    }

    @Override
    public AppBehaviorConfig findByAid(Long aid) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("aid", aid);
        return get(appBehaviorConfigDao.queryForList(wrapper));
    }

    @Override
    public void updateBehavior(BehaviorUpdateParam param) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("id", param.getBehaviorId());
        AppBehaviorConfig appBehaviorConfig = get(appBehaviorConfigDao.queryForList(wrapper));
        if (appBehaviorConfig == null) {
            throw new BusinessException(CodeRes.CODE_15063);
        }
        appBehaviorConfig.setFristTime(param.getFristTime());
        appBehaviorConfig.setGrowthTime(param.getGrowthTime());
        appBehaviorConfig.setMaxAmount(param.getMaxAmount());
        appBehaviorConfig.setMinAmount(param.getMinAmount());
        appBehaviorConfig.setSizeInOnce(param.getSizeInOnce());
        appBehaviorConfig.setTimeInDay(param.getTimeInDay());
        appBehaviorConfig.setTimeInOnce(param.getTimeInOnce());
        appBehaviorConfig.setUnit(param.getUnit());
        appBehaviorConfig.setUpdateTime(new Date());
        save(appBehaviorConfig);
    }

    @Override
    public AppBehaviorConfig findByActivityType(RewardType invite) {
        Map<String, Object> param = new HashMap<>();
        param.put("type", invite.toString());
        List<AppBehaviorConfig> appBehaviorConfig = appBehaviorConfigDao.queryForListMapper("findBehaviorByType", param);
        if (appBehaviorConfig == null || appBehaviorConfig.size() <= 0) {
            return null;
        }
        return appBehaviorConfig.get(0);
    }
}
