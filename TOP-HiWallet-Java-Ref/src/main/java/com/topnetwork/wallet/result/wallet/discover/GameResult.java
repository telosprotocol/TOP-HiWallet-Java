package com.topnetwork.wallet.result.wallet.discover;

import com.topnetwork.wallet.common.enums.discover.GameType;
import io.swagger.v3.oas.annotations.media.Schema;

public class GameResult {

    @Schema(title = "游戏 id")
    private Long id;
    @Schema(title = "标题")
    private String title;
    @Schema(title = "描述")
    private String desc;
    @Schema(title = "游戏类型 TOPSTAKING,//top staking ；HTML,//2.跳转至H5，用内置浏览器打开； CENTRALIAPP,//4.跳转至新青年等中心化app；DECENTRALIAPP,//3.跳转至DApp等去中心化app；")
    private GameType type;
    @Schema(title = "图标url")
    private String iconUrl;
    @Schema(title = "链接地址")
    private String activityUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public GameType getType() {
        return type;
    }

    public void setType(GameType type) {
        this.type = type;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getActivityUrl() {
        return activityUrl;
    }

    public void setActivityUrl(String activityUrl) {
        this.activityUrl = activityUrl;
    }
}
