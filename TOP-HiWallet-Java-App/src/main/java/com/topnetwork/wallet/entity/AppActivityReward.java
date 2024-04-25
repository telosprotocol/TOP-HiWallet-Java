package com.topnetwork.wallet.entity;

import com.topnetwork.wallet.common.enums.BalanceType;
import com.topnetwork.wallet.common.enums.RewardFromType;
import com.topnetwork.wallet.common.enums.RewardSide;
import com.topnetwork.wallet.common.enums.RewardType;
import com.base.core.framework.sql.entity.Base;
import com.gitee.magic.jdbc.persistence.annotation.Entity;
import com.gitee.magic.jdbc.persistence.annotation.Table;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.ColumnDef;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.Indexes;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.ScriptConverter;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.indexes.Normal;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.converter.FieldBoolean;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.converter.FieldDecimal;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.converter.FieldLong;

import java.math.BigDecimal;
import java.util.Date;

@Entity("appActivityReward")
@Table("wal_app_activity_reward")
public class AppActivityReward extends Base {

    private static final long serialVersionUID = 1L;

    public AppActivityReward() {
    }

    @ColumnDef(comment = "用户唯一标识", indexes = @Indexes(normal = @Normal))
    private Long uid;
    @ColumnDef(comment = "对应活动id")
    @ScriptConverter(FieldLong.class)
    private Long aid;
    @ColumnDef(comment = "奖励类型（注册空投、新用户空投、每日空投、转账空投、答题空投、区块链游戏空投/奖励、竞猜空投/奖励）")
    private RewardType type;
    @ColumnDef(comment = "奖励方向（花费、奖励）")
    private RewardSide side;
    @ColumnDef(comment = "奖励来源（空投/游戏奖励）")
    private RewardFromType source;
    @ColumnDef(comment = "奖励数量")
    @ScriptConverter(FieldDecimal.class)
    private BigDecimal amount;
    @ColumnDef(comment = "生效时间")
    private Date effectiveTime;
    @ColumnDef(comment = "过期时间")
    private Date expirationTime;
    @ColumnDef(comment = "是否被验证")
    @ScriptConverter(FieldBoolean.class)
    private Boolean verification;
    @ColumnDef(comment = "是否被领取")
    @ScriptConverter(FieldBoolean.class)
    private Boolean receive;
    @ColumnDef(comment = "备注", isNull = true)
    private String comment;
    @ColumnDef(comment = "创建时间")
    private Date createTime;
    @ColumnDef(comment = "领取时间", isNull = true)
    private Date receiveTime;
    @ColumnDef(comment = "奖励单位")
    private BalanceType unit;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getAid() {
        return aid;
    }

    public void setAid(Long aid) {
        this.aid = aid;
    }

    public RewardType getType() {
        return type;
    }

    public void setType(RewardType type) {
        this.type = type;
    }

    public RewardSide getSide() {
        return side;
    }

    public void setSide(RewardSide side) {
        this.side = side;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(Date effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    public Date getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(Date expirationTime) {
        this.expirationTime = expirationTime;
    }

    public Boolean getVerification() {
        return verification;
    }

    public void setVerification(Boolean verification) {
        this.verification = verification;
    }

    public Boolean getReceive() {
        return receive;
    }

    public void setReceive(Boolean receive) {
        this.receive = receive;
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

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    public RewardFromType getSource() {
        return source;
    }

    public void setSource(RewardFromType source) {
        this.source = source;
    }

    public BalanceType getUnit() {
        return unit;
    }

    public void setUnit(BalanceType unit) {
        this.unit = unit;
    }
}
