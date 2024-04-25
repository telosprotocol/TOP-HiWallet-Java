package com.topnetwork.wallet.result.changelly;

import com.topnetwork.wallet.common.enums.changelly.OrderStatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

/**
 * @ClassName GetOrderPageResult
 * @Description
 * @Author bran
 * @Date 2020/6/15 16:03
 */
public class GetOrderPageResult {

    @Schema(title="对换订单Id")
    private String exchangeId;
    @Schema(title="订单状态")
    private OrderStatusEnum status;
    @Schema(title="创建时间")
    private Long createTime;
    @Schema(title="卖出币种symbol")
    private String from;
    @Schema(title="买入币种symbol")
    private String to;
    @Schema(title="卖出数量")
    private BigDecimal fromAmount;
    @Schema(title="买入数量")
    private BigDecimal toAmount;


    public OrderStatusEnum getStatus() {
        return status;
    }

    public void setStatus(OrderStatusEnum status) {
        this.status = status;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public BigDecimal getFromAmount() {
        return fromAmount;
    }

    public void setFromAmount(BigDecimal fromAmount) {
        this.fromAmount = fromAmount;
    }

    public BigDecimal getToAmount() {
        return toAmount;
    }

    public void setToAmount(BigDecimal toAmount) {
        this.toAmount = toAmount;
    }

    public String getExchangeId() {
        return exchangeId;
    }

    public void setExchangeId(String exchangeId) {
        this.exchangeId = exchangeId;
    }
}
