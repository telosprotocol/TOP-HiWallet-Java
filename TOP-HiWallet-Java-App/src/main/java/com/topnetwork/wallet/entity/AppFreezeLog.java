package com.topnetwork.wallet.entity;

import com.topnetwork.wallet.common.enums.withdraw.FreezeType;
import com.base.core.framework.sql.entity.BaseExt;
import com.gitee.magic.jdbc.persistence.annotation.Entity;
import com.gitee.magic.jdbc.persistence.annotation.Table;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.ColumnDef;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.Indexes;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.indexes.Normal;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.indexes.Unique;

import java.math.BigDecimal;

@Entity("appFreezeLog")
@Table("wal_app_freeze_log")
public class AppFreezeLog extends BaseExt {

    private static final long serialVersionUID = 1L;

    public AppFreezeLog() {
    }

    @ColumnDef(comment = "创建订单用户id，wal_app_user表中用户", indexes = @Indexes(normal = @Normal))
    private Long userId;
    @ColumnDef(comment = "数量")
    private BigDecimal amount;
    @ColumnDef(comment = "操作类型，冻结、解冻", indexes = @Indexes(normal = @Normal))
    private FreezeType type;


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

    public FreezeType getType() {
        return type;
    }

    public void setType(FreezeType type) {
        this.type = type;
    }
}
