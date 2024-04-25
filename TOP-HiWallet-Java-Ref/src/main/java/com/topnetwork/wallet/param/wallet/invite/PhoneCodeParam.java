package com.topnetwork.wallet.param.wallet.invite;

import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.common.enums.LanguageEnum;
import com.topnetwork.wallet.param.wallet.verifycode.VerifyParam;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Enum;
import com.gitee.magic.core.valid.annotation.Format;
import com.gitee.magic.core.valid.annotation.Length;
import com.gitee.magic.core.valid.annotation.NotNull;

/**
 * @ClassName PhoneCodeParam
 * @Description
 * @Author bran
 * @Date 2020/5/7 14:49
 */
public class PhoneCodeParam extends VerifyParam {

    @Schema(title = "手机号码", required = true)
    @NotNull(CodeRes.CODE_11013)
    @Length(message = CodeRes.CODE_11013)
    private String mobile;

    @Schema(title = "设备当前所用语言，CN：中文，EN：英文", required = true)
    @NotNull(CodeRes.CODE_15000)
    @Enum(CodeRes.CODE_15000)
    private LanguageEnum language;

    @Schema(title = "国家码", required = true)
    @Format(type = Format.FormatType.INTEGER, message = CodeRes.CODE_21024)
    @NotNull(CodeRes.CODE_21024)
    private Integer countryCode;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public LanguageEnum getLanguage() {
        return language;
    }

    public void setLanguage(LanguageEnum language) {
        this.language = language;
    }

    public Integer getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(Integer countryCode) {
        this.countryCode = countryCode;
    }
}
