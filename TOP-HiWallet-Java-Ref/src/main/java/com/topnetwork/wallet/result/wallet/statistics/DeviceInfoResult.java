package com.topnetwork.wallet.result.wallet.statistics;

import com.topnetwork.wallet.common.enums.LanguageEnum;
import com.topnetwork.wallet.common.enums.PlatformEnum;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @ClassName DeviceInfoResult
 * @Description
 * @Author bran
 * @Date 2020/6/23 17:55
 */
public class DeviceInfoResult {

    @Schema(title="数量")
    private Integer num;
    @Schema(title="设备数量")
    private PlatformEnum platform;
    @Schema(title="地址数量")
    private LanguageEnum language;

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public PlatformEnum getPlatform() {
        return platform;
    }

    public void setPlatform(PlatformEnum platform) {
        this.platform = platform;
    }

    public LanguageEnum getLanguage() {
        return language;
    }

    public void setLanguage(LanguageEnum language) {
        this.language = language;
    }
}
