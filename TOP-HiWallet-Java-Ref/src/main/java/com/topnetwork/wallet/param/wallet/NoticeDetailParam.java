package com.topnetwork.wallet.param.wallet;

import com.topnetwork.wallet.common.CodeRes;

import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Format;
import com.gitee.magic.core.valid.annotation.NotNull;

public class NoticeDetailParam {

    @Schema(title = "公告id", required = true)
    @NotNull(CodeRes.CODE_14001)
    @Format(type = Format.FormatType.LONG, message = CodeRes.CODE_14001)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
