package com.topnetwork.wallet.param.wallet.defijpush;

import com.topnetwork.wallet.common.CodeRes;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.NotNull;

import java.util.List;

/**
 * @program: TOP-HiWallet-Java
 * @description:
 * @author: Tyrone
 * @create: 2020-11-27 17:32
 **/
public class DefiInfoParam {
    @Schema(title = "通知列表", required = true)
    @NotNull(CodeRes.CODE_40008)
    private List<DefiPushMessageParam> pushMessages;

    public List<DefiPushMessageParam> getPushMessages() {
        return pushMessages;
    }

    public void setPushMessages(List<DefiPushMessageParam> pushMessages) {
        this.pushMessages = pushMessages;
    }

    @Override
    public String toString() {
        return "DefiInfoParam{" +
                "pushMessages=" + pushMessages +
                '}';
    }
}
