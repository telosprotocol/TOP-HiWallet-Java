package com.topnetwork.wallet.service;

import com.topnetwork.wallet.common.enums.LanguageEnum;
import com.topnetwork.wallet.result.wallet.discover.GetLabelsI18nResult;
import com.base.core.framework.sql.service.SqlBaseService;
import com.topnetwork.wallet.entity.AppDiscoverLabelI18n;

import java.util.List;

public interface AppDiscoverLabelI18nService extends SqlBaseService<AppDiscoverLabelI18n,Long> {

    List<GetLabelsI18nResult> getByLabelId(Long id);

    AppDiscoverLabelI18n findByLabelIdAndLanguage(Long labelId, LanguageEnum language);
}
