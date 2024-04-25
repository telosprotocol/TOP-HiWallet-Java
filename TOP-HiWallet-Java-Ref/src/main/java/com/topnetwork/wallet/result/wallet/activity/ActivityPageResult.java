package com.topnetwork.wallet.result.wallet.activity;

import com.topnetwork.wallet.common.enums.ActivityType;
import com.topnetwork.wallet.common.enums.RewardType;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

public class ActivityPageResult {

    @Schema(title = "activity id")
    private Long id;

    @Schema(title = "标题")
    private String title;
    @Schema(title = "标题")
    private String englishTitle;
    @Schema(title = "描述")
    private String desc;
    @Schema(title = "英文描述")
    private String englishDesc;
    @Schema(title = "活动类型（行为、游戏、行为和游戏）")
    private ActivityType activityType;
    @Schema(title = "行为类型（注册、新用户、每日、转账、砖块游戏、竞猜、答题）")
    private RewardType behaviorType;
    @Schema(title = "图片url")
    private String backgroundUrl;
    @Schema(title = "游戏h5链接地址")
    private String activityUrl;
    @Schema(title = "游戏免费次数")
    private Integer freeSize;
    @Schema(title = "排序，数字越小越靠前")
    private Integer order;
    @Schema(title = "修改时间")
    private Long updateTime;


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

    public Integer getFreeSize() {
        return freeSize;
    }

    public void setFreeSize(Integer freeSize) {
        this.freeSize = freeSize;
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

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
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

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }
}
