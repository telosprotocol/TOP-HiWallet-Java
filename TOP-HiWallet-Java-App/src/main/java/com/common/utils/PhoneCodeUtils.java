package com.common.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;

import com.common.bean.PhoneCodeRedisData;
import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.common.enums.LanguageEnum;
import com.topnetwork.wallet.result.wallet.invite.PhoneCodeResult;

import com.gitee.magic.framework.base.constant.Config;
import com.gitee.magic.framework.head.exception.BusinessException;
import com.gitee.magic.framework.base.rest.HttpRequest;
import com.gitee.magic.framework.base.rest.HttpWrapper;
import com.gitee.magic.framework.base.rest.RequestMethodEnum;
import com.gitee.magic.core.exception.ApplicationException;
import com.gitee.magic.core.json.JsonObject;

/**
 * @ClassName PhoneCodeUtils
 * @Description
 * @Author bran
 * @Date 2020/5/7 17:00
 */
public class PhoneCodeUtils {

    /**
     * 每天发送验证码次数
     */
    public final static Integer SEND_MAX_TIMES = 5;
    /**
     * 两次发送验证码间隔
     */
    public final static Integer CODE_FREQUENCY_SEC = 60;
    /**
     * 验证码过期时间（min）
     */
    public final static Integer CODE_EXPIRATION_TIME_MIN = 10;
    /**
     * 验证码长度（min）
     */
    public final static Integer CODE_LENGTH = 6;
    /**
     * 验证码错误时间（min，配合下面参数使用）
     */
    public final static Integer VERIFY_TIME_MIN = 1;
    /**
     * 验证码错误时间内可验证次数（min）
     */
    public final static Integer VERIFY_MAX_TIMES = 3;

    private final static String SEND_HOST_TEST = "http://54.241.249.96:8080/smssvr/topsms";
    private final static String SEND_HOST_PRO = "http://ld-smserver-552723959.us-west-1.elb.amazonaws.com:8080/smssvr/topsms";
    private final static String SEND_COUNTRY_CODE_HOST_TEST = "http://54.241.249.96:8080/smssvr/top/send";
    private final static String SEND_COUNTRY_CODE_HOST_PRO = "http://smssvr.dt-pn1.com:8080/smssvr/top/send";


    public static String getCode() {
        String sources = "0123456789";
        Random rand = new Random();
        StringBuffer flag = new StringBuffer();
        for (int j = 0; j < CODE_LENGTH; j++) {
            flag.append(sources.charAt(rand.nextInt(9)) + "");
        }
        return flag.toString();
    }

    public static String getPrivateMobile(Integer countryCode, String mobile) {
        countryCode = countryCode == null || countryCode <= 0 ? 86 : countryCode;
        if (countryCode == 86) {
            return "+" + countryCode + " " + mobile.substring(0, 3) + "****" + mobile.substring(7, 11);
        }
        return "+" + countryCode + " " + mobile.substring(0, 3) + "***" + mobile.substring(6);
    }

    private static Boolean sendCode(String mobile, String code, LanguageEnum language) {
        String message = getCodeMessage(code, language);
        HttpWrapper httpWrapper = new HttpWrapper();
        HttpRequest request = null;
        String baseUrl = Config.isSystemFlag() ? SEND_HOST_PRO : SEND_HOST_TEST;
        try {
            request = new HttpRequest(baseUrl + "?phoneNumber=" + mobile + "&message=" + URLEncoder.encode(message, "UTF-8"), RequestMethodEnum.GET);
        } catch (UnsupportedEncodingException e) {
            throw new ApplicationException(e);
        }
        String data = httpWrapper.start(request);
        JsonObject jsonObject = new JsonObject(data);
        return "1".equals(jsonObject.get("Result").toString());
    }

    private static Boolean sendCode(Integer countryCode, String mobile, String code, LanguageEnum language) {
        //处理countryCode
        countryCode = countryCode == null || countryCode <= 0 ? 86 : countryCode;
        //获取发送内容
        String message = getCodeMessage(code, language);
        HttpWrapper httpWrapper = new HttpWrapper();
        HttpRequest request = null;
        String baseUrl = Config.isSystemFlag() ? SEND_COUNTRY_CODE_HOST_PRO : SEND_COUNTRY_CODE_HOST_TEST;
        try {
            request = new HttpRequest(baseUrl + "?countryCode=" + countryCode + "&phoneNumber=" + countryCode + mobile + "&message=" + URLEncoder.encode(message, "UTF-8"), RequestMethodEnum.POST);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ApplicationException(e);
        }
        String data = httpWrapper.start(request);
        JsonObject jsonObject = new JsonObject(data);
        System.out.println(jsonObject.toString());
        return "1".equals(jsonObject.get("Result").toString());
    }

