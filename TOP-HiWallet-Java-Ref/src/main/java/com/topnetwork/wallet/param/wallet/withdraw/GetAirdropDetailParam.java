package com.topnetwork.wallet.param.wallet.withdraw;

import com.topnetwork.wallet.common.CodeRes;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Length;
import com.gitee.magic.core.valid.annotation.NotNull;

/**
 * @ClassName GetAirdropDetailParam
 * @Description
 * @Author bran
 * @Date 2020/7/7 15:27
 */
public class GetAirdropDetailParam {

    @Schema(title = "交易号", required = true)
    @NotNull(value = CodeRes.CODE_21014)
    @Length(message = CodeRes.CODE_21014)
    private String tranId;

    public String getTranId() {
        return tranId;
    }

    public void setTranId(String tranId) {
        this.tranId = tranId;
    }
}
