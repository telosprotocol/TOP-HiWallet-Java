package com.topnetwork.wallet.param.wallet.discover;

import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.common.enums.ChainTypeEnum;
import com.topnetwork.wallet.common.enums.discover.DappShowType;
import com.topnetwork.wallet.common.enums.discover.GameType;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Enum;
import com.gitee.magic.core.valid.annotation.Format;
import com.gitee.magic.core.valid.annotation.Length;
import com.gitee.magic.core.valid.annotation.NotNull;

public class GameUpdateParam {

    @Schema(title = "游戏id", required = true)
    @NotNull(CodeRes.CODE_15041)
    @Length(message = CodeRes.CODE_15041)
    @Format(type = Format.FormatType.LONG, message = CodeRes.CODE_15041)
    private Long id;
    @Schema(title = "中文标题", required = true)
    @NotNull(CodeRes.CODE_15042)
    @Length(message = CodeRes.CODE_15042)
    private String title;
    @Schema(title = "英文标题", required = true)
    @NotNull(CodeRes.CODE_15042)
    @Length(message = CodeRes.CODE_15042)
    private String englishTitle;
    @Schema(title = "中文副标题", required = true)
    @NotNull(CodeRes.CODE_15042)
    @Length(message = CodeRes.CODE_15042)
    private String desc;
    @Schema(title = "英文副标题", required = true)
    @NotNull(CodeRes.CODE_15042)
    @Length(message = CodeRes.CODE_15042)
    private String englishDesc;
    @Schema(title = "游戏类型", required = true)
    @NotNull(CodeRes.CODE_15043)
    @Enum(CodeRes.CODE_15043)
    private GameType type;
    @Schema(title = "公链类型", required = false)
    @Enum(CodeRes.CODE_15044)
    private ChainTypeEnum chainType;
    @Schema(title = "图标url", required = true)
    @NotNull(CodeRes.CODE_15045)
    @Length(message = CodeRes.CODE_15045)
    private String iconUrl;
    @Schema(title = "链接地址", required = false)
    private String activityUrl;
    @Schema(title = "游戏分类id", required = true)
    @NotNull(CodeRes.CODE_15040)
    @Format(type = Format.FormatType.LONG, message = CodeRes.CODE_15040)
    @Length(message = CodeRes.CODE_15040)
    private Long classifyId;
    @Schema(title = "是否展示到主页", required = true)
    @NotNull(CodeRes.CODE_15047)
    @Length(message = CodeRes.CODE_15047)
    @Format(type = Format.FormatType.BOOLEAN, message = CodeRes.CODE_15047)
    private Boolean showToHome;

    @Schema(title = "是否展示", required = true)
    @NotNull(CodeRes.CODE_15077)
    @Length(message = CodeRes.CODE_15077)
    @Format(type = Format.FormatType.BOOLEAN, message = CodeRes.CODE_15077)
    private Boolean gameShow;
    @Schema(title = "排序，数字越小越靠前", required = true)
    @NotNull(CodeRes.CODE_15048)
    @Length(message = CodeRes.CODE_15048)
    @Format(type = Format.FormatType.INTEGER, message = CodeRes.CODE_15048)
    private Integer gameOrder;
    @Schema(title = "展示类型（ 审核时可用：examine, 非审核时可用： normal, 无限制、任何时候都显示： all）")
    @Enum(CodeRes.CODE_15076)
    @NotNull(CodeRes.CODE_15076)
    private DappShowType showType;

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

    public String getEnglishTitle() {
        return englishTitle;
    }

    public void setEnglishTitle(String englishTitle) {
        this.englishTitle = englishTitle;
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

    public ChainTypeEnum getChainType() {
        return chainType;
    }

    public void setChainType(ChainTypeEnum chainType) {
        this.chainType = chainType;
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

    public Long getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(Long classifyId) {
        this.classifyId = classifyId;
    }

    public Boolean getShowToHome() {
        return showToHome;
    }

    public void setShowToHome(Boolean showToHome) {
        this.showToHome = showToHome;
    }

    public Integer getGameOrder() {
        return gameOrder;
    }

    public void setGameOrder(Integer gameOrder) {
        this.gameOrder = gameOrder;
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
