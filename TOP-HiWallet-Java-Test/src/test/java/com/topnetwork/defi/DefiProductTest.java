package com.topnetwork.defi;

import org.junit.Test;

import com.topnetwork.common.TestHeader;
import com.topnetwork.wallet.common.enums.LanguageEnum;
import com.topnetwork.wallet.param.defi.ProductIdLanguageParam;
import com.topnetwork.wallet.param.defi.ProductPageParam;

import com.gitee.magic.framework.base.rest.RestfulTemplate;

/**
 * 
 * @author jode
 * @date Oct 29, 2020
 */
public class DefiProductTest extends TestHeader {

	@Test
    public void test_v1_get_product_list() throws Exception {
        RestfulTemplate template = new RestfulTemplate(HOST,clientaccessid,clientaccesskey);
        ProductPageParam param = new ProductPageParam();
        param.setLanguage(LanguageEnum.EN);
        template.getForEntityBase("/v1/defi/get_product_list", param);
    }
	
	@Test
    public void test_get_prodct_detil() throws Exception {
        RestfulTemplate template = new RestfulTemplate(HOST,clientaccessid,clientaccesskey);
        ProductIdLanguageParam param = new ProductIdLanguageParam();
        param.setId(790885228473294848L);
        param.setLanguage(LanguageEnum.CN);
        template.getForEntityBase("/v1/defi/get_product_detail", param);
    }
	
	@Test
    public void test_v1_get_product_listv2() throws Exception {
        RestfulTemplate template = new RestfulTemplate(HOST,clientaccessid,clientaccesskey);
        ProductPageParam param = new ProductPageParam();
        param.setLanguage(LanguageEnum.EN);
        template.getForEntityBase("/v2/defi/get_product_list", param);
    }
	
	@Test
    public void test_get_prodct_detilv2() throws Exception {
        RestfulTemplate template = new RestfulTemplate(HOST,clientaccessid,clientaccesskey);
        ProductIdLanguageParam param = new ProductIdLanguageParam();
        param.setId(789444937174294528L);
        param.setLanguage(LanguageEnum.CN);
        template.getForEntityBase("/v2/defi/get_product_detail", param);
    }
	
	@Test
	public void test_get_product_base_info() throws Exception {
        RestfulTemplate template = new RestfulTemplate(HOST,clientaccessid,clientaccesskey);
        ProductIdLanguageParam param = new ProductIdLanguageParam();
        param.setId(790885228473294848L);
        param.setLanguage(LanguageEnum.CN);
		template.getForEntityBase("/v1/defi/get_product_base_info", param);
	}
	
	@Test
	public void test_get_product_basic_info() throws Exception {
        RestfulTemplate template = new RestfulTemplate(HOST,clientaccessid,clientaccesskey);
        ProductIdLanguageParam param = new ProductIdLanguageParam();
        param.setId(790885228473294848L);
        param.setLanguage(LanguageEnum.CN);
		template.getForEntityBase("/defi/get_product_basic_info", param);
	}

}
