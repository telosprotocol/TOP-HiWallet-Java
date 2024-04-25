package com.topnetwork.wallet.service;

import com.topnetwork.wallet.common.enums.LanguageEnum;
import com.base.core.framework.sql.service.SqlBaseService;
import com.topnetwork.wallet.entity.AppDiscoverHtmlI18n;

import java.util.List;

public interface AppDiscoverHtmlI18nService extends SqlBaseService<AppDiscoverHtmlI18n,Long> {

    AppDiscoverHtmlI18n findByAppIdAndLanguage(Long targetId, LanguageEnum language);

    AppDiscoverHtmlI18n findById(Long i18nId);

    List<AppDiscoverHtmlI18n> findByAppId(Long id);

    Long saveReturnId(AppDiscoverHtmlI18n appDiscoverProI18n);
}
