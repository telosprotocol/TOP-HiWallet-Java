package com.topnetwork.wallet.result.wallet.discover;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public class RecommendGamesV3Result {

    @Schema(title = "游戏分类id")
    private Long classifyId;

    @Schema(title = "游戏分类名称")
    private String title;

    @Schema(title = "游戏列表")
    private List<GameResult> items;

    @Schema(title = "是否显示更多")
    private Boolean showMore;

    public Long getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(Long classifyId) {
        this.classifyId = classifyId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<GameResult> getItems() {
        return items;
    }

    public void setItems(List<GameResult> items) {
        this.items = items;
    }

    public Boolean getShowMore() {
        return showMore;
    }

    public void setShowMore(Boolean showMore) {
        this.showMore = showMore;
    }
}
