package com.topnetwork.wallet.param.wallet.discover;

import com.topnetwork.wallet.common.CodeRes;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Format;
import com.gitee.magic.core.valid.annotation.Length;
import com.gitee.magic.core.valid.annotation.NotNull;

/**
 * @ClassName BannerNewUpdParam
 * @Description
 * @Author bran
 * @Date 2020/5/25 14:59
 */
public class BannerNewUpdParam extends BannerNewAddParam {
    @Schema(title = "Banner id", required = true)
    @NotNull(CodeRes.CODE_15049)
    @Length(message = CodeRes.CODE_15049)
    @Format(type = Format.FormatType.LONG, message = CodeRes.CODE_15049)
    private Long bannerId;

    public Long getBannerId() {
        return bannerId;
    }

    public void setBannerId(Long bannerId) {
        this.bannerId = bannerId;
    }
}
