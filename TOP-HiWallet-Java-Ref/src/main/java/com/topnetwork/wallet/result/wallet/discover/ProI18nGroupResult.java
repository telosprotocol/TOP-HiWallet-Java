package com.topnetwork.wallet.result.wallet.discover;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @ClassName ProI18nGroupResult
 * @Description
 * @Author bran
 * @Date 2020/5/26 11:37
 */
public class ProI18nGroupResult {

    @Schema(title="分组id")
    private Long groupId;
    @Schema(title="是否显示到首页")
    private Boolean showToHome;
    @Schema(title="在该分组内显示排序")
    private Integer showOrder;

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Boolean getShowToHome() {
        return showToHome;
    }

    public void setShowToHome(Boolean showToHome) {
        this.showToHome = showToHome;
    }

    public Integer getShowOrder() {
        return showOrder;
    }

    public void setShowOrder(Integer showOrder) {
        this.showOrder = showOrder;
    }
}
