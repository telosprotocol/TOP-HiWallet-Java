package com.topnetwork.wallet.service;

import com.topnetwork.wallet.common.enums.LanguageEnum;
import com.base.core.framework.sql.service.SqlBaseService;
import com.topnetwork.wallet.entity.AppDiscoverAppI18n;

import java.util.List;

public interface AppDiscoverAppI18nService extends SqlBaseService<AppDiscoverAppI18n,Long> {

    AppDiscoverAppI18n findByAppIdAndLanguage(Long targetId, LanguageEnum language);

    AppDiscoverAppI18n findById(Long i18nId);

    List<AppDiscoverAppI18n> findByAppId(Long id);

    Long saveReturnId(AppDiscoverAppI18n appDiscoverProI18n);
}
