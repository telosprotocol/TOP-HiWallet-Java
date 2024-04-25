package com.topnetwork.system;

import java.util.*;

import com.topnetwork.wallet.common.enums.RoleEnum;
import com.topnetwork.wallet.param.system.UserCreateParam;
import com.topnetwork.wallet.param.wyre.AddCountryParam;
import org.junit.Test;

import com.topnetwork.common.HttpServer;
import com.topnetwork.common.TestHeader;
import com.topnetwork.wallet.param.system.UserLoginParam;
import com.topnetwork.wallet.result.common.system.UserLoginResult;

import com.gitee.magic.framework.base.rest.RestfulTemplate;
import com.gitee.magic.core.utils.codec.DigestUtils;

public class UserTest extends TestHeader {

//	private final String SERVERURL = "http://178.128.84.127:8090/";.

	
    @Test
    public void test_user_login() throws Exception {
    	RestfulTemplate template=new RestfulTemplate(HOST, accessid, accesskey);
    	UserLoginParam param=new UserLoginParam();
    	param.setUserName("admin");
    	param.setPassword(DigestUtils.md5Hex("123123"));
    	UserLoginResult result=template.postForEntityResult("/user/login", param,UserLoginResult.class);
    	System.out.println("登录成功单独调用请修如下配置信息：");
        UserTest.manageraccessid = result.getAccessid();
        UserTest.manageraccesskey = result.getAccesskey();
        System.out.println("manageraccessid:" + result.getAccessid());
        System.out.println("manageraccesskey:" + result.getAccesskey());
    }

    @Test
    public void test_sys_user_manager_page() throws Exception {
        Map<String, Object> content = new HashMap<String, Object>();
        HttpServer server = new HttpServer();
        server.postRequest("/user/manager_page", manageraccessid, manageraccesskey, content);
    }

    @Test
    public void test_sys_user_detail() throws Exception {
        Map<String, Object> content = new HashMap<String, Object>();
        content.put("userId", "583438207601868800");
        HttpServer server = new HttpServer();
        server.postRequest("/user/detail", manageraccessid, manageraccesskey, content);
    }

    @Test
    public void test_sys_modify_pwd() throws Exception {
        Map<String, Object> content = new HashMap<String, Object>();
        content.put("newpassword", DigestUtils.md5Hex("123123"));
        content.put("oldpassword", DigestUtils.md5Hex("123123"));
        HttpServer server = new HttpServer();
        server.postRequest("/user/modify_pwd", manageraccessid, manageraccesskey, content);
    }

    @Test
    public void test_sys_user_delete() throws Exception {
        Map<String, Object> content = new HashMap<String, Object>();
        content.put("userId", "583438207601868800");
        HttpServer server = new HttpServer();
        server.postRequest("/user/delete", manageraccessid, manageraccesskey, content);
    }

    @Test
    public void test_sys_user_modify() throws Exception {
        Map<String, Object> content = new HashMap<String, Object>();
        content.put("userId", "583438207601868800");
        content.put("mobile", "13012344321");
        content.put("name", "管理员");
        content.put("role", "ADMIN");
        content.put("disable", "false");
        List<String> ids = new ArrayList<>();
        ids.add("583438194737938432");
        ids.add("583438196222722048");
        ids.add("583438196231110656");
        ids.add("583438196239499264");
        ids.add("583438196247887872");
        ids.add("583438196256276480");
        ids.add("583438196260470784");
        ids.add("583438196268859392");
        content.put("permissionIds", ids);
        HttpServer server = new HttpServer();
        server.postRequest("/user/modify", manageraccessid, manageraccesskey, content);
    }

    @Test
    public void test_user_create() throws Exception {
        RestfulTemplate template = new RestfulTemplate(HOST, manageraccessid, manageraccesskey);
        UserCreateParam param = new UserCreateParam();
        param.setDisable(false);
        param.setMobile("15737978239");
        param.setName("aaa");
        param.setPassword("5690dddfa28ae085d23518a035707282");
        param.setPermissionIds(new ArrayList<>());
        param.setUserName("bbb");
        param.setRole(RoleEnum.ADMIN);
        template.postForEntityBase("/user/create", param);
    }

}
