package com.topnetwork.wallet.param.wallet.discover;

import com.topnetwork.wallet.common.CodeRes;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Format;
import com.gitee.magic.core.valid.annotation.Length;
import com.gitee.magic.core.valid.annotation.NotNull;

/**
 * @ClassName UpdateDappParam
 * @Description
 * @Author bran
 * @Date 2020/5/26 16:43
 */
public class UpdateAppParam extends AddAppParam {

    @Schema(title = "app id", required = true)
    @NotNull(CodeRes.CODE_20014)
    @Length(message = CodeRes.CODE_20014)
    @Format(type = Format.FormatType.LONG, message = CodeRes.CODE_20014)
    private Long dappId;

    public Long getDappId() {
        return dappId;
    }

    public void setDappId(Long dappId) {
        this.dappId = dappId;
    }
}
