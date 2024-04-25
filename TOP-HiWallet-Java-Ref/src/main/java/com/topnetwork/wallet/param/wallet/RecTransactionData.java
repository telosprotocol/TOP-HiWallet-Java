package com.topnetwork.wallet.param.wallet;

import java.math.BigDecimal;

import com.topnetwork.wallet.common.CodeRes;

import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Format;
import com.gitee.magic.core.valid.annotation.NotNull;

public class RecTransactionData {

    @Schema(title = "交易hash", required = true)
    @NotNull(CodeRes.CODE_15012)
    private String hash;
    @Schema(title = "合约地址,为空表示当前币为主币，否则为chainType下的代币")
    private String contract;
    @Schema(title = "发起账户", required = true)
    @NotNull(CodeRes.CODE_14036)
    private String fromAccount;
    @Schema(title = "接收账户账户", required = true)
    @NotNull(CodeRes.CODE_14036)
    private String toAccount;
    @Schema(title = "数量", required = true)
    @NotNull(CodeRes.CODE_15011)
    @Format(type = Format.FormatType.GE_ZERO, message = CodeRes.CODE_14017)
    private BigDecimal amount;
    @Schema(title = "备注", required = false)
    private String comment;
    @Schema(title = "交易gas费", required = true)
    @NotNull(CodeRes.CODE_15015)
    private String gas;

    public String getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(String fromAccount) {
        this.fromAccount = fromAccount;
    }

    public String getToAccount() {
        return toAccount;
    }

    public void setToAccount(String toAccount) {
        this.toAccount = toAccount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getGas() {
        return gas;
    }

    public void setGas(String gas) {
        this.gas = gas;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }
}
