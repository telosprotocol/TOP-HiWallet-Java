package com.topnetwork.wallet.entity;

import com.topnetwork.wallet.common.enums.withdraw.OrderStatus;
import com.base.core.framework.sql.entity.BaseExt;
import com.gitee.magic.jdbc.persistence.annotation.Entity;
import com.gitee.magic.jdbc.persistence.annotation.Table;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.ColumnDef;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.Indexes;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.TableDef;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.indexes.Normal;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.indexes.Unique;

import java.math.BigDecimal;
import java.util.Date;

@Entity("appWithdrawOrder")
@Table("wal_app_withdraw_order")
@TableDef(comment = "提现订单表")
public class AppWithdrawOrder extends BaseExt {

    private static final long serialVersionUID = 1L;

    public AppWithdrawOrder() {
    }

    @ColumnDef(comment = "创建订单用户id，wal_app_user表中用户", indexes = @Indexes(normal = @Normal))
    private Long userId;
    @ColumnDef(comment = "交易号", indexes = @Indexes(unique = @Unique))
    private String tranId;
    @ColumnDef(comment = "批次号", indexes = @Indexes(normal = @Normal), isNull = true)
    private String batchId;
    @ColumnDef(comment = "提现数量，已扣除手续费的数量")
    private BigDecimal amount;
    @ColumnDef(comment = "交易费")
    private BigDecimal fee;
    @ColumnDef(comment = "提现到账地址")
    private String address;
    @ColumnDef(comment = "订单状态")
    private OrderStatus status;
    @ColumnDef(comment = "审核时间", isNull = true)
    private Date reviewTime;
    @ColumnDef(comment = "完成时间", isNull = true)
    private Date endTime;
    @ColumnDef(comment = "审核人，sys_user表中用户", isNull = true)
    private Long reviewUserId;


    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTranId() {
        return tranId;
    }

    public void setTranId(String tranId) {
        this.tranId = tranId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Date getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(Date reviewTime) {
        this.reviewTime = reviewTime;
    }

    public Long getReviewUserId() {
        return reviewUserId;
    }

    public void setReviewUserId(Long reviewUserId) {
        this.reviewUserId = reviewUserId;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
