package com.topnetwork.wallet.service;

import com.topnetwork.wallet.common.enums.LanguageEnum;
import com.topnetwork.wallet.param.wallet.discover.AddGroupParam;
import com.topnetwork.wallet.param.wallet.discover.GetAllGroupParam;
import com.topnetwork.wallet.param.wallet.discover.GetGroupsParam;
import com.topnetwork.wallet.param.wallet.discover.UpdateGroupParam;
import com.topnetwork.wallet.result.wallet.discover.GetAllGroupResult;
import com.topnetwork.wallet.result.wallet.discover.GetGroupsResult;
import com.gitee.magic.framework.base.result.QueryResult;
import com.base.core.framework.sql.service.SqlBaseService;
import com.topnetwork.wallet.entity.AppDiscoverGroup;

import java.util.List;

public interface AppDiscoverGroupService extends SqlBaseService<AppDiscoverGroup, Long> {

    QueryResult<List<GetGroupsResult>> getGroups(GetGroupsParam param);

    void updateGroup(UpdateGroupParam param);

    void addGroup(AddGroupParam param);

    List<AppDiscoverGroup> findByApprovedLanguage(Boolean approved, LanguageEnum language);

    List<GetAllGroupResult> getAllGroup(GetAllGroupParam param);
}
