package com.topnetwork.wallet.service.impl;

import com.topnetwork.wallet.common.enums.LanguageEnum;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import com.base.core.framework.sql.service.impl.SqlBaseServiceImpl;
import com.topnetwork.wallet.dao.AppDiscoverDappI18nDao;
import com.topnetwork.wallet.entity.AppDiscoverDappI18n;
import com.topnetwork.wallet.service.AppDiscoverDappI18nService;
import com.gitee.magic.jdbc.persistence.source.jdbc.sqlplus.conditions.query.QueryWrapper;

import java.util.List;

@Service("appDiscoverDappI18nService")
@Transactional
public class AppDiscoverDappI18nServiceImpl extends SqlBaseServiceImpl<AppDiscoverDappI18n, Long>
        implements AppDiscoverDappI18nService {

    @SuppressWarnings("unused")
    private AppDiscoverDappI18nDao appDiscoverDappI18nDao;

    public AppDiscoverDappI18nServiceImpl(@Qualifier("appDiscoverDappI18nDao") AppDiscoverDappI18nDao appDiscoverDappI18nDao) {
        super(appDiscoverDappI18nDao);
        this.appDiscoverDappI18nDao = appDiscoverDappI18nDao;
    }

    @Override
    public AppDiscoverDappI18n findByAppIdAndLanguage(Long targetId, LanguageEnum language) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("appId", targetId).eq("language", language.toString());
        return get(appDiscoverDappI18nDao.queryForList(wrapper));
    }

    @Override
    public AppDiscoverDappI18n findById(Long i18nId) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("id", i18nId);
        wrapper.orderByAsc("language");
        return get(appDiscoverDappI18nDao.queryForList(wrapper));
    }

    @Override
    public List<AppDiscoverDappI18n> findByAppId(Long id) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("appId", id);
        wrapper.orderByAsc("language");
        return appDiscoverDappI18nDao.queryForList(wrapper);
    }

    @Override
    public Long saveReturnId(AppDiscoverDappI18n appDiscoverProI18n) {
        Long id = appDiscoverDappI18nDao.getSnowflakeIdWorkerNextId();
        appDiscoverProI18n.setId(id);
        appDiscoverDappI18nDao.persist(appDiscoverProI18n);
        return id;
    }
}
