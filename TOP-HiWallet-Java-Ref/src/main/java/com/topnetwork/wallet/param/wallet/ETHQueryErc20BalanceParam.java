package com.topnetwork.wallet.param.wallet;

import com.topnetwork.wallet.common.CodeRes;

import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Length;
import com.gitee.magic.core.valid.annotation.NotNull;

public class ETHQueryErc20BalanceParam extends ETHQueryBalanceParam {

    @Schema(title = "eth合约地址", required = true)
    @NotNull(CodeRes.CODE_14037)
    @Length(message = CodeRes.CODE_14037)
    private String contract;

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }
}
