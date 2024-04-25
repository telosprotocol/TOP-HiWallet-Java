package com.topnetwork.wallet.result.wallet.invite;

import com.topnetwork.wallet.common.enums.InviteStatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

/**
 * @ClassName UserInfoResult
 * @Description
 * @Author bran
 * @Date 2020/5/7 14:32
 */
public class UserListResult {

    @Schema(title = "被邀请人名称")
    private String name;
    @Schema(title = "被邀请人电话")
    private String phone;
    @Schema(title = "被邀请人头像")
    private String iconUrl;
    @Schema(title = "可获得奖励")
    private BigDecimal total;
    @Schema(title = "被邀请人状态")
    private InviteStatusEnum status;
    @Schema(title = "被邀请接收邀请时间")
    private Long createTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public InviteStatusEnum getStatus() {
        return status;
    }

    public void setStatus(InviteStatusEnum status) {
        this.status = status;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}
