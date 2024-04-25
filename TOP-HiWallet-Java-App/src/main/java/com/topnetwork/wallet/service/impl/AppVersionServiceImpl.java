package com.topnetwork.wallet.service.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.base.component.aws.s3.UploadS3ServiceImpl;
import com.base.core.framework.sql.service.impl.SqlBaseServiceImpl;
import com.base.core.mvc.business.CommonBusiness;
import com.base.service.base.system.service.ConfigureService;
import com.gitee.magic.core.json.JsonObject;
import com.gitee.magic.framework.base.result.QueryResult;
import com.gitee.magic.framework.head.exception.BusinessException;
import com.gitee.magic.jdbc.persistence.source.jdbc.sqlplus.conditions.query.QueryWrapper;
import com.gitee.magic.jdbc.persistence.source.jdbc.sqlplus.conditions.update.DeleteWrapper;
import com.topnetwork.system.entity.User;
import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.common.enums.LanguageEnum;
import com.topnetwork.wallet.common.enums.PlatformEnum;
import com.topnetwork.wallet.common.enums.RegionEnum;
import com.topnetwork.wallet.common.enums.VersionStatus;
import com.topnetwork.wallet.dao.AppVersionDao;
import com.topnetwork.wallet.entity.AppVersion;
import com.topnetwork.wallet.entity.SecretPlatform;
import com.topnetwork.wallet.param.wallet.VersionAddParam;
import com.topnetwork.wallet.param.wallet.VersionDelParam;
import com.topnetwork.wallet.param.wallet.VersionListParam;
import com.topnetwork.wallet.param.wallet.VersionUpdateParam;
import com.topnetwork.wallet.param.wallet.config.AppConfigParam;
import com.topnetwork.wallet.param.wallet.v2.StakingSwitchParam;
import com.topnetwork.wallet.param.wallet.v3.VersionCheckParam;
import com.topnetwork.wallet.result.wallet.StakingSwitchResult;
import com.topnetwork.wallet.result.wallet.VersionCheckResult;
import com.topnetwork.wallet.result.wallet.VersionCheckV4Result;
import com.topnetwork.wallet.result.wallet.VersionDataResult;
import com.topnetwork.wallet.result.wallet.VersionListResult;
import com.topnetwork.wallet.result.wallet.config.AppConfigResult;
import com.topnetwork.wallet.result.wallet.config.AppConfigV2Result;
import com.topnetwork.wallet.result.wallet.config.DappResult;
import com.topnetwork.wallet.result.wallet.config.DiscoverResult;
import com.topnetwork.wallet.result.wallet.config.StakingResult;
import com.topnetwork.wallet.result.wallet.config.StakingV2Result;
import com.topnetwork.wallet.service.AppVersionService;

