package com.common.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import org.jasypt.util.text.BasicTextEncryptor;

import com.alibaba.druid.util.StringUtils;
import org.springframework.util.DigestUtils;
import com.gitee.magic.core.exception.ApplicationException;

/**
 * @program: TopFramework
 * @description: 解密工具类
 * @author: Tyrone
 * @create: 2019-09-11 10:22
 */
public class JasyptUtils {
	
	public static String password="123456";
	
	static {
		if(StringUtils.isEmpty(password)) {
			//密码取自控制台 运行命令：./catalina.sh run &
			try(Scanner s = new Scanner(System.in);){
	        	System.out.println("Input PBE password:");
	        	password = s.nextLine();
	        }
			//密码取自环境变量
//			password=System.getenv("JASYPT_ENCRYPTOR_PASSWORD");
		}
	}
	
	/**
	 * 根据环境变量密码加密
	 * @param content
	 * @return
	 */
    public static String ecryptEnv(String content){
    	BasicTextEncryptor encryptor=new BasicTextEncryptor();
    	encryptor.setPassword(password);
    	return encryptor.encrypt(content);
    }
    
    /**
	 * 根据环境变量密码解密
	 * @param content
	 * @return
	 */
    public static String decryptEnv(String content){
    	BasicTextEncryptor encryptor=new BasicTextEncryptor();
    	encryptor.setPassword(password);
    	return encryptor.decrypt(content);
    }

	//盐，用于混交md5
	private static final String slat = "&%5123***&&%%$$#@";

	public static String getMD5(String str) {
		String base = str +"/"+slat;
		return DigestUtils.md5DigestAsHex(base.getBytes());
	}


    public static void main(String[] args) {
		System.out.println(getMD5("aaa"));
    }
}
