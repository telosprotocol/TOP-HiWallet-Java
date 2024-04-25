package com.topnetwork.wallet.param.system;

import com.topnetwork.wallet.common.CodeRes;

import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Format;
import com.gitee.magic.core.valid.annotation.NotNull;
import com.gitee.magic.core.valid.annotation.Format.FormatType;

public class UserModifyPwdParam {

    @Schema(title = "旧密码MD5值", required = true)
    @NotNull(CodeRes.CODE_11018)
    @Format(type = FormatType.MD5, message = CodeRes.CODE_11018)
    private String oldpassword;

    @Schema(title = "新密码MD5值", required = true)
    @NotNull(CodeRes.CODE_11020)
    @Format(type = FormatType.MD5, message = CodeRes.CODE_11020)
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
