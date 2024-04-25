package com.topnetwork.wallet.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.base.core.mvc.business.CommonBusiness;
import com.base.service.base.system.service.ConfigureService;
import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.common.enums.LanguageEnum;
import com.topnetwork.wallet.common.enums.discover.DappShowType;
import com.topnetwork.wallet.dao.AppDiscoverGroupDao;
import com.topnetwork.wallet.entity.AppDiscoverGroup;
import com.topnetwork.wallet.param.wallet.discover.AddGroupParam;
import com.topnetwork.wallet.param.wallet.discover.GetAllGroupParam;
import com.topnetwork.wallet.param.wallet.discover.GetGroupsParam;
import com.topnetwork.wallet.param.wallet.discover.UpdateGroupParam;
import com.topnetwork.wallet.result.wallet.discover.GetAllGroupResult;
import com.topnetwork.wallet.result.wallet.discover.GetGroupsResult;
import com.topnetwork.wallet.service.AppDiscoverGroupService;

import com.gitee.magic.framework.head.exception.BusinessException;
import com.gitee.magic.framework.base.result.QueryResult;
import com.base.core.framework.sql.service.impl.SqlBaseServiceImpl;
import com.gitee.magic.jdbc.persistence.source.jdbc.sqlplus.conditions.query.QueryWrapper;
import com.gitee.magic.core.json.JsonObject;

@Service("appDiscoverGroupService")
@Transactional
public class AppDiscoverGroupServiceImpl extends SqlBaseServiceImpl<AppDiscoverGroup, Long>
        implements AppDiscoverGroupService {

    @SuppressWarnings("unused")
    private AppDiscoverGroupDao appDiscoverGroupDao;

    public AppDiscoverGroupServiceImpl(@Qualifier("appDiscoverGroupDao") AppDiscoverGroupDao appDiscoverGroupDao) {
        super(appDiscoverGroupDao);
        this.appDiscoverGroupDao = appDiscoverGroupDao;
    }

    @Override
    public QueryResult<List<GetGroupsResult>> getGroups(GetGroupsParam param) {
        QueryResult<List<GetGroupsResult>> listQueryResult = new QueryResult<>();
        Map<String, Object> params = CommonBusiness.httpPageParam(param);
        QueryResult<List<AppDiscoverGroup>> queryResultSource = queryForPage(AppDiscoverGroup.class, "findGroupPage", params);
        if(ObjectUtils.isEmpty(queryResultSource)) {
        	return listQueryResult;
        }
        listQueryResult.setPageInfo(queryResultSource.getPageInfo());
        List<GetGroupsResult> list = new ArrayList<>();
        for (AppDiscoverGroup appDiscoverGroup : queryResultSource.getResult()) {
            GetGroupsResult getGroupsResult = new GetGroupsResult();
            BeanUtils.copyProperties(appDiscoverGroup, getGroupsResult);
            list.add(getGroupsResult);
        }
        listQueryResult.setResult(list);
        return listQueryResult;
    }

    @Override
    public void updateGroup(UpdateGroupParam param) {
        AppDiscoverGroup appDiscoverGroup = getById(param.getId());
        if (appDiscoverGroup == null) {
            throw new BusinessException(CodeRes.CODE_20004);
        }
        appDiscoverGroup.setGroupOrder(param.getGroupOrder());
        appDiscoverGroup.setGroupShow(param.getGroupShow());
        appDiscoverGroup.setLanguage(param.getLanguage());
        appDiscoverGroup.setName(param.getName());
        appDiscoverGroup.setShowType(param.getShowType());
        appDiscoverGroup.setModifiedDate(new Date());
        save(appDiscoverGroup);
    }

    private AppDiscoverGroup getById(Long groupId) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("id", groupId);
        return get(appDiscoverGroupDao.queryForList(wrapper));
    }

    @Override
    public void addGroup(AddGroupParam param) {
        AppDiscoverGroup appDiscoverGroup = new AppDiscoverGroup();
        appDiscoverGroup.setGroupOrder(param.getGroupOrder());
        appDiscoverGroup.setGroupShow(param.getGroupShow());
        appDiscoverGroup.setLanguage(param.getLanguage());
        appDiscoverGroup.setName(param.getName());
        appDiscoverGroup.setShowType(param.getShowType());
        save(appDiscoverGroup);
    }

    @Override
    public List<AppDiscoverGroup> findByApprovedLanguage(Boolean approved, LanguageEnum language) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("language", language.toString()).eq("groupShow", "true");
        wrapper.ne(approved, "showType", DappShowType.examine.toString());
        wrapper.ne(!approved, "showType", DappShowType.normal.toString());
        wrapper.orderByAsc("groupOrder");
        return appDiscoverGroupDao.queryForList(wrapper);
    }

    @Autowired
    private ConfigureService configureService;

    @Override
    public List<GetAllGroupResult> getAllGroup(GetAllGroupParam param) {
        QueryWrapper wrapper = new QueryWrapper();
        if (param.getLanguage() != null) {
            wrapper.eq("language", param.getLanguage().toString());
        }
        List<AppDiscoverGroup> list = appDiscoverGroupDao.queryForList(wrapper);
        List<GetAllGroupResult> results = new ArrayList<>();
        if (list == null || list.size() <= 0) {
            return results;
        }
        JsonObject jsonObject = (JsonObject) configureService.getValue("newDiscover");
        String recentlyGroupIds = String.valueOf(jsonObject.get("recentlyGroupIds"));
        for (AppDiscoverGroup appDiscoverGroup : list) {
            if (recentlyGroupIds.contains(appDiscoverGroup.getId().toString())) {
                continue;
            }
            GetAllGroupResult getAllGroupResult = new GetAllGroupResult();
            getAllGroupResult.setGroupId(appDiscoverGroup.getId());
            getAllGroupResult.setName(appDiscoverGroup.getName());
            getAllGroupResult.setLanguage(appDiscoverGroup.getLanguage());
            results.add(getAllGroupResult);
        }
        return results;
    }
}
