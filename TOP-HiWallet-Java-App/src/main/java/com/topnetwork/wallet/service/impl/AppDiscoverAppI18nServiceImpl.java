package com.topnetwork.wallet.service.impl;

import com.topnetwork.wallet.common.enums.LanguageEnum;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import com.base.core.framework.sql.service.impl.SqlBaseServiceImpl;
import com.topnetwork.wallet.dao.AppDiscoverAppI18nDao;
import com.topnetwork.wallet.entity.AppDiscoverAppI18n;
import com.topnetwork.wallet.service.AppDiscoverAppI18nService;
import com.gitee.magic.jdbc.persistence.source.jdbc.sqlplus.conditions.query.QueryWrapper;

import java.util.List;

@Service("appDiscoverAppI18nService")
@Transactional
public class AppDiscoverAppI18nServiceImpl extends SqlBaseServiceImpl<AppDiscoverAppI18n, Long>
        implements AppDiscoverAppI18nService {

    @SuppressWarnings("unused")
    private AppDiscoverAppI18nDao appDiscoverAppI18nDao;

    public AppDiscoverAppI18nServiceImpl(@Qualifier("appDiscoverAppI18nDao") AppDiscoverAppI18nDao appDiscoverAppI18nDao) {
        super(appDiscoverAppI18nDao);
        this.appDiscoverAppI18nDao = appDiscoverAppI18nDao;
    }

    @Override
    public AppDiscoverAppI18n findByAppIdAndLanguage(Long targetId, LanguageEnum language) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("appId", targetId).eq("language", language.toString());
        return get(appDiscoverAppI18nDao.queryForList(wrapper));
    }

    @Override
    public AppDiscoverAppI18n findById(Long i18nId) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("id", i18nId);
        wrapper.orderByAsc("language");
        return get(appDiscoverAppI18nDao.queryForList(wrapper));
    }

    @Override
    public List<AppDiscoverAppI18n> findByAppId(Long id) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("appId", id);
        wrapper.orderByAsc("language");
        return appDiscoverAppI18nDao.queryForList(wrapper);
    }

    @Override
    public Long saveReturnId(AppDiscoverAppI18n appDiscoverProI18n) {
        Long id = appDiscoverAppI18nDao.getSnowflakeIdWorkerNextId();
        appDiscoverProI18n.setId(id);
        appDiscoverAppI18nDao.persist(appDiscoverProI18n);
        return id;
    }
}
