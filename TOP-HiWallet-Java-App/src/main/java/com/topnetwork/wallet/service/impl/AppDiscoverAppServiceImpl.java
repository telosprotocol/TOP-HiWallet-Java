package com.topnetwork.wallet.service.impl;

import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.common.enums.discover.AppType;
import com.topnetwork.wallet.entity.AppDiscoverAppI18n;
import com.topnetwork.wallet.param.wallet.discover.*;
import com.topnetwork.wallet.result.wallet.discover.AppI18nResult;
import com.topnetwork.wallet.result.wallet.discover.GetManAppDetailResult;
import com.topnetwork.wallet.result.wallet.discover.ProI18nGroupResult;
import com.topnetwork.wallet.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import com.gitee.magic.framework.head.exception.BusinessException;
import com.base.core.framework.sql.service.impl.SqlBaseServiceImpl;
import com.topnetwork.wallet.dao.AppDiscoverAppDao;
import com.topnetwork.wallet.entity.AppDiscoverApp;
import com.gitee.magic.jdbc.persistence.source.jdbc.sqlplus.conditions.query.QueryWrapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("appDiscoverAppService")
@Transactional
public class AppDiscoverAppServiceImpl extends SqlBaseServiceImpl<AppDiscoverApp, Long>
        implements AppDiscoverAppService {

    @SuppressWarnings("unused")
    private AppDiscoverAppDao appDiscoverAppDao;

    public AppDiscoverAppServiceImpl(@Qualifier("appDiscoverAppDao") AppDiscoverAppDao appDiscoverAppDao) {
        super(appDiscoverAppDao);
        this.appDiscoverAppDao = appDiscoverAppDao;
    }

    @Override
    public AppDiscoverApp getById(Long targetId) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("id", targetId);
        return get(appDiscoverAppDao.queryForList(wrapper));
    }

    @Autowired
    private AppDiscoverLabelService appDiscoverLabelService;
    @Autowired
    private AppDiscoverLabelApplicationService appDiscoverLabelApplicationService;

    @Override
    public GetManAppDetailResult getAppDetail(GetAppDetailBaseParam param) {
        GetManAppDetailResult getManAppDetailResult = new GetManAppDetailResult();
        AppDiscoverApp appDiscoverApp = getById(param.getId());
        if (appDiscoverApp == null) {
            throw new BusinessException(CodeRes.CODE_20014);
        }
        getManAppDetailResult.setLabels(appDiscoverLabelService.getByAppIdAndType(param.getId(), AppType.APP));
        getManAppDetailResult.setSubUrl(appDiscoverApp.getSubUrl());
        getManAppDetailResult.setUrl(appDiscoverApp.getUrl());
        getManAppDetailResult.setAppShow(appDiscoverApp.getAppShow());
        getManAppDetailResult.setId(appDiscoverApp.getId());
        getManAppDetailResult.setImageUrl(appDiscoverApp.getImageUrl());
        getManAppDetailResult.setTitle(appDiscoverApp.getTitle());
        getManAppDetailResult.setType(AppType.APP);
        return getManAppDetailResult;
    }

    @Override
    public void addApp(AddAppParam param) {
        AppDiscoverApp appDiscoverApp = new AppDiscoverApp();
        appDiscoverApp.setSubUrl(param.getSubUrl());
        appDiscoverApp.setUrl(param.getUrl());
        appDiscoverApp.setAppShow(param.getAppShow());
        appDiscoverApp.setImageUrl(param.getImageUrl());
        appDiscoverApp.setTitle(param.getTitle());
        Long appId = appDiscoverAppDao.getSnowflakeIdWorkerNextId();
        appDiscoverApp.setId(appId);
        appDiscoverAppDao.persist(appDiscoverApp);
        appDiscoverLabelApplicationService.saveLabel(appId, param.getLabels(), AppType.APP);
    }

    @Override
    public void updateApp(UpdateAppParam param) {
        AppDiscoverApp appDiscoverApp = getById(param.getDappId());
        if (appDiscoverApp == null) {
            throw new BusinessException(CodeRes.CODE_20014);
        }
        appDiscoverApp.setSubUrl(param.getSubUrl());
        appDiscoverApp.setUrl(param.getUrl());
        appDiscoverApp.setAppShow(param.getAppShow());
        appDiscoverApp.setImageUrl(param.getImageUrl());
        appDiscoverApp.setTitle(param.getTitle());
        appDiscoverApp.setModifiedDate(new Date());
        save(appDiscoverApp);
        appDiscoverLabelApplicationService.saveLabel(param.getDappId(), param.getLabels(), AppType.APP);

    }

    @Autowired
    private AppDiscoverAppI18nService appDiscoverAppI18nService;
    @Autowired
    private AppDiscoverGroupI18nService appDiscoverGroupI18nService;

    @Override
    public List<AppI18nResult> getAppI18n(GetAppDetailBaseParam param) {
        List<AppI18nResult> results = new ArrayList<>();
        List<AppDiscoverAppI18n> list = appDiscoverAppI18nService.findByAppId(param.getId());
        for (AppDiscoverAppI18n appDiscoverAppI18n : list) {
            AppI18nResult appI18nResult = new AppI18nResult();
            List<ProI18nGroupResult> byI18nIdAndType = appDiscoverGroupI18nService.findByI18nIdAndType(appDiscoverAppI18n.getId(), AppType.APP);
            appI18nResult.setGroups(byI18nIdAndType);
            appI18nResult.setDetail(appDiscoverAppI18n.getDetail());
            appI18nResult.setImageUrl(appDiscoverAppI18n.getImageUrl());
            appI18nResult.setI18nId(appDiscoverAppI18n.getId());
            appI18nResult.setLanguage(appDiscoverAppI18n.getLanguage());
            appI18nResult.setSubTitle(appDiscoverAppI18n.getSubTitle());
            appI18nResult.setTitle(appDiscoverAppI18n.getTitle());
            results.add(appI18nResult);
        }
        return results;
    }

    @Override
    public void editAppI18n(EditAppI18nParam param) {
        AppDiscoverApp appDiscoverApp = getById(param.getAppId());
        if (appDiscoverApp == null) {
            throw new BusinessException(CodeRes.CODE_20014);
        }
        List<AppI18nParam> i18n = param.getI18n();
        List<AppDiscoverAppI18n> list = new ArrayList<>();
        for (AppI18nParam appI18nParam : i18n) {

            List<GroupI18nParam> groups = appI18nParam.getGroups();
            if (groups == null || groups.size() <= 0) {
                throw new BusinessException(CodeRes.CODE_20023);
            }
            AppDiscoverAppI18n appDiscoverProI18n = new AppDiscoverAppI18n();
            appDiscoverProI18n.setAppId(param.getAppId());
            appDiscoverProI18n.setLanguage(appI18nParam.getLanguage());
            appDiscoverProI18n.setSubTitle(appI18nParam.getSubTitle());
            appDiscoverProI18n.setTitle(appI18nParam.getTitle());
            appDiscoverProI18n.setDetail(appI18nParam.getDetail());
            appDiscoverProI18n.setCreatedDate(new Date());
            appDiscoverProI18n.setModifiedDate(new Date());
            appDiscoverProI18n.setImageUrl(appI18nParam.getImageUrl());
            if (appI18nParam.getI18nId() != null) {
                appDiscoverProI18n.setId(appI18nParam.getI18nId());
                list.add(appDiscoverProI18n);
                appDiscoverGroupI18nService.save(appI18nParam.getI18nId(), groups, AppType.APP);
            } else {
                Long id = appDiscoverAppI18nService.saveReturnId(appDiscoverProI18n);
                appDiscoverGroupI18nService.save(id, groups, AppType.APP);
            }
        }
        appDiscoverAppI18nService.saveBatch(list);
    }
}
