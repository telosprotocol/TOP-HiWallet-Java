package com.topnetwork.wallet.entity;

import com.topnetwork.wallet.common.enums.discover.BannerNewType;
import com.topnetwork.wallet.common.enums.discover.DappShowType;
import com.base.core.framework.sql.entity.BaseExt;
import com.gitee.magic.jdbc.persistence.annotation.Entity;
import com.gitee.magic.jdbc.persistence.annotation.Table;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.ColumnDef;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.Indexes;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.TableDef;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.indexes.Normal;

@Entity("appDiscoverBannerNew")
@Table("wal_app_discover_banner_new")
@TableDef(comment = "新版banner表")
public class AppDiscoverBannerNew extends BaseExt {

    private static final long serialVersionUID = 1L;

    public AppDiscoverBannerNew() {
    }

    @ColumnDef(comment = "banner类型", indexes = @Indexes(normal = @Normal))
    private BannerNewType bannerType;
    @ColumnDef(comment = "目标id，绑定的h5/dapp/app/专业应用id", isNull = true)
    private Long targetId;
    @ColumnDef(comment = "跳转url", isNull = true)
    private String url;
    @ColumnDef(comment = "是否展示")
    private Boolean bannerShow;
    @ColumnDef(comment = "banner标题")
    private String title;
    @ColumnDef(comment = "展示类型（ 审核时可用：examine, 非审核时可用： normal, 无限制、任何时候都显示： all）")
    private DappShowType showType;

    public BannerNewType getBannerType() {
        return bannerType;
    }

    public void setBannerType(BannerNewType bannerType) {
        this.bannerType = bannerType;
    }

    public Long getTargetId() {
        return targetId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getBannerShow() {
        return bannerShow;
    }

    public void setBannerShow(Boolean bannerShow) {
        this.bannerShow = bannerShow;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public DappShowType getShowType() {
        return showType;
    }

    public void setShowType(DappShowType showType) {
        this.showType = showType;
    }
}
