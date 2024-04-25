package com.topnetwork.wallet.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.topnetwork.wallet.dao.AppCoinConfigDao;
import com.topnetwork.wallet.entity.AppCoinConfig;
import com.topnetwork.wallet.param.wallet.CoinConfigParam;
import com.topnetwork.wallet.result.wallet.CoinConfigResult;
import com.topnetwork.wallet.service.AppCoinConfigService;
import com.topnetwork.wallet.service.AppCoinFeeService;

import com.base.core.framework.sql.service.impl.SqlBaseServiceImpl;
import com.gitee.magic.jdbc.persistence.source.jdbc.sqlplus.conditions.query.QueryWrapper;

@Service("appCoinConfigService")
@Transactional
public class AppCoinConfigServiceImpl extends SqlBaseServiceImpl<AppCoinConfig, Long>
        implements AppCoinConfigService {

    @SuppressWarnings("unused")
    private AppCoinConfigDao appCoinConfigDao;

    @Autowired
    private AppCoinFeeService appCoinFeeService;

    public AppCoinConfigServiceImpl(@Qualifier("appCoinConfigDao") AppCoinConfigDao appCoinConfigDao) {
        super(appCoinConfigDao);
        this.appCoinConfigDao = appCoinConfigDao;
    }

    @Override
    public CoinConfigResult getConfig(CoinConfigParam param) {

        CoinConfigResult coinConfigResult = new CoinConfigResult();
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("chainType", param.getChainType().toString());
        AppCoinConfig appCoinConfig = get(appCoinConfigDao.queryForList(wrapper));
        if (appCoinConfig == null) {
            return coinConfigResult;
        }
        coinConfigResult.setRequestMethod(appCoinConfig.getRequestMethod());
        coinConfigResult.setRequestParam(appCoinConfig.getRequestParam());
        coinConfigResult.setRequestUrl(appCoinConfig.getRequestUrl());
        coinConfigResult.setType(appCoinConfig.getType());
        coinConfigResult.setFee(appCoinFeeService.queryByChainType(param.getChainType()));

        return coinConfigResult;
    }
}
