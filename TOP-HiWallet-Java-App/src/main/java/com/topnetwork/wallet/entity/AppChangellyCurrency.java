package com.topnetwork.wallet.entity;

import com.base.core.framework.sql.entity.BaseExt;
import com.gitee.magic.jdbc.persistence.annotation.Entity;
import com.gitee.magic.jdbc.persistence.annotation.Table;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.ColumnDef;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.Indexes;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.TableDef;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.indexes.Normal;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.indexes.Unique;

import java.math.BigDecimal;

@Entity("appChangellyCurrency")
@Table("wal_app_changelly_currency")
@TableDef(comment = "币币兑换配置")
public class AppChangellyCurrency extends BaseExt {

    private static final long serialVersionUID = 1L;

    public AppChangellyCurrency() {
    }

    @ColumnDef(comment = "币种id", indexes = @Indexes(unique = @Unique))
    private Long coinId;
    @ColumnDef(comment = "是否禁用")
    private Boolean disable;
    @ColumnDef(comment = "最小值是否自定义")
    private Boolean customized;
    @ColumnDef(comment = "最大交易金额")
    private BigDecimal maxSellAmount;
    @ColumnDef(comment = "最小交易金额", isNull = true)
    private BigDecimal minSellAmount;

    public Long getCoinId() {
        return coinId;
    }

    public void setCoinId(Long coinId) {
        this.coinId = coinId;
    }

    public Boolean getDisable() {
        return disable;
    }

    public void setDisable(Boolean disable) {
        this.disable = disable;
    }

    public Boolean getCustomized() {
        return customized;
    }

    public void setCustomized(Boolean customized) {
        this.customized = customized;
    }

    public BigDecimal getMaxSellAmount() {
        return maxSellAmount;
    }

    public void setMaxSellAmount(BigDecimal maxSellAmount) {
        this.maxSellAmount = maxSellAmount;
    }

    public BigDecimal getMinSellAmount() {
        return minSellAmount;
    }

    public void setMinSellAmount(BigDecimal minSellAmount) {
        this.minSellAmount = minSellAmount;
    }
}
