package com.topnetwork.wallet.result.wallet.config;

import io.swagger.v3.oas.annotations.media.Schema;

public class DiscoverResult {

    @Schema(title = "discover 开关")
    private Boolean discoverSwitch;

    public Boolean getDiscoverSwitch() {
        return discoverSwitch;
    }

    public void setDiscoverSwitch(Boolean discoverSwitch) {
        this.discoverSwitch = discoverSwitch;
    }
}
