package com.topnetwork.wallet.param.wallet;

import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.common.enums.RegionEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Enum;
import com.gitee.magic.core.valid.annotation.Length;
import com.gitee.magic.core.valid.annotation.NotNull;

public class ResourceAddParam {
    @Schema(title = "资源名称", required = true)
    @NotNull(CodeRes.CODE_15082)
    @Length(message = CodeRes.CODE_15082)
    private String name;

    @Schema(title = "下载链接", required = true)
    @NotNull(CodeRes.CODE_14022)
    @Length(message = CodeRes.CODE_14022)
    private String url;

    @Schema(title = "地域（海外，国内）", required = true)
    @NotNull(CodeRes.CODE_15013)
    @Enum(CodeRes.CODE_15013)
    private RegionEnum region;


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

    public RegionEnum getRegion() {
        return region;
    }

    public void setRegion(RegionEnum region) {
        this.region = region;
    }
}
