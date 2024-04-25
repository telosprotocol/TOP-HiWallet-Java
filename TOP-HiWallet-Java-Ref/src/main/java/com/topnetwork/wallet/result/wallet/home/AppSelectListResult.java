package com.topnetwork.wallet.result.wallet.home;

import com.base.core.head.converter.UploadKeyConverterEditor;
import com.topnetwork.wallet.common.enums.discover.AppType;

import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.converter.PropertyConverter;

/**
 * @ClassName AppSelectListResult
 * @Description
 * @Author bran
 * @Date 2020/9/24 17:09
 */
public class AppSelectListResult {

    @Schema(title="应用id")
    private Long appId;
    @Schema(title="应用类型")
    private AppType appType;
    @Schema(title="应用名称")
    private String appName;
    @Schema(title="应用图标icon")
    @PropertyConverter(UploadKeyConverterEditor.class)
    private String icon;

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public AppType getAppType() {
        return appType;
    }

    public void setAppType(AppType appType) {
        this.appType = appType;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

}
