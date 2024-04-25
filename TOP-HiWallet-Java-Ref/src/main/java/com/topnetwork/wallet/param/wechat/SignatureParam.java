package com.topnetwork.wallet.param.wechat;

import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.common.enums.PlatformEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Enum;
import com.gitee.magic.core.valid.annotation.Length;
import com.gitee.magic.core.valid.annotation.NotNull;

/**
 * @program: TOP-HiWallet-Java
 * @description:
 * @author: Tyrone
 * @create: 2020-05-06 13:53
 **/
public class SignatureParam {

    @Schema(title = "url", required = true)
    @NotNull(CodeRes.CODE_19001)
    @Length(max = 200, message = CodeRes.CODE_19001)
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "SignatureParam{" +
                "url='" + url + '\'' +
                '}';
    }
}
