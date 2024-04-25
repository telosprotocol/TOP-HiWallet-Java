package com.topnetwork.wallet.result;

import io.swagger.v3.oas.annotations.media.Schema;


/**
 * @ClassName ETHConfigResult
 * @Description
 * @Author bran
 * @Date 2020/8/24 15:21
 */
public class ETHConfigResult {

    @Schema(title="最大值")
    private String gasLimit;
    @Schema(title="计算用")
    private String showGasUsed;

    public String getGasLimit() {
        return gasLimit;
    }

    public void setGasLimit(String gasLimit) {
        this.gasLimit = gasLimit;
    }

    public String getShowGasUsed() {
        return showGasUsed;
    }

    public void setShowGasUsed(String showGasUsed) {
        this.showGasUsed = showGasUsed;
    }
}
