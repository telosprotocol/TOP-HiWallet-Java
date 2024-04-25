package com.topnetwork.jpush;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.device.TagAliasResult;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import cn.jpush.api.push.PushResult;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.topnetwork.wallet.common.enums.LanguageEnum;
import com.topnetwork.wallet.common.enums.PlatformEnum;
import com.topnetwork.wallet.common.enums.RegionEnum;
import com.topnetwork.wallet.entity.Device;
import com.topnetwork.wallet.service.DeviceService;
import com.turo.pushy.apns.ApnsClient;
import com.turo.pushy.apns.ApnsClientBuilder;
import com.turo.pushy.apns.auth.ApnsSigningKey;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileUrlResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import com.gitee.magic.core.exception.ApplicationException;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service("pushService")
public class PushService implements InitializingBean {

    @Value("${jpush.appKey}")
    private String appKey;
    @Value("${jpush.masterSecret}")
    private String masterSecret;

    @Value("${fcm.database}")
    private String dataBaseUrl;
    @Value("${fcm.json.path}")
    private String jsonPath;

    @Value("${ios.apns.teamId}")
    private String teamId;
    @Value("${ios.apns.certificatePath}")
    private String certificatePath;
    @Value("${ios.apns.keyId}")
    private String keyId;
    @Value("${ios.apns.iosPackage}")
    private String iosPackage;
    @Value("${ios.apns.contentAvailable}")
    private boolean contentAvailable;

    @Autowired
    private DeviceService deviceService;

    ExecutorService executorService = Executors.newFixedThreadPool(20);


    //极光
    private JPushClient jpushClient;
    //fcm
    private FirebaseApp firebaseApp;
    //ios
    private ApnsClient apnsClient;

    @Override
    public void afterPropertiesSet() throws Exception {

        //初始化极光推送
        ClientConfig clientConfig = ClientConfig.getInstance();
        this.jpushClient = new JPushClient(masterSecret, appKey, null, clientConfig);

        //初始化firebaseApp
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(new ClassPathResource(jsonPath).getInputStream()))
                .setDatabaseUrl(dataBaseUrl).build();
        this.firebaseApp = FirebaseApp.initializeApp(options);

