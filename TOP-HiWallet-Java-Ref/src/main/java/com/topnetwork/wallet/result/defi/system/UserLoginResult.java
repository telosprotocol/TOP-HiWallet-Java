package com.topnetwork.wallet.result.defi.system;

import io.swagger.v3.oas.annotations.media.Schema;

public class UserLoginResult extends UserDetailResult {

    @Schema(title="访问ID")
    private String accessid;

    @Schema(title="访问KEY")
    private String accesskey;

    public String getAccessid() {
        return accessid;
    }

    public void setAccessid(String accessid) {
        this.accessid = accessid;
    }

    public String getAccesskey() {
        return accesskey;
    }

    public void setAccesskey(String accesskey) {
        this.accesskey = accesskey;
    }

}
