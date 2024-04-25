package com.topnetwork.wallet.common.enums;

/**
 * 消息推送类型
 */
//new push add  新增推送类型
public enum PushType {
    ROUTE,//路由跳转（）
    AWAKEN,//唤醒推送
    NOTICE_ALL ,//公告全部，不需要调用接口显示详情
    ACTIVITY ,//活动，跳转到h5显示活动内容，
    NOTICE_SIMPLE,//公告简略，需要调接口展示详情
    TRANSACTION;//交易推送
}
