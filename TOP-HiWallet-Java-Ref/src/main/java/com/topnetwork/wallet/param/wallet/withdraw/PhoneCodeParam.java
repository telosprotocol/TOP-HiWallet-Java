package com.topnetwork.wallet.param.wallet.withdraw;

import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.common.enums.LanguageEnum;
import com.topnetwork.wallet.param.wallet.verifycode.VerifyParam;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Enum;
import com.gitee.magic.core.valid.annotation.Format;
import com.gitee.magic.core.valid.annotation.NotNull;

/**
 * @ClassName PhoneCodeParam
 * @Description
 * @Author bran
 * @Date 2020/5/7 14:49
 */
public class PhoneCodeParam extends VerifyParam {

    @Schema(title = "设备当前所用语言，CN：中文，EN：英文", required = true)
    @NotNull(CodeRes.CODE_15000)
    @Enum(CodeRes.CODE_15000)
    private LanguageEnum language;

    public LanguageEnum getLanguage() {
        return language;
    }

    public void setLanguage(LanguageEnum language) {
        this.language = language;
    }
}
