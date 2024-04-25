package com.topnetwork.wallet.param.wallet.top;

import com.topnetwork.wallet.common.CodeRes;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Length;
import com.gitee.magic.core.valid.annotation.NotNull;

/**
 * @ClassName TopBaseParam
 * @Description
 * @Author bran
 * @Date 2020/10/10 17:00
 */
public class TopBaseParam {

    @Schema(title = "top地址", required = true)
    @NotNull(CodeRes.CODE_21005)
    @Length(message = CodeRes.CODE_21005)
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
