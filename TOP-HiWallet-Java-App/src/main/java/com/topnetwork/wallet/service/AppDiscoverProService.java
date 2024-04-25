package com.topnetwork.wallet.service;

import com.topnetwork.wallet.common.enums.discover.BannerNewType;
import com.topnetwork.wallet.param.wallet.discover.EditProI18nParam;
import com.topnetwork.wallet.param.wallet.discover.GetAppDetailBaseParam;
import com.topnetwork.wallet.param.wallet.discover.UpdateProAppParam;
import com.topnetwork.wallet.result.wallet.discover.GetAppDetailBaseResult;
import com.topnetwork.wallet.result.wallet.discover.ProI18nResult;
import com.base.core.framework.sql.service.SqlBaseService;
import com.topnetwork.wallet.entity.AppDiscoverPro;

import java.util.List;

public interface AppDiscoverProService extends SqlBaseService<AppDiscoverPro, Long> {

    AppDiscoverPro getById(Long targetId);

    GetAppDetailBaseResult getProDetail(GetAppDetailBaseParam param);

    void updateProApp(UpdateProAppParam param);

    List<ProI18nResult> getProI18n(GetAppDetailBaseParam param);

    void editProI18n(EditProI18nParam param);

    AppDiscoverPro getByType(BannerNewType type);
}
