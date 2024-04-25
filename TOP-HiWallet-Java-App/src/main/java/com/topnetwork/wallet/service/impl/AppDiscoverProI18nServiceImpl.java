package com.topnetwork.wallet.service.impl;

import com.topnetwork.wallet.common.enums.LanguageEnum;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import com.base.core.framework.sql.service.impl.SqlBaseServiceImpl;
import com.topnetwork.wallet.dao.AppDiscoverProI18nDao;
import com.topnetwork.wallet.entity.AppDiscoverProI18n;
import com.topnetwork.wallet.service.AppDiscoverProI18nService;
import com.gitee.magic.jdbc.persistence.source.jdbc.sqlplus.conditions.query.QueryWrapper;

import java.util.List;

@Service("appDiscoverProI18nService")
@Transactional
public class AppDiscoverProI18nServiceImpl extends SqlBaseServiceImpl<AppDiscoverProI18n, Long>
        implements AppDiscoverProI18nService {

    @SuppressWarnings("unused")
    private AppDiscoverProI18nDao appDiscoverProI18nDao;

    public AppDiscoverProI18nServiceImpl(@Qualifier("appDiscoverProI18nDao") AppDiscoverProI18nDao appDiscoverProI18nDao) {
        super(appDiscoverProI18nDao);
        this.appDiscoverProI18nDao = appDiscoverProI18nDao;
    }

    @Override
    public AppDiscoverProI18n findByAppIdAndLanguage(Long targetId, LanguageEnum language) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("appId", targetId).eq("language", language.toString());
        return get(appDiscoverProI18nDao.queryForList(wrapper));
    }

    @Override
    public AppDiscoverProI18n findById(Long i18nId) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("id", i18nId);
        wrapper.orderByAsc("language");
        return get(appDiscoverProI18nDao.queryForList(wrapper));
    }

    @Override
    public List<AppDiscoverProI18n> findByAppId(Long appId) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("appId", appId);
        wrapper.orderByAsc("language");
        return appDiscoverProI18nDao.queryForList(wrapper);
    }

    @Override
    public Long saveReturnId(AppDiscoverProI18n appDiscoverProI18n) {
        Long id = appDiscoverProI18nDao.getSnowflakeIdWorkerNextId();
        appDiscoverProI18n.setId(id);
        appDiscoverProI18nDao.persist(appDiscoverProI18n);
        return id;
    }
}
