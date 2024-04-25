package com.topnetwork.wallet.param.wallet.home;

import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.common.enums.LanguageEnum;
import com.topnetwork.wallet.common.enums.RegionEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Enum;
import com.gitee.magic.core.valid.annotation.Length;
import com.gitee.magic.core.valid.annotation.NotNull;

/**
 * @ClassName GetAppListParam
 * @Description
 * @Author bran
 * @Date 2020/9/24 18:10
 */
public class GetAppListParam {

    @Schema(title = "语言", required = true)
    @Enum(CodeRes.CODE_14011)
    @NotNull(CodeRes.CODE_14011)
    @Length(message = CodeRes.CODE_14011)
    private LanguageEnum language;

    @Schema(title = "版本", required = true)
    @NotNull(CodeRes.CODE_14019)
    @Length(message = CodeRes.CODE_14019)
    private String version;

    @Schema(title = "地域（海外，国内,通用）", required = true)
    @NotNull(CodeRes.CODE_15013)
    @Enum(CodeRes.CODE_15013)
    private RegionEnum region;

    public LanguageEnum getLanguage() {
        return language;
    }

    public void setLanguage(LanguageEnum language) {
        this.language = language;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public RegionEnum getRegion() {
        return region;
    }

    public void setRegion(RegionEnum region) {
        this.region = region;
    }
}
