package com.topnetwork.wallet.result.recharge;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

/**
 * @ClassName GetRechargeHistoryResult
 * @Description
 * @Author bran
 * @Date 2020/9/4 15:17
 */
public class GetRechargeHistoryResult {

    @Schema(title="新青年号")
    private String certNum;
    @Schema(title="top数量")
    private BigDecimal amount;
    @Schema(title="申请时间")
    private Long createTime;

    public String getCertNum() {
        return certNum;
    }

    public void setCertNum(String certNum) {
        this.certNum = certNum;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}
