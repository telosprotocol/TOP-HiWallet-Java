package com.topnetwork.wallet.entity;

import com.topnetwork.wallet.common.enums.BalanceType;
import com.topnetwork.wallet.common.enums.GuessStatus;
import com.topnetwork.wallet.common.enums.UpsAndDowns;
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

@Entity("appActivityHist")
@Table("wal_app_game_hist")
public class AppGameHist extends Base {

    private static final long serialVersionUID = 1L;

    public AppGameHist() {
    }

    @ColumnDef(comment = "活动id",indexes = @Indexes(normal = @Normal))
    private Long aid;
    @ColumnDef(comment = "用户唯一标识")
    private Long uid;
    @ColumnDef(comment = "门票id", isNull = true)
    private Long rewardId;
    @ColumnDef(comment = "游戏分数", isNull = true)
    private Integer record;
    @ColumnDef(comment = "游戏期数")
    private String phase;
    @ColumnDef(comment = "押金", isNull = true)
    private Integer amount;
    @ColumnDef(comment = "复活次数", isNull = true)
    private Integer reset;
    @ColumnDef(comment = "奖励金额", isNull = true)
    @ScriptConverter(FieldDecimal.class)
    private BigDecimal reward;
    @ColumnDef(comment = "涨跌（RISE,FALL）", isNull = true)
    private UpsAndDowns upsAndDowns;
    @ColumnDef(comment = "状态（成功，失败，待公布/待完成）")
    private GuessStatus status;
    @ColumnDef(comment = "创建时间")
    private Date createTime;
    @ColumnDef(comment = "奖励单位")
    private BalanceType unit;

    public Long getAid() {
        return aid;
    }

    public void setAid(Long aid) {
        this.aid = aid;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getRewardId() {
        return rewardId;
    }

    public void setRewardId(Long rewardId) {
        this.rewardId = rewardId;
    }

    public Integer getRecord() {
        return record;
    }

    public void setRecord(Integer record) {
        this.record = record;
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public BigDecimal getReward() {
        return reward;
    }

    public void setReward(BigDecimal reward) {
        this.reward = reward;
    }

    public UpsAndDowns getUpsAndDowns() {
        return upsAndDowns;
    }

    public void setUpsAndDowns(UpsAndDowns upsAndDowns) {
        this.upsAndDowns = upsAndDowns;
    }

    public GuessStatus getStatus() {
        return status;
    }

    public void setStatus(GuessStatus status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getReset() {
        return reset;
    }

    public void setReset(Integer reset) {
        this.reset = reset;
    }

    public BalanceType getUnit() {
        return unit;
    }

    public void setUnit(BalanceType unit) {
        this.unit = unit;
    }
}
