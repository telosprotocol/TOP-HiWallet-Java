package com.topnetwork.wallet.service;

import com.topnetwork.wallet.common.enums.LanguageEnum;
import com.base.core.framework.sql.service.SqlBaseService;
import com.topnetwork.wallet.entity.AppDiscoverBannerNewI18n;

import java.util.List;

public interface AppDiscoverBannerNewI18nService extends SqlBaseService<AppDiscoverBannerNewI18n, Long> {

    AppDiscoverBannerNewI18n getByBannerIdAndLanguage(Long id, LanguageEnum language);

    List<AppDiscoverBannerNewI18n> getI18nListByBannerId(Long bannerId);
}
