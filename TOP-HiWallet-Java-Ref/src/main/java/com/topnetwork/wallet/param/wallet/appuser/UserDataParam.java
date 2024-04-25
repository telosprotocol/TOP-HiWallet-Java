package com.topnetwork.wallet.param.wallet.appuser;

import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Length;
import com.gitee.magic.core.valid.annotation.NotNull;

/**
 * @ClassName UserDataParam
 * @Description
 * @Author bran
 * @Date 2020/3/30 18:17
 */
public class UserDataParam {

    @Schema(title = "用户accessId", required = true)
    @NotNull
    @Length
    private String accessId;

    public String getAccessId() {
        return accessId;
    }

    public void setAccessId(String accessId) {
        this.accessId = accessId;
    }
}
