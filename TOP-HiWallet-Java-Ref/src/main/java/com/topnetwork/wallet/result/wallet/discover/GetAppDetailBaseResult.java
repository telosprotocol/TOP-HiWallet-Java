package com.topnetwork.wallet.result.wallet.discover;

import com.base.core.head.converter.UploadKeyConverterEditor;
import com.topnetwork.wallet.common.enums.discover.AppType;

import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.converter.PropertyConverter;

/**
 * @ClassName GetAppDetailBaseResult
 * @Description
 * @Author bran
 * @Date 2020/5/26 11:08
 */
public class GetAppDetailBaseResult {

    @Schema(title="app类型 PRO;HTML;DAPP;APP")
    private AppType type;
    @Schema(title="应用icon")
    @PropertyConverter(UploadKeyConverterEditor.class)
    private String imageUrl;
    @Schema(title="是否显示")
    private Boolean appShow;
    @Schema(title="应用备注标题")
    private String title;
    @Schema(title="app id")
    private Long id;

    public AppType getType() {
        return type;
    }

    public void setType(AppType type) {
        this.type = type;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Boolean getAppShow() {
        return appShow;
    }

    public void setAppShow(Boolean appShow) {
        this.appShow = appShow;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
