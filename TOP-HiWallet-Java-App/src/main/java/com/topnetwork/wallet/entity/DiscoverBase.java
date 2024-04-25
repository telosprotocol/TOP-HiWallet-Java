package com.topnetwork.wallet.entity;

import com.base.core.framework.sql.entity.BaseExt;
import com.gitee.magic.jdbc.persistence.annotation.MappedSuperclass;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.ColumnDef;

/**
 * @ClassName DiscoverBase
 * @Description
 * @Author bran
 * @Date 2020/5/21 10:36
 */
@MappedSuperclass
public class DiscoverBase extends BaseExt {

    private static final long serialVersionUID = 1L;

    @ColumnDef(comment = "应用icon")
    private String imageUrl;
    @ColumnDef(comment = "是否展示")
    private Boolean appShow;
    @ColumnDef(comment = "备注标题")
    private String title;

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
}
