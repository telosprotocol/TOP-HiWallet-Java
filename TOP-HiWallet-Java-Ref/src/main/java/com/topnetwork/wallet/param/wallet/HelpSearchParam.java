package com.topnetwork.wallet.param.wallet;

import com.base.core.head.ao.PageAO;
import com.topnetwork.wallet.common.CodeRes;

import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.NotNull;

public class HelpSearchParam extends PageAO {


    @Schema(title = "id", required = true)
    @NotNull(CodeRes.CODE_14012)
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
