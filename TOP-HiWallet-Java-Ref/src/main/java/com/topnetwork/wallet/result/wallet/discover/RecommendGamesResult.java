package com.topnetwork.wallet.result.wallet.discover;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public class RecommendGamesResult {

    @Schema(title = "游戏分类id")
    private Long classifyId;

    @Schema(title = "游戏分类名称")
    private String title;

    @Schema(title = "游戏列表")
    private List<GameResult> games;

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

    public List<GameResult> getGames() {
        return games;
    }

    public void setGames(List<GameResult> games) {
        this.games = games;
    }
}
