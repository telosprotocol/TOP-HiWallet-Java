package com.topnetwork.wallet.service.impl;

import com.base.core.mvc.business.CommonBusiness;
import com.topnetwork.system.entity.User;
import com.topnetwork.wallet.param.wallet.ResourceAddParam;
import com.topnetwork.wallet.param.wallet.ResourceListParam;
import com.topnetwork.wallet.result.wallet.GetResourceResult;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.gitee.magic.framework.base.result.QueryResult;
import com.base.core.framework.sql.service.impl.SqlBaseServiceImpl;
import com.topnetwork.wallet.dao.AppResourceDao;
import com.topnetwork.wallet.entity.AppResource;
import com.topnetwork.wallet.service.AppResourceService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("appResourceService")
@Transactional
public class AppResourceServiceImpl extends SqlBaseServiceImpl<AppResource, Long>
        implements AppResourceService {

    @SuppressWarnings("unused")
    private AppResourceDao appResourceDao;

    public AppResourceServiceImpl(@Qualifier("appResourceDao") AppResourceDao appResourceDao) {
        super(appResourceDao);
        this.appResourceDao = appResourceDao;
    }

    @Override
    public QueryResult<List<GetResourceResult>> findResourceList(ResourceListParam param) {
        Map<String, Object> params = CommonBusiness.httpPageParam(param);
        QueryResult<List<AppResource>> queryResult = queryForPage(AppResource.class, "findResourceList", params);

        QueryResult<List<GetResourceResult>> listQueryResult = new QueryResult<>();
        if(ObjectUtils.isEmpty(queryResult)) {
        	return listQueryResult;
        }
        listQueryResult.setPageInfo(queryResult.getPageInfo());
        List<GetResourceResult> list = new ArrayList<>();
        for (AppResource appResource : queryResult.getResult()) {
            GetResourceResult getResourceResult = new GetResourceResult();
            getResourceResult.setCreateTime(appResource.getCreateTime().getTime());
            getResourceResult.setId(appResource.getId());
            getResourceResult.setName(appResource.getName());
            getResourceResult.setRegion(appResource.getRegion());
            getResourceResult.setUrl(appResource.getUrl());
            list.add(getResourceResult);
        }
        listQueryResult.setResult(list);
        return listQueryResult;
    }

    @Override
    public void addResource(ResourceAddParam param, User user) {
        AppResource appResource = new AppResource();
        appResource.setCreateId(user.getId());
        appResource.setCreateTime(new Date());
        appResource.setName(param.getName());
        appResource.setRegion(param.getRegion());
        appResource.setUrl(param.getUrl());
        save(appResource);
    }
}
