package com.topnetwork.wechat.service;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.base.core.context.annotation.Cache;
import com.common.bean.WechatUserInfo;

import com.gitee.magic.framework.base.rest.HttpRequest;
import com.gitee.magic.framework.base.rest.HttpWrapper;
import com.gitee.magic.framework.base.rest.RequestMethodEnum;
import com.gitee.magic.context.RestConverterEditor;
import com.gitee.magic.core.exception.ApplicationException;
import com.gitee.magic.core.json.JsonObject;

/**
 * Description: 微信工具类
 *
 * @param
 * @return:
 * @Author: Tyrone
 * @date: 2020/5/6
 **/
@Component
public class WeChatService {
    @Value("${wechat.appid}")
    private String AppId;
    @Value("${wechat.secret}")
    private String secret;

    //过期时间1小时
    @Cache(key = "getAccessToken", expire = 60L * 60L)
    public String getAccessToken() {
        String access_token = "";
        String grant_type = "client_credential";//获取access_token填写client_credential
        //这个url链接地址和参数皆不能变
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=" + grant_type + "&appid=" + AppId + "&secret=" + secret;
        JsonObject demoJson = sendHttp(url);
        if (demoJson.has("access_token")) {
            access_token = demoJson.getString("access_token");
        }
        return access_token;
    }

    @Cache(key = "webAccessToken", value = "#code", expire = 60L * 60L)
    public Map<String, String> getWebAccessToken(String code) {
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?grant_type=authorization_code&appid=" + AppId + "&secret=" + secret + "&code=" + code;
        JsonObject demoJson = sendHttp(url);
        Map<String, String> data = new HashMap<>();
        if (demoJson.has("access_token")) {
            data.put("accessToken", demoJson.getString("access_token"));
        }
        if (demoJson.has("openid")) {
            data.put("openid", demoJson.getString("openid"));
        }
        return data;
    }

    public WechatUserInfo getWeChat(String code) {
        Map<String, String> map = getWebAccessToken(code);
        String accessToken = map.get("accessToken");
        String openid = map.get("openid");
        if (StringUtils.isEmpty(accessToken) || StringUtils.isEmpty(openid)) {
            return null;
        }
        String url = "https://api.weixin.qq.com/sns/userinfo?access_token=" + accessToken + "&openid=" + openid + "&lang=zh_CN";
        JsonObject demoJson = sendHttp(url);
        WechatUserInfo wechatUserInfo = RestConverterEditor.converterObject(WechatUserInfo.class, demoJson);
        return wechatUserInfo;
    }

    //过期时间1小时
    @Cache(key = "getTicket", expire = 60L * 60L)
    public String getTicket(String access_token) {
        String ticket = null;
        String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + access_token + "&type=jsapi";//这个url链接和参数不能变
        JsonObject jsonObject = sendHttp(url);

        if (jsonObject.has("ticket")) {
            ticket = jsonObject.getString("ticket");
        }
        return ticket;
    }

    private JsonObject sendHttp(String url) {
        HttpWrapper httpWrapper = new HttpWrapper();
        HttpRequest request = new HttpRequest(url, RequestMethodEnum.GET);
        request.setHeader("Content-Type", "application/x-www-form-urlencoded");
        String data = httpWrapper.start(request);
        return new JsonObject(data);
    }

    public static String SHA1(String decript) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(decript.getBytes());
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();

        } catch (Exception e) {
            throw new ApplicationException(e);
        }
    }

    public static void main(String[] args) {
        WeChatService weChatService = new WeChatService();
        //1、获取AccessToken
        String accessToken = weChatService.getAccessToken();

        //2、获取Ticket
        String jsapi_ticket = weChatService.getTicket(accessToken);

        //3、时间戳和随机字符串
        String noncestr = UUID.randomUUID().toString().replace("-", "").substring(0, 16);//随机字符串
        String timestamp = String.valueOf(System.currentTimeMillis() / 1000);//时间戳

        System.out.println("accessToken:" + accessToken + "\njsapi_ticket:" + jsapi_ticket + "\n时间戳：" + timestamp + "\n随机字符串 noncestr：" + noncestr);

        //4、获取url
        String url = "http://localhost:3000/top-activity/answer";
//        String url = "http://www.topnetwork.org";

        //5、将参数排序并拼接字符串
        String str = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + noncestr + "&timestamp=" + timestamp + "&url=" + url;

        //6、将字符串进行sha1加密
        String signature = SHA1(str);
        System.out.println("参数：" + str + "\n签名：" + signature);
    }
}