    public static PhoneCodeResult sendCode(Integer countryCode, String mobile, String code, LanguageEnum languageEnum, RedisTemplate<String, PhoneCodeRedisData> redisTemplate) {

        PhoneCodeResult phoneCodeResult = new PhoneCodeResult();
        if (!Config.isSystemFlag()) {
            phoneCodeResult = new PhoneCodeResult();
            phoneCodeResult.setTimes(4);
            return phoneCodeResult;
        }
        String key = RedisUtils.getPhoneCodeKey(mobile);
        PhoneCodeRedisData data = redisTemplate.opsForValue().get(key);
        if (data == null) {
            data = new PhoneCodeRedisData();
            data.setCode(code);
            data.setTimes(1);
        } else {
            if (data.getTimes() >= SEND_MAX_TIMES) {
                throw new BusinessException(CodeRes.CODE_18004);
            }
            if (System.currentTimeMillis() - data.getLastSendTime().getTime() < CODE_FREQUENCY_SEC * 1000) {
                throw new BusinessException(CodeRes.CODE_18000);
            }
            data.setCode(code);
            data.setTimes(data.getTimes() + 1);

        }
        data.setLastSendTime(new Date());

        if (!sendCode(countryCode, mobile, code, languageEnum)) {
            throw new BusinessException(CodeRes.CODE_18012);
        }

        redisTemplate.delete(key);
        redisTemplate.opsForValue().set(key, data);
        redisTemplate.expireAt(key, DateUtils.getThisDayEnd());
        phoneCodeResult.setTimes(SEND_MAX_TIMES - data.getTimes());
        return phoneCodeResult;
    }

    public static void sendRechargeCode(String code, String mobile, RedisTemplate<String, PhoneCodeRedisData> redisTemplate) {
        if (!Config.isSystemFlag()) {
            return;
        }
        String key = RedisUtils.getPhoneCodeKey(mobile) + "recharge";
        PhoneCodeRedisData data = redisTemplate.opsForValue().get(key);
        if (data == null) {
            data = new PhoneCodeRedisData();
            data.setCode(code);
            data.setTimes(1);
        } else {
            if (System.currentTimeMillis() - data.getLastSendTime().getTime() < CODE_FREQUENCY_SEC * 1000) {
                throw new BusinessException(CodeRes.CODE_18000);
            }
            data.setCode(code);
            data.setTimes(data.getTimes() + 1);
        }
        data.setLastSendTime(new Date());

        if (!sendCode(86, mobile, code, LanguageEnum.CN)) {
            throw new BusinessException(CodeRes.CODE_18012);
        }
        redisTemplate.delete(key);
        redisTemplate.opsForValue().set(key, data);
        redisTemplate.expireAt(key, DateUtils.getThisDayEnd());
    }

    public static void verifyPhoneCode(String mobile, String phoneCode, RedisTemplate<String, PhoneCodeRedisData> redisTemplate) {

        if (!Config.isSystemFlag() && "000000".equalsIgnoreCase(phoneCode)) {
            return;
        }
        String key = RedisUtils.getPhoneCodeKey(mobile);

        PhoneCodeRedisData data = redisTemplate.opsForValue().get(key);
        if (data == null || StringUtils.isEmpty(data.getCode())) {
            throw new BusinessException(CodeRes.CODE_18008);
        }

        if (System.currentTimeMillis() - data.getLastSendTime().getTime() > CODE_EXPIRATION_TIME_MIN * 60 * 1000) {
            throw new BusinessException(CodeRes.CODE_18006);
        }

        String key2 = RedisUtils.getPhoneCodeKey(mobile + "errorTime");
        PhoneCodeRedisData data1 = redisTemplate.opsForValue().get(key2);
        if (data1 != null && data1.getErrorTimes() >= VERIFY_MAX_TIMES) {
            throw new BusinessException(CodeRes.CODE_18009);
        }
        if (!data.getCode().equals(phoneCode)) {
            if (data1 == null) {
                //第一次错误
                data1 = new PhoneCodeRedisData();
                data1.setErrorTimes(1);
                redisTemplate.opsForValue().set(key2, data1, VERIFY_TIME_MIN, TimeUnit.MINUTES);
            } else {
                data1.setErrorTimes(1 + data1.getErrorTimes());
                Long time = redisTemplate.getExpire(key2);
                if (time == null || time == 0L) {
                    time = 1L;
                }
                redisTemplate.opsForValue().set(key2, data1, time, TimeUnit.SECONDS);
            }
            throw new BusinessException(CodeRes.CODE_18005);
        }
        data.setCode(null);
        redisTemplate.delete(key);
        redisTemplate.opsForValue().set(key, data);
        redisTemplate.expireAt(key, DateUtils.getThisDayEnd());
    }


    private static String getCodeMessage(String code, LanguageEnum languageEnum) {
        String message = "";
        if (LanguageEnum.CN.equals(languageEnum)) {
            message = "【HiWallet】验证码：" + code + "，欢迎您加入区块链新青年，请勿向任何人提供您收到的短信验证码哦。";
        } else {
            message = "【HiWallet】Code: " + code + ". Welcome to join the blockchain pioneer, don't give the code to anyone.";
        }
        return message;
//        return "【HiWallet】HiWallet";
    }

    public static void main(String[] args) {
//        System.out.println(sendCode(84, "358677299", "123456", LanguageEnum.EN));
        System.out.println(getPrivateMobile(63, "9956512254"));
    }
}
