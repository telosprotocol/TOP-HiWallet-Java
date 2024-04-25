package com.topnetwork.wallet.entity;

import com.topnetwork.wallet.common.enums.PlatformEnum;
import com.base.core.framework.sql.entity.Base;
import com.gitee.magic.jdbc.persistence.annotation.Entity;
import com.gitee.magic.jdbc.persistence.annotation.Table;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.ColumnDef;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.Indexes;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.indexes.Normal;

@Entity("secretPlatform")
@Table("wal_secretPlatform")
public class SecretPlatform extends Base {

    private static final long serialVersionUID = 1L;

    public SecretPlatform() {
    }

    @ColumnDef(comment = "访问id",indexes = @Indexes(normal = @Normal))
    private String accessId;

    @ColumnDef(comment = "平台")
    private PlatformEnum platform;

    public String getAccessId() {
        return accessId;
    }

    public void setAccessId(String accessId) {
        this.accessId = accessId;
    }

    public PlatformEnum getPlatform() {
        return platform;
    }

    public void setPlatform(PlatformEnum platform) {
        this.platform = platform;
    }
}
