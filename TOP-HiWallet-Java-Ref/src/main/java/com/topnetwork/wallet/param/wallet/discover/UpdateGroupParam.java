package com.topnetwork.wallet.param.wallet.discover;

import com.topnetwork.wallet.common.CodeRes;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Format;
import com.gitee.magic.core.valid.annotation.Length;
import com.gitee.magic.core.valid.annotation.NotNull;

/**
 * @ClassName UpdateGroupParam
 * @Description
 * @Author bran
 * @Date 2020/5/19 16:09
 */
public class UpdateGroupParam extends AddGroupParam {

    @Schema(title = "分组ID")
    @NotNull(CodeRes.CODE_20004)
    @Length(message = CodeRes.CODE_20004)
    @Format(type = Format.FormatType.LONG, message = CodeRes.CODE_20004)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
