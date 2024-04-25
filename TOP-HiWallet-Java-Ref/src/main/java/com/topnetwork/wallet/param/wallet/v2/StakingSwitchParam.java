package com.topnetwork.wallet.param.wallet.v2;

import com.topnetwork.wallet.common.CodeRes;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.NotNull;

public class StakingSwitchParam {

    @Schema(title = "版本", required = true)
    @NotNull(CodeRes.CODE_14019)
    private String version;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
