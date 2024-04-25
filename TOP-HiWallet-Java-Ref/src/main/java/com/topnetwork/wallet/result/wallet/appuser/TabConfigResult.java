package com.topnetwork.wallet.result.wallet.appuser;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @ClassName TabConfigResult
 * @Description
 * @Author bran
 * @Date 2020/5/6 18:51
 */
public class TabConfigResult {

    @Schema(title = "是否新青年用户")
    private Boolean newYouth;
    @Schema(title = "邀请配置")
    private TabDataResult config;

    public Boolean getNewYouth() {
        return newYouth;
    }

    public void setNewYouth(Boolean newYouth) {
        this.newYouth = newYouth;
    }

    public TabDataResult getConfig() {
        return config;
    }

    public void setConfig(TabDataResult config) {
        this.config = config;
    }
}
