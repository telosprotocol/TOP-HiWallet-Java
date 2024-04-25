package com.topnetwork.wallet.result.wallet.withdraw;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @ClassName GetAirdropStatusResult
 * @Description
 * @Author bran
 * @Date 2020/7/7 14:22
 */
public class GetAirdropStatusResult {

    @Schema(title = "是否绑定手机号")
    private Boolean phoneBind;
    @Schema(title = "是否允许打开提现")
    private Boolean allow;

    public Boolean getPhoneBind() {
        return phoneBind;
    }

    public void setPhoneBind(Boolean phoneBind) {
        this.phoneBind = phoneBind;
    }

    public Boolean getAllow() {
        return allow;
    }

    public void setAllow(Boolean allow) {
        this.allow = allow;
    }
}
