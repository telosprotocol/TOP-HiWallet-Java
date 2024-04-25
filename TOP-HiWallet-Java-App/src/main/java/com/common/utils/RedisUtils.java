package com.common.utils;

import com.base.core.context.mvc.Constants;

/**
 * @ClassName RedisUtils
 * @Description redis工具类
 * @Author bran
 * @Date 2020/3/24 10:58
 */
public class RedisUtils {

    private static final String redisKeyPerfix = Constants.REDISPREFIX;

    public static String getVerifyKey(String keyStr) {
        return redisKeyPerfix + "verify:" + keyStr;
    }

    public static String getRewardKey(String keyStr) {
        return redisKeyPerfix + "reward:" + keyStr;
    }
    public static String getKey(String keyStr) {
        return redisKeyPerfix + keyStr;
    }

    public static String getPhoneCodeKey(String mobile) {
        return redisKeyPerfix + "phoneCode:" + mobile;
    }
}
