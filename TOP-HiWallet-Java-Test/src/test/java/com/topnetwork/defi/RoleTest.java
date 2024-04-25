package com.topnetwork.defi;

import java.util.Arrays;

import org.junit.Test;

import com.base.core.head.ao.IDAO;
import com.gitee.magic.framework.base.rest.RestfulTemplate;
import com.topnetwork.common.TestHeader;
import com.topnetwork.wallet.param.defi.system.RoleParam;

public class RoleTest extends TestHeader {
	
	@Test
	public void test_add_role() {
		RoleParam param=new RoleParam();
		param.setName("超级管理员");
		param.setAuthIds(Arrays.asList());
		RestfulTemplate template=new RestfulTemplate(HOST,manageraccessid,manageraccesskey);
		template.postForEntityBase("/role",param);
	}
	
	@Test
	public void test_list_auth_roleid() {
		IDAO param=new IDAO();
		param.setId(0L);
		RestfulTemplate template=new RestfulTemplate(HOST,manageraccessid,manageraccesskey);
    	template.getForEntityBase("/role/list/auth/roleid",param);
	}

}
