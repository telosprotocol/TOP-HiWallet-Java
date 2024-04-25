package com.topnetwork.wallet.param.wallet.appuser;

import com.topnetwork.wallet.common.CodeRes;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Format;
import com.gitee.magic.core.valid.annotation.Length;
import com.gitee.magic.core.valid.annotation.NotNull;

public class ConfigUpdParam {

    @Schema(title = "config id", required = true)
    @NotNull(CodeRes.CODE_15063)
    @Length(message = CodeRes.CODE_15063)
    @Format(type = Format.FormatType.LONG, message = CodeRes.CODE_15063)
    private Long id;
    @Schema(title = "标题", required = true)
    @NotNull(CodeRes.CODE_15064)
    @Length(message = CodeRes.CODE_15064)
    private String name;
    @Schema(title = "值", required = true)
    @NotNull(CodeRes.CODE_15065)
    @Length(message = CodeRes.CODE_15065, max = 2048)
    private String value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
