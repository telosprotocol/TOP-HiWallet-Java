package com.topnetwork.wallet.param.wallet.activity;

import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.common.enums.ActivityType;
import com.topnetwork.wallet.common.enums.RewardType;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Enum;
import com.gitee.magic.core.valid.annotation.Format;
import com.gitee.magic.core.valid.annotation.Length;
import com.gitee.magic.core.valid.annotation.NotNull;


public class ActivityAddParam {

    @Schema(title = "标题", required = true)
    @NotNull(CodeRes.CODE_15042)
    @Length(message = CodeRes.CODE_15042)
    private String title;
    @Schema(title = "英文标题", required = true)
    @NotNull(CodeRes.CODE_15042)
    @Length(message = CodeRes.CODE_15042)
    private String englishTitle;
    @Schema(title = "描述", required = true)
    @NotNull(CodeRes.CODE_15042)
    @Length(message = CodeRes.CODE_15042)
    private String desc;
    @Schema(title = "英文描述", required = true)
    @NotNull(CodeRes.CODE_15042)
    @Length(message = CodeRes.CODE_15042)
    private String englishDesc;
    @Schema(title = "活动类型（行为、游戏、行为和游戏）", required = true)
    @NotNull(CodeRes.CODE_15043)
    @Enum(CodeRes.CODE_15043)
    private ActivityType activityType;
    @Schema(title = "行为类型（注册、新用户、每日、转账、砖块游戏、竞猜、答题）", required = true)
    @NotNull(CodeRes.CODE_15043)
    @Enum(CodeRes.CODE_15043)
    private RewardType behaviorType;
    @Schema(title = "图片url", required = false)
    private String backgroundUrl;
    @Schema(title = "游戏h5链接地址", required = false)
    private String activityUrl;
    @Schema(title = "游戏免费次数", required = false)
    @Format(type = Format.FormatType.INTEGER, message = CodeRes.CODE_15056)
    private Integer freeSize;
    @Schema(title = "排序，数字越小越靠前", required = true)
    @NotNull(CodeRes.CODE_15054)
    @Length(message = CodeRes.CODE_15054)
    @Format(type = Format.FormatType.INTEGER, message = CodeRes.CODE_15054)
    private Integer order;

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

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }
}
