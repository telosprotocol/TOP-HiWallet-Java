package com.topnetwork.defi;

import org.junit.Test;

import com.base.core.head.ao.PageAO;
import com.gitee.magic.framework.base.rest.RestfulTemplate;
import com.topnetwork.common.TestHeader;

/**
 * 
 * @author jode
 * @date Nov 24, 2020
 */
public class DayIncomeTotalTest extends TestHeader {

	@Test
	public void test_page() throws Exception {

		PageAO param = new PageAO();
		RestfulTemplate template = new RestfulTemplate(HOST, clientaccessid, clientaccesskey);
		template.getForEntityBase("/income/day_income_page",param);
	}

}
