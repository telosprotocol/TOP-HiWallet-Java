package com.common.utils;

import javax.servlet.http.HttpServletRequest;

import com.gitee.magic.framework.base.constant.Config;
import com.gitee.magic.framework.base.rest.HttpRequest;
import com.gitee.magic.framework.base.rest.HttpWrapper;
import com.gitee.magic.framework.base.rest.RequestMethodEnum;
import com.gitee.magic.framework.base.utils.IpUtils;
import com.gitee.magic.core.json.JsonObject;

/**
 * @ClassName LocationUtils
 * @Description 获取用户位置
 * @Author bran
 * @Date 2020/5/6 15:07
 */
public class LocationUtils {

    private final static String LOCATION_HOST_TEST = "http://apigateway.dt-dn1.com:9230/location/ip";
    private final static String LOCATION_HOST_PRO = "http://dt-apigatewayv2.dt-pn1.com/location/ip";
    public final static String US_COUNTRY_CODE = "US";

    public static String getLocationByIp(String ip) {
        String host = Config.isSystemFlag() ? LOCATION_HOST_PRO : LOCATION_HOST_TEST;
        HttpWrapper wrapper = new HttpWrapper();
        HttpRequest request = new HttpRequest(host, RequestMethodEnum.GET);
        request.getHeaders().put("X-Forwarded-For", ip);
        String content = wrapper.start(request);
        JsonObject jsonObject = new JsonObject(content);
        return jsonObject.getJsonObject("data").getString("countryCode");
    }

    public static boolean isForbid(String ip, String countryCodes) {
        return countryCodes.contains(getLocationByIp(ip));
    }

    public static String getRequestIP(HttpServletRequest request) {
        String ipString = IpUtils.getRequestIp(request);
        final String[] arr = ipString.split(",");
        for (final String str : arr) {
            if (!"unknown".equalsIgnoreCase(str)) {
                ipString = str;
                break;
            }
        }
        return ipString;
    }

    public static void main(String[] args) {
        System.out.println(getLocationByIp("23.4.247.255"));
//        System.out.println(isForbid("54.241.114.144", US_COUNTRY_CODE));
    }
}