        //ios apns
        apnsClient = new ApnsClientBuilder()
                .setApnsServer(ApnsClientBuilder.DEVELOPMENT_APNS_HOST)
                .setSigningKey(ApnsSigningKey.loadFromPkcs8File(new FileUrlResource(certificatePath).getFile(),
                        teamId, keyId))
                .build();
    }

    /**
     * 推送广播信息
     *
     * @param pushMessage pushMessage
     */
    public void broadcastPush(PushMessage pushMessage) {

        try {
            List<Device> deviceListAndroid = deviceService.findDevice(PlatformEnum.ANDROID);
            for (Device device : deviceListAndroid) {
                executorService.submit(new AnPush(pushMessage, device, firebaseApp, jpushClient));
            }

            List<Device> deviceList = deviceService.findDevice(PlatformEnum.IOS);
            for (Device device : deviceList) {
                executorService.submit(new IosPush(pushMessage, device, apnsClient));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ApplicationException(e);
        }
    }

    /**
     * 推送交易信息
     * 推送的核心类，将封装好的消息推送给指定的device
     * @param pushMessage pushMessage
     * @param device      device
     */
    public void transactionPush(PushMessage pushMessage, Device device) {
        try {
            if (device.getPlatform().equals(PlatformEnum.ANDROID)) {
                //安卓设备海外fcm，国内极光
                if (device.getRegion().equals(RegionEnum.OVERSEAS)) {
                    //海外
                    String code = FirebaseMessaging.getInstance(firebaseApp).send(MessageBuilder.buildFMessage(pushMessage, device));
                } else {
                    PushResult result = jpushClient.sendPush(MessageBuilder.buildJMessage(pushMessage, device));
                }
            }
            if (device.getPlatform().equals(PlatformEnum.IOS)) {
                pushMessage.setIosPackage(iosPackage);
                pushMessage.setContentAvailable(contentAvailable);
                apnsClient.sendNotification(MessageBuilder.buildApnsMessage(pushMessage, device));
            }

        } catch (Exception e) {
            throw new ApplicationException(e);
        }
    }

    public class AnPush implements Runnable {

        private Device device;
        private PushMessage pushMessage;
        //极光
        private JPushClient jpushClient;
        //fcm
        private FirebaseApp firebaseApp;

        public AnPush(PushMessage pushMessage, Device device, FirebaseApp firebaseApp, JPushClient jpushClient) {
            this.pushMessage = pushMessage;
            this.device = device;
            this.firebaseApp = firebaseApp;
            this.jpushClient = jpushClient;
        }

        @Override
        public void run() {
            try {
                if (device.getRegion().equals(RegionEnum.OVERSEAS)) {
                    FirebaseMessaging.getInstance(firebaseApp).send(MessageBuilder.buildFMessage(pushMessage, device));
                } else {
                    jpushClient.sendPush(MessageBuilder.buildJMessage(pushMessage, device));
                }
            } catch (Exception e) {
                throw new ApplicationException(e);
            }
        }
    }

    public class IosPush implements Runnable {

        private Device device;
        private PushMessage pushMessage;
        private ApnsClient apnsClient;

        public IosPush(PushMessage pushMessage, Device device, ApnsClient apnsClient) {
            this.pushMessage = pushMessage;
            this.device = device;
            this.apnsClient = apnsClient;
        }

        @Override
        public void run() {
            try {
                pushMessage.setIosPackage(iosPackage);
                pushMessage.setContentAvailable(contentAvailable);
                apnsClient.sendNotification(MessageBuilder.buildApnsMessage(pushMessage, device));
            } catch (Exception e) {
                throw new ApplicationException(e);
            }
        }
    }

    public void setDeviceTag(Device device) {
        try {
            if (device.getPlatform().equals(PlatformEnum.ANDROID)) {
                if (device.getRegion().equals(RegionEnum.DOMESTIC)) {
                    TagAliasResult tagAliasResult = jpushClient.getDeviceTagAlias(device.getRegistrationId());
                    if (tagAliasResult.tags == null || tagAliasResult.tags.isEmpty()) {
                        HashSet<String> tagAddSet = new HashSet<String>();
                        tagAddSet.add(device.getLanguage().toString());
                        jpushClient.updateDeviceTagAlias(device.getRegistrationId(), null, tagAddSet, null);
                    }
                    if (!tagAliasResult.tags.contains(device.getLanguage().toString())) {
                        HashSet<String> tagAddSet = new HashSet<String>();
                        tagAddSet.add(device.getLanguage().toString());
                        HashSet<String> tagRemoveSet = new HashSet<String>(tagAliasResult.tags);
                        jpushClient.updateDeviceTagAlias(device.getRegistrationId(), null, tagAddSet, tagRemoveSet);
                    }
                } else {
                    List<String> tokens = Arrays.asList(device.getRegistrationId());
                    if (device.getLanguage().equals(LanguageEnum.CN)) {
                        FirebaseMessaging.getInstance(firebaseApp).subscribeToTopic(tokens, device.getLanguage().toString());
                        FirebaseMessaging.getInstance(firebaseApp).unsubscribeFromTopic(tokens, LanguageEnum.EN.toString());
                    } else {
                        FirebaseMessaging.getInstance(firebaseApp).subscribeToTopic(tokens, device.getLanguage().toString());
                        FirebaseMessaging.getInstance(firebaseApp).unsubscribeFromTopic(tokens, LanguageEnum.CN.toString());
                    }
                }
            }
        } catch (Exception e) {
            throw new ApplicationException(e);
        }
    }
}
