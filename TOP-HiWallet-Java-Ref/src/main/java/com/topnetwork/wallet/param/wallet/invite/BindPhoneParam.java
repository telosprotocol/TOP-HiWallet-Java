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
public class BindPhoneParam {

    @Schema(title = "手机号码", required = true)
    @NotNull(CodeRes.CODE_11013)
    @Length(message = CodeRes.CODE_11013)
    private String mobile;
    @Schema(title = "国家码", required = true)
    @Format(type = Format.FormatType.INTEGER, message = CodeRes.CODE_21024)
    @NotNull(CodeRes.CODE_21024)
    private Integer countryCode;
    @Schema(title = "手机验证码", required = true)
    @NotNull(CodeRes.CODE_18005)
    @Length(min = 6, max = 6, message = CodeRes.CODE_18005)
    private String code;
    @Schema(title = "用户id", required = true)
    @NotNull(CodeRes.CODE_15009)
    @Length(message = CodeRes.CODE_15009)
    private String uid;


    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Integer getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(Integer countryCode) {
        this.countryCode = countryCode;
    }
}
