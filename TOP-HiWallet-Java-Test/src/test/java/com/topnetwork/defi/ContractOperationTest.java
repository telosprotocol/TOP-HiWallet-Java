package com.topnetwork.defi;

import org.junit.Test;

import com.topnetwork.common.TestHeader;
import com.topnetwork.wallet.common.enums.defi.SyncHashTypeEnum;
import com.topnetwork.wallet.param.defi.ContractOperationParam;

import com.gitee.magic.framework.base.rest.RestfulTemplate;

/**
 * 
 * @author jode
 * @date Nov 24, 2020
 */
public class ContractOperationTest extends TestHeader {
	
	@Test
	public void test_contractOPeration() throws Exception {
		RestfulTemplate template = new RestfulTemplate(HOST, manageraccessid, manageraccesskey);
		ContractOperationParam param = new ContractOperationParam(); 
		param.setProductId(791301928838578176l);
		param.setHash("0x9112c52d26013c41c48bf406c44ee9d087d37cb2efc7c14dacd08917692f57c5");
		param.setHashType(SyncHashTypeEnum.CANCEL_CONTRACT);//开启时间
		param.setCancelReasonCn("取消合约就是任性");
		param.setCancelReasonEn("cancel");
		template.postForEntityBase("/contract/operation",param);
	}
	

}