@Service("appVersionService")
@Transactional
public class AppVersionServiceImpl extends SqlBaseServiceImpl<AppVersion, Long>
        implements AppVersionService {

    private AppVersionDao appVersionDao;

	@Autowired
	private UploadS3ServiceImpl uploadS3Service;
    @Autowired
    private ConfigureService configureService;

    public AppVersionServiceImpl(@Qualifier("appVersionDao") AppVersionDao appVersionDao) {
        super(appVersionDao);
        this.appVersionDao = appVersionDao;
    }

    @Override
    public AppConfigV2Result findAppV2Config(AppConfigParam param, PlatformEnum platform) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("platform", platform.toString()).eq("version", param.getVersion());
        if (param.getRegion() != null) {
            wrapper.eq("region", param.getRegion().toString());
        }
        AppVersion appVersion_now = get(appVersionDao.queryForList(wrapper));
        if (appVersion_now == null) {
            throw new BusinessException(CodeRes.CODE_15016);
        }
        AppConfigV2Result appConfigResult = new AppConfigV2Result();

        JsonObject jsonObject = (JsonObject) configureService.getValue("appConfig");

        DappResult dappResult = new DappResult();
        dappResult.setProUrl(String.valueOf(jsonObject.get("walletProUrl")));
        dappResult.setTestUrl(String.valueOf(jsonObject.get("walletTestUrl")));
        dappResult.setEosNodeUrl(String.valueOf(jsonObject.get("eosNodeUrl")));
        DiscoverResult discoverResult = new DiscoverResult();
        discoverResult.setDiscoverSwitch(appVersion_now.getDiscover());
        StakingV2Result stakingResult = new StakingV2Result();
        if (LanguageEnum.CN.equals(param.getLanguage())) {
            stakingResult.setButtonText(String.valueOf(jsonObject.get("stakingButtDesc-CN")));
            stakingResult.setDesc(String.valueOf(jsonObject.get("stakingDesc-CN")));
            stakingResult.setIncomeText(String.valueOf(jsonObject.get("incomeText-CN")));
            stakingResult.setName(String.valueOf(jsonObject.get("stakingName-CN")));
        } else {
            stakingResult.setButtonText(String.valueOf(jsonObject.get("stakingButtDesc-EN")));
            stakingResult.setDesc(String.valueOf(jsonObject.get("stakingDesc-EN")));
            stakingResult.setIncomeText(String.valueOf(jsonObject.get("incomeText-EN")));
            stakingResult.setName(String.valueOf(jsonObject.get("stakingName-EN")));
        }
        stakingResult.setDappId(Long.valueOf(jsonObject.get("stakingDappId").toString()));
        stakingResult.setStakingUrl(String.valueOf(jsonObject.get("stakingUrl")));
        stakingResult.setPicUrl(String.valueOf(jsonObject.get("stakingPicUrl")));
        stakingResult.setStakingSwitch(appVersion_now.getStaking());

        appConfigResult.setDappConfig(dappResult);
        appConfigResult.setDiscoverConfig(discoverResult);
        appConfigResult.setStakingConfig(stakingResult);
        appConfigResult.setTopCurrencySwitch(jsonObject.has("topCurrencySwitch")?Boolean.valueOf(String.valueOf(jsonObject.get("topCurrencySwitch"))):false);

        return appConfigResult;
    }
    
    @Override
    public VersionCheckResult checkUpdate(VersionCheckParam param, PlatformEnum platformEnum) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("platform", platformEnum.toString()).eq("version", param.getVersion());
        if (!StringUtils.isEmpty(param.getRegion())) {
            wrapper.eq("region", param.getRegion().toString());
        }
        AppVersion appVersion_now = get(appVersionDao.queryForList(wrapper));
        if (appVersion_now == null) {
            throw new BusinessException(CodeRes.CODE_15016);
        }
        AppVersion appVersion_last = getLastVersion(platformEnum, param.getRegion(), param.getLanguage());
        VersionCheckResult version = new VersionCheckResult();
        BeanUtils.copyProperties(appVersion_last, version);

        version.setIsForcedUpdates(appVersion_now.getIsForcedUpdates());
        version.setDownloadUrl(getDownloadUrl(version.getDownloadUrl()));

        if (LanguageEnum.CN.equals(param.getLanguage())) {
            version.setDescription(appVersion_last.getDescription());
        } else {
            version.setDescription(appVersion_last.getEnglishDescription());
        }
        if (hasUpdate(param.getVersion(), version.getVersion()) == 1) {
            version.setHasUpdate("1");
        } else {
            version.setHasUpdate("0");
        }
        return version;
    }

    @Override
    public VersionCheckV4Result checkUpdateV4(VersionCheckParam param, PlatformEnum platform) {

        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("platform", platform.toString()).eq("version", param.getVersion());
        if (!StringUtils.isEmpty(param.getRegion())) {
            wrapper.eq("region", param.getRegion().toString());
        }
        AppVersion appVersion_now = get(appVersionDao.queryForList(wrapper));
        if (appVersion_now == null) {
            throw new BusinessException(CodeRes.CODE_15016);
        }
        AppVersion appVersion_last = getLastVersion(platform, param.getRegion(), param.getLanguage());
        VersionCheckV4Result versionCheckV4Result = new VersionCheckV4Result();
        if (hasUpdate(param.getVersion(), appVersion_last.getVersion()) != 1) {
            versionCheckV4Result.setStatus(VersionStatus.NOUPDATE);
        } else {
        	VersionDataResult versionDataResult = new VersionDataResult();
        	if(param.getVersion().equals("2.1.0") && param.getRegion().equals(RegionEnum.DOMESTIC)) {//出问题版本，特殊处理
        		if (LanguageEnum.CN.equals(param.getLanguage())) {
                    versionDataResult.setDescription("1 用户体验全新优化 \n 2 请前往HiWallet官网(https://www.hiwallet.org/zh)下载最新版本");
                    versionDataResult.setTitle("发现新版本");
                } else {
                    versionDataResult.setDescription("1 Newly optimized user experience\n" + 
                    		"\n" + 
                    		"2 Please go to HiWallet official website (https://www.hiwallet.org/zh) to download the latest version");
                    versionDataResult.setTitle("found new version");
                }
        		versionDataResult.setVersion(appVersion_last.getVersion());
        		versionDataResult.setDownloadUrl("");
        		versionCheckV4Result.setStatus(VersionStatus.FRAME);
        		versionCheckV4Result.setVersionData(versionDataResult);
        		return versionCheckV4Result;
        	}
            if (LanguageEnum.CN.equals(param.getLanguage())) {
                versionDataResult.setDescription(appVersion_last.getDescription());
                versionDataResult.setTitle(appVersion_last.getTitle());
            } else {
                versionDataResult.setDescription(appVersion_last.getEnglishDescription());
                versionDataResult.setTitle(appVersion_last.getEnglishTitle());
            }
            versionDataResult.setDownloadUrl(getDownloadUrl(appVersion_last.getDownloadUrl()));
            versionDataResult.setVersion(appVersion_last.getVersion());
            versionCheckV4Result.setVersionData(versionDataResult);
            String value = String.valueOf(configureService.getValue("forcedUpdateVersion"));
            if (hasUpdate(param.getVersion(), value) == 1) {
                versionCheckV4Result.setStatus(VersionStatus.FORCE);
            } else {
                versionCheckV4Result.setStatus(VersionStatus.valueOf(appVersion_now.getType().toString()));
            }
        }
        return versionCheckV4Result;
    }

    @Override
    public AppConfigResult findAppConfig(AppConfigParam param, PlatformEnum platform) {

        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("platform", platform.toString()).eq("version", param.getVersion());
        if (param.getRegion() != null) {
            wrapper.eq("region", param.getRegion().toString());
        }
        AppVersion appVersion_now = get(appVersionDao.queryForList(wrapper));
        if (appVersion_now == null) {
            throw new BusinessException(CodeRes.CODE_15016);
        }
        AppConfigResult appConfigResult = new AppConfigResult();

        JsonObject jsonObject = (JsonObject) configureService.getValue("appConfig");

        DappResult dappResult = new DappResult();
        dappResult.setProUrl(String.valueOf(jsonObject.get("walletProUrl")));
        dappResult.setTestUrl(String.valueOf(jsonObject.get("walletTestUrl")));
        DiscoverResult discoverResult = new DiscoverResult();
        discoverResult.setDiscoverSwitch(appVersion_now.getDiscover());
        StakingResult stakingResult = new StakingResult();
        if (LanguageEnum.CN.equals(param.getLanguage())) {
            stakingResult.setButtonText(String.valueOf(jsonObject.get("stakingButtDesc-CN")));
            stakingResult.setDesc(String.valueOf(jsonObject.get("stakingDesc-CN")));
            stakingResult.setIncomeText(String.valueOf(jsonObject.get("incomeText-CN")));
        } else {
            stakingResult.setButtonText(String.valueOf(jsonObject.get("stakingButtDesc-EN")));
            stakingResult.setDesc(String.valueOf(jsonObject.get("stakingDesc-EN")));
            stakingResult.setIncomeText(String.valueOf(jsonObject.get("incomeText-EN")));
        }
        stakingResult.setStakingUrl(String.valueOf(jsonObject.get("stakingUrl")));
        stakingResult.setPicUrl(String.valueOf(jsonObject.get("stakingPicUrl")));
        stakingResult.setStakingSwitch(appVersion_now.getStaking());

        appConfigResult.setDappConfig(dappResult);
        appConfigResult.setDiscoverConfig(discoverResult);
        appConfigResult.setStakingConfig(stakingResult);

        return appConfigResult;
    }


    @Override
    public AppVersion getLastVersion(PlatformEnum platformEnum, RegionEnum region, LanguageEnum language) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("platform", platformEnum.toString()).eq("canUpdate", "true");
        if (!StringUtils.isEmpty(region)) {
            wrapper.eq("region", region.toString());
        }
        wrapper.orderByDesc("version");
        List<AppVersion> list = appVersionDao.queryForList(wrapper);
        Collections.sort(list, new Comparator<AppVersion>() {
            @Override
            public int compare(AppVersion o1, AppVersion o2) {
                return hasUpdate(o1.getVersion(), o2.getVersion());
            }
        });
        return list.get(0);
    }

    /**
     * 比较app当前版本号与库中最新版本号
     * app使用版本号小，返回1
     * 相等，返回0
     * app使用版本号大于库中最新版本返回-1
     *
     * @param versionNow  app当前版本号
     * @param versionBase 库中所存版本号
     * @return i
     */
    @Override
    public Integer hasUpdate(String versionNow, String versionBase) {
        if (versionNow.equals(versionBase)) return 0;
        String[] version_num = versionNow.split("\\.");
        String[] version_num_data = versionBase.split("\\.");
        int return_data = -1;
        for (int i = 0; i < 3; i++) {
            if (Integer.parseInt(version_num[i]) > Integer.parseInt(version_num_data[i])) {
                break;
            }
            if (Integer.parseInt(version_num[i]) < Integer.parseInt(version_num_data[i])) {
                return_data = 1;
                break;
            }
        }
        return return_data;
    }

    private String getDownloadUrl(String url) {
        String downloadURL = "";
        if (url.contains("http")) {
            downloadURL = url;
        } else {
            downloadURL = uploadS3Service.getUrl(url, false);
        }
        return downloadURL;
    }

    @Override
    public void addVersionUpdate(VersionAddParam param, User user) {
        AppVersion appVersion = new AppVersion();
        appVersion.setCreateTime(new Date());
        appVersion.setDescription(param.getDescription());
        appVersion.setIsForcedUpdates(param.getIsForcedUpdates());
        if (param.getDownloadUrl().contains("http")) {
            appVersion.setDownloadUrl(param.getDownloadUrl());
        } else {
//            awsService.checkUploadApplyKey(param.getDownloadUrl());
            appVersion.setDownloadUrl(param.getDownloadUrl());
        }
        appVersion.setPlatform(param.getPlatform());
        appVersion.setApproved(param.getApproved());
        appVersion.setVersion(param.getVersion());
        appVersion.setRegion(param.getRegion());
        appVersion.setCanUpdate(param.getCanUpdate());
        appVersion.setStaking(param.getStaking());
        appVersion.setDiscover(param.getDiscover());
        appVersion.setCreateId(user.getId());
        appVersion.setTitle(param.getTitle());
        appVersion.setEnglishTitle(param.getEnglishTitle());
        appVersion.setType(param.getType());
        appVersion.setEnglishDescription(param.getEnglishDescription());
        save(appVersion);
    }

    @Override
    public void updateAppVersion(VersionUpdateParam param) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("id", param.getId());
        AppVersion appVersion = get(appVersionDao.queryForList(wrapper));
        if (appVersion == null) {
            throw new BusinessException(CodeRes.CODE_14023);
        }
        appVersion.setDescription(param.getDescription());
        appVersion.setEnglishDescription(param.getEnglishDescription());
        appVersion.setIsForcedUpdates(param.getIsForcedUpdates());
        appVersion.setVersion(param.getVersion());
        if (param.getDownloadUrl().contains("http")) {
            appVersion.setDownloadUrl(param.getDownloadUrl());
        } else {
//            awsService.checkUploadApplyKey(param.getDownloadUrl());
            appVersion.setDownloadUrl(param.getDownloadUrl());
        }
        appVersion.setApproved(param.getApproved());
        appVersion.setDiscover(param.getDiscover());
        appVersion.setStaking(param.getStaking());
        appVersion.setCanUpdate(param.getCanUpdate());
        appVersion.setRegion(param.getRegion());
        appVersion.setTitle(param.getTitle());
        appVersion.setEnglishTitle(param.getEnglishTitle());
        appVersion.setType(param.getType());
        save(appVersion);
    }

    @Override
    public void delAppVersion(VersionDelParam param) {
        DeleteWrapper wrapper = new DeleteWrapper();
        wrapper.eq("id", param.getId());
        appVersionDao.executeUpdate(wrapper);
    }

    @Override
    public QueryResult<List<VersionListResult>> findAppVersionList(VersionListParam param) {
        Map<String, Object> params = CommonBusiness.httpPageParam(param);
        QueryResult<List<VersionListResult>> queryResult = queryForPage(VersionListResult.class, "findAppVersionList", params);
        List<VersionListResult> list = queryResult.getResult();
        for (VersionListResult versionListResult : list) {
            versionListResult.setDownloadUrl(getDownloadUrl(versionListResult.getDownloadUrl()));
        }
        return queryResult;
    }

    @Override
    public VersionListResult findAppVersionDetail(VersionDelParam param) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("id", param.getId());
        AppVersion appVersion = get(appVersionDao.queryForList(wrapper));
        VersionListResult versionListResult = new VersionListResult();
        BeanUtils.copyProperties(appVersion, versionListResult);
        //获取url
        versionListResult.setDownloadUrl(getDownloadUrl(versionListResult.getDownloadUrl()));
        return versionListResult;
    }

    @Override
    public AppVersion getVersion(String version, RegionEnum region, SecretPlatform secretPlatform) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("version", version).eq("platform", secretPlatform.getPlatform().toString())
                .eq("region", region.toString());
        AppVersion appVersion = get(appVersionDao.queryForList(wrapper));
        return appVersion;
    }

    @Override
    public StakingSwitchResult getStakingSwitch(StakingSwitchParam param, PlatformEnum platformEnum) {
        String version = param.getVersion();
        StakingSwitchResult stakingSwitchResult = new StakingSwitchResult();
        /**
         * 临时代码
         * If  版本号 < 1.3.2 & iOS 就返回 false
         */
        if (PlatformEnum.IOS.equals(platformEnum)) {
            if (hasUpdate(version, "1.3.2") == 1) {
                stakingSwitchResult.setOpen(false);
                return stakingSwitchResult;
            }
        }
        String value = String.valueOf(configureService.getValue("0001"));
        AppVersion appVersion = getLastVersion(platformEnum, null, null);

        if (hasUpdate(version, appVersion.getVersion()) == -1) {
            String value_ = String.valueOf(configureService.getValue("0002"));
            stakingSwitchResult.setOpen(Boolean.valueOf(value_));
        } else {
            stakingSwitchResult.setOpen(Boolean.valueOf(value));
        }
        return stakingSwitchResult;
    }
}
