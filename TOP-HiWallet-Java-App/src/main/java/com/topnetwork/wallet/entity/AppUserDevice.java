package com.topnetwork.wallet.entity;

import com.base.core.framework.sql.entity.Base;
import com.gitee.magic.jdbc.persistence.annotation.Entity;
import com.gitee.magic.jdbc.persistence.annotation.Table;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.ColumnDef;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.Indexes;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.indexes.Normal;

import java.util.Date;

@Entity("appUserDevice")
@Table("wal_app_user_device")
public class AppUserDevice extends Base {

    private static final long serialVersionUID = 1L;

    public AppUserDevice() {
    }

    @ColumnDef(comment = "用户唯一标识",indexes = @Indexes(normal = @Normal))
    private Long uid;
    @ColumnDef(comment = "创建时间")
    private Date createTime;
    @ColumnDef(comment = "设备id")
    private String deviceId;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}
