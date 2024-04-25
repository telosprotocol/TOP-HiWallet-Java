package com.topnetwork.wallet.param.wallet.invite;

import com.topnetwork.wallet.common.CodeRes;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Format;
import com.gitee.magic.core.valid.annotation.Length;
import com.gitee.magic.core.valid.annotation.NotNull;

/**
 * @ClassName ManagerPhoneCodeResult
 * @Description
 * @Author bran
 * @Date 2020/5/9 16:06
 */
public class ManagerPhoneCodeParam {

    @Schema(title = "手机号码", required = true)
    @NotNull(CodeRes.CODE_11013)
    @Length(message = CodeRes.CODE_11013)
    private String mobile;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
