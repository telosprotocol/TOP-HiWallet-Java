package com.topnetwork.reward.bean;

import com.topnetwork.wallet.common.enums.ActivityType;
import com.topnetwork.wallet.common.enums.UpsAndDowns;
import com.topnetwork.wallet.entity.AppActivity;
import com.topnetwork.wallet.entity.AppBtcPrice;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class RewardMessage {

    private MethodName methodName;
    private Long uid;
    private ActivityType activityType;
    private AppActivity appActivity;
    private Integer record;
    private UpsAndDowns upsAndDowns;
    private AppBtcPrice appBtcPrice;
    private BigDecimal reward;
    private Date userCreateTime;
    /**
     * 奖励未发放日期与当前时间相隔天数（0表示当天）
     */
    private List<Integer> notSendDay;

    public static RewardMessage getInstance(MethodName methodName, Long uid, AppActivity appActivity, ActivityType activityType, Integer record) {
        RewardMessage rewardMessage = new RewardMessage();
        rewardMessage.setMethodName(methodName);
        rewardMessage.setUid(uid);
        rewardMessage.setActivityType(activityType);
        rewardMessage.setAppActivity(appActivity);
        rewardMessage.setRecord(record);
        return rewardMessage;
    }

    public Integer getRecord() {
        return record;
    }

    public void setRecord(Integer record) {
        this.record = record;
    }

    public AppActivity getAppActivity() {
        return appActivity;
    }

    public void setAppActivity(AppActivity appActivity) {
        this.appActivity = appActivity;
    }

    public MethodName getMethodName() {
        return methodName;
    }

    public void setMethodName(MethodName methodName) {
        this.methodName = methodName;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public ActivityType getActivityType() {
        return activityType;
    }

    public void setActivityType(ActivityType activityType) {
        this.activityType = activityType;
    }

    public UpsAndDowns getUpsAndDowns() {
        return upsAndDowns;
    }

    public void setUpsAndDowns(UpsAndDowns upsAndDowns) {
        this.upsAndDowns = upsAndDowns;
    }

    public AppBtcPrice getAppBtcPrice() {
        return appBtcPrice;
    }

    public void setAppBtcPrice(AppBtcPrice appBtcPrice) {
        this.appBtcPrice = appBtcPrice;
    }

    public BigDecimal getReward() {
        return reward;
    }

    public void setReward(BigDecimal reward) {
        this.reward = reward;
    }

    public List<Integer> getNotSendDay() {
        return notSendDay;
    }

    public void setNotSendDay(List<Integer> notSendDay) {
        this.notSendDay = notSendDay;
    }

    public Date getUserCreateTime() {
        return userCreateTime;
    }

    public void setUserCreateTime(Date userCreateTime) {
        this.userCreateTime = userCreateTime;
    }
}
