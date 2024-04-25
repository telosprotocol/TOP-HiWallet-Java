package com.topnetwork.wallet.entity;

import java.util.Date;

import com.topnetwork.wallet.common.enums.ChainTypeEnum;
import com.topnetwork.wallet.common.enums.CoinFeeType;
import com.topnetwork.wallet.common.enums.RequestMethodEnum;

import com.base.core.framework.sql.entity.Base;
import com.gitee.magic.jdbc.persistence.annotation.Entity;
import com.gitee.magic.jdbc.persistence.annotation.Table;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.ColumnDef;

@Entity("appCoinConfig")
@Table("wal_coin_config")
public class AppCoinConfig extends Base {

    private static final long serialVersionUID = 1L;

    public AppCoinConfig() {
    }

    @ColumnDef(comment = "主链类型 ETH,BTC,TOP...")
    private ChainTypeEnum chainType;
    @ColumnDef(comment = "交易费类型 NOFEE,API,SELFCONF")
    private CoinFeeType type;
    @ColumnDef(comment = "api请求地址", isNull = true)
    private String requestUrl;
    @ColumnDef(comment = "api请求参数", isNull = true)
    private String requestParam;
    @ColumnDef(comment = "api请求方法 GET,POST,PUT,DELETE", isNull = true)
    private RequestMethodEnum requestMethod;
    @ColumnDef(comment = "创建人id")
    private Long createId;
    @ColumnDef(comment = "创建时间")
    private Date createTime;
    @ColumnDef(comment = "更新时间", isNull = true)
    private Date updateTime;


    public ChainTypeEnum getChainType() {
        return chainType;
    }

    public void setChainType(ChainTypeEnum chainType) {
        this.chainType = chainType;
    }

    public CoinFeeType getType() {
        return type;
    }

    public void setType(CoinFeeType type) {
        this.type = type;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getRequestParam() {
        return requestParam;
    }

    public void setRequestParam(String requestParam) {
        this.requestParam = requestParam;
    }

    public RequestMethodEnum getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(RequestMethodEnum requestMethod) {
        this.requestMethod = requestMethod;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
