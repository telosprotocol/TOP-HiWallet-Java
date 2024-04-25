package com.topnetwork.wallet.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.common.bean.APPSelectModel;
import com.base.core.mvc.business.CommonBusiness;
import com.common.utils.RedisUtils;
import com.base.service.base.system.service.ConfigureService;
import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.common.enums.LanguageEnum;
import com.topnetwork.wallet.common.enums.PlatformEnum;
import com.topnetwork.wallet.common.enums.discover.AppStyleType;
import com.topnetwork.wallet.common.enums.discover.AppType;
import com.topnetwork.wallet.common.enums.discover.BannerNewType;
import com.topnetwork.wallet.common.enums.discover.DappShowType;
import com.topnetwork.wallet.dao.AppDiscoverBannerNewDao;
import com.topnetwork.wallet.entity.AppDiscoverApp;
import com.topnetwork.wallet.entity.AppDiscoverAppI18n;
import com.topnetwork.wallet.entity.AppDiscoverBannerNew;
import com.topnetwork.wallet.entity.AppDiscoverBannerNewI18n;
import com.topnetwork.wallet.entity.AppDiscoverDapp;
import com.topnetwork.wallet.entity.AppDiscoverDappI18n;
import com.topnetwork.wallet.entity.AppDiscoverGroup;
import com.topnetwork.wallet.entity.AppDiscoverGroupI18n;
import com.topnetwork.wallet.entity.AppDiscoverHtml;
import com.topnetwork.wallet.entity.AppDiscoverHtmlI18n;
import com.topnetwork.wallet.entity.AppDiscoverPro;
import com.topnetwork.wallet.entity.AppDiscoverProI18n;
import com.topnetwork.wallet.entity.AppUser;
import com.topnetwork.wallet.entity.AppVersion;
import com.topnetwork.wallet.entity.SecretPlatform;
import com.topnetwork.wallet.param.wallet.discover.AddNewUsedHistoryParam;
import com.topnetwork.wallet.param.wallet.discover.BannerI18nParam;
import com.topnetwork.wallet.param.wallet.discover.BannerNewAddParam;
import com.topnetwork.wallet.param.wallet.discover.BannerNewUpdParam;
import com.topnetwork.wallet.param.wallet.discover.EditBannerI18nParam;
import com.topnetwork.wallet.param.wallet.discover.GetAppDetailParam;
import com.topnetwork.wallet.param.wallet.discover.GetAppListAllParam;
import com.topnetwork.wallet.param.wallet.discover.GetAppListParam;
import com.topnetwork.wallet.param.wallet.discover.GetBannerDetailParam;
import com.topnetwork.wallet.param.wallet.discover.GetBannerListParam;
import com.topnetwork.wallet.param.wallet.discover.GetBannersV4Param;
import com.topnetwork.wallet.param.wallet.discover.RecommendPageNewParam;
import com.topnetwork.wallet.result.wallet.discover.AppInfoResult;
import com.topnetwork.wallet.result.wallet.discover.BannerI18nResult;
import com.topnetwork.wallet.result.wallet.discover.GetAppDetailResult;
import com.topnetwork.wallet.result.wallet.discover.GetAppListResult;
import com.topnetwork.wallet.result.wallet.discover.GetBannerDetailResult;
import com.topnetwork.wallet.result.wallet.discover.GetBannerListResult;
import com.topnetwork.wallet.result.wallet.discover.GetNewBannersResult;
import com.topnetwork.wallet.result.wallet.discover.RecommendNewResult;
import com.topnetwork.wallet.service.AppDiscoverAppI18nService;
import com.topnetwork.wallet.service.AppDiscoverAppService;
import com.topnetwork.wallet.service.AppDiscoverBannerNewI18nService;
import com.topnetwork.wallet.service.AppDiscoverBannerNewService;
import com.topnetwork.wallet.service.AppDiscoverDappI18nService;
import com.topnetwork.wallet.service.AppDiscoverDappService;
import com.topnetwork.wallet.service.AppDiscoverGroupI18nService;
import com.topnetwork.wallet.service.AppDiscoverGroupService;
import com.topnetwork.wallet.service.AppDiscoverHtmlI18nService;
import com.topnetwork.wallet.service.AppDiscoverHtmlService;
import com.topnetwork.wallet.service.AppDiscoverLabelApplicationService;
import com.topnetwork.wallet.service.AppDiscoverProI18nService;
import com.topnetwork.wallet.service.AppDiscoverProService;
import com.topnetwork.wallet.service.AppUserService;
import com.topnetwork.wallet.service.AppVersionService;
import com.topnetwork.wyre.entity.WyreCountry;
import com.topnetwork.wyre.service.WyreCountryService;

import com.gitee.magic.framework.head.exception.BusinessException;
import com.gitee.magic.framework.base.result.PageResponse;
import com.gitee.magic.framework.base.result.QueryResult;
import com.base.core.framework.sql.service.impl.SqlBaseServiceImpl;
import com.gitee.magic.jdbc.persistence.source.jdbc.sqlplus.conditions.query.QueryWrapper;
import com.gitee.magic.core.json.JsonArray;
import com.gitee.magic.core.json.JsonObject;

