package com.common.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;
import org.topnetwork.foundation.boot.AppEnvironmentPostProcessor;

import com.base.core.context.utils.Jasypt;
import com.gitee.magic.core.utils.StringUtils;

/**
 * 启动加载Key
 * @author start
 *
 */
@Order(0)
public class StartUpLoadKeyProcessor implements EnvironmentPostProcessor {

	/**
	 * 环境变量KEY
	 */
	public static final String ENV_PWD_JASYPT="JASYPT_PASSWORD_PAW";
	
	/**
	 *  TODO:开发人员特别注意每次部署前找相关人员要密文硬编码到代码中不可以从外部传入
	 */
	public final static String CONFIG_TOKEN = "QrRY6SqXM8JyZMl0OXIqLgfPliTE7QSkikLGGjoThVPllwI7nuprsGjoxFPDjE2C";

	@Override
	public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
		String password=environment.getProperty(ENV_PWD_JASYPT,"");
		if(!StringUtils.isEmpty(password)) {
			System.out.println("已读取到环境变量 "+ENV_PWD_JASYPT);
			Jasypt jasypt = new Jasypt(password);
			String content = jasypt.decrypt(CONFIG_TOKEN);
			AppEnvironmentPostProcessor.MFA_CODE = content;
		}
	}
	
}
