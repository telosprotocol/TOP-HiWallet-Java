package com.topnetwork.wallet.param.wallet.invite;

import com.topnetwork.wallet.common.CodeRes;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Format;
import com.gitee.magic.core.valid.annotation.Length;
import com.gitee.magic.core.valid.annotation.NotNull;

/**
 * @ClassName RecInviteParam
 * @Description
 * @Author bran
 * @Date 2020/5/7 14:55
 */
public class RecInviteParam {

    @Schema(title = "手机号码", required = true)
    @NotNull(CodeRes.CODE_11013)
    @Length(message = CodeRes.CODE_11013)
    private String mobile;
    @Schema(title = "手机验证码", required = true)
    @NotNull(CodeRes.CODE_18005)
    @Length(min = 6, max = 6, message = CodeRes.CODE_18005)
    private String phoneCode;
    @Schema(title = "邀请码", required = true)
    @NotNull(CodeRes.CODE_18003)
    @Length(message = CodeRes.CODE_18003)
    private String inviteCode;
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

    public String getPhoneCode() {
        return phoneCode;
    }

    public void setPhoneCode(String phoneCode) {
        this.phoneCode = phoneCode;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public Integer getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(Integer countryCode) {
        this.countryCode = countryCode;
    }
}
