package com.topnetwork.wallet.param.wallet;

import com.topnetwork.wallet.common.CodeRes;

import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Format;

public class CategoryListParam {

    @Schema(title = "上级ID", required = false)
    @Format(type = Format.FormatType.LONG, message = CodeRes.CODE_14015)
    private Long fid;
    @Schema(title = "级别", required = false)
    @Format(type = Format.FormatType.INTEGER, message = CodeRes.CODE_14017)
    private Integer level;

    public Long getFid() {
        return fid;
    }

    public void setFid(Long fid) {
        this.fid = fid;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
