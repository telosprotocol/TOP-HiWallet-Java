package com.topnetwork.wallet.param.wallet.discover;

import com.topnetwork.wallet.common.CodeRes;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Length;
import com.gitee.magic.core.valid.annotation.NotNull;

/**
 * @ClassName GetBannersV4Param
 * @Description
 * @Author bran
 * @Date 2020/5/6 14:42
 */
public class GetBannersV4Param extends GetBannersV3Param {

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
