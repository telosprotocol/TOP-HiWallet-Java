package com.topnetwork.filecoin.entity;

import com.topnetwork.wallet.common.enums.filecoin.SourceEnum;
import com.base.core.framework.sql.entity.BaseExt;
import com.gitee.magic.jdbc.persistence.annotation.Entity;
import com.gitee.magic.jdbc.persistence.annotation.Table;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.ColumnDef;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.Indexes;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.TableDef;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.indexes.Normal;

import java.util.Date;

@Entity("filedRecords")
@Table("fil_filed_records")
@TableDef(comment = "FiledRecords申请记录")
public class FiledRecords extends BaseExt {

    private static final long serialVersionUID = 1L;

    public FiledRecords() {
    }

    @ColumnDef(comment = "申购开始时间")
    private Date startTime;
    @ColumnDef(comment = "申购结束时间")
    private Date endTime;
    @ColumnDef(comment = "付款地址", indexes = @Indexes(normal = @Normal))
    private String payAddress;
    @ColumnDef(comment = "收款地址")
    private String receiveAddress;
    @ColumnDef(comment = "认购数量")
    private Long amount;
    @ColumnDef(comment = "邮箱", indexes = @Indexes(normal = @Normal))
    private String email;
    @ColumnDef(comment = "手机号", indexes = @Indexes(normal = @Normal))
    private String mobile;
    @ColumnDef(comment = "微信号", indexes = @Indexes(normal = @Normal))
    private String wechat;
    @ColumnDef(comment = "staking 地址", isNull = true)
    private String stakingAddress;
    @ColumnDef(comment = "申请来源")
    private SourceEnum source;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getPayAddress() {
        return payAddress;
    }

    public void setPayAddress(String payAddress) {
        this.payAddress = payAddress;
    }

    public String getReceiveAddress() {
        return receiveAddress;
    }

    public void setReceiveAddress(String receiveAddress) {
        this.receiveAddress = receiveAddress;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getStakingAddress() {
        return stakingAddress;
    }

    public void setStakingAddress(String stakingAddress) {
        this.stakingAddress = stakingAddress;
    }

    public SourceEnum getSource() {
        return source;
    }

    public void setSource(SourceEnum source) {
        this.source = source;
    }
}
