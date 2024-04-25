package com.topnetwork.wallet.param.wallet;

import com.base.core.head.ao.PageAO;
import com.topnetwork.wallet.common.CodeRes;

import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.NotNull;

public class ETHTransactionListParam extends PageAO {

    @Schema(title = "eth地址", required = true)
    @NotNull(CodeRes.CODE_14036)
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
