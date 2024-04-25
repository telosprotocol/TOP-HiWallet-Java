package com.topnetwork.wallet.result.wallet.appuser;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @ClassName UserInfoV2Result
 * @Description
 * @Author bran
 * @Date 2020/5/6 18:32
 */
public class UserInfoV2Result extends UserInfoResult {

    @Schema(title = "是否绑定手机号")
    private Boolean phoneBind;
    @Schema(title = "邀请页面链接地址")
    private String inviteUrl;
    @Schema(title = "绑定手机号页面链接地址")
    private String bindUrl;

    public Boolean getPhoneBind() {
        return phoneBind;
    }

    public void setPhoneBind(Boolean phoneBind) {
        this.phoneBind = phoneBind;
    }

    public String getInviteUrl() {
        return inviteUrl;
    }

    public void setInviteUrl(String inviteUrl) {
        this.inviteUrl = inviteUrl;
    }

    public String getBindUrl() {
        return bindUrl;
    }

    public void setBindUrl(String bindUrl) {
        this.bindUrl = bindUrl;
    }
}
