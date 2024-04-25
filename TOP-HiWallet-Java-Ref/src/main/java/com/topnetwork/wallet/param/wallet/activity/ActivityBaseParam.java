package com.topnetwork.wallet.param.wallet.activity;

import com.topnetwork.wallet.common.CodeRes;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Format;
import com.gitee.magic.core.valid.annotation.NotNull;

public class ActivityBaseParam {

    @Schema(title = "活动id", required = true)
    @NotNull(CodeRes.CODE_15021)
    @Format(type = Format.FormatType.LONG, message = CodeRes.CODE_15021)
    private Long aid;

    public Long getAid() {
        return aid;
    }

    public void setAid(Long aid) {
        this.aid = aid;
    }
}
