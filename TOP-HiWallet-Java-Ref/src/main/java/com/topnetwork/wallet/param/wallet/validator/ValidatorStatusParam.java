package com.topnetwork.wallet.param.wallet.validator;

import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.common.enums.PlatformEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Enum;
import com.gitee.magic.core.valid.annotation.NotNull;

/**
 * @program: TOP-HiWallet-Java
 * @description:
 * @author: Tyrone
 * @create: 2020-12-03 17:08
 **/
public class ValidatorStatusParam {
    @Schema(title = "平台（IOS；ANDROID）", required = true)
    @NotNull(CodeRes.CODE_14018)
    @Enum(CodeRes.CODE_14018)
    private PlatformEnum platform;

    @Schema(title = "版本", required = true)
    @NotNull(CodeRes.CODE_14019)
    private String version;

    public PlatformEnum getPlatform() {
        return platform;
    }

    public void setPlatform(PlatformEnum platform) {
        this.platform = platform;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "ValidatorStatusParam{" +
                "platform=" + platform +
                ", version='" + version + '\'' +
                '}';
    }
}
