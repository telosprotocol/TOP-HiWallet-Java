package com.topnetwork.wallet.param.wallet.discover;

import com.topnetwork.wallet.common.CodeRes;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Format;
import com.gitee.magic.core.valid.annotation.Length;
import com.gitee.magic.core.valid.annotation.NotNull;

/**
 * @ClassName AddLabelI18nParam
 * @Description
 * @Author bran
 * @Date 2020/5/19 17:44
 */
public class UpdLabelI18nParam extends AddLabelI18nParam {

    @Schema(title = "i18n ID", required = false)
    @Length(message = CodeRes.CODE_20009)
    @Format(type = Format.FormatType.LONG, message = CodeRes.CODE_20009)
    private Long i18nId;

    public Long getI18nId() {
        return i18nId;
    }

    public void setI18nId(Long i18nId) {
        this.i18nId = i18nId;
    }
}
