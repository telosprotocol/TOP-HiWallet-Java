package com.topnetwork.wallet.service;

import com.topnetwork.wallet.common.enums.LanguageEnum;
import com.base.core.framework.sql.service.SqlBaseService;
import com.topnetwork.wallet.entity.AppDiscoverProI18n;

import java.util.List;

public interface AppDiscoverProI18nService extends SqlBaseService<AppDiscoverProI18n, Long> {

    AppDiscoverProI18n findByAppIdAndLanguage(Long targetId, LanguageEnum language);

    AppDiscoverProI18n findById(Long i18nId);

    List<AppDiscoverProI18n> findByAppId(Long appId);

    Long saveReturnId(AppDiscoverProI18n appDiscoverProI18n);
}
