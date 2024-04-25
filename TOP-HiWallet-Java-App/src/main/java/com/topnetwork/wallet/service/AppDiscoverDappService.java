package com.topnetwork.wallet.service;

import com.topnetwork.wallet.param.wallet.discover.AddDappParam;
import com.topnetwork.wallet.param.wallet.discover.EditDappI18nParam;
import com.topnetwork.wallet.param.wallet.discover.GetAppDetailBaseParam;
import com.topnetwork.wallet.param.wallet.discover.UpdateDappParam;
import com.topnetwork.wallet.result.wallet.discover.DappI18nResult;
import com.topnetwork.wallet.result.wallet.discover.GetDappDetailResult;
import com.base.core.framework.sql.service.SqlBaseService;
import com.topnetwork.wallet.entity.AppDiscoverDapp;

import java.util.List;

public interface AppDiscoverDappService extends SqlBaseService<AppDiscoverDapp,Long> {

    AppDiscoverDapp getById(Long targetId);

    GetDappDetailResult getDappDetail(GetAppDetailBaseParam param);

    void addDapp(AddDappParam param);

    void updateDapp(UpdateDappParam param);

    List<DappI18nResult> getDappI18n(GetAppDetailBaseParam param);

    void editDappI18n(EditDappI18nParam param);
}
