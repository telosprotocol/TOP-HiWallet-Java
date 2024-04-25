package com.topnetwork.wallet.service;

import com.topnetwork.system.entity.User;
import com.topnetwork.wallet.param.wallet.ResourceAddParam;
import com.topnetwork.wallet.param.wallet.ResourceListParam;
import com.topnetwork.wallet.result.wallet.GetResourceResult;
import com.gitee.magic.framework.base.result.QueryResult;
import com.base.core.framework.sql.service.SqlBaseService;
import com.topnetwork.wallet.entity.AppResource;

import java.util.List;

public interface AppResourceService extends SqlBaseService<AppResource,Long> {

    QueryResult<List<GetResourceResult>> findResourceList(ResourceListParam param);

    void addResource(ResourceAddParam param, User user);
}
