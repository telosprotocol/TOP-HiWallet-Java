package com.topnetwork.wallet.entity;

import com.topnetwork.wallet.common.enums.BalanceType;
import com.topnetwork.wallet.common.enums.GrantType;
import com.base.core.framework.sql.entity.Base;
import com.gitee.magic.jdbc.persistence.annotation.Entity;
import com.gitee.magic.jdbc.persistence.annotation.Table;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.ColumnDef;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.Indexes;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.indexes.Normal;

import java.util.Date;

@Entity("appBehaviorConfig")
@Table("wal_app_behavior_config")
public class AppBehaviorConfig extends Base {

    private static final long serialVersionUID = 1L;

    public AppBehaviorConfig() {
    }

    @ColumnDef(comment = "活动id", indexes = @Indexes(normal = @Normal))
    private Long aid;
    @ColumnDef(comment = "每天参与次数")
    private Integer timeInDay;
    @ColumnDef(comment = "每次发放期数")
    private Integer timeInOnce;
    @ColumnDef(comment = "每期间隔时长（h）")
    private Integer growthTime;
    @ColumnDef(comment = "每期泡泡个数")
    private Integer sizeInOnce;
    @ColumnDef(comment = "奖励最小金额")
    private Integer minAmount;
    @ColumnDef(comment = "奖励最大金额")
    private Integer maxAmount;
    @ColumnDef(comment = "首次发放时间（当日，次日）")
    private GrantType fristTime;
    @ColumnDef(comment = "创建时间")
    private Date createTime;
    @ColumnDef(comment = "奖励单位")
    private BalanceType unit;
    @ColumnDef(comment = "更新时间", isNull = true)
    private Date updateTime;

    public Long getAid() {
        return aid;
    }

    public void setAid(Long aid) {
        this.aid = aid;
    }

    public Integer getTimeInDay() {
        return timeInDay;
    }

    public void setTimeInDay(Integer timeInDay) {
        this.timeInDay = timeInDay;
    }

    public Integer getTimeInOnce() {
        return timeInOnce;
    }

    public void setTimeInOnce(Integer timeInOnce) {
        this.timeInOnce = timeInOnce;
    }

    public Integer getGrowthTime() {
        return growthTime;
    }

    public void setGrowthTime(Integer growthTime) {
        this.growthTime = growthTime;
    }

    public Integer getSizeInOnce() {
        return sizeInOnce;
    }

    public void setSizeInOnce(Integer sizeInOnce) {
        this.sizeInOnce = sizeInOnce;
    }

    public Integer getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(Integer minAmount) {
        this.minAmount = minAmount;
    }

    public Integer getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(Integer maxAmount) {
        this.maxAmount = maxAmount;
    }

    public GrantType getFristTime() {
        return fristTime;
    }

    public void setFristTime(GrantType fristTime) {
        this.fristTime = fristTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public BalanceType getUnit() {
        return unit;
    }

    public void setUnit(BalanceType unit) {
        this.unit = unit;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
