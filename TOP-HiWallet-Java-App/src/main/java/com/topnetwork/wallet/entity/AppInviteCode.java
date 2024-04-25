package com.topnetwork.wallet.entity;

import com.base.core.framework.sql.entity.BaseExt;
import com.gitee.magic.jdbc.persistence.annotation.Entity;
import com.gitee.magic.jdbc.persistence.annotation.Table;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.ColumnDef;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.Indexes;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.TableDef;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.indexes.Normal;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.indexes.Unique;

import java.util.Date;

@Entity("appInviteCode")
@Table("wal_app_invite_code")
@TableDef(comment = "App邀请码表")
public class AppInviteCode extends BaseExt {

    private static final long serialVersionUID = 1L;

    public AppInviteCode() {
    }

    @ColumnDef(comment = "邀请人uid", isNull = true, indexes = @Indexes(unique = @Unique))
    private String uid;
    @ColumnDef(comment = "邀请码")
    private String code;
    @ColumnDef(comment = "过期时间")
    private Date expirationTime;
    @ColumnDef(comment = "创建时间")
    private Date createTime;


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(Date expirationTime) {
        this.expirationTime = expirationTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
