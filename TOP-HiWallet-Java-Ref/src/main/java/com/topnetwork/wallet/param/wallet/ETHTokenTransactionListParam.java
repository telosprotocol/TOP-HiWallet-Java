package com.topnetwork.wallet.param.wallet;

import com.topnetwork.wallet.common.CodeRes;

import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.NotNull;

public class ETHTokenTransactionListParam extends ETHTransactionListParam {

    @Schema(title = "方法", required = true)
    @NotNull(CodeRes.CODE_14036)
    private String action;
    @Schema(title = "合约地址", required = true)
    private String contractAddress;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getContractAddress() {
        return contractAddress;
    }

    public void setContractAddress(String contractAddress) {
        this.contractAddress = contractAddress;
    }
}
