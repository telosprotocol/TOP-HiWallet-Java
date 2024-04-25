package com.topnetwork.wallet.param.wallet.v1;

import com.topnetwork.wallet.common.CodeRes;

import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Format;
import com.gitee.magic.core.valid.annotation.NotNull;

public class VersionCheckV1Param {

    @Schema(title = "当前版本", required = true)
    @NotNull(CodeRes.CODE_14019)
    private String version;

    @Schema(title = "平台（1、ios；2、安卓）", required = true)
    @NotNull(CodeRes.CODE_14018)
    @Format(type = Format.FormatType.INTEGER, message = CodeRes.CODE_14018)
    private Integer platform;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Integer getPlatform() {
        return platform;
    }

    public void setPlatform(Integer platform) {
        this.platform = platform;
    }
}
