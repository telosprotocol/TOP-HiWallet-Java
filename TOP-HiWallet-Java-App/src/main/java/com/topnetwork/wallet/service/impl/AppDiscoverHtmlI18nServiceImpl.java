package com.topnetwork.wallet.service.impl;

import com.topnetwork.wallet.common.enums.LanguageEnum;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import com.base.core.framework.sql.service.impl.SqlBaseServiceImpl;
import com.topnetwork.wallet.dao.AppDiscoverHtmlI18nDao;
import com.topnetwork.wallet.entity.AppDiscoverHtmlI18n;
import com.topnetwork.wallet.service.AppDiscoverHtmlI18nService;
import com.gitee.magic.jdbc.persistence.source.jdbc.sqlplus.conditions.query.QueryWrapper;

import java.util.List;

@Service("appDiscoverHtmlI18nService")
@Transactional
public class AppDiscoverHtmlI18nServiceImpl extends SqlBaseServiceImpl<AppDiscoverHtmlI18n, Long>
        implements AppDiscoverHtmlI18nService {

    @SuppressWarnings("unused")
    private AppDiscoverHtmlI18nDao appDiscoverHtmlI18nDao;

    public AppDiscoverHtmlI18nServiceImpl(@Qualifier("appDiscoverHtmlI18nDao") AppDiscoverHtmlI18nDao appDiscoverHtmlI18nDao) {
        super(appDiscoverHtmlI18nDao);
        this.appDiscoverHtmlI18nDao = appDiscoverHtmlI18nDao;
    }

    @Override
    public AppDiscoverHtmlI18n findByAppIdAndLanguage(Long targetId, LanguageEnum language) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("appId", targetId).eq("language", language.toString());
        return get(appDiscoverHtmlI18nDao.queryForList(wrapper));
    }

    @Override
    public AppDiscoverHtmlI18n findById(Long i18nId) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("id", i18nId);
        wrapper.orderByAsc("language");
        return get(appDiscoverHtmlI18nDao.queryForList(wrapper));
    }

    @Override
    public List<AppDiscoverHtmlI18n> findByAppId(Long id) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("appId", id);
        wrapper.orderByAsc("language");
        return appDiscoverHtmlI18nDao.queryForList(wrapper);
    }

    @Override
    public Long saveReturnId(AppDiscoverHtmlI18n appDiscoverProI18n) {
        Long id = appDiscoverHtmlI18nDao.getSnowflakeIdWorkerNextId();
        appDiscoverProI18n.setId(id);
        appDiscoverHtmlI18nDao.persist(appDiscoverProI18n);
        return id;
    }
}
