package com.topnetwork.wallet.param.wallet.changelly;

import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.common.enums.changelly.ExchangeStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Enum;
import com.gitee.magic.core.valid.annotation.Length;
import com.gitee.magic.core.valid.annotation.NotNull;

/**
 * @ClassName ReportTransResultParam
 * @Description
 * @Author bran
 * @Date 2020/6/16 17:58
 */
public class ReportTransResultParam {

    @Schema(title = "兑换订单id", required = true)
    @NotNull(CodeRes.CODE_21007)
    @Length(message = CodeRes.CODE_21007)
    private String exchangeId;
    @Schema(title = "交易状态", required = true)
    @NotNull(CodeRes.CODE_21010)
    @Length(message = CodeRes.CODE_21010)
    @Enum(CodeRes.CODE_21010)
    private ExchangeStatus status;

    public String getExchangeId() {
        return exchangeId;
    }

    public void setExchangeId(String exchangeId) {
        this.exchangeId = exchangeId;
    }

    public ExchangeStatus getStatus() {
        return status;
    }

    public void setStatus(ExchangeStatus status) {
        this.status = status;
    }
}
