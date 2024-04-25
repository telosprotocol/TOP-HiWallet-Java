package com.topnetwork.wallet.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.base.service.base.system.service.ConfigureService;
import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.common.enums.LanguageEnum;
import com.topnetwork.wallet.common.enums.discover.AppType;
import com.topnetwork.wallet.common.enums.discover.BannerNewType;
import com.topnetwork.wallet.dao.HomeAppDao;
import com.topnetwork.wallet.entity.AppDiscoverApp;
import com.topnetwork.wallet.entity.AppDiscoverDapp;
import com.topnetwork.wallet.entity.AppDiscoverHtml;
import com.topnetwork.wallet.entity.AppDiscoverPro;
import com.topnetwork.wallet.entity.AppVersion;
import com.topnetwork.wallet.entity.DiscoverBase;
import com.topnetwork.wallet.entity.HomeApp;
import com.topnetwork.wallet.entity.SecretPlatform;
import com.topnetwork.wallet.param.wallet.home.AddAppParam;
import com.topnetwork.wallet.param.wallet.home.DelAppParam;
import com.topnetwork.wallet.param.wallet.home.GetAppListParam;
import com.topnetwork.wallet.param.wallet.home.GetSelectAppParam;
import com.topnetwork.wallet.param.wallet.home.UpdAppParam;
import com.topnetwork.wallet.result.wallet.discover.GetNewBannersResult;
import com.topnetwork.wallet.result.wallet.home.AppListResult;
import com.topnetwork.wallet.result.wallet.home.AppSelectListResult;
import com.topnetwork.wallet.service.AppDiscoverAppService;
import com.topnetwork.wallet.service.AppDiscoverBannerNewService;
import com.topnetwork.wallet.service.AppDiscoverDappService;
import com.topnetwork.wallet.service.AppDiscoverHtmlService;
import com.topnetwork.wallet.service.AppDiscoverProService;
import com.topnetwork.wallet.service.AppVersionService;
import com.topnetwork.wallet.service.HomeAppService;

import com.gitee.magic.framework.head.exception.BusinessException;
import com.base.core.framework.sql.service.impl.SqlBaseServiceImpl;
import com.gitee.magic.jdbc.persistence.source.jdbc.sqlplus.conditions.query.QueryWrapper;
import com.gitee.magic.jdbc.persistence.source.jdbc.sqlplus.conditions.update.DeleteWrapper;
import com.gitee.magic.core.json.JsonObject;

