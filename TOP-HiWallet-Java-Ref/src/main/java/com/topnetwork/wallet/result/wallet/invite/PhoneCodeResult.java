package com.topnetwork.wallet.result.wallet.invite;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @ClassName PhoneCodeResult
 * @Description
 * @Author bran
 * @Date 2020/5/7 14:50
 */
public class PhoneCodeResult {

    @Schema(title="剩余可发送次数")
    private Integer times;

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }
}
