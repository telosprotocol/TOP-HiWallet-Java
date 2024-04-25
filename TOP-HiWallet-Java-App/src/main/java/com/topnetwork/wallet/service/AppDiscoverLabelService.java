package com.topnetwork.wallet.service;

import com.topnetwork.wallet.common.enums.discover.AppType;
import com.topnetwork.wallet.param.wallet.discover.*;
import com.topnetwork.wallet.result.wallet.discover.GetAllLabelResult;
import com.topnetwork.wallet.result.wallet.discover.GetLabelsResult;
import com.gitee.magic.framework.base.result.QueryResult;
import com.base.core.framework.sql.service.SqlBaseService;
import com.topnetwork.wallet.entity.AppDiscoverLabel;

import java.util.List;

public interface AppDiscoverLabelService extends SqlBaseService<AppDiscoverLabel,Long> {

    void addLabel(AddLabelParam param);

    void updateLabel(UpdateLabelParam param);

    QueryResult<List<GetLabelsResult>> getLabels(GetLabelsParam param);

    List<GetAllLabelResult> getAllLabel(GetAllLabelParam param);

    List<GetAllLabelResult> getByAppIdAndType(Long id, AppType dapp);
}
