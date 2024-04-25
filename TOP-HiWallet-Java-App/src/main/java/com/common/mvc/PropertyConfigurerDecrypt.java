package com.common.mvc;

import com.common.utils.JasyptUtils;
import com.gitee.magic.framework.base.spring.PropertyConfigurer;

public class PropertyConfigurerDecrypt extends PropertyConfigurer {

	@Override
	protected String convertProperty(String propertyName, String propertyValue) {
		if(propertyName.toLowerCase().contains(".privatekey")) {
			//对私钥进行解密
			propertyValue=JasyptUtils.decryptEnv(propertyValue);
//			System.out.println("Key:"+propertyName+",Value:"+PBECoder.encrypt(propertyValue, "123456"));
//			System.out.println("Key:"+propertyName+",Value:"+JasyptUtils.encrypt(propertyValue, "123456"));
		}
		return super.convertProperty(propertyName, propertyValue);
	}
	
}
