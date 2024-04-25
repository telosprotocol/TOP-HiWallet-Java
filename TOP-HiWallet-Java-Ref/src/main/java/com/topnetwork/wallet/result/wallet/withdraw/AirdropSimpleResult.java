package com.topnetwork.wallet.result.wallet.withdraw;

import com.topnetwork.wallet.common.enums.withdraw.OrderStatus;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

/**
 * @ClassName AirdropSimpleResult
 * @Description
 * @Author bran
 * @Date 2020/7/7 15:07
 */
public class AirdropSimpleResult {

    @Schema(title="创建时间/申请时间")
    private Long createTime;
    @Schema(title="可到账数量")
    private BigDecimal amount;
    @Schema(title="订单状态")
    private OrderStatus status;
    @Schema(title="交易号")
    private String tranId;

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String getTranId() {
        return tranId;
    }

    public void setTranId(String tranId) {
        this.tranId = tranId;
    }
}
