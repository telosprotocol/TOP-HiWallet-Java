package com.topnetwork.wallet.entity;

import com.topnetwork.wallet.common.enums.ChainTypeEnum;
import com.topnetwork.wallet.common.enums.discover.DappShowType;
import com.topnetwork.wallet.common.enums.discover.GameType;
import com.base.core.framework.sql.entity.Base;
import com.gitee.magic.jdbc.persistence.annotation.Entity;
import com.gitee.magic.jdbc.persistence.annotation.Table;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.ColumnDef;

import java.util.Date;

@Entity("appDiscoverGame")
@Table("wal_app_discover_game")
public class AppDiscoverGame extends Base {

    private static final long serialVersionUID = 1L;

    public AppDiscoverGame() {
    }

    @ColumnDef(comment = "标题")
    private String title;
    @ColumnDef(comment = "英文标题")
    private String englishTitle;
    @ColumnDef(comment = "描述")
    private String chineseDesc;
    @ColumnDef(comment = "英文描述")
    private String englishDesc;
    @ColumnDef(comment = "游戏类型")
    private GameType type;
    @ColumnDef(comment = "公链类型")
    private ChainTypeEnum chainType;
    @ColumnDef(comment = "图标url")
    private String iconUrl;
    @ColumnDef(comment = "链接地址", isNull = true)
    private String activityUrl;
    @ColumnDef(comment = "游戏分类id")
    private Long classifyId;
    @ColumnDef(comment = "是否展示")
    private Boolean gameShow;
    @ColumnDef(comment = "展示类型（ 审核时可用：examine, 非审核时可用： normal, 无限制、任何时候都显示： all）")
    private DappShowType showType;
    @ColumnDef(comment = "是否展示到主页")
    private Boolean showToHome;
    @ColumnDef(comment = "排序，数字越小越靠前")
    private Integer gameOrder;
    @ColumnDef(comment = "更新时间", isNull = true)
    private Date updateTime;
    @ColumnDef(comment = "创建时间")
    private Date createTime;

    public Boolean getGameShow() {
        return gameShow;
    }

    public void setGameShow(Boolean gameShow) {
        this.gameShow = gameShow;
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

    public String getChineseDesc() {
        return chineseDesc;
    }

    public void setChineseDesc(String chineseDesc) {
        this.chineseDesc = chineseDesc;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public ChainTypeEnum getChainType() {
        return chainType;
    }

    public void setChainType(ChainTypeEnum chainType) {
        this.chainType = chainType;
    }

    public DappShowType getShowType() {
        return showType;
    }

    public void setShowType(DappShowType showType) {
        this.showType = showType;
    }
}
