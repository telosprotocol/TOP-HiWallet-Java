package com.topnetwork.defi;

import java.math.BigDecimal;

import org.junit.Test;

import com.topnetwork.common.TestHeader;
import com.topnetwork.wallet.common.enums.LanguageEnum;
import com.topnetwork.wallet.param.defi.DefiApproveParam;
import com.topnetwork.wallet.param.defi.DefiLanguagePage;

import com.gitee.magic.framework.base.rest.RestfulTemplate;

/**
 * 
 * @author jode
 * @date Nov 24, 2020
 */
public class DefiOrderTest extends TestHeader {

	@Test
	public void test_get_order_conllect_list() throws Exception {
		DefiLanguagePage param = new DefiLanguagePage();
		param.setLanguage(LanguageEnum.CZ);
		RestfulTemplate template = new RestfulTemplate(HOST, clientaccessid, clientaccesskey);
		template.getForEntityBase("/v1/defi/get_order_conllect_list",param);
	}
	
	@Test
	public void test_get_order_conllect_v2_list() throws Exception {
		DefiLanguagePage param = new DefiLanguagePage();
		param.setLanguage(LanguageEnum.EN);
		RestfulTemplate template = new RestfulTemplate(HOST, clientaccessid, clientaccesskey);
		template.getForEntityBase("/v2/defi/get_order_conllect_list",param);
	}

	@Test
	public void test_get_order_list() throws Exception {
		DefiLanguagePage param = new DefiLanguagePage();
		param.setLanguage(LanguageEnum.CN);
		RestfulTemplate template = new RestfulTemplate(HOST, clientaccessid, clientaccesskey);
		template.getForEntityBase("/v1/defi/get_order_list", param);
	}
	
	@Test
	public void test_get_invest_statistics() throws Exception {
		RestfulTemplate template = new RestfulTemplate(HOST, clientaccessid, clientaccesskey);
		template.getForEntityBase("/v1/defi/get_invest_statistics");
	}
	
	
	@Test
	public void test_get_income_record_list() throws Exception {
		RestfulTemplate template = new RestfulTemplate(HOST, clientaccessid, clientaccesskey);
		template.getForEntityBase("/v1/defi/get_income_record_list");
	}
	
	@Test
	public void test_get_approve_balance() throws Exception {
		RestfulTemplate template = new RestfulTemplate(HOST, clientaccessid, clientaccesskey);
		template.getForEntityBase("/v1/defi/get_approve_balance");
	}
	
	@Test
	public void test_approve() throws Exception {
		RestfulTemplate template = new RestfulTemplate(HOST, clientaccessid, clientaccesskey);
		DefiApproveParam param = new DefiApproveParam(); 
		param.setAmount(BigDecimal.ONE);
		param.setProductId(781241467472445440l);
		String hexValue = "0xf8a90d843b9aca00830186a094d9ba894e0097f8cc2bbc9d24d308b98e36dc6d0280b844095ea7b3000000000000000000000000ddcfbaa2106f178f578463256c1182c34e60bd4000000000000000000000000000000000000000000000000000000000000000011ca0feb6cae429e6758e088a9dc62b80104bbf8604ddfa621443e6bf69661b8ac75ca01b522b5532ad21cb11f7c17f28f45da81e07bd662341cbf0d550e25d82234e40";
		param.setSignData(hexValue);
		template.postForEntityBase("/v1/defi/approve",param);
	}
	

}
