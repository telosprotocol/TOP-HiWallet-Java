package com.topnetwork.wallet.param.defi.system;

import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.NotNull;
import com.gitee.magic.core.valid.annotation.number.LongValid;

/**
 * 用户
 *
 * @author Start
 */
public class UserDetailParam {

    @Schema(title = "用户ID")
    @NotNull
    @LongValid
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
