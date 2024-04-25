package com.topnetwork.wallet.entity;

import com.base.core.framework.sql.entity.Base;
import com.gitee.magic.jdbc.persistence.annotation.Entity;
import com.gitee.magic.jdbc.persistence.annotation.Table;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.*;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.indexes.Normal;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.indexes.Unique;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.indexes.UniqueUnion;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.converter.FieldDecimal;

import java.math.BigDecimal;
import java.util.Date;

@Entity("appUser")
@Table("wal_app_user")
@TableDef(comment = "钱包新青年用户表",indexes=@IndexesUnion(
        unique ={@UniqueUnion(fields={"countryCode","mobile"})}
))
public class AppUser extends Base {

    private static final long serialVersionUID = 1L;

    public AppUser() {
    }

    @ColumnDef(comment = "用户唯一标识", isNull = true, indexes = @Indexes(unique = @Unique))
    private String uid;
    @ColumnDef(comment = "国家码", isNull = true)
    private Integer countryCode;
    @ColumnDef(indexes = @Indexes(unique = @Unique), length = 32, comment = "手机号码", isNull = true)
    private String mobile;
    @ColumnDef(comment = "用户等级", isNull = true)
    private Integer level;
    @ColumnDef(comment = "是否禁用")
    private Boolean disable;
    @ColumnDef(comment = "创建时间")
    private Date createTime;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Boolean getDisable() {
        return disable;
    }

    public void setDisable(Boolean disable) {
        this.disable = disable;
    }

    public Integer getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(Integer countryCode) {
        this.countryCode = countryCode;
    }
}
