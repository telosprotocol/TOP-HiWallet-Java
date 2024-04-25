package com.topnetwork.wallet.param.wallet.discover;

import com.topnetwork.wallet.common.CodeRes;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Format;
import com.gitee.magic.core.valid.annotation.Length;
import com.gitee.magic.core.valid.annotation.NotNull;

/**
 * @ClassName AddDappLabelParam
 * @Description
 * @Author bran
 * @Date 2020/5/26 16:20
 */
public class AddDappLabelParam {

    @Schema(title = "标签Id", required = true)
    @NotNull(CodeRes.CODE_20008)
    @Length(message = CodeRes.CODE_20008)
    @Format(type = Format.FormatType.LONG, message = CodeRes.CODE_20008)
    private Long labelId;

    public Long getLabelId() {
        return labelId;
    }

    public void setLabelId(Long labelId) {
        this.labelId = labelId;
    }
}
