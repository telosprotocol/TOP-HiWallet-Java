package com.topnetwork.wallet.result.wallet.appuser;

import io.swagger.v3.oas.annotations.media.Schema;

public class IsNewResult {

    @Schema(title = "是否新用户")
    private Boolean newUser;

    public Boolean getNewUser() {
        return newUser;
    }

    public void setNewUser(Boolean newUser) {
        this.newUser = newUser;
    }
}
