package com.topnetwork.wallet.param.wallet.changelly;

import com.topnetwork.wallet.common.CodeRes;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Length;
import com.gitee.magic.core.valid.annotation.NotNull;

/**
 * @ClassName GetOrderDetailParam
 * @Description
 * @Author bran
 * @Date 2020/6/15 16:17
 */
public class GetOrderDetailParam {

    @Schema(title = "兑换订单id", required = true)
    @NotNull(CodeRes.CODE_21007)
    @Length(message = CodeRes.CODE_21007)
    private String exchangeId;

    public String getExchangeId() {
        return exchangeId;
    }

    public void setExchangeId(String exchangeId) {
        this.exchangeId = exchangeId;
    }
}
