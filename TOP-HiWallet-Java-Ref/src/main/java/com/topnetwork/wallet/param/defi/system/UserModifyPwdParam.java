package com.topnetwork.wallet.param.defi.system;

import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Format;
import com.gitee.magic.core.valid.annotation.Format.FormatType;
import com.gitee.magic.core.valid.annotation.NotNull;

public class UserModifyPwdParam {

    @Schema(title = "旧密码")
    @NotNull
    @Format(type = FormatType.MD5)
    private String oldpassword;

    @Schema(title = "新密码")
    @NotNull
    @Format(type = FormatType.MD5)
    private String newpassword;

    public String getOldpassword() {
        return oldpassword;
    }

    public void setOldpassword(String oldpassword) {
        this.oldpassword = oldpassword;
    }

    public String getNewpassword() {
        return newpassword;
    }

    public void setNewpassword(String newpassword) {
        this.newpassword = newpassword;
    }
}
