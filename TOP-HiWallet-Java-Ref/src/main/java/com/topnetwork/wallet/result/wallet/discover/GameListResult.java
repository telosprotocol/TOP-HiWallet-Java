package com.topnetwork.wallet.result.wallet.discover;

import com.topnetwork.wallet.common.enums.ChainTypeEnum;
import com.topnetwork.wallet.common.enums.discover.DappShowType;
import com.topnetwork.wallet.common.enums.discover.GameType;
import io.swagger.v3.oas.annotations.media.Schema;

public class GameListResult {

    @Schema(title = "游戏id")
    private Long id;
    @Schema(title = "中文标题")
    private String title;
    @Schema(title = "英文标题")
    private String englishTitle;
    @Schema(title = "中文描述")
    private String desc;
    @Schema(title = "英文描述")
    private String englishDesc;
    @Schema(title = "所属公链")
    private ChainTypeEnum chainType;
    @Schema(title = "所属类型")
    private GameClassifyResult classify;
    @Schema(title = "是否显示到主页")
    private Boolean showToHome;
    @Schema(title = "是否展示")
    private Boolean gameShow;
    @Schema(title = "排序")
    private Integer order;
    @Schema(title = "类型")
    private GameType type;
    @Schema(title = "跳转链接")
    private String activityUrl;
    @Schema(title = "logo")
    private String iconUrl;
    @Schema(title = "展示类型（ 审核时可用：examine, 非审核时可用： normal, 无限制、任何时候都显示： all）")
    private DappShowType showType;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEnglishTitle() {
        return englishTitle;
    }

    public void setEnglishTitle(String englishTitle) {
        this.englishTitle = englishTitle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ChainTypeEnum getChainType() {
        return chainType;
    }

    public void setChainType(ChainTypeEnum chainType) {
        this.chainType = chainType;
    }

    public GameClassifyResult getClassify() {
        return classify;
    }

    public void setClassify(GameClassifyResult classify) {
        this.classify = classify;
    }

    public Boolean getShowToHome() {
        return showToHome;
    }

    public void setShowToHome(Boolean showToHome) {
        this.showToHome = showToHome;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }


    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getEnglishDesc() {
        return englishDesc;
    }

    public void setEnglishDesc(String englishDesc) {
        this.englishDesc = englishDesc;
    }

    public GameType getType() {
        return type;
    }

    public void setType(GameType type) {
        this.type = type;
    }

    public String getActivityUrl() {
        return activityUrl;
    }

    public void setActivityUrl(String activityUrl) {
        this.activityUrl = activityUrl;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public DappShowType getShowType() {
        return showType;
    }

    public void setShowType(DappShowType showType) {
        this.showType = showType;
    }

    public Boolean getGameShow() {
        return gameShow;
    }

    public void setGameShow(Boolean gameShow) {
        this.gameShow = gameShow;
    }
}
