package com.topnetwork.wallet.param.defi.system;

import com.base.core.head.ao.PageAO;

import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Length;

/**
 * 用户列表
 *
 * @author Start
 */
public class UserPageParam extends PageAO {

    @Schema(title="账号")
    @Length(max = 32)
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
