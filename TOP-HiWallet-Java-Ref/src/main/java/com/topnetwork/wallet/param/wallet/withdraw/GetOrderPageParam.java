package com.topnetwork.wallet.param.wallet.withdraw;

import com.base.core.head.ao.PageAO;
import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.common.enums.withdraw.OrderStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Enum;
import com.gitee.magic.core.valid.annotation.Format;
import com.gitee.magic.core.valid.annotation.Length;

/**
 * @ClassName GetOrderPageParam
 * @Description
 * @Author bran
 * @Date 2020/7/7 11:52
 */
public class GetOrderPageParam extends PageAO {

    @Schema(title = "用户id", required = false)
    @Length(message = CodeRes.CODE_15009)
    private String uid;
    @Schema(title = "交易号", required = false)
    @Length(message = CodeRes.CODE_15009)
    private String tranId;
    @Schema(title = "提现申请开始时间戳", required = false)
    @Length(message = CodeRes.CODE_14024)
    @Format(type = Format.FormatType.LONG, message = CodeRes.CODE_14024)
    private Long startTime;
    @Schema(title = "提现申请结束时间戳", required = false)
    @Length(message = CodeRes.CODE_14024)
    @Format(type = Format.FormatType.LONG, message = CodeRes.CODE_14024)
    private Long endTime;
    @Schema(title = "提现订单状态", required = false)
    @Enum(CodeRes.CODE_15051)
    private OrderStatus status;

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

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
