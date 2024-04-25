package com.topnetwork.wallet.param.wallet.v3;

import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.common.enums.LanguageEnum;
import com.topnetwork.wallet.common.enums.RegionEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Enum;
import com.gitee.magic.core.valid.annotation.NotNull;

public class VersionCheckParam {

    @Schema(title = "当前版本", required = true)
    @NotNull(CodeRes.CODE_14019)
    private String version;

    @Schema(title = "设备当前所用语言，CN：中文，EN：英文", required = true)
    @NotNull(CodeRes.CODE_15000)
    @Enum(CodeRes.CODE_15000)
    private LanguageEnum language;

    @Schema(title = "地域（海外版，国内版,通用版）", required = true)
    @NotNull(CodeRes.CODE_15013)
    @Enum(CodeRes.CODE_15013)
    private RegionEnum region;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public LanguageEnum getLanguage() {
        return language;
    }

    public void setLanguage(LanguageEnum language) {
        this.language = language;
    }

    public RegionEnum getRegion() {
        return region;
    }

    public void setRegion(RegionEnum region) {
        this.region = region;
    }
}
