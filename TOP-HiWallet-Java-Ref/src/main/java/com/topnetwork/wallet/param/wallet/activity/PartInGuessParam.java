package com.topnetwork.wallet.param.wallet.activity;

import com.topnetwork.wallet.common.CodeRes;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Format;
import com.gitee.magic.core.valid.annotation.Length;
import com.gitee.magic.core.valid.annotation.NotNull;
import com.gitee.magic.core.valid.annotation.number.IntegerValid;

public class PartInGuessParam {

    @Schema(title = "用户id", required = true)
    @NotNull(CodeRes.CODE_15009)
    @Length(message = CodeRes.CODE_15009)
    private String uid;

    @Schema(title = "竞猜金额")
    @NotNull(CodeRes.CODE_15036)
    @Format(type = Format.FormatType.INTEGER, message = CodeRes.CODE_15036)
    @IntegerValid(min = 20, max = 500, message = CodeRes.CODE_15036)
    private Integer amount;

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
