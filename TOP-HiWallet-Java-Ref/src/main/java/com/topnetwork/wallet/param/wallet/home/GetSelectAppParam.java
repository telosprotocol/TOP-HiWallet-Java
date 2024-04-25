package com.topnetwork.wallet.param.wallet.home;

import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.common.enums.discover.AppType;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Enum;
import com.gitee.magic.core.valid.annotation.Length;
import com.gitee.magic.core.valid.annotation.NotNull;

/**
 * @ClassName GetSelectAppParam
 * @Description
 * @Author bran
 * @Date 2020/9/24 17:20
 */
public class GetSelectAppParam {

    @Schema(title = "app类型 PRO;HTML;DAPP;APP", required = true)
    @Enum(CodeRes.CODE_20010)
    @NotNull(CodeRes.CODE_20010)
    @Length(message = CodeRes.CODE_20010)
    private AppType appType;

    public AppType getAppType() {
        return appType;
    }

    public void setAppType(AppType appType) {
        this.appType = appType;
    }
}
