package com.topnetwork.wallet.param.wallet.discover;

import com.topnetwork.wallet.common.CodeRes;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Format;
import com.gitee.magic.core.valid.annotation.Length;
import com.gitee.magic.core.valid.annotation.NotNull;

/**
 * @ClassName AddUsedHistoryParam
 * @Description 上报用户dapp使用记录参数
 * @Author bran
 * @Date 2020/4/22 13:48
 */
public class AddUsedHistoryParam {

    @Schema(title = "用户id", required = true)
    @NotNull(CodeRes.CODE_15009)
    @Length(message = CodeRes.CODE_15009)
    private String uid;
    @Schema(title = "dapp id", required = true)
    @NotNull(CodeRes.CODE_15041)
    @Format(type = Format.FormatType.LONG, message = CodeRes.CODE_15041)
    private Long dappId;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Long getDappId() {
        return dappId;
    }

    public void setDappId(Long dappId) {
        this.dappId = dappId;
    }
}
