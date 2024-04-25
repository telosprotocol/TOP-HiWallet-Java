package com.topnetwork.wallet.param.wallet.discover;

import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.common.enums.ChainTypeEnum;
import com.topnetwork.wallet.common.enums.LanguageEnum;
import com.topnetwork.wallet.common.enums.discover.BannerType;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Enum;
import com.gitee.magic.core.valid.annotation.Format;
import com.gitee.magic.core.valid.annotation.Length;
import com.gitee.magic.core.valid.annotation.NotNull;

import java.util.Date;

public class BannerUpdateParam extends BannerAddParam {

    @Schema(title = "Banner id", required = true)
    @NotNull(CodeRes.CODE_15049)
    @Length(message = CodeRes.CODE_15049)
    @Format(type = Format.FormatType.LONG, message = CodeRes.CODE_15049)
    private Long id;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }


}
