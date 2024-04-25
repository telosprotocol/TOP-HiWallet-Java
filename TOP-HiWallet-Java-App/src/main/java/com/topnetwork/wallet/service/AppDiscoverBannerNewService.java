package com.topnetwork.wallet.service;

import com.topnetwork.wallet.common.enums.LanguageEnum;
import com.topnetwork.wallet.common.enums.discover.BannerNewType;
import com.topnetwork.wallet.entity.AppDiscoverBannerNewI18n;
import com.topnetwork.wallet.entity.AppVersion;
import com.topnetwork.wallet.entity.SecretPlatform;
import com.topnetwork.wallet.param.wallet.discover.*;
import com.topnetwork.wallet.result.wallet.discover.*;
import com.gitee.magic.framework.base.result.QueryResult;
import com.base.core.framework.sql.service.SqlBaseService;
import com.topnetwork.wallet.entity.AppDiscoverBannerNew;
import com.gitee.magic.core.json.JsonObject;

import java.util.List;

public interface AppDiscoverBannerNewService extends SqlBaseService<AppDiscoverBannerNew, Long> {

    List<GetNewBannersResult> getBanners(GetBannersV4Param param, SecretPlatform secretPlatform);

    List<RecommendNewResult> getRecommendList(GetBannersV4Param param, SecretPlatform secretPlatform, String ip);

    void addUsedHistory(AddNewUsedHistoryParam param);

    QueryResult<List<GetNewBannersResult>> getDiscoverGamesPage(RecommendPageNewParam param, SecretPlatform secretPlatform, String ip);

    GetAppDetailResult getAppDetail(GetAppDetailParam param, SecretPlatform secretPlatform);

    void deleteUsedHistory(AddNewUsedHistoryParam param);

    QueryResult<List<GetBannerListResult>> getBannerList(GetBannerListParam param);

    GetBannerDetailResult getBannerDetail(GetBannerDetailParam param);

    void addBanner(BannerNewAddParam param);

    void updateBanner(BannerNewUpdParam param);

    List<BannerI18nResult> getBannerI18n(GetBannerDetailParam param);

    void editBannerI18n(EditBannerI18nParam param);

    QueryResult<List<GetAppListResult>> getAppList(GetAppListParam param);

    List<GetAppListResult> getAppListAll(GetAppListAllParam param);

    List<LanguageEnum> getLanguage();

    void filterDapp(List<GetNewBannersResult> byGroup, AppVersion appVersion, JsonObject jsonObject, String ip);

    GetNewBannersResult getByIdAndType(Long dappId, BannerNewType type, LanguageEnum language, AppDiscoverBannerNewI18n appDiscoverBannerNewI18n, AppDiscoverBannerNew appDiscoverBannerNew);
}
