package com.topnetwork.wallet.result.wallet.activity;

import com.topnetwork.wallet.common.enums.UpsAndDowns;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.Date;

public class BtcPriceListResult {

    @Schema(title = "id")
    private Long id;
    @Schema(title = "活动期数")
    private String phase;
    @Schema(title = "btc收盘价格")
    private BigDecimal closePrice;
    @Schema(title = "价格波动")
    private String percentage;
    @Schema(title = "涨跌")
    private UpsAndDowns status;
    @Schema(title = "创建时间")
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    public BigDecimal getClosePrice() {
        return closePrice;
    }

    public void setClosePrice(BigDecimal closePrice) {
        this.closePrice = closePrice;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    public UpsAndDowns getStatus() {
        return status;
    }

    public void setStatus(UpsAndDowns status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
