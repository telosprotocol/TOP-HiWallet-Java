package com.topnetwork.wallet.param.wallet.home;

import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.common.enums.discover.AppType;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Enum;
import com.gitee.magic.core.valid.annotation.Format;
import com.gitee.magic.core.valid.annotation.Length;
import com.gitee.magic.core.valid.annotation.NotNull;

/**
 * @ClassName AddAppParam
 * @Description
 * @Author bran
 * @Date 2020/9/24 17:14
 */
public class UpdAppParam extends AddAppParam {

    @Schema(title = "记录id", required = true)
    @Format(type = Format.FormatType.LONG, message = CodeRes.CODE_14023)
    @NotNull(CodeRes.CODE_14023)
    @Length(message = CodeRes.CODE_14023)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
