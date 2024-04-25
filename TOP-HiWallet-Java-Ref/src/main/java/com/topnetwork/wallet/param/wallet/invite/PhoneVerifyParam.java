package com.topnetwork.wallet.param.wallet.invite;

import com.topnetwork.wallet.common.CodeRes;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Format;
import com.gitee.magic.core.valid.annotation.Length;
import com.gitee.magic.core.valid.annotation.NotNull;

/**
 * @ClassName PhoneVerifyParam
 * @Description
 * @Author bran
 * @Date 2020/5/12 17:49
 */
public class PhoneVerifyParam {

    @Schema(title = "手机号码", required = true)
    @NotNull(CodeRes.CODE_11013)
    @Length(message = CodeRes.CODE_11013)
    private String mobile;
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

    public Integer getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(Integer countryCode) {
        this.countryCode = countryCode;
    }
}
