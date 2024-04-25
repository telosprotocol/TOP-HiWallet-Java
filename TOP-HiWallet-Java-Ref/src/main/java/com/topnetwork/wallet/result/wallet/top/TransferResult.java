package com.topnetwork.wallet.result.wallet.top;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @ClassName TransferResult
 * @Description
 * @Author bran
 * @Date 2020/10/12 16:50
 */
public class TransferResult {

    @Schema(title="广播是否成功")
    private Boolean success;
    @Schema(title="交易hash")
    private String hash;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}
