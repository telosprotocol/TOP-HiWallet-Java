package com.topnetwork.wallet.result.wallet;

import io.swagger.v3.oas.annotations.media.Schema;

public class VersionCheckResult {

    @Schema(title = "是否有新版本（0、无；1、有）", required = true)
    private String hasUpdate;
    @Schema(title = "最新版本", required = true)
    private String version;
    @Schema(title = "最新版本描述", required = true)
    private String description;
    @Schema(title = "是否强制更新", required = true)
    private Integer isForcedUpdates;
    @Schema(title = "下载链接", required = true)
    private String downloadUrl;

    public String getHasUpdate() {
        return hasUpdate;
    }

    public void setHasUpdate(String hasUpdate) {
        this.hasUpdate = hasUpdate;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getIsForcedUpdates() {
        return isForcedUpdates;
    }

    public void setIsForcedUpdates(Integer isForcedUpdates) {
        this.isForcedUpdates = isForcedUpdates;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

}
