package com.topnetwork.wallet.entity;

import com.topnetwork.wallet.common.enums.discover.AppType;
import com.base.core.framework.sql.entity.BaseExt;
import com.gitee.magic.jdbc.persistence.annotation.Entity;
import com.gitee.magic.jdbc.persistence.annotation.Table;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.ColumnDef;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.Indexes;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.TableDef;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.indexes.Normal;

@Entity("appDiscoverLabelApplication")
@Table("wal_app_discover_label_application")
@TableDef(comment = "应用标签关联关系表")
public class AppDiscoverLabelApplication extends BaseExt {

    private static final long serialVersionUID = 1L;

    public AppDiscoverLabelApplication() {
    }

    @ColumnDef(comment = "应用id", indexes = @Indexes(normal = @Normal))
    private Long appId;
    @ColumnDef(comment = "标签id", indexes = @Indexes(normal = @Normal))
    private Long labelId;
    @ColumnDef(comment = "应用类型")
    private AppType appType;

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public Long getLabelId() {
        return labelId;
    }

    public void setLabelId(Long labelId) {
        this.labelId = labelId;
    }

    public AppType getAppType() {
        return appType;
    }

    public void setAppType(AppType appType) {
        this.appType = appType;
    }
}
