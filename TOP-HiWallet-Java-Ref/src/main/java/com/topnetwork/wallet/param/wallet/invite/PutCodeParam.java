package com.topnetwork.wallet.param.wallet.invite;

import com.topnetwork.wallet.common.CodeRes;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Format;
import com.gitee.magic.core.valid.annotation.Length;
import com.gitee.magic.core.valid.annotation.NotNull;

/**
 * @ClassName BindPhoneParam
 * @Description
 * @Author bran
 * @Date 2020/5/7 15:02
 */
public class PutCodeParam {

    @Schema(title = "手机号码", required = true)
    @NotNull(CodeRes.CODE_11013)
    @Length(message = CodeRes.CODE_11013)
    private String mobile;
    @Schema(title = "微信用户code", required = true)
    @NotNull(CodeRes.CODE_18007)
    @Length(message = CodeRes.CODE_18007)
    private String userCode;
    @Schema(title = "国家码", required = true)
    @Format(type = Format.FormatType.INTEGER, message = CodeRes.CODE_21024)
    @NotNull(CodeRes.CODE_21024)
    private Integer countryCode;

    public Integer getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(Integer countryCode) {
        this.countryCode = countryCode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
}
