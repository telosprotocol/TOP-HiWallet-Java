package com.topnetwork.wallet.result.changelly;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @ClassName CheckCurrencyResult
 * @Description
 * @Author bran
 * @Date 2020/6/16 15:07
 */
public class CheckCurrencyResult {

    @Schema(title="支持或者不支持币币兑换，true支持，false不支持")
    private Boolean enabled;

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
