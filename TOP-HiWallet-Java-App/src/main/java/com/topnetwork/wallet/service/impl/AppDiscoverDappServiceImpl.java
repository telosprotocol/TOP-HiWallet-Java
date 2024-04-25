package com.topnetwork.wallet.service.impl;

import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.common.enums.PlatformEnum;
import com.topnetwork.wallet.common.enums.discover.AppType;
import com.topnetwork.wallet.entity.AppDiscoverDappI18n;
import com.topnetwork.wallet.param.wallet.discover.*;
import com.topnetwork.wallet.result.wallet.discover.DappI18nResult;
import com.topnetwork.wallet.result.wallet.discover.GetDappDetailResult;
import com.topnetwork.wallet.result.wallet.discover.ProI18nGroupResult;
import com.topnetwork.wallet.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import com.gitee.magic.framework.head.exception.BusinessException;
import com.base.core.framework.sql.service.impl.SqlBaseServiceImpl;
import com.topnetwork.wallet.dao.AppDiscoverDappDao;
import com.topnetwork.wallet.entity.AppDiscoverDapp;
import com.gitee.magic.jdbc.persistence.source.jdbc.sqlplus.conditions.query.QueryWrapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("appDiscoverDappService")
@Transactional
public class AppDiscoverDappServiceImpl extends SqlBaseServiceImpl<AppDiscoverDapp, Long>
        implements AppDiscoverDappService {

    @SuppressWarnings("unused")
    private AppDiscoverDappDao appDiscoverDappDao;

    public AppDiscoverDappServiceImpl(@Qualifier("appDiscoverDappDao") AppDiscoverDappDao appDiscoverDappDao) {
        super(appDiscoverDappDao);
        this.appDiscoverDappDao = appDiscoverDappDao;
    }

    @Override
    public AppDiscoverDapp getById(Long targetId) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("id", targetId);
        return get(appDiscoverDappDao.queryForList(wrapper));
    }

    @Autowired
    private AppDiscoverLabelService appDiscoverLabelService;

    @Override
    public GetDappDetailResult getDappDetail(GetAppDetailBaseParam param) {
        AppDiscoverDapp appDiscoverDapp = getById(param.getId());
        if (appDiscoverDapp == null) {
            throw new BusinessException(CodeRes.CODE_20014);
        }
        GetDappDetailResult getAppDetailBaseResult = new GetDappDetailResult();
        BeanUtils.copyProperties(appDiscoverDapp, getAppDetailBaseResult);
        getAppDetailBaseResult.setType(AppType.DAPP);
        getAppDetailBaseResult.setLabels(appDiscoverLabelService.getByAppIdAndType(param.getId(), AppType.DAPP));
        return getAppDetailBaseResult;
    }

    @Autowired
    private AppDiscoverLabelApplicationService appDiscoverLabelApplicationService;

    @Override
    public void addDapp(AddDappParam param) {
        AppDiscoverDapp appDiscoverDapp = new AppDiscoverDapp();
        appDiscoverDapp.setChainType(param.getChainType());
        appDiscoverDapp.setUrl(param.getUrl());
        appDiscoverDapp.setAppShow(param.getAppShow());
        appDiscoverDapp.setImageUrl(param.getImageUrl());
        appDiscoverDapp.setTitle(param.getTitle());
        String[] pf = new String[] {PlatformEnum.IOS.name(),PlatformEnum.ANDROID.name()};
        appDiscoverDapp.setPlatformSupports(pf);
        Long appId = appDiscoverDappDao.getSnowflakeIdWorkerNextId();
        appDiscoverDapp.setId(appId);
        appDiscoverDappDao.persist(appDiscoverDapp);
        appDiscoverLabelApplicationService.saveLabel(appId, param.getLabels(), AppType.DAPP);
    }

    @Override
    public void updateDapp(UpdateDappParam param) {

        AppDiscoverDapp appDiscoverDapp = findById(param.getDappId());
        if (appDiscoverDapp == null) {
            throw new BusinessException(CodeRes.CODE_20014);
        }
        appDiscoverDapp.setChainType(param.getChainType());
        appDiscoverDapp.setUrl(param.getUrl());
        appDiscoverDapp.setAppShow(param.getAppShow());
        appDiscoverDapp.setImageUrl(param.getImageUrl());
        appDiscoverDapp.setTitle(param.getTitle());
        appDiscoverDapp.setModifiedDate(new Date());
        save(appDiscoverDapp);
        appDiscoverLabelApplicationService.saveLabel(param.getDappId(), param.getLabels(), AppType.DAPP);
    }

    private AppDiscoverDapp findById(Long dappId) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("id", dappId);
        return get(appDiscoverDappDao.queryForList(wrapper));
    }

    @Autowired
    private AppDiscoverDappI18nService appDiscoverDappI18nService;
    @Autowired
    private AppDiscoverGroupI18nService appDiscoverGroupI18nService;

    @Override
    public List<DappI18nResult> getDappI18n(GetAppDetailBaseParam param) {
        List<DappI18nResult> results = new ArrayList<>();
        List<AppDiscoverDappI18n> list = appDiscoverDappI18nService.findByAppId(param.getId());
        for (AppDiscoverDappI18n appDiscoverDappI18n : list) {
            DappI18nResult dappI18nResult = new DappI18nResult();
            List<ProI18nGroupResult> byI18nIdAndType = appDiscoverGroupI18nService.findByI18nIdAndType(appDiscoverDappI18n.getId(), AppType.DAPP);
            dappI18nResult.setGroups(byI18nIdAndType);
            dappI18nResult.setDesc(appDiscoverDappI18n.getDescription());
            dappI18nResult.setDetail(appDiscoverDappI18n.getDetail());
            dappI18nResult.setImageUrl(appDiscoverDappI18n.getImageUrl());
            dappI18nResult.setI18nId(appDiscoverDappI18n.getId());
            dappI18nResult.setLanguage(appDiscoverDappI18n.getLanguage());
            dappI18nResult.setSubTitle(appDiscoverDappI18n.getSubTitle());
            dappI18nResult.setTitle(appDiscoverDappI18n.getTitle());
            results.add(dappI18nResult);
        }
        return results;
    }

    @Override
    public void editDappI18n(EditDappI18nParam param) {
        AppDiscoverDapp appDiscoverDapp = getById(param.getAppId());
        if (appDiscoverDapp == null) {
            throw new BusinessException(CodeRes.CODE_20014);
        }
        List<DappI18nParam> i18n = param.getI18n();
        List<AppDiscoverDappI18n> list = new ArrayList<>();
        for (DappI18nParam proI18nParam : i18n) {

            List<GroupI18nParam> groups = proI18nParam.getGroups();
            if (groups == null || groups.size() <= 0) {
                throw new BusinessException(CodeRes.CODE_20023);
            }
            AppDiscoverDappI18n appDiscoverProI18n = new AppDiscoverDappI18n();
            appDiscoverProI18n.setAppId(param.getAppId());
            appDiscoverProI18n.setLanguage(proI18nParam.getLanguage());
            appDiscoverProI18n.setSubTitle(proI18nParam.getSubTitle());
            appDiscoverProI18n.setTitle(proI18nParam.getTitle());
            appDiscoverProI18n.setCreatedDate(new Date());
            appDiscoverProI18n.setModifiedDate(new Date());
            appDiscoverProI18n.setDescription(proI18nParam.getDesc());
            appDiscoverProI18n.setDetail(proI18nParam.getDetail());
            appDiscoverProI18n.setImageUrl(proI18nParam.getImageUrl());
            if (proI18nParam.getI18nId() != null) {
                appDiscoverProI18n.setId(proI18nParam.getI18nId());
                list.add(appDiscoverProI18n);
                appDiscoverGroupI18nService.save(proI18nParam.getI18nId(), groups, AppType.DAPP);
            } else {
                Long id = appDiscoverDappI18nService.saveReturnId(appDiscoverProI18n);
                appDiscoverGroupI18nService.save(id, groups, AppType.DAPP);
            }
        }
        appDiscoverDappI18nService.saveBatch(list);
    }
}
