package com.topnetwork.wallet.entity;

import com.topnetwork.wallet.common.enums.BalanceType;
import com.topnetwork.wallet.common.enums.GameConfigType;
import com.base.core.framework.sql.entity.Base;
import com.gitee.magic.jdbc.persistence.annotation.Entity;
import com.gitee.magic.jdbc.persistence.annotation.Table;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.ColumnDef;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.Indexes;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.indexes.Normal;

import java.util.Date;

@Entity("appGameConfig")
@Table("wal_app_game_config")
public class AppGameConfig extends Base {

    private static final long serialVersionUID = 1L;

    public AppGameConfig() {
    }

    @ColumnDef(comment = "活动id",indexes = @Indexes(normal = @Normal))
    private Long aid;
    @ColumnDef(comment = "每天参与次数")
    private Integer timeInDay;
    @ColumnDef(comment = "类型（门票，重置，奖励")
    private GameConfigType type;
    @ColumnDef(comment = "游戏分数/重置次数", isNull = true)
    private Integer record;
    @ColumnDef(comment = "分数/重置次数/门票对应消耗或奖励数量")
    private Integer amount;
    @ColumnDef(comment = "备注")
    private String comment;
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

    public GameConfigType getType() {
        return type;
    }

    public void setType(GameConfigType type) {
        this.type = type;
    }

    public Integer getRecord() {
        return record;
    }

    public void setRecord(Integer record) {
        this.record = record;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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
