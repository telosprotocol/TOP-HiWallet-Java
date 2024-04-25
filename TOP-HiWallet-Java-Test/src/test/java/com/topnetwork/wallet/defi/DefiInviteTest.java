package com.topnetwork.wallet.defi;

import org.junit.Test;

import com.topnetwork.common.TestHeader;
import com.topnetwork.wallet.param.wallet.defi.BindDefiInviteParam;
import com.topnetwork.wallet.param.wallet.defi.GenerateDefiInviteParam;
import com.topnetwork.wallet.result.defi.DefiInviteResult;

import com.gitee.magic.framework.base.rest.RestfulTemplate;

/**
 * 
 * @author jode
 * @date Oct 29, 2020
 */
public class DefiInviteTest extends TestHeader {

    @Test
    public void test_generate() throws Exception {

    	GenerateDefiInviteParam param = new GenerateDefiInviteParam();
    	param.setEthAddress("0xccb7E9130fdF49504c5543A29E9D3e62112bBe54");
        RestfulTemplate template = new RestfulTemplate(HOST, accessid, accesskey);
        DefiInviteResult result = template.postForEntityResult("/defi_invite/generate", param,DefiInviteResult.class);
        System.out.println(result.toString());
    }

    @Test
    public void test_bind() throws Exception {
    	BindDefiInviteParam param = new BindDefiInviteParam();
    	param.setEthAddress("0x904760665ECB5dCAF1de5F5eeeA4042Ef63d14b5");
    	param.setBindInviteCode("3BZN");
        RestfulTemplate template = new RestfulTemplate(HOST, accessid, accesskey);
        template.postForEntityBase("/defi_invite/bind", param);
    }

}
