package com.topnetwork.defi;

import java.util.Arrays;

import org.junit.Test;

import com.topnetwork.common.TestHeader;
import com.topnetwork.wallet.param.defi.system.UserCreateParam;
import com.topnetwork.wallet.param.defi.system.UserDetailParam;
import com.topnetwork.wallet.param.defi.system.UserLoginParam;
import com.topnetwork.wallet.param.defi.system.UserPageParam;

import com.gitee.magic.framework.base.rest.RestfulTemplate;
import com.gitee.magic.core.utils.codec.Md5;

public class UserTest extends TestHeader {
	
	@Test
	public void test_login() {
		UserLoginParam param=new UserLoginParam();
		param.setUserName("admin");
		param.setPassword(Md5.md5("123123"));
		RestfulTemplate template=new RestfulTemplate(HOST);
    	template.postForEntityBase("/user/login",param);
	}

	@Test
	public void test_create() {
		UserCreateParam param=new UserCreateParam();
		param.setUserName("member");
		param.setPassword(Md5.md5("123123"));
		param.setName("成员");
		param.setDisable(false);
		param.setRoleId(Arrays.asList(781119421006479360L));
		RestfulTemplate template=new RestfulTemplate(HOST,manageraccessid,manageraccesskey);
    	template.postForEntityBase("/user",param);
	}
	
	@Test
	public void test_page() {
		UserPageParam param=new UserPageParam();
		RestfulTemplate template=new RestfulTemplate(HOST,manageraccessid,manageraccesskey);
    	template.getForEntityBase("/user/page",param);
	}
	
	@Test
	public void test_detail() {
		UserDetailParam param=new UserDetailParam();
		param.setUserId(1L);
		RestfulTemplate template=new RestfulTemplate(HOST,manageraccessid,manageraccesskey);
    	template.getForEntityBase("/user/detail",param);
	}

}
