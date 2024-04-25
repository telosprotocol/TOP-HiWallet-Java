package com.topnetwork.jpush;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.topnetwork.wallet.common.enums.LanguageEnum;
import com.topnetwork.wallet.common.enums.PushType;
import com.topnetwork.wallet.entity.AppNotice;
import com.topnetwork.wallet.param.wallet.RecTransactionData;
import com.topnetwork.wallet.param.wallet.commonpush.CommonPushMessageParam;
import com.topnetwork.wallet.param.wallet.defijpush.DefiPushMessageParam;
//new push add
public class PushMessage extends AppNotice {

    //推送cid，唯一标识用
    private String cid;
    //推送标签
    private List<String> tag;
    //附加参数
    private Map<String, String> extras;
    //iosPackage ios包名
    private String iosPackage;
    //badge 如果badge小于0，则不推送这个右上角的角标，主要用于ios消息盒子新增或者已读时，更新此状态
    private Integer badge = 1;
    //ios 是否为测试推送  true：表示的是产品发布推送服务 false：表示的是产品测试推送服务
    private Boolean contentAvailable;

    public PushMessage() {
        this.cid = UUID.randomUUID().toString();
    }

    public static PushMessage buildFromTranMessage(RecTransactionData transactionData) {
        //封装推送消息对象
        PushMessage pushMessage = new PushMessage();
        pushMessage.setNoticeType(PushType.TRANSACTION);
        Map<String, String> extras = new HashMap<>();
        extras.put("txhash", transactionData.getHash());
        pushMessage.setExtras(extras);
        pushMessage.setContent("您发出的交易:" + transactionData.getHash() + "已成功");
        pushMessage.setTitle("交易成功");
        pushMessage.setEnglishTitle("Transaction Success");
        pushMessage.setEnglishContent("You Transaction:" + transactionData.getHash() + " Success");
        pushMessage.setCreateTime(new Date());
        return pushMessage;
    }

    /**
     *  封装defi的消息体
     * @param defiPushMessage
     * @param language
     * @return
     */
    public static PushMessage buildFromDefiMessage(DefiPushMessageParam defiPushMessage, LanguageEnum language) {
        //封装推送消息对象
        PushMessage pushMessage = new PushMessage();
        pushMessage.setNoticeType(PushType.NOTICE_ALL);
        HashMap<String, String> map = new HashMap<>(2);
        //添加详情
        if (language.equals(LanguageEnum.CN)){
            map.put("desc",defiPushMessage.getContent());
        }else {
            map.put("desc",defiPushMessage.getEnglishContent());
        }
        pushMessage.setExtras(map);
        pushMessage.setContent(defiPushMessage.getContent());
        pushMessage.setTitle(defiPushMessage.getTitle());
        pushMessage.setEnglishTitle(defiPushMessage.getEnglishTitle());
        pushMessage.setEnglishContent(defiPushMessage.getEnglishContent());
        pushMessage.setCreateTime(new Date());
        return pushMessage;
    }

    public static PushMessage buildFromRouteMessage(CommonPushMessageParam commonPushMessageParam, LanguageEnum language) {
        //封装推送消息对象
        PushMessage pushMessage = new PushMessage();
        pushMessage.setNoticeType(PushType.ROUTE);
        HashMap<String, String> map = new HashMap<>(2);
        //添加详情
        map.put("url",commonPushMessageParam.getUrl());
        pushMessage.setUrl(commonPushMessageParam.getUrl());
        if (language.equals(LanguageEnum.CN)){
            map.put("desc",commonPushMessageParam.getContent());
        }else {
            map.put("desc",commonPushMessageParam.getEnglishContent());
        }
        pushMessage.setExtras(map);
        pushMessage.setContent(commonPushMessageParam.getContent());
        pushMessage.setTitle(commonPushMessageParam.getTitle());
        pushMessage.setEnglishTitle(commonPushMessageParam.getEnglishTitle());
        pushMessage.setEnglishContent(commonPushMessageParam.getEnglishContent());
        pushMessage.setCreateTime(new Date());
        return pushMessage;
    }

    public static PushMessage buildToTranMessage(RecTransactionData transactionData) {
        //封装推送消息对象
        PushMessage pushMessage = new PushMessage();
        pushMessage.setNoticeType(PushType.TRANSACTION);
        Map<String, String> extras = new HashMap<>();
        extras.put("txhash", transactionData.getHash());
        pushMessage.setExtras(extras);
        pushMessage.setContent("您接收到转账交易:" + transactionData.getHash());
        pushMessage.setTitle("新的转账");
        pushMessage.setEnglishTitle("New Transaction");
        pushMessage.setEnglishContent("You Receive Transaction:" + transactionData.getHash());
        pushMessage.setCreateTime(new Date());
        return pushMessage;
    }

    public List<String> getTag() {
        return tag;
    }

    public void setTag(List<String> tag) {
        this.tag = tag;
    }

    public Map<String, String> getExtras() {
        return extras;
    }

    public void setExtras(Map<String, String> extras) {
        this.extras = extras;
    }

    public String getCid() {
        return cid;
    }

    public String getIosPackage() {
        return iosPackage;
    }

    public void setIosPackage(String iosPackage) {
        this.iosPackage = iosPackage;
    }

    public Integer getBadge() {
        return badge;
    }

    public void setBadge(Integer badge) {
        this.badge = badge;
    }

    public Boolean getContentAvailable() {
        return contentAvailable;
    }

    public void setContentAvailable(Boolean contentAvailable) {
        this.contentAvailable = contentAvailable;
    }

}
