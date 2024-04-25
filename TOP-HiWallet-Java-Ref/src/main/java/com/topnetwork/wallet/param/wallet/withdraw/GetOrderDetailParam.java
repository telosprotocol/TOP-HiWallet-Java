package com.topnetwork.wallet.param.wallet.withdraw;

import com.topnetwork.wallet.common.CodeRes;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Format;
import com.gitee.magic.core.valid.annotation.Length;
import com.gitee.magic.core.valid.annotation.NotNull;

/**
 * @ClassName GetOrderDetailParam
 * @Description
 * @Author bran
 * @Date 2020/7/28 14:04
 */
public class GetOrderDetailParam {

    @Schema(title = "订单id", required = true)
    @NotNull(CodeRes.CODE_21014)
    @Length(message = CodeRes.CODE_21014)
    @Format(type = Format.FormatType.LONG, message = CodeRes.CODE_21014)
    private Long orderId;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
