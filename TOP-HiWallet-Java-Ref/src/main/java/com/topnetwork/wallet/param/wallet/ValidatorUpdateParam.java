package com.topnetwork.wallet.param.wallet;

import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.common.enums.PlatformEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Enum;
import com.gitee.magic.core.valid.annotation.Format;
import com.gitee.magic.core.valid.annotation.NotNull;

/**
 * @program: TOP-HiWallet-Java
 * @description:
 * @author: Tyrone
 * @create: 2020-12-03 15:49
 **/
public class ValidatorUpdateParam {

    @Schema(title = "id", required = true)
    @Format(type = Format.FormatType.LONG, message = CodeRes.CODE_14023)
    private Long id;

    @Schema(title = "平台（IOS；ANDROID）", required = true)
    @NotNull(CodeRes.CODE_14018)
    @Enum(CodeRes.CODE_14018)
    private PlatformEnum platform;

    @Schema(title = "版本", required = true)
    @NotNull(CodeRes.CODE_14019)
    private String version;


    @Schema(title = "defi开关", required = true)
    @NotNull(CodeRes.CODE_15085)
    @Format(type = Format.FormatType.BOOLEAN, message = CodeRes.CODE_15085)
    private Boolean defi;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Boolean getDefi() {
        return defi;
    }

    public void setDefi(Boolean defi) {
        this.defi = defi;
    }

    @Override
    public String toString() {
        return "ValidatorUpdateParam{" +
                "id=" + id +
                ", platform=" + platform +
                ", version='" + version + '\'' +
                ", defi=" + defi +
                '}';
    }
}
