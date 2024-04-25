//package com.topnetwork.common.service;
//
//import com.common.mvc.SendMessage;
//import com.topnetwork.config.param.MessageTextParam;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import start.framework.commons.rest.RESTfulTemplate;
//import com.gitee.magic.core.utils.StringUtils;
//
//@Service("messageService")
//public class MessageService implements SendMessage {
//	
//	@Value("${config.message.host:}")
//	public String host;
//
//	@Override
//	public void sendMessage(String title, String content) {
//		if(StringUtils.isBlank(host)) {
//			return;
//		}
//		MessageTextParam param=new MessageTextParam();
//		param.setTitle(title);
//		param.setContent(content);
//		RESTfulTemplate template=new RESTfulTemplate(host);
//		template.postForEntityBase("/message/send", param);
//	}
//
//}
