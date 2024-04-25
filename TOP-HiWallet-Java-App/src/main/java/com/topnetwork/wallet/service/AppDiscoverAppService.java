package com.topnetwork.wallet.service;

import com.topnetwork.wallet.param.wallet.discover.AddAppParam;
import com.topnetwork.wallet.param.wallet.discover.EditAppI18nParam;
import com.topnetwork.wallet.param.wallet.discover.GetAppDetailBaseParam;
import com.topnetwork.wallet.param.wallet.discover.UpdateAppParam;
import com.topnetwork.wallet.result.wallet.discover.AppI18nResult;
import com.topnetwork.wallet.result.wallet.discover.GetManAppDetailResult;
import com.base.core.framework.sql.service.SqlBaseService;
import com.topnetwork.wallet.entity.AppDiscoverApp;

import java.util.List;

public interface AppDiscoverAppService extends SqlBaseService<AppDiscoverApp, Long> {

    AppDiscoverApp getById(Long targetId);

    GetManAppDetailResult getAppDetail(GetAppDetailBaseParam param);

    void addApp(AddAppParam param);

    void updateApp(UpdateAppParam param);

    List<AppI18nResult> getAppI18n(GetAppDetailBaseParam param);

    void editAppI18n(EditAppI18nParam param);
}
