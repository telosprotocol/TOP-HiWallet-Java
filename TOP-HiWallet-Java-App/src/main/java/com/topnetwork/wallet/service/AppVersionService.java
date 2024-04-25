package com.topnetwork.wallet.service;

import com.topnetwork.system.entity.User;
import com.topnetwork.wallet.common.enums.LanguageEnum;
import com.topnetwork.wallet.common.enums.PlatformEnum;
import com.topnetwork.wallet.common.enums.RegionEnum;
import com.topnetwork.wallet.entity.SecretPlatform;
import com.topnetwork.wallet.param.wallet.config.AppConfigParam;
import com.topnetwork.wallet.param.wallet.v2.StakingSwitchParam;
import com.topnetwork.wallet.param.wallet.v3.VersionCheckParam;
import com.topnetwork.wallet.result.wallet.VersionCheckV4Result;
import com.topnetwork.wallet.result.wallet.config.AppConfigResult;
import com.topnetwork.wallet.result.wallet.config.AppConfigV2Result;
import com.gitee.magic.framework.base.result.QueryResult;
import com.base.core.framework.sql.service.SqlBaseService;
import com.topnetwork.wallet.entity.AppVersion;
import com.topnetwork.wallet.param.wallet.*;
import com.topnetwork.wallet.result.wallet.StakingSwitchResult;
import com.topnetwork.wallet.result.wallet.VersionCheckResult;
import com.topnetwork.wallet.result.wallet.VersionListResult;

import java.util.List;

public interface AppVersionService extends SqlBaseService<AppVersion, Long> {

    VersionCheckResult checkUpdate(VersionCheckParam param, PlatformEnum platformEnum);

    void addVersionUpdate(VersionAddParam param, User user);

    void updateAppVersion(VersionUpdateParam param);

    void delAppVersion(VersionDelParam param);

    QueryResult<List<VersionListResult>> findAppVersionList(VersionListParam param);

    VersionListResult findAppVersionDetail(VersionDelParam param);

    StakingSwitchResult getStakingSwitch(StakingSwitchParam param, PlatformEnum platformEnum);

    AppVersion getLastVersion(PlatformEnum platform, RegionEnum region, LanguageEnum language);

    Integer hasUpdate(String versionNow, String versionBase);

    AppConfigResult findAppConfig(AppConfigParam param, PlatformEnum platform);

    VersionCheckV4Result checkUpdateV4(VersionCheckParam param, PlatformEnum platform);

    AppConfigV2Result findAppV2Config(AppConfigParam param, PlatformEnum platform);

    AppVersion getVersion(String version, RegionEnum region, SecretPlatform secretPlatform);
}
