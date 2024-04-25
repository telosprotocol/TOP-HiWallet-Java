package com.topnetwork.wallet.param.wallet.invite;

import com.base.core.head.ao.PageAO;
import com.topnetwork.wallet.common.CodeRes;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Length;
import com.gitee.magic.core.valid.annotation.NotNull;

/**
 * @ClassName UserListParam
 * @Description
 * @Author bran
 * @Date 2020/5/7 14:31
 */
public class UserListParam extends PageAO {

    @Schema(title = "用户id", required = true)
    @NotNull(CodeRes.CODE_15009)
    @Length(message = CodeRes.CODE_15009)
    private String uid;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
