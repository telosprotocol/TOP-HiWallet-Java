package com.topnetwork.wallet.service;

import com.topnetwork.wallet.entity.SecretPlatform;
import com.topnetwork.wallet.param.wallet.home.*;
import com.topnetwork.wallet.result.wallet.discover.GetNewBannersResult;
import com.topnetwork.wallet.result.wallet.home.AppListResult;
import com.topnetwork.wallet.result.wallet.home.AppSelectListResult;
import com.base.core.framework.sql.service.SqlBaseService;
import com.topnetwork.wallet.entity.HomeApp;

import java.util.List;

public interface HomeAppService extends SqlBaseService<HomeApp, Long> {

    List<AppListResult> getAppList();

    void addApp(AddAppParam addAppParam);

    void delApp(DelAppParam delAppParam);

    void updateApp(UpdAppParam param);

    List<AppSelectListResult> getSelectAppList(GetSelectAppParam param);

    List<GetNewBannersResult> getDiscoverApp(GetAppListParam param, SecretPlatform secretPlatform, String requestIP);
}
