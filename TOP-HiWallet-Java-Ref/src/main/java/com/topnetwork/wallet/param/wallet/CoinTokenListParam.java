package com.topnetwork.wallet.param.wallet;

import com.base.core.head.ao.PageAO;
import com.topnetwork.wallet.common.CodeRes;

import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Format;

public class CoinTokenListParam extends PageAO {

    @Schema(title = "名称", required = true)
    private String name;
    @Schema(title = "父币种id", required = true)
    @Format(type = Format.FormatType.LONG, message = CodeRes.CODE_14025)
    private Long fid;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getFid() {
        return fid;
    }

    public void setFid(Long fid) {
        this.fid = fid;
    }
}
