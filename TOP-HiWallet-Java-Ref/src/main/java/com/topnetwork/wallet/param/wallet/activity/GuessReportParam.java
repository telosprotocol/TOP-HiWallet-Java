package com.topnetwork.wallet.param.wallet.activity;

import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.common.enums.GuessAmount;
import com.topnetwork.wallet.common.enums.UpsAndDowns;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Enum;
import com.gitee.magic.core.valid.annotation.NotNull;

public class GuessReportParam extends ActivityBaseParam {

    @Schema(title = "抵押数量", required = true)
    @Enum(CodeRes.CODE_15036)
    @NotNull(CodeRes.CODE_15036)
    private GuessAmount amount;

    @Schema(title = "涨跌（RISE,FALL）", required = true)
    @Enum(CodeRes.CODE_15025)
    @NotNull(CodeRes.CODE_15025)
    private UpsAndDowns status;

    public GuessAmount getAmount() {
        return amount;
    }

    public void setAmount(GuessAmount amount) {
        this.amount = amount;
    }

    public UpsAndDowns getStatus() {
        return status;
    }

    public void setStatus(UpsAndDowns status) {
        this.status = status;
    }
}
