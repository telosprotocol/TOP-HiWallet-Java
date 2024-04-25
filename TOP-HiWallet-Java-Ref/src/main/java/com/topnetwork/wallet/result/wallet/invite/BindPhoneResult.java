package com.topnetwork.wallet.result.wallet.invite;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @ClassName BindPhoneResult
 * @Description
 * @Author bran
 * @Date 2020/5/12 16:10
 */
public class BindPhoneResult {

    @Schema(title="返回信息")
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
