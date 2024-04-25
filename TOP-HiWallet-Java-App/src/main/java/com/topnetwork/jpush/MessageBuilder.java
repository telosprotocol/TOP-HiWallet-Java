package com.topnetwork.jpush;

import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import com.google.firebase.messaging.AndroidConfig;
import com.google.firebase.messaging.Message;
import com.topnetwork.wallet.common.enums.LanguageEnum;
import com.topnetwork.wallet.entity.Device;
import com.turo.pushy.apns.util.ApnsPayloadBuilder;
import com.turo.pushy.apns.util.SimpleApnsPushNotification;
import com.turo.pushy.apns.util.TokenUtil;
import org.springframework.util.StringUtils;
import com.gitee.magic.core.exception.ApplicationException;

import java.util.*;
import java.util.regex.Pattern;

public class MessageBuilder {

    /**
     * 极光推送对象封装
     *
     * @param pushMessage 消息对象
     * @param device      设备
     * @return PushPayload
     */
    public static PushPayload buildJMessage(PushMessage pushMessage, Device device) {
        Map<String, String> extras = getExtras(pushMessage, device.getLanguage());
        String title = getTitle(pushMessage, device.getLanguage(), true);
        String alert = getBody(pushMessage, device.getLanguage(), true);
        return PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.registrationId(device.getRegistrationId()))
                .setNotification(Notification.newBuilder()
                        .setAlert(alert)
                        .addPlatformNotification(AndroidNotification.newBuilder()
                                .setTitle(title)
                                .addExtras(extras).build())
                        .build())
                .build();
    }

    /**
     * 谷歌推送对象封装
     *
     * @param pushMessage 消息对象
     * @param device      设备
     * @return Message
     */
    public static Message buildFMessage(PushMessage pushMessage, Device device) {
        Map<String, String> extras = getExtras(pushMessage, device.getLanguage());
        String title = getTitle(pushMessage, device.getLanguage(), true);
        String body = getBody(pushMessage, device.getLanguage(), true);
        return Message.builder()
                .setToken(device.getRegistrationId())
                .setAndroidConfig(AndroidConfig.builder()
                        .putAllData(extras)
//                        .setRestrictedPackageName("")
                        .setNotification(com.google.firebase.messaging.AndroidNotification.builder()
                                .setTitle(title)
                                .setBody(body)
//                                .setIcon("")
//                                .setColor("")
                                .build())
                        .build())
                .setNotification(new com.google.firebase.messaging.Notification(title, body))
                .putAllData(extras).build();

    }

    /**
     * ios推送消息封装
     *
     * @param pushMessage pushMessage
     * @param device      device
     * @return
     */
    public static SimpleApnsPushNotification buildApnsMessage(PushMessage pushMessage, Device device) {
        Map<String, String> extras = getExtras(pushMessage, device.getLanguage());
        String title = getTitle(pushMessage, device.getLanguage(), true);
        String body = getBody(pushMessage, device.getLanguage(), true);
        ApnsPayloadBuilder payloadBuilder = new ApnsPayloadBuilder();
        payloadBuilder.setAlertBody(body);
        payloadBuilder.setAlertTitle(title);
        if (pushMessage.getBadge() != null) {
            //设置消息数量角标
            payloadBuilder.setBadgeNumber(pushMessage.getBadge());
        }
        // true：表示的是产品发布推送服务 false：表示的是产品测试推送服务
        payloadBuilder.setContentAvailable(pushMessage.getContentAvailable());
        if (extras != null) {
            for (Map.Entry<String, String> map : extras.entrySet()) {
                payloadBuilder.addCustomProperty(map.getKey(), map.getValue());
            }
        }
        return new SimpleApnsPushNotification(
                TokenUtil.sanitizeTokenString(
                        device.getRegistrationId()
                ),
                pushMessage.getIosPackage(),
                payloadBuilder.buildWithDefaultMaximumLength());
    }

    private static Map<String, String> getExtras(PushMessage pushMessage, LanguageEnum languageEnum) {
        Map<String, String> extras = pushMessage.getExtras();
        if (extras == null) {
            extras = new HashMap<>();
        }
        if (StringUtils.isEmpty(pushMessage.getId())) {
            extras.put("id", pushMessage.getCid());
        } else {
            extras.put("id", String.valueOf(pushMessage.getId()));
        }
        extras.put("type", pushMessage.getNoticeType().toString());
        extras.put("time", String.valueOf(pushMessage.getCreateTime().getTime()));
        extras.put("title", getTitle(pushMessage, languageEnum, false));
        extras.put("desc", getBody(pushMessage, languageEnum, false));
        extras.put("url", pushMessage.getUrl() == null ? "" : pushMessage.getUrl());
        return extras;
    }

    private static String getTitle(PushMessage pushMessage, LanguageEnum languageEnum, Boolean removeTag) {
        String title = "";
        if (languageEnum.equals(LanguageEnum.CN)) {
            title = pushMessage.getTitle();
        } else {
            title = pushMessage.getEnglishTitle();
        }
        if (removeTag) {
            return removeHtmlTag(title);
        } else {
            return title;
        }
    }

    private static String getBody(PushMessage pushMessage, LanguageEnum languageEnum, Boolean removeTag) {
        String alert = "";
        if (languageEnum.equals(LanguageEnum.CN)) {
            alert = pushMessage.getContent();
        } else {
            alert = pushMessage.getEnglishContent();
        }
        if (removeTag) {
            return removeHtmlTag(alert);
        } else {
            return alert;
        }

    }

    /**
     * 去除富文本编辑器标签
     *
     * @param inputString
     * @return
     */
    public static String removeHtmlTag(String inputString) {
        if (inputString == null) {
            return null;
        }
        String htmlStr = inputString;
        String textStr = "";
        java.util.regex.Pattern p_script;
        java.util.regex.Matcher m_script;
        java.util.regex.Pattern p_style;
        java.util.regex.Matcher m_style;
        java.util.regex.Pattern p_html;
        java.util.regex.Matcher m_html;
        try {
            //定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
            String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>";
            //定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>
            String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>";
            String regEx_html = "<[^>]+>";
            p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
            m_script = p_script.matcher(htmlStr);
            htmlStr = m_script.replaceAll("");
            p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
            m_style = p_style.matcher(htmlStr);
            htmlStr = m_style.replaceAll("");
            p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
            m_html = p_html.matcher(htmlStr);
            htmlStr = m_html.replaceAll("");
            textStr = htmlStr;
        } catch (Exception e) {
            throw new ApplicationException(e);
        }
        return textStr;
    }

}
