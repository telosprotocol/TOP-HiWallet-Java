package com.topnetwork.recharge.entity;

import com.base.core.framework.sql.entity.BaseExt;
import com.gitee.magic.jdbc.persistence.annotation.Entity;
import com.gitee.magic.jdbc.persistence.annotation.Table;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.ColumnDef;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.Indexes;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.TableDef;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.indexes.Normal;

import java.math.BigDecimal;

@Entity("appUserRecharge")
@Table("wal_app_user_recharge")
@TableDef(comment = "新青年充值记录表")
public class AppUserRecharge extends BaseExt {

    private static final long serialVersionUID = 1L;

    public AppUserRecharge() {
    }

    @ColumnDef(comment = "用户id", indexes = @Indexes(normal = @Normal))
    private Long userId;
    @ColumnDef(comment = "充值数量")
    private BigDecimal amount;
    @ColumnDef(comment = "操作人id")
    private Long createId;
    @ColumnDef(comment = "证书编号", indexes = @Indexes(normal = @Normal))
    private String certNum;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getCreateId() {
        return createId;
    }

    public void setCreateId(Long createId) {
        this.createId = createId;
    }

    public String getCertNum() {
        return certNum;
    }

    public void setCertNum(String certNum) {
        this.certNum = certNum;
    }
}
