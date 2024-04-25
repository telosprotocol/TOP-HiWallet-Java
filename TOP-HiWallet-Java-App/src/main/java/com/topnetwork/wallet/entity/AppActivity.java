package com.topnetwork.wallet.entity;

import com.topnetwork.wallet.common.enums.ActivityType;
import com.topnetwork.wallet.common.enums.RewardType;
import com.base.core.framework.sql.entity.Base;
import com.gitee.magic.jdbc.persistence.annotation.Entity;
import com.gitee.magic.jdbc.persistence.annotation.Table;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.ColumnDef;

import java.util.Date;

@Entity("appActivity")
@Table("wal_app_activity")
public class AppActivity extends Base {

    private static final long serialVersionUID = 1L;

    public AppActivity() {
    }

    @ColumnDef(comment = "标题")
    private String title;
    @ColumnDef(comment = "英文标题")
    private String englishTitle;
    @ColumnDef(comment = "描述")
    private String chineseDesc;
    @ColumnDef(comment = "英文描述")
    private String englishDesc;
    @ColumnDef(comment = "活动类型（行为、游戏、行为和游戏）")
    private ActivityType activityType;
    @ColumnDef(comment = "行为类型（注册、新用户、每日、转账、砖块游戏、竞猜、答题）")
    private RewardType behaviorType;
    @ColumnDef(comment = "背景图片key", isNull = true)
    private String backgroundUrl;
    @ColumnDef(comment = "游戏h5链接地址", isNull = true)
    private String activityUrl;
    @ColumnDef(comment = "游戏免费次数", isNull = true)
    private Integer freeSize;
    @ColumnDef(comment = "排序，数字越小越靠前")
    private Integer activityOrder;
    @ColumnDef(comment = "修改时间", isNull = true)
    private Date updateTime;
    @ColumnDef(comment = "创建时间")
    private Date createTime;

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

    public ActivityType getActivityType() {
        return activityType;
    }

    public void setActivityType(ActivityType activityType) {
        this.activityType = activityType;
    }

    public RewardType getBehaviorType() {
        return behaviorType;
    }

    public void setBehaviorType(RewardType behaviorType) {
        this.behaviorType = behaviorType;
    }

    public String getBackgroundUrl() {
        return backgroundUrl;
    }

    public void setBackgroundUrl(String backgroundUrl) {
        this.backgroundUrl = backgroundUrl;
    }

    public String getActivityUrl() {
        return activityUrl;
    }

    public void setActivityUrl(String activityUrl) {
        this.activityUrl = activityUrl;
    }

    public Integer getFreeSize() {
        return freeSize;
    }

    public void setFreeSize(Integer freeSize) {
        this.freeSize = freeSize;
    }

    public Integer getActivityOrder() {
        return activityOrder;
    }

    public void setActivityOrder(Integer activityOrder) {
        this.activityOrder = activityOrder;
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
}
