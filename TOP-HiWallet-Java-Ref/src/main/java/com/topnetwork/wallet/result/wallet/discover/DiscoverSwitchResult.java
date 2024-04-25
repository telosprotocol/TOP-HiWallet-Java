package com.topnetwork.wallet.result.wallet.discover;

import io.swagger.v3.oas.annotations.media.Schema;

public class DiscoverSwitchResult {

    @Schema(title = "是否打开", required = true)
    private Boolean open;

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }
}
