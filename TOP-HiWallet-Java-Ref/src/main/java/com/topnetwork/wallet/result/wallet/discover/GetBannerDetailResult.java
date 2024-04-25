package com.topnetwork.wallet.result.wallet.discover;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

/**
 * @ClassName GetBannerDetailResult
 * @Description
 * @Author bran
 * @Date 2020/5/25 14:40
 */
public class GetBannerDetailResult extends GetBannerListResult {

    @Schema(title="banner i18n信息")
    private List<BannerI18nResult> i18n;

    public List<BannerI18nResult> getI18n() {
        return i18n;
    }

    public void setI18n(List<BannerI18nResult> i18n) {
        this.i18n = i18n;
    }
}
