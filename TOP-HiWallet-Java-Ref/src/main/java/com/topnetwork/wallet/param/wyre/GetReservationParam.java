package com.topnetwork.wallet.param.wyre;

import com.topnetwork.wallet.common.CodeRes;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Format;
import com.gitee.magic.core.valid.annotation.Length;
import com.gitee.magic.core.valid.annotation.NotNull;

import java.math.BigDecimal;

/**
 * @ClassName GetReservationParam
 * @Description
 * @Author bran
 * @Date 2020/9/16 16:45
 */
public class GetReservationParam {

    @Schema(title = "目标货币",required = true)
    @NotNull(CodeRes.CODE_21004)
    @Length(message = CodeRes.CODE_21004)
    private String destCurrency;
    @Schema(title = "卖出货币",required = true)
    @NotNull(CodeRes.CODE_21004)
    @Length(message = CodeRes.CODE_21004)
    private String sourceCurrency;
    @Schema(title = "数量",required = true)
    @NotNull(CodeRes.CODE_15011)
    @Length(message = CodeRes.CODE_15011)
    @Format(type = Format.FormatType.DOUBLE, message = CodeRes.CODE_15011)
    private BigDecimal amount;
    @Schema(title = "目标地址",required = true)
    @NotNull(CodeRes.CODE_15006)
    @Length(message = CodeRes.CODE_15006)
    private String dest;
    @Schema(title = "支付方式",required = true)
    @NotNull(CodeRes.CODE_23003)
    @Length(message = CodeRes.CODE_23003)
    private String paymentMethod;


    public String getDestCurrency() {
        return destCurrency;
    }

    public void setDestCurrency(String destCurrency) {
        this.destCurrency = destCurrency;
    }

    public String getSourceCurrency() {
        return sourceCurrency;
    }

    public void setSourceCurrency(String sourceCurrency) {
        this.sourceCurrency = sourceCurrency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
