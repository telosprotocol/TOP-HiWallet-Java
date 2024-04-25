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
import org.springframework.util.StringUtils;

import com.base.core.mvc.business.CommonBusiness;
import com.common.utils.LocationUtils;
import com.base.service.base.system.service.ConfigureService;
import com.topnetwork.common.service.IconService;
import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.common.enums.LanguageEnum;
import com.topnetwork.wallet.common.enums.discover.BannerType;
import com.topnetwork.wallet.common.enums.discover.DappShowType;
import com.topnetwork.wallet.dao.AppDiscoverBannerDao;
import com.topnetwork.wallet.entity.AppDiscoverBanner;
import com.topnetwork.wallet.entity.AppUser;
import com.topnetwork.wallet.entity.AppVersion;
import com.topnetwork.wallet.entity.SecretPlatform;
import com.topnetwork.wallet.param.wallet.discover.BannerAddParam;
import com.topnetwork.wallet.param.wallet.discover.BannerListParam;
import com.topnetwork.wallet.param.wallet.discover.BannerUpdateParam;
import com.topnetwork.wallet.param.wallet.discover.GetBannersParam;
import com.topnetwork.wallet.param.wallet.discover.GetBannersV3Param;
import com.topnetwork.wallet.param.wallet.discover.GetBannersV4Param;
import com.topnetwork.wallet.result.wallet.discover.BannerListResult;
import com.topnetwork.wallet.result.wallet.discover.GetBannersResult;
import com.topnetwork.wallet.result.wallet.discover.GetBannersV2Result;
import com.topnetwork.wallet.service.AppDiscoverBannerService;
import com.topnetwork.wallet.service.AppUserService;
import com.topnetwork.wallet.service.AppVersionService;

import com.gitee.magic.framework.head.exception.BusinessException;
import com.gitee.magic.framework.base.result.QueryResult;
import com.base.core.framework.sql.service.impl.SqlBaseServiceImpl;
import com.gitee.magic.jdbc.persistence.source.jdbc.sqlplus.conditions.query.QueryWrapper;
import com.gitee.magic.core.json.JsonObject;

