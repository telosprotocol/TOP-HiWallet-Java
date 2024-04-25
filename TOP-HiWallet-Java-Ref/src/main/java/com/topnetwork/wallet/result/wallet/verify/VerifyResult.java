package com.topnetwork.wallet.result.wallet.verify;

import com.topnetwork.wallet.common.enums.verify.StatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @program: topverificationcode
 * @description: 验证结果
 * @author: Tyrone
 * @create: 2020-03-13 14:37
 **/
public class VerifyResult {
    @Schema(title = "验证是否成功")
    private StatusEnum success;

    public StatusEnum getSuccess() {
        return success;
    }

    public void setSuccess(StatusEnum success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "VerifyResult{" +
                "success=" + success +
                '}';
    }
}
