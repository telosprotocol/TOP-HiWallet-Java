package com.topnetwork.wallet.result.wallet;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @ClassName VersionDataResult
 * @Description
 * @Author bran
 * @Date 2020/3/31 18:28
 */
public class VersionDataResult {

    @Schema(title = "最新版本", required = true)
    private String version;
    @Schema(title = "标题", required = true)
    private String title;
    @Schema(title = "最新版本描述", required = true)
    private String description;
    @Schema(title = "下载链接", required = true)
    private String downloadUrl;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getDownloadUrl() {
    	if(downloadUrl != null && downloadUrl != "" && downloadUrl.toLowerCase().indexOf("apk") >0) {
    		return "";
    	}
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }
}
