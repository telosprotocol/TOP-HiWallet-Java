package com.topnetwork.wallet.param.wallet.activity;

import com.base.core.head.ao.PageAO;
import com.topnetwork.wallet.common.enums.RewardFromType;
import com.topnetwork.wallet.common.enums.RewardSide;
import com.topnetwork.wallet.common.enums.RewardType;
import io.swagger.v3.oas.annotations.media.Schema;

public class RewardPageParam extends PageAO {

    @Schema(title = "新青年号", required = false)
    private String certCode;
    @Schema(title = "活动", required = false)
    private RewardType type;
    @Schema(title = "奖励方向（花费、奖励）", required = false)
    private RewardSide side;
    @Schema(title = "奖励来源（空投/游戏奖励）", required = false)
    private RewardFromType source;
    @Schema(title = "是否被领取", required = false)
    private Boolean receive;
    @Schema(title = "状态：已过期=0、未过期=1", required = false)
    private Integer status;

    public String getCertCode() {
        return certCode;
    }

    public void setCertCode(String certCode) {
        this.certCode = certCode;
    }

    public RewardType getType() {
        return type;
    }

    public void setType(RewardType type) {
        this.type = type;
    }

    public RewardSide getSide() {
        return side;
    }

    public void setSide(RewardSide side) {
        this.side = side;
    }

    public RewardFromType getSource() {
        return source;
    }

    public void setSource(RewardFromType source) {
        this.source = source;
    }

    public Boolean getReceive() {
        return receive;
    }

    public void setReceive(Boolean receive) {
        this.receive = receive;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
