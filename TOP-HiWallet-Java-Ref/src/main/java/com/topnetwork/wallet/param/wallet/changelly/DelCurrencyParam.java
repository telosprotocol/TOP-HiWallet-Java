package com.topnetwork.wallet.param.wallet.changelly;

import com.topnetwork.wallet.common.CodeRes;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Format;
import com.gitee.magic.core.valid.annotation.Length;
import com.gitee.magic.core.valid.annotation.NotNull;

/**
 * @ClassName DelCurrencyParam
 * @Description
 * @Author bran
 * @Date 2020/6/15 10:40
 */
public class DelCurrencyParam {

    @Schema(title = "支持币id", required = true)
    @NotNull(CodeRes.CODE_21002)
    @Format(type = Format.FormatType.LONG, message = CodeRes.CODE_21002)
    @Length(message = CodeRes.CODE_21002)
    private Long currencyId;

    public Long getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Long currencyId) {
        this.currencyId = currencyId;
    }
}
