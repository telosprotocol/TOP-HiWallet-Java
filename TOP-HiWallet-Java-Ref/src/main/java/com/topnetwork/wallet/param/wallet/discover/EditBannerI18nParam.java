package com.topnetwork.wallet.param.wallet.discover;

import com.topnetwork.wallet.common.CodeRes;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Format;
import com.gitee.magic.core.valid.annotation.Length;
import com.gitee.magic.core.valid.annotation.NotNull;

import java.util.List;

/**
 * @ClassName EditBannerI18nParam
 * @Description
 * @Author bran
 * @Date 2020/5/25 15:04
 */
public class EditBannerI18nParam {

    @Schema(title = "Banner id", required = true)
    @NotNull(CodeRes.CODE_15049)
    @Length(message = CodeRes.CODE_15049)
    @Format(type = Format.FormatType.LONG, message = CodeRes.CODE_15049)
    private Long bannerId;

    @Schema(title = "国际化数据列表", required = true)
    @NotNull(CodeRes.CODE_20018)
    private List<BannerI18nParam> i18n;

    public Long getBannerId() {
        return bannerId;
    }

    public void setBannerId(Long bannerId) {
        this.bannerId = bannerId;
    }

    public List<BannerI18nParam> getI18n() {
        return i18n;
    }

    public void setI18n(List<BannerI18nParam> i18n) {
        this.i18n = i18n;
    }
}