@Service("homeAppService")
@Transactional
public class HomeAppServiceImpl extends SqlBaseServiceImpl<HomeApp, Long>
        implements HomeAppService {

    @SuppressWarnings("unused")
    private HomeAppDao homeAppDao;

    @Autowired
    private AppDiscoverAppService appDiscoverAppService;
    @Autowired
    private AppDiscoverDappService appDiscoverDappService;
    @Autowired
    private AppDiscoverProService appDiscoverProService;
    @Autowired
    private AppDiscoverHtmlService appDiscoverHtmlService;
    @Autowired
    private AppVersionService appVersionService;
    @Autowired
    private ConfigureService configureService;
    @Autowired
    private AppDiscoverBannerNewService appDiscoverBannerNewService;

    public HomeAppServiceImpl(@Qualifier("homeAppDao") HomeAppDao homeAppDao) {
        super(homeAppDao);
        this.homeAppDao = homeAppDao;
    }

    @Override
    public List<AppListResult> getAppList() {
        List<HomeApp> dataList = getAllByOrder(null);
        List<AppListResult> results = new ArrayList<>();
        for (HomeApp homeApp : dataList) {
            AppListResult appListResult = new AppListResult();
            DiscoverBase discoverBase = null;
            if (AppType.DAPP.equals(homeApp.getAppType())) {
                discoverBase = appDiscoverDappService.getById(homeApp.getAppId());
            }
            if (AppType.APP.equals(homeApp.getAppType())) {
                discoverBase = appDiscoverAppService.getById(homeApp.getAppId());
            }
            if (AppType.HTML.equals(homeApp.getAppType())) {
                discoverBase = appDiscoverHtmlService.getById(homeApp.getAppId());
            }
            if (AppType.PRO.equals(homeApp.getAppType())) {
                discoverBase = appDiscoverProService.getById(homeApp.getAppId());
            }
            if (discoverBase == null) {
                continue;
            }
            appListResult.setShowType(homeApp.getShowType());
            appListResult.setAppId(homeApp.getAppId());
            appListResult.setAppName(discoverBase.getTitle());
            appListResult.setAppOrder(homeApp.getAppOrder());
            appListResult.setAppType(homeApp.getAppType());
            appListResult.setIcon(discoverBase.getImageUrl());
            appListResult.setId(homeApp.getId());
            results.add(appListResult);
        }
        return results;
    }

    @Override
    public void addApp(AddAppParam addAppParam) {

        check(addAppParam.getAppType(), addAppParam.getAppId(), null);
        HomeApp homeApp = new HomeApp();
        homeApp.setAppId(addAppParam.getAppId());
        homeApp.setAppOrder(addAppParam.getAppOrder());
        homeApp.setAppType(addAppParam.getAppType());
        homeApp.setCreatedDate(new Date());
        homeApp.setModifiedDate(new Date());
        homeApp.setShowType(addAppParam.getShowType());
        save(homeApp);
    }

    private void check(AppType appType, Long appId, Long recordId) {
        BannerNewType type = getType(appType);
        JsonObject jsonObject = (JsonObject) configureService.getValue("newDiscover");
        String stakingId = String.valueOf(jsonObject.get("stakingDappId"));
        if (appId.equals(Long.valueOf(stakingId))) {
            type = BannerNewType.TOPSTAKING;
        }
        GetNewBannersResult byIdAndType = appDiscoverBannerNewService.getByIdAndType(appId, type, LanguageEnum.EN, null, null);
        GetNewBannersResult byIdAndType2 = appDiscoverBannerNewService.getByIdAndType(appId, type, LanguageEnum.CN, null, null);
        if (byIdAndType == null || byIdAndType2 == null) {
            throw new BusinessException(CodeRes.CODE_14023);
        }
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("appId", appId).eq("appType", appType.toString());
        if (recordId == null) {
            List<HomeApp> list = homeAppDao.queryForList(wrapper);
            if (list != null && list.size() > 0) {
                throw new BusinessException(CodeRes.CODE_14023);
            }
        } else {
            wrapper.ne("id", recordId);
            List<HomeApp> list = homeAppDao.queryForList(wrapper);
            if (list != null && list.size() > 0) {
                throw new BusinessException(CodeRes.CODE_14023);
            }
        }
    }

    @Override
    public void updateApp(UpdAppParam param) {
        check(param.getAppType(), param.getAppId(), param.getId());
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("id", param.getId());
        HomeApp homeApp = get(homeAppDao.queryForList(wrapper));
        if (homeApp == null) {
            throw new BusinessException(CodeRes.CODE_14023);
        }
        homeApp.setModifiedDate(new Date());
        homeApp.setAppType(param.getAppType());
        homeApp.setAppOrder(param.getAppOrder());
        homeApp.setShowType(param.getShowType());
        homeApp.setAppId(param.getAppId());
        save(homeApp);
    }

    @Override
    public void delApp(DelAppParam delAppParam) {
        DeleteWrapper wrapper = new DeleteWrapper();
        wrapper.eq("id", delAppParam.getId());
        homeAppDao.executeUpdate(wrapper);
    }

    private List<HomeApp> getAllByOrder(Boolean approved) {
        String sql = "";
        if (approved == null) {
            sql = "SELECT * FROM wal_home_app ORDER BY appOrder asc";
        } else if (approved) {
            sql = "SELECT * FROM wal_home_app where showType!='examine' ORDER BY appOrder asc";
        } else {
            sql = "SELECT * FROM wal_home_app where showType!='normal' ORDER BY appOrder asc";
        }

        return homeAppDao.queryForList(sql, null);
    }

    @Override
    public List<AppSelectListResult> getSelectAppList(GetSelectAppParam param) {
        List<DiscoverBase> list = new ArrayList<>();
        if (AppType.DAPP.equals(param.getAppType())) {
            List<AppDiscoverDapp> all = appDiscoverDappService.queryForAll();
            list.addAll(all);
        }
        if (AppType.APP.equals(param.getAppType())) {
            List<AppDiscoverApp> all = appDiscoverAppService.queryForAll();
            list.addAll(all);
        }
        if (AppType.HTML.equals(param.getAppType())) {
            List<AppDiscoverHtml> all = appDiscoverHtmlService.queryForAll();
            list.addAll(all);
        }
        if (AppType.PRO.equals(param.getAppType())) {
            List<AppDiscoverPro> all = appDiscoverProService.queryForAll();
            list.addAll(all);
        }
        List<AppSelectListResult> listResults = new ArrayList<>();
        for (DiscoverBase discoverBase : list) {
            AppSelectListResult appSelectListResult = new AppSelectListResult();
            appSelectListResult.setAppId(discoverBase.getId());
            appSelectListResult.setAppName(discoverBase.getTitle());
            appSelectListResult.setAppType(param.getAppType());
            appSelectListResult.setIcon(discoverBase.getImageUrl());
            listResults.add(appSelectListResult);
        }
        return listResults;
    }

    @Override
    public List<GetNewBannersResult> getDiscoverApp(GetAppListParam param, SecretPlatform secretPlatform, String requestIP) {

        AppVersion appVersion = appVersionService.getVersion(param.getVersion(), param.getRegion(), secretPlatform);
        if (appVersion == null) {
            throw new BusinessException(CodeRes.CODE_14019);
        }

        List<HomeApp> dataList = getAllByOrder(appVersion.getApproved());
        List<GetNewBannersResult> list = new ArrayList<>();
        JsonObject jsonObject = (JsonObject) configureService.getValue("newDiscover");
        for (HomeApp homeApp : dataList) {
            BannerNewType type = getType(homeApp.getAppType());
            String stakingId = String.valueOf(jsonObject.get("stakingDappId"));
            if (homeApp.getAppId().equals(Long.valueOf(stakingId))) {
                type = BannerNewType.TOPSTAKING;
            }
            if (type == null) {
                continue;
            }
            GetNewBannersResult byIdAndType = appDiscoverBannerNewService.getByIdAndType(homeApp.getAppId(), type, param.getLanguage(), null, null);
            if (byIdAndType != null) {
                list.add(byIdAndType);
            }
        }

        appDiscoverBannerNewService.filterDapp(list, appVersion, jsonObject, requestIP);
        return list;
    }

    private BannerNewType getType(AppType appType) {
        BannerNewType type = null;
        if (AppType.DAPP.equals(appType)) {
            type = BannerNewType.DECENTRALIAPP;
        }
        if (AppType.APP.equals(appType)) {
            type = BannerNewType.APP;
        }
        if (AppType.HTML.equals(appType)) {
            type = BannerNewType.HTMLDAPP;
        }
        if (AppType.PRO.equals(appType)) {
            type = BannerNewType.CENTRALIAPP;
        }
        return type;
    }
}
