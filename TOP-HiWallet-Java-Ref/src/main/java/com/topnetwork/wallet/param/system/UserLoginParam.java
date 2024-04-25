package com.topnetwork.wallet.param.system;

import com.topnetwork.wallet.common.CodeRes;

import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Format;
import com.gitee.magic.core.valid.annotation.Format.FormatType;
import com.gitee.magic.core.valid.annotation.Length;
import com.gitee.magic.core.valid.annotation.NotNull;

/**
 * 用户登录
 *
 * @author Start
 */
public class UserLoginParam {

    @Schema(title = "账号", required = true)
    @NotNull(CodeRes.CODE_11009)
    @Length(max = 32, message = CodeRes.CODE_11009)
    private String userName;

    @Schema(title = "密码MD5值", required = true)
    @NotNull(CodeRes.CODE_11016)
    @Format(type = FormatType.MD5, message = CodeRes.CODE_11016)
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
