package com.topnetwork.wallet.param.wallet;

import com.base.core.head.ao.PageAO;
import com.topnetwork.wallet.common.CodeRes;

import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Format;

public class CoinMainListParam extends PageAO {

    @Schema(title = "名称", required = false)
    private String name;

    @Schema(title = "显示状态（0：正常，1：隐藏）", required = true)
    @Format(type = Format.FormatType.INTEGER, message = CodeRes.CODE_14027)
    private Integer isHide;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIsHide() {
        return isHide;
    }

    public void setIsHide(Integer isHide) {
        this.isHide = isHide;
    }
}
