package com.topnetwork.wallet.service;

import com.topnetwork.wallet.param.wallet.discover.AddHtmlParam;
import com.topnetwork.wallet.param.wallet.discover.EditHtmlI18nParam;
import com.topnetwork.wallet.param.wallet.discover.GetAppDetailBaseParam;
import com.topnetwork.wallet.param.wallet.discover.UpdateHtmlParam;
import com.topnetwork.wallet.result.wallet.discover.GetHtmlDetailResult;
import com.topnetwork.wallet.result.wallet.discover.HtmlI18nResult;
import com.base.core.framework.sql.service.SqlBaseService;
import com.topnetwork.wallet.entity.AppDiscoverHtml;

import java.util.List;

public interface AppDiscoverHtmlService extends SqlBaseService<AppDiscoverHtml,Long> {

    AppDiscoverHtml getById(Long targetId);

    GetHtmlDetailResult getHtmlDetail(GetAppDetailBaseParam param);

    void addHtml(AddHtmlParam param);

    void updateHtml(UpdateHtmlParam param);

    List<HtmlI18nResult> getHtmlI18n(GetAppDetailBaseParam param);

    void editHtmlI18n(EditHtmlI18nParam param);
}
