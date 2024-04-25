package com.topnetwork.wallet.result.wallet.withdraw;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Date;

/**
 * @ClassName GetOrderPageResult
 * @Description
 * @Author bran
 * @Date 2020/7/7 11:53
 */
public class GetOrderPageResult extends AirdropDetailResult {

    @Schema(title="订单id")
    private Long orderId;
    @Schema(title="用户uid")
    private String uid;
    @Schema(title="用户电话")
    private String phone;
    @Schema(title="审核人名字")
    private String name;
    @Schema(title="批次id")
    private String batchId;

    private Date startDate;
    private Date endDate;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }
}
