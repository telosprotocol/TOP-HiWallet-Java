package com.topnetwork.wallet.result.wallet;

import com.topnetwork.wallet.common.enums.VersionStatus;
import io.swagger.v3.oas.annotations.media.Schema;

public class VersionCheckV4Result {

    @Schema(title = "更新类型 NOUPDATE 无更新,FORCE 强制更新,FRAME 弹框更新,SILENCE 静默更新（红点）", required = true)
    private VersionStatus status;
    @Schema(title = "要更新的版本信息，无更新则为空不返回")
    private VersionDataResult versionData;


    public VersionStatus getStatus() {
        return status;
    }

    public void setStatus(VersionStatus status) {
        this.status = status;
    }

    public VersionDataResult getVersionData() {
        return versionData;
    }

    public void setVersionData(VersionDataResult versionData) {
        this.versionData = versionData;
    }
}
