package com.topnetwork.wallet.result.wallet;

import com.topnetwork.wallet.common.enums.RegionEnum;
import io.swagger.v3.oas.annotations.media.Schema;

public class GetResourceResult {

    @Schema(title = "id")
    private Long id;
    @Schema(title = "资源名称")
    private String name;
    @Schema(title = "下载链接")
    private String url;
    @Schema(title = "地区")
    private RegionEnum region;
    @Schema(title = "上传时间")
    private Long createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public RegionEnum getRegion() {
        return region;
    }

    public void setRegion(RegionEnum region) {
        this.region = region;
    }

}
