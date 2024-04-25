package com.topnetwork.wallet.entity;

import com.topnetwork.wallet.common.enums.BalanceType;
import com.base.core.framework.sql.entity.Base;
import com.gitee.magic.jdbc.persistence.annotation.Entity;
import com.gitee.magic.jdbc.persistence.annotation.Table;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.ColumnDef;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.Indexes;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.ScriptConverter;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.indexes.Normal;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.converter.FieldDecimal;

import java.math.BigDecimal;
import java.util.Date;

@Entity("appUserBalance")
@Table("wal_app_user_balance")
public class AppUserBalance extends Base {

    private static final long serialVersionUID = 1L;

    public AppUserBalance() {
    }

    @ColumnDef(comment = "用户id", indexes = @Indexes(normal = @Normal))
    private Long uid;
    @ColumnDef(comment = "总余额")
    @ScriptConverter(FieldDecimal.class)
    private BigDecimal balance;
    @ColumnDef(comment = "冻结余额")
    @ScriptConverter(FieldDecimal.class)
    private BigDecimal freezeBalance;
    @ColumnDef(comment = "余额单位（余额类型 TOP, BTC, EOS）")
    private BalanceType type;
    @ColumnDef(comment = "创建时间")
    private Date createTime;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BalanceType getType() {
        return type;
    }

    public void setType(BalanceType type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getFreezeBalance() {
        return freezeBalance;
    }

    public void setFreezeBalance(BigDecimal freezeBalance) {
        this.freezeBalance = freezeBalance;
    }
}
