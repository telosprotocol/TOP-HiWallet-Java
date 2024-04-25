package com.topnetwork.wallet.service.impl;

import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.common.enums.GameConfigType;
import com.topnetwork.wallet.param.wallet.activity.GameConfigUpdParam;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import com.gitee.magic.framework.head.exception.BusinessException;
import com.base.core.framework.sql.service.impl.SqlBaseServiceImpl;
import com.topnetwork.wallet.dao.AppGameConfigDao;
import com.topnetwork.wallet.entity.AppGameConfig;
import com.topnetwork.wallet.service.AppGameConfigService;
import com.gitee.magic.jdbc.persistence.source.jdbc.sqlplus.conditions.query.QueryWrapper;

import java.util.Date;
import java.util.List;

@Service("appGameConfigService")
@Transactional
public class AppGameConfigServiceImpl extends SqlBaseServiceImpl<AppGameConfig, Long>
        implements AppGameConfigService {

    @SuppressWarnings("unused")
    private AppGameConfigDao appGameConfigDao;

    public AppGameConfigServiceImpl(@Qualifier("appGameConfigDao") AppGameConfigDao appGameConfigDao) {
        super(appGameConfigDao);
        this.appGameConfigDao = appGameConfigDao;
    }

    @Override
    public AppGameConfig findTicketByAid(Long id) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("aid", id).eq("type", GameConfigType.TICKET.toString());
        return get(appGameConfigDao.queryForList(wrapper));
    }

    @Override
    public List<AppGameConfig> findByAid(Long aid) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("aid", aid);
        return appGameConfigDao.queryForList(wrapper);
    }

    @Override
    public void updateGameConfig(GameConfigUpdParam param) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("id", param.getConfigId());
        AppGameConfig appGameConfig = get(appGameConfigDao.queryForList(wrapper));
        if (appGameConfig == null) {
            throw new BusinessException(CodeRes.CODE_15063);
        }
        appGameConfig.setAmount(param.getAmount());
        appGameConfig.setRecord(param.getRecord());
        appGameConfig.setTimeInDay(param.getTimeInDay());
        appGameConfig.setType(param.getType());
        appGameConfig.setUnit(param.getUnit());
        appGameConfig.setUpdateTime(new Date());
        save(appGameConfig);
    }
}
