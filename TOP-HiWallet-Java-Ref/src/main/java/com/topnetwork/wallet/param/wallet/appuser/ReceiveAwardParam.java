package com.topnetwork.wallet.param.wallet.appuser;

import com.topnetwork.wallet.common.CodeRes;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Format;
import com.gitee.magic.core.valid.annotation.Length;
import com.gitee.magic.core.valid.annotation.NotNull;

public class ReceiveAwardParam {

    @Schema(title = "奖励id", required = true)
    @NotNull(CodeRes.CODE_15020)
    @Format(type = Format.FormatType.LONG, message = CodeRes.CODE_15020)
    private Long rewardId;

    public Long getRewardId() {
        return rewardId;
    }

    public void setRewardId(Long rewardId) {
        this.rewardId = rewardId;
    }
}
