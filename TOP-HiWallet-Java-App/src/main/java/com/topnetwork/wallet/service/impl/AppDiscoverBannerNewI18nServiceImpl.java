package com.topnetwork.wallet.service.impl;

import com.topnetwork.wallet.common.enums.LanguageEnum;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import com.base.core.framework.sql.service.impl.SqlBaseServiceImpl;
import com.topnetwork.wallet.dao.AppDiscoverBannerNewI18nDao;
import com.topnetwork.wallet.entity.AppDiscoverBannerNewI18n;
import com.topnetwork.wallet.service.AppDiscoverBannerNewI18nService;
import com.gitee.magic.jdbc.persistence.source.jdbc.sqlplus.conditions.query.QueryWrapper;

import java.util.Date;
import java.util.List;

@Service("appDiscoverBannerNewI18nService")
@Transactional
public class AppDiscoverBannerNewI18nServiceImpl extends SqlBaseServiceImpl<AppDiscoverBannerNewI18n, Long>
        implements AppDiscoverBannerNewI18nService {

    @SuppressWarnings("unused")
    private AppDiscoverBannerNewI18nDao appDiscoverBannerNewI18nDao;

    public AppDiscoverBannerNewI18nServiceImpl(@Qualifier("appDiscoverBannerNewI18nDao") AppDiscoverBannerNewI18nDao appDiscoverBannerNewI18nDao) {
        super(appDiscoverBannerNewI18nDao);
        this.appDiscoverBannerNewI18nDao = appDiscoverBannerNewI18nDao;
    }

    @Override
    public AppDiscoverBannerNewI18n getByBannerIdAndLanguage(Long bannerId, LanguageEnum language) {
        QueryWrapper wrapper = new QueryWrapper();
        Date now = new Date();
        wrapper.eq("language", language.toString()).eq("bannerId", bannerId).le("startTime", now).ge("endTime", now);
        return get(appDiscoverBannerNewI18nDao.queryForList(wrapper));
    }

    @Override
    public List<AppDiscoverBannerNewI18n> getI18nListByBannerId(Long bannerId) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("bannerId", bannerId);
        return appDiscoverBannerNewI18nDao.queryForList(wrapper);
    }
}
