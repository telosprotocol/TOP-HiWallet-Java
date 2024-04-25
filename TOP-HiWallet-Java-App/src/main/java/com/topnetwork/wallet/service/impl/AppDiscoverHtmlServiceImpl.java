package com.topnetwork.wallet.service.impl;

import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.common.enums.discover.AppType;
import com.topnetwork.wallet.entity.AppDiscoverHtmlI18n;
import com.topnetwork.wallet.param.wallet.discover.*;
import com.topnetwork.wallet.result.wallet.discover.GetHtmlDetailResult;
import com.topnetwork.wallet.result.wallet.discover.HtmlI18nResult;
import com.topnetwork.wallet.result.wallet.discover.ProI18nGroupResult;
import com.topnetwork.wallet.service.AppDiscoverGroupI18nService;
import com.topnetwork.wallet.service.AppDiscoverHtmlI18nService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import com.gitee.magic.framework.head.exception.BusinessException;
import com.base.core.framework.sql.service.impl.SqlBaseServiceImpl;
import com.topnetwork.wallet.dao.AppDiscoverHtmlDao;
import com.topnetwork.wallet.entity.AppDiscoverHtml;
import com.topnetwork.wallet.service.AppDiscoverHtmlService;
import com.gitee.magic.jdbc.persistence.source.jdbc.sqlplus.conditions.query.QueryWrapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("appDiscoverHtmlService")
@Transactional
public class AppDiscoverHtmlServiceImpl extends SqlBaseServiceImpl<AppDiscoverHtml, Long>
        implements AppDiscoverHtmlService {

    @SuppressWarnings("unused")
    private AppDiscoverHtmlDao appDiscoverHtmlDao;

    public AppDiscoverHtmlServiceImpl(@Qualifier("appDiscoverHtmlDao") AppDiscoverHtmlDao appDiscoverHtmlDao) {
        super(appDiscoverHtmlDao);
        this.appDiscoverHtmlDao = appDiscoverHtmlDao;
    }

    @Override
    public AppDiscoverHtml getById(Long targetId) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("id", targetId);
        return get(appDiscoverHtmlDao.queryForList(wrapper));
    }

    @Override
    public GetHtmlDetailResult getHtmlDetail(GetAppDetailBaseParam param) {
        AppDiscoverHtml appDiscoverHtml = getById(param.getId());
        if (appDiscoverHtml == null) {
            throw new BusinessException(CodeRes.CODE_20014);
        }
        GetHtmlDetailResult getHtmlDetailResult = new GetHtmlDetailResult();
        getHtmlDetailResult.setUrl(appDiscoverHtml.getUrl());
        getHtmlDetailResult.setAppShow(appDiscoverHtml.getAppShow());
        getHtmlDetailResult.setId(appDiscoverHtml.getId());
        getHtmlDetailResult.setImageUrl(appDiscoverHtml.getImageUrl());
        getHtmlDetailResult.setTitle(appDiscoverHtml.getTitle());
        getHtmlDetailResult.setType(AppType.HTML);
        return getHtmlDetailResult;
    }

    @Override
    public void addHtml(AddHtmlParam param) {
        AppDiscoverHtml appDiscoverHtml = new AppDiscoverHtml();
        appDiscoverHtml.setUrl(param.getUrl());
        appDiscoverHtml.setAppShow(param.getAppShow());
        appDiscoverHtml.setImageUrl(param.getImageUrl());
        appDiscoverHtml.setTitle(param.getTitle());
        save(appDiscoverHtml);
    }

    @Override
    public void updateHtml(UpdateHtmlParam param) {
        AppDiscoverHtml appDiscoverHtml = getById(param.getDappId());
        if (appDiscoverHtml == null) {
            throw new BusinessException(CodeRes.CODE_20014);
        }
        appDiscoverHtml.setUrl(param.getUrl());
        appDiscoverHtml.setAppShow(param.getAppShow());
        appDiscoverHtml.setImageUrl(param.getImageUrl());
        appDiscoverHtml.setTitle(param.getTitle());
        appDiscoverHtml.setModifiedDate(new Date());
        save(appDiscoverHtml);
    }

    @Autowired
    private AppDiscoverHtmlI18nService appDiscoverHtmlI18nService;
    @Autowired
    private AppDiscoverGroupI18nService appDiscoverGroupI18nService;

    @Override
    public List<HtmlI18nResult> getHtmlI18n(GetAppDetailBaseParam param) {
        List<HtmlI18nResult> results = new ArrayList<>();
        List<AppDiscoverHtmlI18n> list = appDiscoverHtmlI18nService.findByAppId(param.getId());
        for (AppDiscoverHtmlI18n appDiscoverHtmlI18n : list) {
            HtmlI18nResult htmlI18nResult = new HtmlI18nResult();
            List<ProI18nGroupResult> byI18nIdAndType = appDiscoverGroupI18nService.findByI18nIdAndType(appDiscoverHtmlI18n.getId(), AppType.HTML);
            htmlI18nResult.setGroups(byI18nIdAndType);
            htmlI18nResult.setDesc(appDiscoverHtmlI18n.getDetail());
            htmlI18nResult.setImageUrl(appDiscoverHtmlI18n.getImageUrl());
            htmlI18nResult.setI18nId(appDiscoverHtmlI18n.getId());
            htmlI18nResult.setLanguage(appDiscoverHtmlI18n.getLanguage());
            htmlI18nResult.setSubTitle(appDiscoverHtmlI18n.getSubTitle());
            htmlI18nResult.setTitle(appDiscoverHtmlI18n.getTitle());
            htmlI18nResult.setDetail(appDiscoverHtmlI18n.getDetail());
            results.add(htmlI18nResult);
        }
        return results;
    }

    @Override
    public void editHtmlI18n(EditHtmlI18nParam param) {
        AppDiscoverHtml appDiscoverHtml = getById(param.getAppId());
        if (appDiscoverHtml == null) {
            throw new BusinessException(CodeRes.CODE_20014);
        }
        List<HtmlI18nParam> i18n = param.getI18n();
        List<AppDiscoverHtmlI18n> list = new ArrayList<>();
        for (HtmlI18nParam htmlI18nParam : i18n) {

            List<GroupI18nParam> groups = htmlI18nParam.getGroups();
            if (groups == null || groups.size() <= 0) {
                throw new BusinessException(CodeRes.CODE_20023);
            }
            AppDiscoverHtmlI18n appDiscoverProI18n = new AppDiscoverHtmlI18n();
            appDiscoverProI18n.setAppId(param.getAppId());
            appDiscoverProI18n.setLanguage(htmlI18nParam.getLanguage());
            appDiscoverProI18n.setSubTitle(htmlI18nParam.getSubTitle());
            appDiscoverProI18n.setTitle(htmlI18nParam.getTitle());
            appDiscoverProI18n.setDetail(htmlI18nParam.getDetail());
            appDiscoverProI18n.setCreatedDate(new Date());
            appDiscoverProI18n.setModifiedDate(new Date());
            appDiscoverProI18n.setImageUrl(htmlI18nParam.getImageUrl());
            appDiscoverProI18n.setDescription(htmlI18nParam.getDesc());
            appDiscoverProI18n.setImageUrl(htmlI18nParam.getImageUrl());
            if (htmlI18nParam.getI18nId() != null) {
                appDiscoverProI18n.setId(htmlI18nParam.getI18nId());
                list.add(appDiscoverProI18n);
                appDiscoverGroupI18nService.save(htmlI18nParam.getI18nId(), groups, AppType.HTML);
            } else {
                Long id = appDiscoverHtmlI18nService.saveReturnId(appDiscoverProI18n);
                appDiscoverGroupI18nService.save(id, groups, AppType.HTML);
            }
        }
        appDiscoverHtmlI18nService.saveBatch(list);
    }
}
