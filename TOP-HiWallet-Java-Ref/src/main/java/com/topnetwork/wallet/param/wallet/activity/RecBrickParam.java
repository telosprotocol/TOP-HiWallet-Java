package com.topnetwork.wallet.param.wallet.activity;

import com.topnetwork.wallet.common.CodeRes;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Format;
import com.gitee.magic.core.valid.annotation.NotNull;

public class RecBrickParam extends ActivityBaseParam{

    @Schema(title = "分数（块数）", required = true)
    @NotNull(CodeRes.CODE_15027)
    @Format(type = Format.FormatType.INTEGER, message = CodeRes.CODE_15027)
    private Integer record;

    public Integer getRecord() {
        return record;
    }

    public void setRecord(Integer record) {
        this.record = record;
    }
}
