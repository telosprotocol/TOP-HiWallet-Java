package com.topnetwork.wallet.entity;

import com.topnetwork.wallet.common.enums.discover.AppType;
import com.base.core.framework.sql.entity.BaseExt;
import com.gitee.magic.jdbc.persistence.annotation.Entity;
import com.gitee.magic.jdbc.persistence.annotation.Table;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.ColumnDef;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.Indexes;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.TableDef;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.indexes.Normal;

@Entity("appDiscoverGroupI18n")
@Table("wal_app_discover_group_i18n")
@TableDef(comment = "应用i18n与分组关联关系表")
public class AppDiscoverGroupI18n extends BaseExt {

    private static final long serialVersionUID = 1L;

    public AppDiscoverGroupI18n() {
    }

    @ColumnDef(comment = "i18n id", indexes = @Indexes(normal = @Normal))
    private Long i18nId;
    @ColumnDef(comment = "分组 id", indexes = @Indexes(normal = @Normal))
    private Long groupId;
    @ColumnDef(comment = "应用类型")
    private AppType appType;
    @ColumnDef(comment = "是否展示到主页")
    private Boolean showToHome;
    @ColumnDef(comment = "排序，数字越小越靠前")
    private Integer appOrder;


    public Long getI18nId() {
        return i18nId;
    }

    public void setI18nId(Long i18nId) {
        this.i18nId = i18nId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public AppType getAppType() {
        return appType;
    }

    public void setAppType(AppType appType) {
        this.appType = appType;
    }

    public Boolean getShowToHome() {
        return showToHome;
    }

    public void setShowToHome(Boolean showToHome) {
        this.showToHome = showToHome;
    }

    public Integer getAppOrder() {
        return appOrder;
    }

    public void setAppOrder(Integer appOrder) {
        this.appOrder = appOrder;
    }
}
