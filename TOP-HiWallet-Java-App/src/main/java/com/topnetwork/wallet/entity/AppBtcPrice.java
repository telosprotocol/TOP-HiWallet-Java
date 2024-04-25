package com.topnetwork.wallet.entity;

import com.topnetwork.wallet.common.enums.UpsAndDowns;
import com.base.core.framework.sql.entity.Base;
import com.gitee.magic.jdbc.persistence.annotation.Entity;
import com.gitee.magic.jdbc.persistence.annotation.Table;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.ColumnDef;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.ScriptConverter;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.converter.FieldDecimal;

import java.math.BigDecimal;
import java.util.Date;

@Entity("appBtcPrice")
@Table("wal_app_btc_price")
public class AppBtcPrice extends Base {

    private static final long serialVersionUID = 1L;

    public AppBtcPrice() {
    }

    @ColumnDef(comment = "活动期数")
    private String phase;
    @ColumnDef(comment = "开盘价格")
    @ScriptConverter(FieldDecimal.class)
    private BigDecimal openPrice;
    @ColumnDef(comment = "收盘价格", isNull = true)
    @ScriptConverter(FieldDecimal.class)
    private BigDecimal closePrice;
    @ColumnDef(comment = "波动百分比", isNull = true)
    private String percentage;
    @ColumnDef(comment = "涨跌", isNull = true)
    private UpsAndDowns status;
    @ColumnDef(comment = "创建时间")
    private Date createTime;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    public BigDecimal getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(BigDecimal openPrice) {
        this.openPrice = openPrice;
    }

    public BigDecimal getClosePrice() {
        return closePrice;
    }

    public void setClosePrice(BigDecimal closePrice) {
        this.closePrice = closePrice;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    public UpsAndDowns getStatus() {
        return status;
    }

    public void setStatus(UpsAndDowns status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
