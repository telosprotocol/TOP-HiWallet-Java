package com.topnetwork.common;

import org.junit.Test;

import com.topnetwork.wallet.param.system.ConfigureParam;

import com.gitee.magic.framework.base.rest.RestfulTemplate;

public class ConfigTest extends TestHeader{
	
	@Test
	public void test_getConfig() throws Exception {
		RestfulTemplate template = new RestfulTemplate(HOST,accessid,accesskey);
		ConfigureParam param = new ConfigureParam();
		param.setCode("gasLimit");
		template.getForEntityBase("/config",param);
	}

}
