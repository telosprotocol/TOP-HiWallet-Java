package com.topnetwork.wallet.param.wallet.v2;

import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.common.enums.LanguageEnum;
import com.topnetwork.wallet.common.enums.RegionEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Enum;
import com.gitee.magic.core.valid.annotation.Length;
import com.gitee.magic.core.valid.annotation.NotNull;

public class DeviceBaseParam {

    @Schema(title = "推送id", required = true)
    @NotNull(CodeRes.CODE_15001)
    @Length(max = 1000, min = 5, message = CodeRes.CODE_15001)
    private String registrationId;

    @Schema(title = "设备Id", required = true)
    @NotNull(CodeRes.CODE_15001)
    private String clientId;

    @Schema(title = "用户唯一标识", required = true)
    @NotNull(CodeRes.CODE_15001)
    private String userId;

    @Schema(title = "设备当前所用语言，CN：中文，EN：英文", required = true)
    @NotNull(CodeRes.CODE_15000)
    @Enum(CodeRes.CODE_15000)
    private LanguageEnum language;

    @Schema(title = "地域（海外版，国内版,通用版）", required = true)
    @NotNull(CodeRes.CODE_15013)
    @Enum(CodeRes.CODE_15013)
    private RegionEnum region;

    public String getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
