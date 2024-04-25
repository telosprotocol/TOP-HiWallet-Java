package com.topnetwork.wallet.result.wallet.home;

import com.base.core.head.converter.UploadKeyConverterEditor;
import com.topnetwork.wallet.common.enums.discover.AppType;
import com.topnetwork.wallet.common.enums.discover.DappShowType;

import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.converter.PropertyConverter;

/**
 * @ClassName AppListResult
 * @Description
 * @Author bran
 * @Date 2020/9/24 17:09
 */
public class AppListResult {

    @Schema(title="记录id")
    private Long id;
    @Schema(title="应用id")
    private Long appId;
    @Schema(title="应用类型")
    private AppType appType;
    @Schema(title="应用名称")
    private String appName;
    @Schema(title="应用图标icon")
    @PropertyConverter(UploadKeyConverterEditor.class)
    private String icon;
    @Schema(title="app排序")
    private Integer appOrder;
    @Schema(title = "展示类型（ 审核时可用：examine, 非审核时可用： normal, 无限制、任何时候都显示： all）")
    private DappShowType showType;

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

    public Integer getAppOrder() {
        return appOrder;
    }

    public void setAppOrder(Integer appOrder) {
        this.appOrder = appOrder;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DappShowType getShowType() {
        return showType;
    }

    public void setShowType(DappShowType showType) {
        this.showType = showType;
    }
}
