package com.topnetwork.wallet.service;

import com.topnetwork.wallet.common.enums.discover.AppType;
import com.topnetwork.wallet.param.wallet.discover.GroupI18nParam;
import com.topnetwork.wallet.result.wallet.discover.ProI18nGroupResult;
import com.base.core.framework.sql.service.SqlBaseService;
import com.topnetwork.wallet.entity.AppDiscoverGroupI18n;

import java.util.List;

public interface AppDiscoverGroupI18nService extends SqlBaseService<AppDiscoverGroupI18n, Long> {

    List<AppDiscoverGroupI18n> findByGroupId(Long id, Boolean showToHome);

    List<ProI18nGroupResult> findByI18nIdAndType(Long i18nId, AppType pro);

    void save(Long i18nId, List<GroupI18nParam> groups, AppType appType);
}
