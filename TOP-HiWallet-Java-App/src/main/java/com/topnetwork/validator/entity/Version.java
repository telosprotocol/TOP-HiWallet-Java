package com.topnetwork.validator.entity;

import com.base.core.framework.sql.entity.BaseExt;
import com.gitee.magic.jdbc.persistence.annotation.Entity;
import com.gitee.magic.jdbc.persistence.annotation.Table;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.ColumnDef;
import com.topnetwork.wallet.common.enums.PlatformEnum;

@Entity("version")
@Table("val_version")
public class Version extends BaseExt {

    private static final long serialVersionUID = 1L;

    public Version() {
    }

    @ColumnDef(comment = "版本号")
    private String version;

    @ColumnDef(comment = "平台")
    private PlatformEnum platform;

    @ColumnDef(comment = "是否开启defi模块")
    private Boolean openDefi;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public PlatformEnum getPlatform() {
        return platform;
    }

    public void setPlatform(PlatformEnum platform) {
        this.platform = platform;
    }

    public Boolean getOpenDefi() {
        return openDefi;
    }

    public void setOpenDefi(Boolean openDefi) {
        this.openDefi = openDefi;
    }

    @Override
    public String toString() {
        return "Version{" +
                "version='" + version + '\'' +
                ", platform=" + platform +
                ", openDefi=" + openDefi +
                '}';
    }
}
