package com.topnetwork.wallet.param.system;

import com.topnetwork.wallet.common.CodeRes;

import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Format;
import com.gitee.magic.core.valid.annotation.Format.FormatType;
import com.gitee.magic.core.valid.annotation.NotNull;

/**
 * 用户
 *
 * @author Start
 */
public class UserDetailParam {

    @Schema(title = "用户ID", required = true)
    @NotNull(CodeRes.CODE_11007)
    @Format(type = FormatType.LONG, message = CodeRes.CODE_11007)
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
