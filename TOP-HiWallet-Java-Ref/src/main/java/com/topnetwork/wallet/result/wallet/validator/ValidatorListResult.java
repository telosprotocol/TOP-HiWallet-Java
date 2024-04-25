package com.topnetwork.wallet.result.wallet.validator;

import com.topnetwork.wallet.common.enums.PlatformEnum;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @program: TOP-HiWallet-Java
 * @description:
 * @author: Tyrone
 * @create: 2020-12-03 15:58
 **/
public class ValidatorListResult {

    @Schema(title = "id", required = true)
    private Long id;

    @Schema(title = "平台（IOS；ANDROID）", required = true)
    private PlatformEnum platform;

    @Schema(title = "版本", required = true)
    private String version;

    @Schema(title = "defi开关", required = true)
    private Boolean openDefi;

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

    public Boolean getOpenDefi() {
        return openDefi;
    }

    public void setOpenDefi(Boolean openDefi) {
        this.openDefi = openDefi;
    }

    @Override
    public String toString() {
        return "ValidatorListResult{" +
                "id=" + id +
                ", platform=" + platform +
                ", version='" + version + '\'' +
                ", openDefi=" + openDefi +
                '}';
    }
}
