package com.topnetwork.wallet.param.wallet.changelly;

import com.topnetwork.wallet.common.CodeRes;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Format;
import com.gitee.magic.core.valid.annotation.Length;
import com.gitee.magic.core.valid.annotation.NotNull;

/**
 * @ClassName GetPairInfoParam
 * @Description
 * @Author bran
 * @Date 2020/6/15 14:23
 */
public class GetPairInfoParam {

    @Schema(title = "卖出币种symbol", required = true)
    @NotNull(CodeRes.CODE_21004)
    @Length(message = CodeRes.CODE_21004)
    private String from;
    @Schema(title = "买入币种symbol", required = true)
    @NotNull(CodeRes.CODE_21004)
    @Length(message = CodeRes.CODE_21004)
    private String to;

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
}
