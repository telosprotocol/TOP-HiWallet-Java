package com.topnetwork.wallet.param.wallet.statistics;

import com.base.core.head.ao.PageAO;
import com.topnetwork.wallet.common.CodeRes;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Format;
import com.gitee.magic.core.valid.annotation.Length;
import com.gitee.magic.core.valid.annotation.NotNull;

/**
 * @ClassName WalletDatePageParam
 * @Description
 * @Author bran
 * @Date 2020/6/23 18:29
 */
public class WalletDatePageParam{

    @Schema(title = "时间开始时间戳", required = false)
    @Format(type = Format.FormatType.LONG, message = CodeRes.CODE_21013)
    private Long startTime;
    @Schema(title = "时间结束时间戳", required = false)
    @Format(type = Format.FormatType.LONG, message = CodeRes.CODE_21013)
    private Long endTime;

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }
}
