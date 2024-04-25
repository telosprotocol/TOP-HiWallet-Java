package com.topnetwork.wallet.param.wallet.appuser;

import com.base.core.head.enums.ValueTypeEnum;
import com.topnetwork.wallet.common.CodeRes;

import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Enum;
import com.gitee.magic.core.valid.annotation.Length;
import com.gitee.magic.core.valid.annotation.NotNull;

public class ConfigAddParam {

    @Schema(title = "标题", required = true)
    @NotNull(CodeRes.CODE_15064)
    @Length(message = CodeRes.CODE_15064)
    private String name;
    @Schema(title = "值", required = true)
    @NotNull(CodeRes.CODE_15065)
    @Length(message = CodeRes.CODE_15065)
    private String value;

    @Schema(title = "类型")
    @NotNull(CodeRes.CODE_15083)
    @Length(message = CodeRes.CODE_15083)
    @Enum(CodeRes.CODE_15083)
    private ValueTypeEnum type;

    @Schema(title = "编号")
    @NotNull(CodeRes.CODE_15084)
    @Length(message = CodeRes.CODE_15084)
    private String code;

    public ValueTypeEnum getType() {
        return type;
    }

    public void setType(ValueTypeEnum type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
