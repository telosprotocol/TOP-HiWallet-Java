package com.topnetwork.wallet.service;

import com.topnetwork.wallet.common.enums.LanguageEnum;
import com.base.core.framework.sql.service.SqlBaseService;
import com.topnetwork.wallet.entity.AppDiscoverDappI18n;

import java.util.List;

public interface AppDiscoverDappI18nService extends SqlBaseService<AppDiscoverDappI18n,Long> {

    AppDiscoverDappI18n findByAppIdAndLanguage(Long targetId, LanguageEnum language);

    AppDiscoverDappI18n findById(Long i18nId);

    List<AppDiscoverDappI18n> findByAppId(Long id);

    Long saveReturnId(AppDiscoverDappI18n appDiscoverProI18n);
}
