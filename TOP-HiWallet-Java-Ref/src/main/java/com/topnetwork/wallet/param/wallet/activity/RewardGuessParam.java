package com.topnetwork.wallet.param.wallet.activity;

import com.topnetwork.wallet.common.CodeRes;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.NotNull;

import java.util.List;

/**
 * @ClassName RewardGuessParam
 * @Description
 * @Author bran
 * @Date 2020/3/19 14:05
 */
public class RewardGuessParam {

    @Schema(title = "奖励列表", required = true)
    @NotNull(CodeRes.CODE_15074)
    private List<RewardParam> rewardList;

    public List<RewardParam> getRewardList() {
        return rewardList;
    }

    public void setRewardList(List<RewardParam> rewardList) {
        this.rewardList = rewardList;
    }
}
