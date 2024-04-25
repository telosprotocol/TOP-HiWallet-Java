package com.topnetwork.wallet.entity;

import com.topnetwork.wallet.common.enums.ChainTypeEnum;
import com.base.core.framework.sql.entity.Base;
import com.gitee.magic.jdbc.persistence.annotation.Entity;
import com.gitee.magic.jdbc.persistence.annotation.Table;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.ColumnDef;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.Indexes;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.TableDef;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.indexes.Normal;

import java.util.Date;

@Entity("deviceCoins")
@Table("wal_deviceCoins")
@TableDef(comment = "设备币地址关系表")
public class DeviceCoins extends Base {

    private static final long serialVersionUID = 1L;

    public DeviceCoins() {
    }

    @ColumnDef(comment = "对应设备表id", indexes = @Indexes(normal = @Normal))
    private Long deviceId;
    @ColumnDef(comment = "主链类型")
    private ChainTypeEnum chainType;
    @ColumnDef(comment = "合约地址", isNull = true)
    private String contract;
    @ColumnDef(comment = "账户地址")
    private String address;
    @ColumnDef(comment = "是否有效账户（存币账户）", indexes = @Indexes(normal = @Normal))
    private Boolean effectiveAddress;
    @ColumnDef(comment = "创建时间")
    private Date createTime;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public ChainTypeEnum getChainType() {
        return chainType;
    }

    public void setChainType(ChainTypeEnum chainType) {
        this.chainType = chainType;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public Boolean getEffectiveAddress() {
        return effectiveAddress;
    }

    public void setEffectiveAddress(Boolean effectiveAddress) {
        this.effectiveAddress = effectiveAddress;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
