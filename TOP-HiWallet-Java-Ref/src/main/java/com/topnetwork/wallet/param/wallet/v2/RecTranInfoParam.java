package com.topnetwork.wallet.param.wallet.v2;

import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.param.wallet.RecTransactionData;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.NotNull;

import java.util.List;

public class RecTranInfoParam {

    @Schema(title = "交易列表", required = true)
    @NotNull(CodeRes.CODE_15010)
    List<RecTransactionData> transactions;

    public List<RecTransactionData> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<RecTransactionData> transactions) {
        this.transactions = transactions;
    }
}
