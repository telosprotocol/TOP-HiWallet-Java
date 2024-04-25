package com.topnetwork.wallet.entity;

import com.topnetwork.wallet.common.enums.discover.AppType;
import com.topnetwork.wallet.common.enums.discover.DappShowType;
import com.base.core.framework.sql.entity.BaseExt;
import com.gitee.magic.jdbc.persistence.annotation.Entity;
import com.gitee.magic.jdbc.persistence.annotation.Table;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.ColumnDef;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.Indexes;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.IndexesUnion;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.TableDef;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.indexes.Normal;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.indexes.UniqueUnion;

@Entity("homeApp")
@Table("wal_home_app")
@TableDef(comment = "主页要展示的app表", indexes = @IndexesUnion(
        unique = {@UniqueUnion(fields = {"appId", "appType"})}
))
public class HomeApp extends BaseExt {

    private static final long serialVersionUID = 1L;

    public HomeApp() {
    }

    @ColumnDef(comment = "app id", indexes = @Indexes(normal = @Normal))
    private Long appId;
    @ColumnDef(comment = "应用类型")
    private AppType appType;
    @ColumnDef(comment = "排序，数字越小越靠前")
    private Integer appOrder;
    @ColumnDef(comment = "展示类型（ 审核时可用：examine, 非审核时可用： normal, 无限制、任何时候都显示： all）")
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

    public Integer getAppOrder() {
        return appOrder;
    }

    public void setAppOrder(Integer appOrder) {
        this.appOrder = appOrder;
    }

    public DappShowType getShowType() {
        return showType;
    }

    public void setShowType(DappShowType showType) {
        this.showType = showType;
    }
}
