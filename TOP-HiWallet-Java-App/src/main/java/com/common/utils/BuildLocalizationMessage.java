package com.common.utils;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.TreeMap;

import com.topnetwork.wallet.common.CodeRes;

import com.gitee.magic.framework.base.constant.Message;

public class BuildLocalizationMessage {
	
	public static void main(String[] args) throws Exception {
		Map<Integer, String> content = new TreeMap<Integer, String>();
		Field[] fields = CodeRes.class.getFields();
        for (Field field : fields) {
            String value = String.valueOf(field.get(null));
            Message message = Message.parse(value);
            content.put(message.getCode(), message.getMessage());
        }
        for(Integer key:content.keySet()) {
            System.out.println(key+"="+content.get(key));
        }
	}
	
}
