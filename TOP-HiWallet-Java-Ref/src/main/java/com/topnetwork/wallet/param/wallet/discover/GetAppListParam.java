package com.topnetwork.wallet.param.wallet.discover;

import com.base.core.head.ao.PageAO;
import com.topnetwork.wallet.common.enums.discover.AppType;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @ClassName GetAppListParam
 * @Description
 * @Author bran
 * @Date 2020/5/25 17:11
 */
public class GetAppListParam extends PageAO {

    @Schema(title="app类型 PRO;HTML;DAPP;APP")
    private AppType appType;

    public AppType getAppType() {
        return appType;
    }

    public void setAppType(AppType appType) {
        this.appType = appType;
    }
}
