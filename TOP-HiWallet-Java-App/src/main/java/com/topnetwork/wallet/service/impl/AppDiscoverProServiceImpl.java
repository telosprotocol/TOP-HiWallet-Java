package com.topnetwork.wallet.service.impl;

import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.common.enums.discover.AppType;
import com.topnetwork.wallet.common.enums.discover.BannerNewType;
import com.topnetwork.wallet.entity.AppDiscoverProI18n;
import com.topnetwork.wallet.param.wallet.discover.*;
import com.topnetwork.wallet.result.wallet.discover.GetAppDetailBaseResult;
import com.topnetwork.wallet.result.wallet.discover.ProI18nResult;
import com.topnetwork.wallet.service.AppDiscoverGroupI18nService;
import com.topnetwork.wallet.service.AppDiscoverProI18nService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import com.gitee.magic.framework.head.exception.BusinessException;
import com.base.core.framework.sql.service.impl.SqlBaseServiceImpl;
import com.topnetwork.wallet.dao.AppDiscoverProDao;
import com.topnetwork.wallet.entity.AppDiscoverPro;
import com.topnetwork.wallet.service.AppDiscoverProService;
import com.gitee.magic.jdbc.persistence.source.jdbc.sqlplus.conditions.query.QueryWrapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("appDiscoverProService")
@Transactional
public class AppDiscoverProServiceImpl extends SqlBaseServiceImpl<AppDiscoverPro, Long>
        implements AppDiscoverProService {

    @SuppressWarnings("unused")
    private AppDiscoverProDao appDiscoverProDao;

    public AppDiscoverProServiceImpl(@Qualifier("appDiscoverProDao") AppDiscoverProDao appDiscoverProDao) {
        super(appDiscoverProDao);
        this.appDiscoverProDao = appDiscoverProDao;
    }

    @Override
    public AppDiscoverPro getById(Long targetId) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("id", targetId);
        return get(appDiscoverProDao.queryForList(wrapper));
    }

    @Override
    public GetAppDetailBaseResult getProDetail(GetAppDetailBaseParam param) {

        AppDiscoverPro appDiscoverPro = getById(param.getId());
        if (appDiscoverPro == null) {
            throw new BusinessException(CodeRes.CODE_11007);
        }
        GetAppDetailBaseResult getAppDetailBaseResult = new GetAppDetailBaseResult();
        BeanUtils.copyProperties(appDiscoverPro, getAppDetailBaseResult);
        getAppDetailBaseResult.setType(AppType.PRO);
        return getAppDetailBaseResult;
    }


    @Override
    public void updateProApp(UpdateProAppParam param) {
        AppDiscoverPro appDiscoverPro = getById(param.getId());
        if (appDiscoverPro == null) {
            throw new BusinessException(CodeRes.CODE_11007);
        }
        appDiscoverPro.setTitle(param.getTitle());
        appDiscoverPro.setImageUrl(param.getImageUrl());
        appDiscoverPro.setAppShow(param.getAppShow());
        save(appDiscoverPro);
    }

    @Autowired
    private AppDiscoverProI18nService appDiscoverProI18nService;
    @Autowired
    private AppDiscoverGroupI18nService appDiscoverGroupI18nService;

    @Override
    public List<ProI18nResult> getProI18n(GetAppDetailBaseParam param) {
        List<ProI18nResult> results = new ArrayList<>();
        List<AppDiscoverProI18n> appDiscoverProI18nList = appDiscoverProI18nService.findByAppId(param.getId());
        for (AppDiscoverProI18n appDiscoverProI18n : appDiscoverProI18nList) {
            ProI18nResult result = new ProI18nResult();
            result.setGroups(appDiscoverGroupI18nService.findByI18nIdAndType(appDiscoverProI18n.getId(), AppType.PRO));
            result.setI18nId(appDiscoverProI18n.getId());
            result.setLanguage(appDiscoverProI18n.getLanguage());
            result.setSubTitle(appDiscoverProI18n.getSubTitle());
            result.setTitle(appDiscoverProI18n.getTitle());
            results.add(result);
        }

        return results;
    }

    @Override
    public AppDiscoverPro getByType(BannerNewType type) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("proType", type.toString());
        return get(appDiscoverProDao.queryForList(wrapper));
    }

    @Override
    public void editProI18n(EditProI18nParam param) {
        AppDiscoverPro appDiscoverPro = getById(param.getAppId());
        if (appDiscoverPro == null) {
            throw new BusinessException(CodeRes.CODE_20014);
        }
        List<ProI18nParam> i18n = param.getI18n();
        List<AppDiscoverProI18n> list = new ArrayList<>();
        for (ProI18nParam proI18nParam : i18n) {

            List<GroupI18nParam> groups = proI18nParam.getGroups();
            if (groups == null || groups.size() <= 0) {
                throw new BusinessException(CodeRes.CODE_20023);
            }
            AppDiscoverProI18n appDiscoverProI18n = new AppDiscoverProI18n();
            appDiscoverProI18n.setAppId(param.getAppId());
            appDiscoverProI18n.setLanguage(proI18nParam.getLanguage());
            appDiscoverProI18n.setSubTitle(proI18nParam.getSubTitle());
            appDiscoverProI18n.setTitle(proI18nParam.getTitle());
            appDiscoverProI18n.setCreatedDate(new Date());
            appDiscoverProI18n.setModifiedDate(new Date());
            if (proI18nParam.getI18nId() != null) {
                appDiscoverProI18n.setId(proI18nParam.getI18nId());
                list.add(appDiscoverProI18n);
                appDiscoverGroupI18nService.save(proI18nParam.getI18nId(), groups, AppType.PRO);
            } else {
                Long id = appDiscoverProI18nService.saveReturnId(appDiscoverProI18n);
                appDiscoverGroupI18nService.save(id, groups, AppType.PRO);
            }
        }
        appDiscoverProI18nService.saveBatch(list);
    }
}
