package com.topnetwork.wallet;

import org.junit.Test;

import com.topnetwork.common.TestHeader;
import com.topnetwork.wallet.param.wallet.v2.TransactionDetailParam;
import com.topnetwork.wallet.result.wallet.ETHTransactionResult;

import com.gitee.magic.framework.base.rest.RestfulTemplate;

/**
 * @ClassName EOSTest
 * @Description
 * @Author bran
 * @Date 2020/4/18 14:07
 */
public class ETHControllerTest extends TestHeader {

	@Test
	public void test_getETH() throws Exception {
		TransactionDetailParam content = new TransactionDetailParam();
		content.setHash("0x78e271d61874ed65e6f444821c6b978ea539bff222e8960cd8a0f3fd8f93c0e0");
		RestfulTemplate template = new RestfulTemplate(HOST, accessid, accesskey);
		ETHTransactionResult ethGasResult = template.postForEntityResult("/v2/app/query_eth_tx", content,ETHTransactionResult.class);
		System.out.println(ethGasResult.getNonce());

	}
}
