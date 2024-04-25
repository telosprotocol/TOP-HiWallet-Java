package com.topnetwork.wallet.result.wallet.activity;

import com.topnetwork.wallet.common.enums.BalanceType;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.math.BigInteger;

public class ActivityListResult {

    @Schema(title = "活动标题")
    private String title;
    @Schema(title = "活动描述")
    private String desc;
    @Schema(title = "背景图片url")
    private String backgroundUrl;
    @Schema(title = "活动id")
    private Long aid;
    @Schema(title = "活动地址")
    private String activityUrl;
    @Schema(title = "活动历史分数")
    private BigDecimal record;
    @Schema(title = "门票价格")
    private Integer ticketPrice;
    @Schema(title = "完成人数次数描述")
    private String timeDesc;
    @Schema(title = "单位")
    private BalanceType unit;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getBackgroundUrl() {
        return backgroundUrl;
    }

    public void setBackgroundUrl(String backgroundUrl) {
        this.backgroundUrl = backgroundUrl;
    }

    public Long getAid() {
        return aid;
    }

    public void setAid(Long aid) {
        this.aid = aid;
    }

    public String getActivityUrl() {
        return activityUrl;
    }

    public void setActivityUrl(String activityUrl) {
        this.activityUrl = activityUrl;
    }

    public BigDecimal getRecord() {
        return record;
    }

    public void setRecord(BigDecimal record) {
        this.record = record;
    }

    public Integer getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(Integer ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public String getTimeDesc() {
        return timeDesc;
    }

    public void setTimeDesc(String timeDesc) {
        this.timeDesc = timeDesc;
    }

    public BalanceType getUnit() {
        return unit;
    }

    public void setUnit(BalanceType unit) {
        this.unit = unit;
    }
}
