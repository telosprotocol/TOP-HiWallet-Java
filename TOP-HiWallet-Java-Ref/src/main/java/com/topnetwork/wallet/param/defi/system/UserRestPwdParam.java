package com.topnetwork.wallet.param.defi.system;

import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Format;
import com.gitee.magic.core.valid.annotation.Format.FormatType;
import com.gitee.magic.core.valid.annotation.NotNull;

/**
 * 用户密码重置
 *
 * @author Start
 */
public class UserRestPwdParam extends UserDetailParam {

    @Schema(title="密码")
    @NotNull
    @Format(type = FormatType.MD5)
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
