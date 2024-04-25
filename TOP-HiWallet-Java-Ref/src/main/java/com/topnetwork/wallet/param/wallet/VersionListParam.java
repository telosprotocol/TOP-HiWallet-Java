package com.topnetwork.wallet.param.wallet;

import com.base.core.head.ao.PageAO;

import io.swagger.v3.oas.annotations.media.Schema;

public class VersionListParam extends PageAO {

    @Schema(title = "版本", required = false)
    private String version;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
