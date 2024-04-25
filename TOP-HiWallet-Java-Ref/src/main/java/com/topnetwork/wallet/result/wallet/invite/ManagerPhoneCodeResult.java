package com.topnetwork.wallet.result.wallet.invite;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Date;

/**
 * @ClassName ManagerPhoneCodeResult
 * @Description
 * @Author bran
 * @Date 2020/5/9 16:06
 */
public class ManagerPhoneCodeResult {
    @Schema(title="已发送次数")
    private Integer times;
    @Schema(title="最后一次验证码发送时间")
    private Date lastSendTime;
    @Schema(title="验证码")
    private String code;
    @Schema(title="当前验证码错误次数")
    private Integer errorTimes;

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }

    public Date getLastSendTime() {
        return lastSendTime;
    }

    public void setLastSendTime(Date lastSendTime) {
        this.lastSendTime = lastSendTime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getErrorTimes() {
        return errorTimes;
    }

    public void setErrorTimes(Integer errorTimes) {
        this.errorTimes = errorTimes;
    }
}
