package com.topnetwork.wallet.entity;

import com.topnetwork.wallet.common.enums.ChainTypeEnum;
import com.base.core.framework.sql.entity.Base;
import com.gitee.magic.jdbc.persistence.annotation.Entity;
import com.gitee.magic.jdbc.persistence.annotation.Table;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.ColumnDef;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.Indexes;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.indexes.Normal;

import java.math.BigDecimal;
import java.util.Date;

@Entity("appCoinFee")
@Table("wal_coin_fee")
public class AppCoinFee extends Base {

    private static final long serialVersionUID = 1L;

    public AppCoinFee() {
    }

    @ColumnDef(comment = "主链类型 ETH,BTC,TOP...",indexes = @Indexes(normal = @Normal))
    private ChainTypeEnum chainType;
    @ColumnDef(comment = "描述")
    private String chineseDesc;
    @ColumnDef(comment = "交易费数量")
    private BigDecimal amount;
    @ColumnDef(comment = "创建人id")
    private Long createId;
    @ColumnDef(comment = "创建时间")
    private Date createTime;

    public ChainTypeEnum getChainType() {
        return chainType;
    }

    public void setChainType(ChainTypeEnum chainType) {
        this.chainType = chainType;
    }

    public String getChineseDesc() {
        return chineseDesc;
    }

    public void setChineseDesc(String chineseDesc) {
        this.chineseDesc = chineseDesc;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
