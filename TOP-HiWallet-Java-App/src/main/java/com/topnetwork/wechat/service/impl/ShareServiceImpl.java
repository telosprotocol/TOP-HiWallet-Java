package com.topnetwork.wechat.service.impl;

import com.topnetwork.wallet.param.wechat.SignatureParam;
import com.topnetwork.wallet.result.wechat.SignatureResult;
import com.topnetwork.wechat.service.WeChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.topnetwork.wechat.service.ShareService;

import java.util.UUID;

@Service("shareService")
public class ShareServiceImpl implements ShareService {
	@Value("${wechat.appid}")
	private String AppId;


	@Autowired
	private WeChatService weChatService;


	@Override
	public SignatureResult getSignature(SignatureParam signatureParam) {
		//1、获取AccessToken
		String accessToken = weChatService.getAccessToken();
		//2、获取Ticket
		String jsapi_ticket = weChatService.getTicket(accessToken);
		//3、时间戳和随机字符串
		String noncestr = UUID.randomUUID().toString().replace("-", "").substring(0, 16);//随机字符串
		String timestamp = String.valueOf(System.currentTimeMillis() / 1000);//时间戳
		//5、将参数排序并拼接字符串
		String str = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + noncestr + "&timestamp=" + timestamp + "&url=" + signatureParam.getUrl();
		String signature = WeChatService.SHA1(str);

		SignatureResult signatureResult = new SignatureResult();
		signatureResult.setNoncestr(noncestr);
		signatureResult.setSignature(signature);
		signatureResult.setTimestamp(timestamp);
		signatureResult.setAppId(AppId);
		return signatureResult;
	}
}
