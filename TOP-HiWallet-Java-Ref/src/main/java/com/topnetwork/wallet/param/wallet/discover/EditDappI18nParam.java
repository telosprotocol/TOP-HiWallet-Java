package com.topnetwork.wallet.param.wallet.discover;

import com.topnetwork.wallet.common.CodeRes;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Format;
import com.gitee.magic.core.valid.annotation.Length;
import com.gitee.magic.core.valid.annotation.NotNull;

import java.util.List;

/**
 * @ClassName EditDappI18nParam
 * @Description
 * @Author bran
 * @Date 2020/5/26 13:42
 */
public class EditDappI18nParam {

    @Schema(title = "国际化数据列表", required = true)
    @NotNull(CodeRes.CODE_20018)
    private List<DappI18nParam> i18n;
    @Schema(title = "应用 id", required = true)
    @NotNull(CodeRes.CODE_20014)
    @Length(message = CodeRes.CODE_20014)
    @Format(type = Format.FormatType.LONG, message = CodeRes.CODE_20014)
    private Long appId;

    public List<DappI18nParam> getI18n() {
        return i18n;
    }

    public void setI18n(List<DappI18nParam> i18n) {
        this.i18n = i18n;
    }

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }
}
