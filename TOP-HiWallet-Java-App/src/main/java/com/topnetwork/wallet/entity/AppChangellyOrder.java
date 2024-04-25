package com.topnetwork.wallet.entity;

import com.topnetwork.wallet.common.enums.changelly.OrderStatusEnum;
import com.base.core.framework.sql.entity.BaseExt;
import com.gitee.magic.jdbc.persistence.annotation.Entity;
import com.gitee.magic.jdbc.persistence.annotation.Table;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.ColumnDef;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.Indexes;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.TableDef;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.indexes.Normal;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.indexes.Unique;

import java.math.BigDecimal;

@Entity("appChangellyOrder")
@Table("wal_app_changelly_order")
@TableDef(comment = "币币兑换订单")
public class AppChangellyOrder extends BaseExt {

    private static final long serialVersionUID = 1L;

    public AppChangellyOrder() {
    }

    @ColumnDef(comment = "用户id", indexes = @Indexes(normal = @Normal))
    private String uid;
    @ColumnDef(comment = "changelly交易id", indexes = @Indexes(unique = @Unique))
    private String tranId;
    @ColumnDef(comment = "changelly查询交易URL")
    private String trackUrl;
    @ColumnDef(comment = "第三方收到客户转账时间", isNull = true)
    private Long moneyReceived;
    @ColumnDef(comment = "第三方发送交易时间", isNull = true)
    private Long moneySent;
    @ColumnDef(comment = "费率", isNull = true)
    private BigDecimal rate;
    @ColumnDef(comment = "订单状态", indexes = @Indexes(normal = @Normal))
    private OrderStatusEnum status;
    @ColumnDef(comment = "从当前币换币")
    private String currencyFrom;
    @ColumnDef(comment = "要换的币")
    private String currencyTo;
    @ColumnDef(comment = "要客户转账的地址")
    private String payinAddress;
    @ColumnDef(comment = "客户转账的地址（退款地址）")
    private String payAddress;
    @ColumnDef(comment = "客户转账hash", isNull = true)
    private String payinHash;
    @ColumnDef(comment = "客户接收币账户（第三方需要转账的账户地址）")
    private String payoutAddress;
    @ColumnDef(comment = "第三方转账hash", isNull = true)
    private String payoutHash;
    @ColumnDef(comment = "第三方退款hash", isNull = true)
    private String refundHash;
    @ColumnDef(comment = "第三方接收数量")
    private BigDecimal amountFrom;
    @ColumnDef(comment = "客户获得数量", isNull = true)
    private BigDecimal amountTo;
    @ColumnDef(comment = "客户期望获得数量")
    private BigDecimal amountExpectedTo;
    @ColumnDef(comment = "是否禁用")
    private Boolean disable;


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTranId() {
        return tranId;
    }

    public void setTranId(String tranId) {
        this.tranId = tranId;
    }

    public Long getMoneyReceived() {
        return moneyReceived;
    }

    public void setMoneyReceived(Long moneyReceived) {
        this.moneyReceived = moneyReceived;
    }

    public Long getMoneySent() {
        return moneySent;
    }

    public void setMoneySent(Long moneySent) {
        this.moneySent = moneySent;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public OrderStatusEnum getStatus() {
        return status;
    }

    public void setStatus(OrderStatusEnum status) {
        this.status = status;
    }

    public String getCurrencyFrom() {
        return currencyFrom;
    }

    public void setCurrencyFrom(String currencyFrom) {
        this.currencyFrom = currencyFrom;
    }

    public String getCurrencyTo() {
        return currencyTo;
    }

    public void setCurrencyTo(String currencyTo) {
        this.currencyTo = currencyTo;
    }

    public String getPayinAddress() {
        return payinAddress;
    }

    public void setPayinAddress(String payinAddress) {
        this.payinAddress = payinAddress;
    }

    public String getPayAddress() {
        return payAddress;
    }

    public void setPayAddress(String payAddress) {
        this.payAddress = payAddress;
    }

    public String getPayinHash() {
        return payinHash;
    }

    public void setPayinHash(String payinHash) {
        this.payinHash = payinHash;
    }

    public String getPayoutAddress() {
        return payoutAddress;
    }

    public void setPayoutAddress(String payoutAddress) {
        this.payoutAddress = payoutAddress;
    }

    public String getPayoutHash() {
        return payoutHash;
    }

    public void setPayoutHash(String payoutHash) {
        this.payoutHash = payoutHash;
    }

    public String getRefundHash() {
        return refundHash;
    }

    public void setRefundHash(String refundHash) {
        this.refundHash = refundHash;
    }

    public BigDecimal getAmountFrom() {
        return amountFrom;
    }

    public void setAmountFrom(BigDecimal amountFrom) {
        this.amountFrom = amountFrom;
    }

    public BigDecimal getAmountTo() {
        return amountTo;
    }

    public void setAmountTo(BigDecimal amountTo) {
        this.amountTo = amountTo;
    }

    public BigDecimal getAmountExpectedTo() {
        return amountExpectedTo;
    }

    public void setAmountExpectedTo(BigDecimal amountExpectedTo) {
        this.amountExpectedTo = amountExpectedTo;
    }

    public String getTrackUrl() {
        return trackUrl;
    }

    public void setTrackUrl(String trackUrl) {
        this.trackUrl = trackUrl;
    }

    public Boolean getDisable() {
        return disable;
    }

    public void setDisable(Boolean disable) {
        this.disable = disable;
    }
}