@Service("appDiscoverBannerNewService")
@Transactional
public class AppDiscoverBannerNewServiceImpl extends SqlBaseServiceImpl<AppDiscoverBannerNew, Long>
        implements AppDiscoverBannerNewService {

    @SuppressWarnings("unused")
    private AppDiscoverBannerNewDao appDiscoverBannerNewDao;

    public AppDiscoverBannerNewServiceImpl(@Qualifier("appDiscoverBannerNewDao") AppDiscoverBannerNewDao appDiscoverBannerNewDao) {
        super(appDiscoverBannerNewDao);
        this.appDiscoverBannerNewDao = appDiscoverBannerNewDao;
    }

    @Autowired
    private AppVersionService appVersionService;
    @Autowired
    private AppDiscoverBannerNewI18nService appDiscoverBannerNewI18nService;
    @Autowired
    private AppDiscoverDappService appDiscoverDappService;
    @Autowired
    private AppDiscoverDappI18nService appDiscoverDappI18nService;
    @Autowired
    private AppDiscoverHtmlService appDiscoverHtmlService;
    @Autowired
    private AppDiscoverHtmlI18nService appDiscoverHtmlI18nService;
    @Autowired
    private AppDiscoverAppService appDiscoverAppService;
    @Autowired
    private AppDiscoverAppI18nService appDiscoverAppI18nService;
    @Autowired
    private AppDiscoverProService appDiscoverProService;
    @Autowired
    private AppDiscoverProI18nService appDiscoverProI18nService;
    @Autowired
    private AppDiscoverLabelApplicationService appDiscoverLabelApplicationService;
    @Autowired
    private ConfigureService configureService;
    @Autowired
    private AppUserService appUserService;

    @Override
    public List<LanguageEnum> getLanguage() {
        JsonArray jsonArray = (JsonArray) configureService.getValue("language");
        List<LanguageEnum> languageEnums = new ArrayList<>();
        for (Object o : jsonArray) {
            try {
                String languageStr = (String) o;
                languageEnums.add(LanguageEnum.valueOf(languageStr));
            } catch (Exception e) {
                throw new BusinessException("10000-配置有误");
            }
        }
        return languageEnums;
    }

    @Override
    public List<GetNewBannersResult> getBanners(GetBannersV4Param param, SecretPlatform secretPlatform) {
        AppVersion appVersion = appVersionService.getVersion(param.getVersion(), param.getRegion(), secretPlatform);
        if (appVersion == null) {
            throw new BusinessException(CodeRes.CODE_14019);
        }
        List<GetNewBannersResult> results = new ArrayList<>();
        QueryWrapper queryWrapper = new QueryWrapper();

        queryWrapper.eq("bannerShow", "true");
        queryWrapper.ne(appVersion.getApproved(), "showType", DappShowType.examine.toString());
        queryWrapper.ne(!appVersion.getApproved(), "showType", DappShowType.normal.toString());
        queryWrapper.orderByDesc("id");
        List<AppDiscoverBannerNew> list = appDiscoverBannerNewDao.queryForList(queryWrapper);
        if (list == null || list.size() == 0) {
            return results;
        }
        AppUser userByUid = appUserService.getUserByUid(param.getUid());
        Long inviteBannerId = null;

        if (userByUid == null) {
            //过滤掉邀请banner
            JsonObject jsonObject = (JsonObject) configureService.getValue("newDiscover");
            inviteBannerId = Long.valueOf(jsonObject.get("inviteBannerId").toString());
        }

        for (AppDiscoverBannerNew appDiscoverBannerNew : list) {

            if (inviteBannerId != null && inviteBannerId.equals(appDiscoverBannerNew.getId())) {
                //过滤掉邀请banner
                continue;
            }

            AppDiscoverBannerNewI18n appDiscoverBannerNewI18n = appDiscoverBannerNewI18nService.getByBannerIdAndLanguage(appDiscoverBannerNew.getId(), param.getLanguage());
            if (appDiscoverBannerNewI18n == null) {
                continue;
            }
            GetNewBannersResult byIdAndType = getByIdAndType(appDiscoverBannerNew.getTargetId(), appDiscoverBannerNew.getBannerType(), param.getLanguage(), appDiscoverBannerNewI18n, appDiscoverBannerNew);
            if (byIdAndType != null) {
                results.add(byIdAndType);
            }

        }
        return results;
    }

    @Autowired
    private AppDiscoverGroupService appDiscoverGroupService;
    @Autowired
    private RedisTemplate<String, GetNewBannersResult> redisTemplate;
    @Autowired
    private AppDiscoverGroupI18nService appDiscoverGroupI18nService;

    @Override
    public List<RecommendNewResult> getRecommendList(GetBannersV4Param param, SecretPlatform secretPlatform, String ip) {

        AppVersion appVersion = appVersionService.getVersion(param.getVersion(), param.getRegion(), secretPlatform);
        if (appVersion == null) {
            throw new BusinessException(CodeRes.CODE_14019);
        }
        List<RecommendNewResult> results = new ArrayList<>();
        List<AppDiscoverGroup> appDiscoverGroupList = appDiscoverGroupService.findByApprovedLanguage(appVersion.getApproved(), param.getLanguage());
        if (appDiscoverGroupList == null || appDiscoverGroupList.size() == 0) {
            return results;
        }

        JsonObject jsonObject = (JsonObject) configureService.getValue("newDiscover");
        String recentlyGroupIds = jsonObject.get("recentlyGroupIds").toString();
        Integer recentlyShowNum = Integer.valueOf(jsonObject.get("recentlyShowNum").toString());
        for (AppDiscoverGroup appDiscoverGroup : appDiscoverGroupList) {
            RecommendNewResult recommendNewResult = new RecommendNewResult();
            recommendNewResult.setGroupId(appDiscoverGroup.getId());
            recommendNewResult.setTitle(appDiscoverGroup.getName());
            if (recentlyGroupIds.contains(appDiscoverGroup.getId().toString())) {
                //最近使用分组,数据从redis中查询
                List<GetNewBannersResult> items = redisTemplate.opsForList().range(RedisUtils.getKey("history20220303" + param.getUid() + ":" + param.getLanguage()), 0, -1);
                if (items != null && items.size() > 0) {
                    int end = Math.min(recentlyShowNum, items.size());
                    Long id = 715944129279819777L;
                    items.stream().forEach(i -> {
                        if (i != null && i.getId() != null && i.getId().equals(id)) {
                            i.setUrl("HiWallet://open/NewYang");
                        }
                    });
                    recommendNewResult.setItems(items.subList(0, end));
                } else {
                    continue;
                }
                recommendNewResult.setDelete(true);
                recommendNewResult.setStyle(AppStyleType.transverse);
            } else {
                recommendNewResult.setStyle(AppStyleType.portrait);
                recommendNewResult.setDelete(false);
                List<GetNewBannersResult> byGroup = getByGroup(appDiscoverGroup.getId(), true, param.getLanguage(),secretPlatform);
                if (byGroup.size() <= 0) {
                    continue;
                }
                /**
                 * 判断版本号小于1.8.1的不显示币币兑换应用2020/6/15
                 * 版本号小于1.11.0不显示法币支付2020/8/14
                 */
                filterDapp(byGroup, appVersion, jsonObject, ip);
                recommendNewResult.setItems(byGroup);
            }
            results.add(recommendNewResult);
        }
        return results;
    }

    @Autowired
    private WyreCountryService wyreCountryService;

    @Override
    public void filterDapp(List<GetNewBannersResult> byGroup, AppVersion appVersion, JsonObject jsonObject, String ip) {

        String changellyDappId = String.valueOf(jsonObject.get("changellyDappId"));
        /**
         * 大于等于1.8.1不做处理
         */
        if (appVersionService.hasUpdate(appVersion.getVersion(), "1.9.0") == 1) {
            for (int i = 0; i < byGroup.size(); i++) {
                GetNewBannersResult getNewBannersResult = byGroup.get(i);
                if (getNewBannersResult.getId().equals(Long.valueOf(changellyDappId))) {
                    byGroup.remove(getNewBannersResult);
                    return;
                }
            }
        }

        //  小于1.11.0不显示法币支付
        boolean support = false;
        JsonObject jsonObject_buy = (JsonObject) configureService.getValue("buycryptoConfig");
        boolean buySwitch = Boolean.parseBoolean(String.valueOf(jsonObject_buy.get("switch")));
        if (buySwitch && appVersionService.hasUpdate(appVersion.getVersion(), "1.11.0") != 1) {
            //大于等于1.11.0的看ip是否支持显示
            List<WyreCountry> countryList = wyreCountryService.getSupportCountryByIp(ip);
            if (countryList != null && !countryList.isEmpty()) {
                support = true;
            }
        }
        if (!support) {
            String wyreDappId = String.valueOf(jsonObject_buy.get("wyreDappId"));
            for (int i = 0; i < byGroup.size(); i++) {
                GetNewBannersResult getNewBannersResult = byGroup.get(i);
                if (getNewBannersResult.getId().equals(Long.valueOf(wyreDappId))) {
                    byGroup.remove(getNewBannersResult);
                    return;
                }
            }
        }

    }


    @Override
    public void addUsedHistory(AddNewUsedHistoryParam param) {
        if (BannerNewType.HTML.equals(param.getType()) || BannerNewType.PICONLY.equals(param.getType())) {
            return;
        }
        GetNewBannersResult appDiscoverGame = getByIdAndType(param.getDappId(), param.getType(), param.getLanguage(), null, null);
        if (appDiscoverGame != null) {
            if (BannerNewType.APP.equals(appDiscoverGame.getType())) {
                appDiscoverGame.setHasDetails(true);
            } else {
                appDiscoverGame.setHasDetails(false);
            }
            saveHistoryToRedis(appDiscoverGame, param);
        }
    }

    @Override
    public void deleteUsedHistory(AddNewUsedHistoryParam param) {
        List<GetNewBannersResult> list = redisTemplate.opsForList().range(RedisUtils.getKey("history20220303" + param.getUid() + ":" + param.getLanguage()), 0, -1);
        if (list == null || list.size() == 0) {
            return;
        }

        for (int i = 0; i < list.size(); i++) {
            GetNewBannersResult getNewBannersResult = list.get(i);
            if (getNewBannersResult.getId().equals(param.getDappId())) {
                list.remove(getNewBannersResult);
            }
        }

        redisTemplate.delete(RedisUtils.getKey("history20220303" + param.getUid() + ":" + param.getLanguage()));
        if (list.size() > 0) {
            redisTemplate.opsForList().rightPushAll(RedisUtils.getKey("history20220303" + param.getUid() + ":" + param.getLanguage()), list);
        }
    }

    @Override
    public GetNewBannersResult getByIdAndType(Long dappId, BannerNewType type, LanguageEnum language, AppDiscoverBannerNewI18n appDiscoverBannerNewI18n, AppDiscoverBannerNew appDiscoverBannerNew) {
        GetNewBannersResult getNewBannersResult = new GetNewBannersResult();
        getNewBannersResult.setType(type);

        if (appDiscoverBannerNewI18n != null) {
            getNewBannersResult.setImageUrl(appDiscoverBannerNewI18n.getImageUrl());
        }

        AppInfoResult infoResult = new AppInfoResult();
        if (BannerNewType.HTMLDAPP.equals(type)) {
            //html应用
            AppDiscoverHtml appDiscoverHtml = appDiscoverHtmlService.getById(dappId);
            if (appDiscoverHtml == null) {
                return null;
            }
            AppDiscoverHtmlI18n appDiscoverHtmlI18n = appDiscoverHtmlI18nService.findByAppIdAndLanguage(dappId, language);
            if (appDiscoverHtmlI18n == null) {
                return null;
            }
            getNewBannersResult.setId(dappId);
            getNewBannersResult.setHasDetails(true);
            getNewBannersResult.setSubTitle(appDiscoverHtmlI18n.getSubTitle());
            getNewBannersResult.setTitle(appDiscoverHtmlI18n.getTitle());
            getNewBannersResult.setUrl(appDiscoverHtml.getUrl());

            infoResult.setIconUrl(appDiscoverHtml.getImageUrl());
            infoResult.setDesc(appDiscoverHtmlI18n.getDescription());
            infoResult.setDetail(appDiscoverHtmlI18n.getDetail());
            infoResult.setImage(appDiscoverHtmlI18n.getImageUrl());

        } else if (BannerNewType.TOPSTAKING.equals(type)
                || BannerNewType.CENTRALIAPP.equals(type)) {
            //专业应用
            AppDiscoverPro appDiscoverPro = appDiscoverProService.getById(dappId);
            if (appDiscoverPro == null) {
                return null;
            }
            AppDiscoverProI18n appDiscoverProI18n = appDiscoverProI18nService.findByAppIdAndLanguage(dappId, language);
            if (appDiscoverProI18n == null) {
                return null;
            }
            getNewBannersResult.setId(dappId);
            getNewBannersResult.setSubTitle(appDiscoverProI18n.getSubTitle());
            getNewBannersResult.setTitle(appDiscoverProI18n.getTitle());
            getNewBannersResult.setHasDetails(false);
            if (appDiscoverBannerNew != null) {
                getNewBannersResult.setUrl(appDiscoverBannerNew.getUrl());
            } else {
                getNewBannersResult.setUrl(appDiscoverPro.getUrl());
            }
            infoResult.setIconUrl(appDiscoverPro.getImageUrl());

        } else if (BannerNewType.DECENTRALIAPP.equals(type)) {
            //dapp应用
            AppDiscoverDapp appDiscoverDapp = appDiscoverDappService.getById(dappId);
            if (appDiscoverDapp == null) {
                return null;
            }
            AppDiscoverDappI18n appDiscoverDappI18n = appDiscoverDappI18nService.findByAppIdAndLanguage(dappId, language);
            if (appDiscoverDappI18n == null) {
                return null;
            }
            getNewBannersResult.setId(dappId);
            getNewBannersResult.setChainType(appDiscoverDapp.getChainType());
            getNewBannersResult.setSubTitle(appDiscoverDappI18n.getSubTitle());
            getNewBannersResult.setTitle(appDiscoverDappI18n.getTitle());
            getNewBannersResult.setUrl(appDiscoverDapp.getUrl());
            getNewBannersResult.setHasDetails(true);

            infoResult.setTips(appDiscoverLabelApplicationService.getByTypeAndAppId(AppType.DAPP, dappId, language));
            infoResult.setImage(appDiscoverDappI18n.getImageUrl());
            infoResult.setDetail(appDiscoverDappI18n.getDetail());
            infoResult.setDesc(appDiscoverDappI18n.getDescription());
            infoResult.setIconUrl(appDiscoverDapp.getImageUrl());

        } else if (BannerNewType.APP.equals(type)) {

            AppDiscoverApp appDiscoverApp = appDiscoverAppService.getById(dappId);
            if (appDiscoverApp == null) {
                return null;
            }
            AppDiscoverAppI18n appDiscoverAppI18n = appDiscoverAppI18nService.findByAppIdAndLanguage(dappId, language);
            //app应用
            if (appDiscoverAppI18n == null) {
                return null;
            }
            getNewBannersResult.setHasDetails(true);
            getNewBannersResult.setId(dappId);
            getNewBannersResult.setSubTitle(appDiscoverAppI18n.getSubTitle());
            getNewBannersResult.setTitle(appDiscoverAppI18n.getTitle());

            infoResult.setUrl(appDiscoverApp.getUrl());
            infoResult.setTips(appDiscoverLabelApplicationService.getByTypeAndAppId(AppType.APP, dappId, language));
            infoResult.setSubUrl(appDiscoverApp.getSubUrl());
            infoResult.setImage(appDiscoverAppI18n.getImageUrl());
            infoResult.setDetail(appDiscoverAppI18n.getDetail());
            infoResult.setIconUrl(appDiscoverApp.getImageUrl());

        } else {
            if (appDiscoverBannerNew != null) {
                getNewBannersResult.setHasDetails(false);
                getNewBannersResult.setId(appDiscoverBannerNew.getId());
                getNewBannersResult.setTitle(appDiscoverBannerNew.getTitle());
                getNewBannersResult.setUrl(appDiscoverBannerNew.getUrl());
            }

        }
        getNewBannersResult.setInfo(infoResult);
        return getNewBannersResult;
    }

    @Override
    public QueryResult<List<GetBannerListResult>> getBannerList(GetBannerListParam param) {
        QueryResult<List<GetBannerListResult>> listQueryResult = new QueryResult<>();
        Map<String, Object> params = CommonBusiness.httpPageParam(param);
        QueryResult<List<AppDiscoverBannerNew>> queryResultSource = queryForPage(AppDiscoverBannerNew.class, "getBannerList", params);
        if(ObjectUtils.isEmpty(queryResultSource)) {
        	return listQueryResult;
        }
        listQueryResult.setPageInfo(queryResultSource.getPageInfo());
        List<GetBannerListResult> list = new ArrayList<>();
        for (AppDiscoverBannerNew appDiscoverBannerNew : queryResultSource.getResult()) {
            GetBannerListResult getBannerListResult = new GetBannerListResult();
            GetBannerDetailParam getBannerDetailParam = new GetBannerDetailParam();
            getBannerDetailParam.setBannerId(appDiscoverBannerNew.getId());
            List<BannerI18nResult> i18ns = getBannerI18n(getBannerDetailParam);
            getBannerListResult.setBannerShow(appDiscoverBannerNew.getBannerShow() && i18ns != null && i18ns.size() > 0);
            getBannerListResult.setId(appDiscoverBannerNew.getId());
            getBannerListResult.setTitle(appDiscoverBannerNew.getTitle());
            getBannerListResult.setType(appDiscoverBannerNew.getBannerType());
            getBannerListResult.setUrl(appDiscoverBannerNew.getUrl());
            getBannerListResult.setShowType(appDiscoverBannerNew.getShowType());
            getBannerListResult.setDappId(appDiscoverBannerNew.getTargetId());
            list.add(getBannerListResult);
        }
        listQueryResult.setResult(list);
        return listQueryResult;
    }

    @Override
    public QueryResult<List<GetAppListResult>> getAppList(GetAppListParam param) {
        QueryResult<List<GetAppListResult>> listQueryResult = new QueryResult<>();
        Map<String, Object> params = CommonBusiness.httpPageParam(param);
        String sqlId = "getAppList";
        QueryResult<List<APPSelectModel>> queryResultSource = queryForPage(APPSelectModel.class, sqlId, params);
        if(ObjectUtils.isEmpty(queryResultSource)) {
        	return listQueryResult;
        }
        listQueryResult.setPageInfo(queryResultSource.getPageInfo());
        List<GetAppListResult> list = new ArrayList<>();
        for (APPSelectModel appSelectModel : queryResultSource.getResult()) {
            GetAppListResult getAppListResult = new GetAppListResult();
            getAppListResult.setAppShow(appSelectModel.getAppShow());
            getAppListResult.setChainType(appSelectModel.getChainType());
            getAppListResult.setId(appSelectModel.getId());
            getAppListResult.setImageUrl(appSelectModel.getImageUrl());
            getAppListResult.setTitle(appSelectModel.getTitle());
            getAppListResult.setType(param.getAppType());
            list.add(getAppListResult);
        }
        listQueryResult.setResult(list);
        return listQueryResult;
    }

    @Override
    public List<GetAppListResult> getAppListAll(GetAppListAllParam param) {
        List<GetAppListResult> listResults = new ArrayList<>();
        String sql = "SELECT * FROM ";
        AppType appType;

        if (BannerNewType.HTMLDAPP.equals(param.getBannerType())) {
            sql += " wal_app_discover_html ";
            appType = AppType.HTML;
        } else if (BannerNewType.DECENTRALIAPP.equals(param.getBannerType())) {
            sql += " wal_app_discover_dapp ";
            appType = AppType.DAPP;
        } else if (BannerNewType.APP.equals(param.getBannerType())) {
            sql += " wal_app_discover_app ";
            appType = AppType.APP;
        } else {
            return null;
        }
        sql += " order by `id` DESC ";
        List<APPSelectModel> appSelectModels = result(APPSelectModel.class,appDiscoverBannerNewDao.queryForMap(sql));
//        List<APPSelectModel> appSelectModels = queryForMap(APPSelectModel.class, sql, null);
        for (APPSelectModel appSelectModel : appSelectModels) {
            GetAppListResult getAppListResult = new GetAppListResult();
            getAppListResult.setAppShow(appSelectModel.getAppShow());
            getAppListResult.setChainType(appSelectModel.getChainType());
            getAppListResult.setId(appSelectModel.getId());
            getAppListResult.setImageUrl(appSelectModel.getImageUrl());
            getAppListResult.setTitle(appSelectModel.getTitle());
            getAppListResult.setType(appType);
            listResults.add(getAppListResult);
        }
        return listResults;
    }

    @Override
    public GetBannerDetailResult getBannerDetail(GetBannerDetailParam param) {
        GetBannerDetailResult getBannerDetailResult = new GetBannerDetailResult();
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("id", param.getBannerId());
        AppDiscoverBannerNew appDiscoverBannerNew = get(appDiscoverBannerNewDao.queryForList(wrapper));
        if (appDiscoverBannerNew == null) {
            throw new BusinessException(CodeRes.CODE_15049);
        }
        getBannerDetailResult.setUrl(appDiscoverBannerNew.getUrl());
        List<BannerI18nResult> i18ns = getBannerI18n(param);
        getBannerDetailResult.setI18n(i18ns);
        getBannerDetailResult.setBannerShow(appDiscoverBannerNew.getBannerShow() && i18ns != null && i18ns.size() > 0);
        getBannerDetailResult.setId(appDiscoverBannerNew.getId());
        getBannerDetailResult.setTitle(appDiscoverBannerNew.getTitle());
        getBannerDetailResult.setType(appDiscoverBannerNew.getBannerType());
        return getBannerDetailResult;
    }

    @Override
    public void addBanner(BannerNewAddParam param) {
        AppDiscoverBannerNew appDiscoverBannerNew = new AppDiscoverBannerNew();
        appDiscoverBannerNew.setBannerShow(param.getBannerShow());
        appDiscoverBannerNew.setBannerType(param.getType());
        if (BannerNewType.HTML.equals(param.getType()) && StringUtils.isEmpty(param.getUrl())) {
            throw new BusinessException(CodeRes.CODE_19001);
        }
        if (BannerNewType.DECENTRALIAPP.equals(param.getType()) || BannerNewType.APP.equals(param.getType())
                || BannerNewType.HTMLDAPP.equals(param.getType())) {
            if (param.getDappId() == null) {
                throw new BusinessException(CodeRes.CODE_14023);
            }
        }
        Long targetId = param.getDappId();
        if (BannerNewType.CENTRALIAPP.equals(param.getType()) || BannerNewType.TOPSTAKING.equals(param.getType())) {
            AppDiscoverPro appDiscoverPro = appDiscoverProService.getByType(param.getType());
            if (appDiscoverPro == null) {
                throw new BusinessException(CodeRes.CODE_20024);
            }
            targetId = appDiscoverPro.getId();
        }
        appDiscoverBannerNew.setShowType(param.getShowType());
        appDiscoverBannerNew.setTargetId(targetId);
        appDiscoverBannerNew.setTitle(param.getTitle());
        appDiscoverBannerNew.setUrl(param.getUrl());
        save(appDiscoverBannerNew);
    }

    @Override
    public void updateBanner(BannerNewUpdParam param) {

        if (BannerNewType.HTML.equals(param.getType()) && StringUtils.isEmpty(param.getUrl())) {
            throw new BusinessException(CodeRes.CODE_19001);
        }
        if (BannerNewType.DECENTRALIAPP.equals(param.getType()) || BannerNewType.APP.equals(param.getType())
                || BannerNewType.HTMLDAPP.equals(param.getType())) {
            if (param.getDappId() == null) {
                throw new BusinessException(CodeRes.CODE_14023);
            }
        }
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("id", param.getBannerId());
        AppDiscoverBannerNew appDiscoverBannerNew = get(appDiscoverBannerNewDao.queryForList(wrapper));
        if (appDiscoverBannerNew == null) {
            throw new BusinessException(CodeRes.CODE_15049);
        }
        Long targetId = param.getDappId();
        if (BannerNewType.CENTRALIAPP.equals(param.getType()) || BannerNewType.TOPSTAKING.equals(param.getType())) {
            AppDiscoverPro appDiscoverPro = appDiscoverProService.getByType(param.getType());
            if (appDiscoverPro == null) {
                throw new BusinessException(CodeRes.CODE_20024);
            }
            targetId = appDiscoverPro.getId();
        }
        appDiscoverBannerNew.setBannerShow(param.getBannerShow());
        appDiscoverBannerNew.setBannerType(param.getType());
        appDiscoverBannerNew.setShowType(param.getShowType());
        appDiscoverBannerNew.setTargetId(targetId);
        appDiscoverBannerNew.setTitle(param.getTitle());
        appDiscoverBannerNew.setUrl(param.getUrl());
        save(appDiscoverBannerNew);
    }

    @Override
    public List<BannerI18nResult> getBannerI18n(GetBannerDetailParam param) {
        List<BannerI18nResult> results = new ArrayList<>();
        List<AppDiscoverBannerNewI18n> i18nListByBannerId = appDiscoverBannerNewI18nService.getI18nListByBannerId(param.getBannerId());
        for (AppDiscoverBannerNewI18n appDiscoverBannerNewI18n : i18nListByBannerId) {
            BannerI18nResult bannerI18nResult = new BannerI18nResult();
            bannerI18nResult.setEndTime(appDiscoverBannerNewI18n.getEndTime().getTime());
            bannerI18nResult.setI18nId(appDiscoverBannerNewI18n.getId());
            bannerI18nResult.setImageUrl(appDiscoverBannerNewI18n.getImageUrl());
            bannerI18nResult.setLanguage(appDiscoverBannerNewI18n.getLanguage());
            bannerI18nResult.setStartTime(appDiscoverBannerNewI18n.getStartTime().getTime());
            results.add(bannerI18nResult);
        }
        return results;
    }

    @Override
    public void editBannerI18n(EditBannerI18nParam param) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("id", param.getBannerId());
        AppDiscoverBannerNew appDiscoverBannerNew = get(appDiscoverBannerNewDao.queryForList(wrapper));
        if (appDiscoverBannerNew == null) {
            throw new BusinessException(CodeRes.CODE_15049);
        }
        List<BannerI18nParam> i18n = param.getI18n();
        List<AppDiscoverBannerNewI18n> list = new ArrayList<>();
        for (BannerI18nParam bannerI18nParam : i18n) {
            AppDiscoverBannerNewI18n appDiscoverBannerNewI18n = new AppDiscoverBannerNewI18n();
            appDiscoverBannerNewI18n.setBannerId(param.getBannerId());
            appDiscoverBannerNewI18n.setEndTime(new Date(bannerI18nParam.getEndTime()));
            appDiscoverBannerNewI18n.setImageUrl(bannerI18nParam.getImageUrl());
            appDiscoverBannerNewI18n.setLanguage(bannerI18nParam.getLanguage());
            appDiscoverBannerNewI18n.setStartTime(new Date(bannerI18nParam.getStartTime()));
            appDiscoverBannerNewI18n.setCreatedDate(new Date());
            appDiscoverBannerNewI18n.setId(bannerI18nParam.getI18nId());
            appDiscoverBannerNewI18n.setModifiedDate(new Date());
            list.add(appDiscoverBannerNewI18n);
        }
        appDiscoverBannerNewI18nService.saveBatch(list);
    }

    private synchronized void saveHistoryToRedis(GetNewBannersResult getNewBannersResult, AddNewUsedHistoryParam param) {
        List<GetNewBannersResult> list = redisTemplate.opsForList().range(RedisUtils.getKey("history20220303" + param.getUid() + ":" + param.getLanguage()), 0, -1);
        List<GetNewBannersResult> newList = new ArrayList<>();
        newList.add(getNewBannersResult);
        JsonObject jsonObject = (JsonObject) configureService.getValue("newDiscover");
        int recentlyAllNum = Integer.parseInt(jsonObject.get("recentlyAllNum").toString());
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                GetNewBannersResult discoverGame = list.get(i);
                if (discoverGame == null || discoverGame.getId() == null) {
                    list.remove(discoverGame);
                    continue;
                }
                if (discoverGame.getId().equals(param.getDappId())) {
                    list.remove(discoverGame);
                }
            }
            if (list.size() <= recentlyAllNum) {
                newList.addAll(list);
            } else {
                for (int i = 0; i < recentlyAllNum; i++) {
                    newList.add(list.get(i));
                }
            }
        }
        redisTemplate.delete(RedisUtils.getKey("history20220303" + param.getUid() + ":" + param.getLanguage()));
        redisTemplate.opsForList().rightPushAll(RedisUtils.getKey("history20220303" + param.getUid() + ":" + param.getLanguage()), newList);
    }

    @Override
    public QueryResult<List<GetNewBannersResult>> getDiscoverGamesPage(RecommendPageNewParam param, SecretPlatform secretPlatform, String ip) {
        AppVersion appVersion = appVersionService.getVersion(param.getVersion(), param.getRegion(), secretPlatform);
        if (appVersion == null) {
            throw new BusinessException(CodeRes.CODE_14019);
        }
        QueryResult<List<GetNewBannersResult>> result = new QueryResult<>();
        JsonObject jsonObject = (JsonObject) configureService.getValue("newDiscover");
        String recentlyGroupIds = String.valueOf(jsonObject.get("recentlyGroupIds"));
        PageResponse.PageInfo pageInfo = new PageResponse.PageInfo();
        int start = 0;
        int end = 0;
        if (param.getPageIndex() == 1) {
            end = param.getPageSize();
        } else {
            start = param.getPageIndex() * param.getPageSize() - param.getPageSize();
            end = start + param.getPageSize();
        }
        if (recentlyGroupIds.contains(param.getGroupId().toString())) {
            //最近使用分组,数据从redis中查询
            List<GetNewBannersResult> items = redisTemplate.opsForList().range(RedisUtils.getKey("history20220303" + param.getUid() + ":" + param.getLanguage()), 0, -1);


            if (items != null && items.size() != 0) {
                if (items.size() < start) {
                    pageInfo.setTotalCount(items.size());
                    result.setPageInfo(pageInfo);
                    result.setResult(null);
                } else {
                    pageInfo.setTotalCount(items.size());
                    result.setPageInfo(pageInfo);
                    int toIndex = Math.min(end, (items.size()));
                    result.setResult(items.subList(start, toIndex));
                }

            } else {
                pageInfo.setTotalCount(0);
                result.setPageInfo(pageInfo);
                result.setResult(null);
            }

        } else {
            List<GetNewBannersResult> items = getByGroup(param.getGroupId(), null, param.getLanguage(),secretPlatform);
            if (items.size() != 0) {
                /**
                 * 判断版本号小于1.8.1的不显示币币兑换应用2020/6/15
                 * 版本号小于1.11.0不显示法币支付2020/8/14
                 */
                filterDapp(items, appVersion, jsonObject, ip);
                pageInfo.setTotalCount(items.size());
                result.setPageInfo(pageInfo);
                if (items.size() < start) {
                    result.setResult(null);
                } else {
                    int toIndex = Math.min(end, (items.size()));
                    result.setResult(items.subList(start, toIndex));
                }
            } else {
                pageInfo.setTotalCount(0);
                result.setPageInfo(pageInfo);
                result.setResult(null);
            }
        }
        return result;
    }

    private List<GetNewBannersResult> getByGroup(Long groupId, Boolean showToHome, LanguageEnum language,SecretPlatform secretPlatform) {
        List<GetNewBannersResult> items = new ArrayList<>();
        List<AppDiscoverGroupI18n> groupI18nList = appDiscoverGroupI18nService.findByGroupId(groupId, showToHome);
        for (AppDiscoverGroupI18n appDiscoverGroupI18n : groupI18nList) {
            GetNewBannersResult getNewBannersResult = new GetNewBannersResult();
            getNewBannersResult.setHasDetails(true);
            AppInfoResult infoResult = new AppInfoResult();
            if (AppType.DAPP.equals(appDiscoverGroupI18n.getAppType())) {

                getNewBannersResult.setType(BannerNewType.DECENTRALIAPP);
                AppDiscoverDappI18n appDiscoverDappI18n = appDiscoverDappI18nService.findById(appDiscoverGroupI18n.getI18nId());
                if (appDiscoverDappI18n == null) {
                    continue;
                }
                AppDiscoverDapp appDiscoverDapp = appDiscoverDappService.getById(appDiscoverDappI18n.getAppId());
                if (appDiscoverDapp == null || !appDiscoverDapp.getAppShow()) {
                    continue;
                }
                if(secretPlatform.getPlatform().equals(PlatformEnum.IOS)) {//ios 平台调用
                	List<String> pfs = Arrays.stream(appDiscoverDapp.getPlatformSupports()).collect(Collectors.toList());
                	System.out.println(pfs.size());
                	if(!pfs.contains(PlatformEnum.IOS.name())) {
                		continue;
                	}
                }
                getNewBannersResult.setId(appDiscoverDapp.getId());
                getNewBannersResult.setChainType(appDiscoverDapp.getChainType());
                getNewBannersResult.setSubTitle(appDiscoverDappI18n.getSubTitle());
                getNewBannersResult.setTitle(appDiscoverDappI18n.getTitle());
                getNewBannersResult.setUrl(appDiscoverDapp.getUrl());

                infoResult.setUrl(null);
                infoResult.setTips(appDiscoverLabelApplicationService.getByTypeAndAppId(AppType.DAPP, appDiscoverDappI18n.getAppId(), language));
                infoResult.setSubUrl(null);
                infoResult.setImage(appDiscoverDappI18n.getImageUrl());
                infoResult.setDetail(appDiscoverDappI18n.getDetail());
                infoResult.setDesc(appDiscoverDappI18n.getDescription());
                infoResult.setIconUrl(appDiscoverDapp.getImageUrl());
            } else if (AppType.HTML.equals(appDiscoverGroupI18n.getAppType())) {
                getNewBannersResult.setType(BannerNewType.HTMLDAPP);
                AppDiscoverHtmlI18n appDiscoverHtmlI18n = appDiscoverHtmlI18nService.findById(appDiscoverGroupI18n.getI18nId());
                if (appDiscoverHtmlI18n == null) {
                    continue;
                }
                AppDiscoverHtml appDiscoverHtml = appDiscoverHtmlService.getById(appDiscoverHtmlI18n.getAppId());
                if (appDiscoverHtml == null || !appDiscoverHtml.getAppShow()) {
                    continue;
                }
                getNewBannersResult.setId(appDiscoverHtml.getId());
                getNewBannersResult.setSubTitle(appDiscoverHtmlI18n.getSubTitle());
                getNewBannersResult.setTitle(appDiscoverHtmlI18n.getTitle());
                getNewBannersResult.setUrl(appDiscoverHtml.getUrl());
                infoResult.setIconUrl(appDiscoverHtml.getImageUrl());
                infoResult.setDesc(appDiscoverHtmlI18n.getDescription());
                infoResult.setDetail(appDiscoverHtmlI18n.getDetail());
                infoResult.setImage(appDiscoverHtmlI18n.getImageUrl());
            } else if (AppType.APP.equals(appDiscoverGroupI18n.getAppType())) {
                getNewBannersResult.setType(BannerNewType.APP);
                AppDiscoverAppI18n appDiscoverAppI18n = appDiscoverAppI18nService.findById(appDiscoverGroupI18n.getI18nId());
                if (appDiscoverAppI18n == null) {
                    continue;
                }
                AppDiscoverApp appDiscoverApp = appDiscoverAppService.getById(appDiscoverAppI18n.getAppId());
                if (appDiscoverApp == null || !appDiscoverApp.getAppShow()) {
                    continue;
                }
                //app应用
                getNewBannersResult.setId(appDiscoverAppI18n.getAppId());
                getNewBannersResult.setChainType(null);
                getNewBannersResult.setSubTitle(appDiscoverAppI18n.getSubTitle());
                getNewBannersResult.setTitle(appDiscoverAppI18n.getTitle());
                getNewBannersResult.setUrl(null);

                infoResult.setUrl(appDiscoverApp.getUrl());
                infoResult.setTips(appDiscoverLabelApplicationService.getByTypeAndAppId(AppType.APP, appDiscoverAppI18n.getAppId(), language));
                infoResult.setSubUrl(appDiscoverApp.getSubUrl());
                infoResult.setImage(appDiscoverAppI18n.getImageUrl());
                infoResult.setDetail(appDiscoverAppI18n.getDetail());
                infoResult.setIconUrl(appDiscoverApp.getImageUrl());
            } else if (AppType.PRO.equals(appDiscoverGroupI18n.getAppType())) {
                AppDiscoverProI18n appDiscoverProI18n = appDiscoverProI18nService.findById(appDiscoverGroupI18n.getI18nId());
                if (appDiscoverProI18n == null) {
                    continue;
                }
                AppDiscoverPro appDiscoverPro = appDiscoverProService.getById(appDiscoverProI18n.getAppId());
                if (appDiscoverPro == null || !appDiscoverPro.getAppShow()) {
                    continue;
                }
                getNewBannersResult.setType(appDiscoverPro.getProType());
                getNewBannersResult.setHasDetails(false);
                getNewBannersResult.setId(appDiscoverProI18n.getAppId());
                getNewBannersResult.setSubTitle(appDiscoverProI18n.getSubTitle());
                getNewBannersResult.setTitle(appDiscoverProI18n.getTitle());
                getNewBannersResult.setUrl(appDiscoverPro.getUrl());
                infoResult.setIconUrl(appDiscoverPro.getImageUrl());
            } else {
                continue;
            }
            getNewBannersResult.setInfo(infoResult);
            items.add(getNewBannersResult);
        }
        return items;
    }

    @Override
    public GetAppDetailResult getAppDetail(GetAppDetailParam param, SecretPlatform secretPlatform) {
        GetNewBannersResult appDiscoverGame = getByIdAndType(param.getDappId(), param.getType(), param.getLanguage(), null, null);
        if (appDiscoverGame == null) {
            return null;
        }
        GetAppDetailResult getAppDetailResult = new GetAppDetailResult();
        getAppDetailResult.setChainType(appDiscoverGame.getChainType());
        getAppDetailResult.setDesc(appDiscoverGame.getInfo().getDesc());
        getAppDetailResult.setDetail(appDiscoverGame.getInfo().getDetail());
        getAppDetailResult.setId(appDiscoverGame.getId());
        getAppDetailResult.setImage(appDiscoverGame.getInfo().getImage());
        getAppDetailResult.setSubUrl(appDiscoverGame.getInfo().getSubUrl());
        getAppDetailResult.setTips(appDiscoverGame.getInfo().getTips());
        getAppDetailResult.setTitle(appDiscoverGame.getTitle());
        getAppDetailResult.setType(appDiscoverGame.getType());
        getAppDetailResult.setUrl(appDiscoverGame.getInfo().getUrl());
        getAppDetailResult.setIconUrl(appDiscoverGame.getInfo().getIconUrl());
        return getAppDetailResult;
    }
}
