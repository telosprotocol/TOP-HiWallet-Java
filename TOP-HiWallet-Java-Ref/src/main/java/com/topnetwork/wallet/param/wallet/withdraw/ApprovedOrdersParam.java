package com.topnetwork.wallet.param.wallet.withdraw;

import com.topnetwork.wallet.common.CodeRes;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Format;
import com.gitee.magic.core.valid.annotation.Length;
import com.gitee.magic.core.valid.annotation.NotNull;

import java.util.List;

/**
 * @ClassName ApprovedOrdersParam
 * @Description
 * @Author bran
 * @Date 2020/7/7 14:02
 */
public class ApprovedOrdersParam {

    @Schema(title = "订单id", required = true)
    @NotNull(CodeRes.CODE_21014)
    @Format(type = Format.FormatType.JSONArrayLong, message = CodeRes.CODE_21014)
    private List<Long> orderId;

    public List<Long> getOrderId() {
        return orderId;
    }

    public void setOrderId(List<Long> orderId) {
        this.orderId = orderId;
    }
}
