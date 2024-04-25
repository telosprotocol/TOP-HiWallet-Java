package com.topnetwork.wallet.entity;

import com.topnetwork.wallet.common.enums.ChainTypeEnum;
import com.topnetwork.wallet.common.enums.discover.BannerType;
import com.topnetwork.wallet.common.enums.discover.DappShowType;
import com.base.core.framework.sql.entity.Base;
import com.gitee.magic.jdbc.persistence.annotation.Entity;
import com.gitee.magic.jdbc.persistence.annotation.Table;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.ColumnDef;

import java.util.Date;

@Entity("appDiscoverBanner")
@Table("wal_app_discover_banner")
public class AppDiscoverBanner extends Base {

    private static final long serialVersionUID = 1L;

    public AppDiscoverBanner() {
    }


    @ColumnDef(comment = "对应发现游戏id", isNull = true)
    private Long discoverGameId;
    @ColumnDef(comment = "banner标题")
    private String title;
    @ColumnDef(comment = "banner英文标题")
    private String englishTitle;
    @ColumnDef(comment = "banner类型")
    private BannerType type;
    @ColumnDef(comment = "公链类型")
    private ChainTypeEnum chainType;
    @ColumnDef(comment = "目标id，如跳转公告详情则为公告id", isNull = true)
    private Long targetId;
    @ColumnDef(comment = "banner图片url")
    private String bannerUrl;
    @ColumnDef(comment = "英文banner图片url")
    private String englishBannerUrl;
    @ColumnDef(comment = "跳转url", isNull = true)
    private String actionUrl;
    @ColumnDef(comment = "是否展示")
    private Boolean bannerShow;
    @ColumnDef(comment = "展示类型（ 审核时可用：examine, 非审核时可用： normal, 无限制、任何时候都显示： all）")
    private DappShowType showType;
    @ColumnDef(comment = "排序，数字越小越靠前")
    private Integer bannerOrder;
    @ColumnDef(comment = "活动开始时间")
    private Date startTime;
    @ColumnDef(comment = "活动结束时间")
    private Date endTime;
    @ColumnDef(comment = "更新时间", isNull = true)
    private Date updateTime;
    @ColumnDef(comment = "创建时间")
    private Date createTime;

    public Long getDiscoverGameId() {
        return discoverGameId;
    }

    public void setDiscoverGameId(Long discoverGameId) {
        this.discoverGameId = discoverGameId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BannerType getType() {
        return type;
    }

    public void setType(BannerType type) {
        this.type = type;
    }

    public String getEnglishTitle() {
        return englishTitle;
    }

    public void setEnglishTitle(String englishTitle) {
        this.englishTitle = englishTitle;
    }

    public String getEnglishBannerUrl() {
        return englishBannerUrl;
    }

    public void setEnglishBannerUrl(String englishBannerUrl) {
        this.englishBannerUrl = englishBannerUrl;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Long getTargetId() {
        return targetId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }

    public String getActionUrl() {
        return actionUrl;
    }

    public void setActionUrl(String actionUrl) {
        this.actionUrl = actionUrl;
    }

    public Boolean getBannerShow() {
        return bannerShow;
    }

    public void setBannerShow(Boolean bannerShow) {
        this.bannerShow = bannerShow;
    }

    public Integer getBannerOrder() {
        return bannerOrder;
    }

    public void setBannerOrder(Integer bannerOrder) {
        this.bannerOrder = bannerOrder;
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
