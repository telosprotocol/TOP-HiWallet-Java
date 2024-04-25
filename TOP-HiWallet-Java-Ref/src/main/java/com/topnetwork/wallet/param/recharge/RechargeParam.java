package com.topnetwork.wallet.param.recharge;

import com.topnetwork.wallet.common.CodeRes;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Format;
import com.gitee.magic.core.valid.annotation.Length;
import com.gitee.magic.core.valid.annotation.NotNull;

import java.math.BigDecimal;

/**
 * @ClassName RechargeParam
 * @Description
 * @Author bran
 * @Date 2020/9/4 15:23
 */
public class RechargeParam {

    @Schema(title="新青年号")
    @NotNull(CodeRes.CODE_23000)
    @Length(message = CodeRes.CODE_23000)
    private String certNum;

    @Schema(title="top数量")
    @NotNull(CodeRes.CODE_23001)
    @Length(message = CodeRes.CODE_23001)
    @Format(type = Format.FormatType.DOUBLE, message = CodeRes.CODE_23001)
    private BigDecimal amount;

    public String getCertNum() {
        return certNum;
    }

    public void setCertNum(String certNum) {
        this.certNum = certNum;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

}
