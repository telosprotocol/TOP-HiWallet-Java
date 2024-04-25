package com.topnetwork.wallet.result.wallet.invite;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @ClassName InviteCodeResult
 * @Description
 * @Author bran
 * @Date 2020/5/7 14:44
 */
public class InviteCodeResult {

    @Schema(title = "邀请码")
    private String code;
    @Schema(title = "本人微信名字")
    private String name;
    @Schema(title = "本人微信头像")
    private String iconUrl;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }
}
