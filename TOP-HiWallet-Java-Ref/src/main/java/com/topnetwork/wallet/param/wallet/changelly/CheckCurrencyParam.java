package com.topnetwork.wallet.param.wallet.changelly;

import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.common.enums.changelly.GetListTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Enum;
import com.gitee.magic.core.valid.annotation.Format;
import com.gitee.magic.core.valid.annotation.Length;
import com.gitee.magic.core.valid.annotation.NotNull;


/**
 * @ClassName CheckCurrencyParam
 * @Description
 * @Author bran
 * @Date 2020/6/16 15:05
 */
public class CheckCurrencyParam {

    @Schema(title = "币种symbol", required = true)
    @NotNull(CodeRes.CODE_21009)
    @Length(message = CodeRes.CODE_21009)
    private String symbol;
    @Schema(title = "买入或卖出", required = true)
    @Enum(CodeRes.CODE_21008)
    @NotNull(CodeRes.CODE_21008)
    @Length(message = CodeRes.CODE_21008)
    private GetListTypeEnum type;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public GetListTypeEnum getType() {
        return type;
    }

    public void setType(GetListTypeEnum type) {
        this.type = type;
    }
}
