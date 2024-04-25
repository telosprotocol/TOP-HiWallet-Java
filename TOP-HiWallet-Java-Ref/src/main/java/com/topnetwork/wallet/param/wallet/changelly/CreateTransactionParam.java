package com.topnetwork.wallet.param.wallet.changelly;

import com.topnetwork.wallet.common.CodeRes;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Length;
import com.gitee.magic.core.valid.annotation.NotNull;

import java.math.BigDecimal;

/**
 * @ClassName CreateTransactionParam
 * @Description
 * @Author bran
 * @Date 2020/6/15 14:45
 */
public class CreateTransactionParam {

    @Schema(title = "用户id", required = true)
    @NotNull(CodeRes.CODE_15009)
    @Length(message = CodeRes.CODE_15009)
    private String uid;

    @Schema(title = "卖出币种symbol", required = true)
    @NotNull(CodeRes.CODE_21004)
    @Length(message = CodeRes.CODE_21004)
    private String from;
    @Schema(title = "买入币种symbol", required = true)
    @NotNull(CodeRes.CODE_21004)
    @Length(message = CodeRes.CODE_21004)
    private String to;
    @Schema(title = "卖出钱包地址", required = true)
    @NotNull(CodeRes.CODE_21005)
    @Length(message = CodeRes.CODE_21005)
    private String fromAddress;
    @Schema(title = "买入钱包地址", required = true)
    @NotNull(CodeRes.CODE_21005)
    @Length(message = CodeRes.CODE_21005)
    private String toAddress;
    @Schema(title = "卖出数量", required = true)
    @NotNull(CodeRes.CODE_21006)
    @Length(message = CodeRes.CODE_21006)
    private BigDecimal amount;

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

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
