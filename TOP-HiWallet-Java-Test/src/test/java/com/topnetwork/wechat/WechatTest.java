package com.topnetwork.wechat;

import com.topnetwork.common.TestHeader;
import com.topnetwork.wallet.param.wechat.SignatureParam;
import com.topnetwork.wallet.result.wechat.SignatureResult;
import org.junit.Test;
import com.gitee.magic.framework.base.rest.RestfulTemplate;

public class WechatTest extends TestHeader {
    @Test
    public void test_share() throws Exception {
        SignatureParam signatureParam = new SignatureParam();
        signatureParam.setUrl("www.topnetwork.org");
        RestfulTemplate template = new RestfulTemplate(HOST, accessid, accesskey);
        SignatureResult signatureResult = template.getForEntityResult("/share/get_signature", signatureParam, SignatureResult.class);
        System.out.println(signatureResult);
    }
}