package com.topnetwork.wallet.entity;

import com.topnetwork.wallet.common.enums.CertType;
import com.base.core.framework.sql.entity.Base;
import com.gitee.magic.jdbc.persistence.annotation.Entity;
import com.gitee.magic.jdbc.persistence.annotation.Table;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.ColumnDef;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.Indexes;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.indexes.Normal;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.indexes.Unique;

import java.util.Date;

@Entity("appUserCert")
@Table("wal_app_user_cert")
public class AppUserCert extends Base {

    private static final long serialVersionUID = 1L;

    public AppUserCert() {
    }

    @ColumnDef(comment = "用户id", indexes = @Indexes(normal = @Normal))
    private Long uid;
    @ColumnDef(comment = "证书编号", indexes = @Indexes(unique = @Unique))
    private String certNum;
    @ColumnDef(comment = "证书类型 NEWUSER新用户,OTHER其他")
    private CertType type;
    @ColumnDef(comment = "创建时间")
    private Date createTime;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getCertNum() {
        return certNum;
    }

    public void setCertNum(String certNum) {
        this.certNum = certNum;
    }

    public CertType getType() {
        return type;
    }

    public void setType(CertType type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
