package com.topnetwork.wallet.result.wallet.discover;

import com.topnetwork.wallet.common.enums.discover.AppStyleType;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public class RecommendNewResult {

    @Schema(title = "应用分组id")
    private Long groupId;

    @Schema(title = "应用分组名称")
    private String title;

    @Schema(title = "显示样式  横向:transverse, 纵向:portrait")
    private AppStyleType style;

    @Schema(title = "是否可删除")
    private Boolean delete;

    @Schema(title = "应用列表")
    private List<GetNewBannersResult> items;

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<GetNewBannersResult> getItems() {
        return items;
    }

    public void setItems(List<GetNewBannersResult> items) {
        this.items = items;
    }

    public AppStyleType getStyle() {
        return style;
    }

    public void setStyle(AppStyleType style) {
        this.style = style;
    }

    public Boolean getDelete() {
        return delete;
    }

    public void setDelete(Boolean delete) {
        this.delete = delete;
    }
}
