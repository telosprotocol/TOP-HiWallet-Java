package com.topnetwork.wallet.param.wallet.appuser;

import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.param.wallet.verifycode.VerifyParam;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Format;
import com.gitee.magic.core.valid.annotation.Length;
import com.gitee.magic.core.valid.annotation.NotNull;

public class UserRegisterParam extends VerifyParam {

    @Schema(title = "用户id", required = true)
    @NotNull(CodeRes.CODE_15009)
    @Length(message = CodeRes.CODE_15009)
    private String uid;
    @Schema(title = "设备id", required = true)
    @NotNull(CodeRes.CODE_15017)
    @Length(message = CodeRes.CODE_15017)
    private String deviceId;
    @Schema(title = "手机号码", required = false)
    @Length(message = CodeRes.CODE_11013)
    private String mobile;
    @Schema(title = "国家码", required = false)
    @Format(type = Format.FormatType.INTEGER, message = CodeRes.CODE_21024)
    private Integer countryCode;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

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
