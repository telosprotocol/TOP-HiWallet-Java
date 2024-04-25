package com.topnetwork.wallet.service;

import com.topnetwork.wallet.common.enums.LanguageEnum;
import com.topnetwork.wallet.common.enums.discover.AppType;
import com.topnetwork.wallet.param.wallet.discover.AddDappLabelParam;
import com.base.core.framework.sql.service.SqlBaseService;
import com.topnetwork.wallet.entity.AppDiscoverLabelApplication;

import java.util.List;

public interface AppDiscoverLabelApplicationService extends SqlBaseService<AppDiscoverLabelApplication, Long> {

    String[] getByTypeAndAppId(AppType dapp, Long targetId, LanguageEnum language);

    void saveLabel(Long appId, List<AddDappLabelParam> labels, AppType dapp);
}
