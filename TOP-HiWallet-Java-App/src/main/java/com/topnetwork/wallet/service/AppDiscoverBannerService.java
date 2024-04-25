package com.topnetwork.wallet.service;

import com.topnetwork.wallet.entity.SecretPlatform;
import com.topnetwork.wallet.param.wallet.discover.*;
import com.topnetwork.wallet.result.wallet.discover.BannerListResult;
import com.topnetwork.wallet.result.wallet.discover.GetBannersResult;
import com.topnetwork.wallet.result.wallet.discover.GetBannersV2Result;
import com.gitee.magic.framework.base.context.Http;
import com.gitee.magic.framework.base.result.QueryResult;
import com.base.core.framework.sql.service.SqlBaseService;
import com.topnetwork.wallet.entity.AppDiscoverBanner;

import java.util.List;

public interface AppDiscoverBannerService extends SqlBaseService<AppDiscoverBanner,Long> {

    List<GetBannersResult> getBanners(GetBannersParam param);

    QueryResult<List<BannerListResult>> findBannerList(BannerListParam param);

    void updateBanner(BannerUpdateParam param);

    void addBanner(BannerAddParam param);

    List<GetBannersV2Result> getV2Banners(GetBannersParam param);

    List<GetBannersV2Result> getV3Banners(GetBannersV3Param param, SecretPlatform secretPlatform);

    List<GetBannersV2Result> getV4Banners(GetBannersV4Param param, SecretPlatform secretPlatform, String requestIP);
}