@Service("appDiscoverBannerService")
@Transactional
public class AppDiscoverBannerServiceImpl extends SqlBaseServiceImpl<AppDiscoverBanner, Long>
        implements AppDiscoverBannerService {

    @SuppressWarnings("unused")
    private AppDiscoverBannerDao appDiscoverBannerDao;
    @Autowired
    @Qualifier("iconService")
    private IconService iconService;
    @Autowired
    private ConfigureService configureService;
    @Autowired
    private AppVersionService appVersionService;

    public AppDiscoverBannerServiceImpl(@Qualifier("appDiscoverBannerDao") AppDiscoverBannerDao appDiscoverBannerDao) {
        super(appDiscoverBannerDao);
        this.appDiscoverBannerDao = appDiscoverBannerDao;
    }

    @Override
    public List<GetBannersV2Result> getV2Banners(GetBannersParam param) {
        List<GetBannersV2Result> result = new ArrayList<>();
        QueryWrapper queryWrapper = new QueryWrapper();
        Date now = new Date();
        queryWrapper.eq("bannerShow", "true").le("startTime", now).ge("endTime", now).eq("showType", DappShowType.normal.toString());
        queryWrapper.orderByAsc("bannerOrder");
        List<AppDiscoverBanner> list = appDiscoverBannerDao.queryForList(queryWrapper);
        return tran(list, param.getLanguage());
    }

    @Override
    public List<GetBannersV2Result> getV3Banners(GetBannersV3Param param, SecretPlatform secretPlatform) {
        return tran(getBanners(param, secretPlatform), param.getLanguage());
    }

    private List<AppDiscoverBanner> getBanners(GetBannersV3Param param, SecretPlatform secretPlatform) {
        AppVersion appVersion = appVersionService.getVersion(param.getVersion(), param.getRegion(), secretPlatform);
        if (appVersion == null) {
            throw new BusinessException(CodeRes.CODE_14019);
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        Date now = new Date();
        queryWrapper.eq("bannerShow", "true").le("startTime", now).ge("endTime", now);
        queryWrapper.ne(appVersion.getApproved(), "showType", DappShowType.examine.toString());
        queryWrapper.ne(!appVersion.getApproved(), "showType", DappShowType.normal.toString());
        queryWrapper.orderByAsc("bannerOrder");
        List<AppDiscoverBanner> list = appDiscoverBannerDao.queryForList(queryWrapper);
        return list;
    }

    @Autowired
    private AppUserService appUserService;

    @Override
    public List<GetBannersV2Result> getV4Banners(GetBannersV4Param param, SecretPlatform secretPlatform, String requestIP) {
        GetBannersV3Param getBannersV3Param = new GetBannersV3Param();
        BeanUtils.copyProperties(param, getBannersV3Param);
        List<AppDiscoverBanner> result = getBanners(getBannersV3Param, secretPlatform);
        if (result == null || result.size() <= 0) {
            return tran(result, param.getLanguage());
        }
        /**
         * 根据ip过滤掉新青年banner与邀请banner
         */
        List<Long> filterIds = null;
        String countryCodes = String.valueOf(configureService.getValue("forbidCountryCodes"));
        if (StringUtils.isEmpty(countryCodes) || LocationUtils.isForbid(requestIP, countryCodes)) {
            //过滤掉新青年banner与邀请banner
            filterIds = new ArrayList<>();
            JsonObject jsonObject = (JsonObject) configureService.getValue("forbidResourceIds");
            String youngBannerId = String.valueOf(jsonObject.get("youngBannerId"));
            filterIds.add(Long.valueOf(youngBannerId));
            String inviteBannerId = String.valueOf(jsonObject.get("inviteBannerId"));
            filterIds.add(Long.valueOf(inviteBannerId));

        } else {
            //判断是否新青年用户用来过滤邀请banner
            AppUser userByUid = appUserService.getUserByUid(param.getUid());
            if (userByUid == null) {
                //过滤掉邀请banner
                JsonObject jsonObject = (JsonObject) configureService.getValue("forbidResourceIds");
                filterIds = new ArrayList<>();
                String inviteBannerId = String.valueOf(jsonObject.get("inviteBannerId"));
                filterIds.add(Long.valueOf(inviteBannerId));
            }
        }

        if (filterIds != null) {
            List<AppDiscoverBanner> removeBanner = new ArrayList<>();
            for (int i = 0; i < result.size(); i++) {
                AppDiscoverBanner appDiscoverBanner = result.get(i);
                for (Long filterId : filterIds) {
                    if (filterId.equals(appDiscoverBanner.getId())) {
                        removeBanner.add(appDiscoverBanner);
                    }
                }
            }
            for (AppDiscoverBanner appDiscoverBanner : removeBanner) {
                result.remove(appDiscoverBanner);
            }

        }
        return tran(result, param.getLanguage());
    }


    private List<GetBannersV2Result> tran(List<AppDiscoverBanner> list, LanguageEnum languageEnum) {
        List<GetBannersV2Result> result = new ArrayList<>();
        for (AppDiscoverBanner appDiscoverBanner : list) {

            GetBannersV2Result getBannersResult = new GetBannersV2Result();
            String bannerUrl = "";
            if (LanguageEnum.CN.equals(languageEnum)) {
                if (appDiscoverBanner.getBannerUrl().contains("http")) {
                    bannerUrl = appDiscoverBanner.getBannerUrl();
                } else {
                    bannerUrl = iconService.buildURL(appDiscoverBanner.getBannerUrl());
                }
                getBannersResult.setName(appDiscoverBanner.getTitle());
            } else {
                if (appDiscoverBanner.getEnglishBannerUrl().contains("http")) {
                    bannerUrl = appDiscoverBanner.getEnglishBannerUrl();
                } else {
                    bannerUrl = iconService.buildURL(appDiscoverBanner.getEnglishBannerUrl());
                }
                getBannersResult.setName(appDiscoverBanner.getEnglishTitle());
            }
            getBannersResult.setDappId(appDiscoverBanner.getDiscoverGameId());
            getBannersResult.setActionUrl(appDiscoverBanner.getActionUrl());
            getBannersResult.setBannerUrl(bannerUrl);
            getBannersResult.setTargetId(appDiscoverBanner.getTargetId());
            getBannersResult.setType(appDiscoverBanner.getType());
            result.add(getBannersResult);

        }
        return result;
    }

    @Override
    public List<GetBannersResult> getBanners(GetBannersParam param) {
        List<GetBannersResult> result = new ArrayList<>();
        QueryWrapper queryWrapper = new QueryWrapper();
        Date now = new Date();
        queryWrapper.eq("bannerShow", "true").le("startTime", now).ge("endTime", now);
        queryWrapper.orderByAsc("bannerOrder");
        List<AppDiscoverBanner> list = appDiscoverBannerDao.queryForList(queryWrapper);

        for (AppDiscoverBanner appDiscoverBanner : list) {
            /**
             * 老版本不支持新青年
             */
            if (appDiscoverBanner.getType().equals(BannerType.CENTRALIAPP)) {
                continue;
            }
            GetBannersResult getBannersResult = new GetBannersResult();
            String bannerUrl = "";
            if (LanguageEnum.CN.equals(param.getLanguage())) {
                if (appDiscoverBanner.getBannerUrl().contains("http")) {
                    bannerUrl = appDiscoverBanner.getBannerUrl();
                } else {
                    bannerUrl = iconService.buildURL(appDiscoverBanner.getBannerUrl());
                }
            } else {
                if (appDiscoverBanner.getEnglishBannerUrl().contains("http")) {
                    bannerUrl = appDiscoverBanner.getEnglishBannerUrl();
                } else {
                    bannerUrl = iconService.buildURL(appDiscoverBanner.getEnglishBannerUrl());
                }
            }
            getBannersResult.setActionUrl(appDiscoverBanner.getActionUrl());
            getBannersResult.setBannerUrl(bannerUrl);
            getBannersResult.setTargetId(appDiscoverBanner.getTargetId());
            getBannersResult.setType(appDiscoverBanner.getType());
            result.add(getBannersResult);

        }
        return result;
    }


    @Override
    public QueryResult<List<BannerListResult>> findBannerList(BannerListParam param) {
        QueryResult<List<BannerListResult>> queryResult = new QueryResult<>();
        Map<String, Object> params = CommonBusiness.httpPageParam(param);
        QueryResult<List<AppDiscoverBanner>> queryResultSource = queryForPage(AppDiscoverBanner.class, "findBannerList", params);
        if(ObjectUtils.isEmpty(queryResultSource)) {
        	return queryResult;
        }
        queryResult.setPageInfo(queryResultSource.getPageInfo());
        List<AppDiscoverBanner> list = queryResultSource.getResult();
        List<BannerListResult> results = new ArrayList<>();
        for (AppDiscoverBanner appDiscoverBanner : list) {
            BannerListResult bannerListResult = new BannerListResult();
            BeanUtils.copyProperties(appDiscoverBanner, bannerListResult);
            bannerListResult.setStartTime(appDiscoverBanner.getStartTime().getTime());
            bannerListResult.setEndTime(appDiscoverBanner.getEndTime().getTime());
            if (appDiscoverBanner.getBannerUrl().contains("http")) {
                bannerListResult.setBannerUrl(appDiscoverBanner.getBannerUrl());
            } else {
                bannerListResult.setBannerUrl(iconService.buildURL(appDiscoverBanner.getBannerUrl()));
            }
            if (appDiscoverBanner.getEnglishBannerUrl().contains("http")) {
                bannerListResult.setEnglishBannerUrl(appDiscoverBanner.getEnglishBannerUrl());
            } else {
                bannerListResult.setEnglishBannerUrl(iconService.buildURL(appDiscoverBanner.getEnglishBannerUrl()));
            }
            results.add(bannerListResult);
        }
        queryResult.setResult(results);
        return queryResult;
    }

    @Override
    public void updateBanner(BannerUpdateParam param) {
        if (param.getType().equals(BannerType.DECENTRALIAPP) && param.getChainType() == null) {
            throw new BusinessException(CodeRes.CODE_15044);
        }
        if ((param.getType().equals(BannerType.DECENTRALIAPP) || param.getType().equals(BannerType.HTML))
                && param.getActionUrl() == null) {
            throw new BusinessException(CodeRes.CODE_15046);
        }
        if (param.getType().equals(BannerType.NOTICEDETAIL)
                && param.getTargetId() == null) {
            throw new BusinessException(CodeRes.CODE_15055);
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", param.getId());
        AppDiscoverBanner appDiscoverBanner = get(appDiscoverBannerDao.queryForList(queryWrapper));
        if (appDiscoverBanner == null) {
            throw new BusinessException(CodeRes.CODE_15049);
        }
        appDiscoverBanner.setActionUrl(param.getActionUrl());
        appDiscoverBanner.setBannerOrder(param.getBannerOrder());
        appDiscoverBanner.setBannerShow(param.getBannerShow());
        appDiscoverBanner.setShowType(param.getShowType());
        appDiscoverBanner.setBannerUrl(param.getBannerUrl());
        appDiscoverBanner.setChainType(param.getChainType());
        appDiscoverBanner.setTargetId(param.getTargetId());
        appDiscoverBanner.setTitle(param.getTitle());
        appDiscoverBanner.setUpdateTime(new Date());
        appDiscoverBanner.setType(param.getType());
        appDiscoverBanner.setEndTime(param.getEndTime());
        appDiscoverBanner.setEnglishTitle(param.getEnglishTitle());
        appDiscoverBanner.setEnglishBannerUrl(param.getEnglishBannerUrl());
        appDiscoverBanner.setStartTime(param.getStartTime());
        save(appDiscoverBanner);
    }

    @Override
    public void addBanner(BannerAddParam param) {
        if (param.getType().equals(BannerType.DECENTRALIAPP) && param.getChainType() == null) {
            throw new BusinessException(CodeRes.CODE_15044);
        }
        if ((param.getType().equals(BannerType.DECENTRALIAPP) || param.getType().equals(BannerType.HTML))
                && param.getActionUrl() == null) {
            throw new BusinessException(CodeRes.CODE_15046);
        }
        if (param.getType().equals(BannerType.NOTICEDETAIL)
                && param.getTargetId() == null) {
            throw new BusinessException(CodeRes.CODE_15055);
        }
        AppDiscoverBanner appDiscoverBanner = new AppDiscoverBanner();
        appDiscoverBanner.setActionUrl(param.getActionUrl());
        appDiscoverBanner.setBannerOrder(param.getBannerOrder());
        appDiscoverBanner.setBannerShow(param.getBannerShow());
        appDiscoverBanner.setShowType(param.getShowType());
        appDiscoverBanner.setBannerUrl(param.getBannerUrl());
        appDiscoverBanner.setChainType(param.getChainType());
        appDiscoverBanner.setTargetId(param.getTargetId());
        appDiscoverBanner.setTitle(param.getTitle());
        appDiscoverBanner.setCreateTime(new Date());
        appDiscoverBanner.setType(param.getType());
        appDiscoverBanner.setEndTime(param.getEndTime());
        appDiscoverBanner.setEnglishTitle(param.getEnglishTitle());
        appDiscoverBanner.setEnglishBannerUrl(param.getEnglishBannerUrl());
        appDiscoverBanner.setStartTime(param.getStartTime());
        save(appDiscoverBanner);

    }
}
