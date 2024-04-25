package com.topnetwork.wallet;

import com.topnetwork.common.TestHeader;
import com.topnetwork.wallet.common.enums.LanguageEnum;
import com.topnetwork.wallet.common.enums.RegionEnum;
import com.topnetwork.wallet.param.wallet.discover.GetBannersV4Param;
import com.topnetwork.wallet.param.wallet.invite.*;
import org.junit.Test;
import com.gitee.magic.framework.base.rest.RestfulTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * @ClassName InviteTest
 * @Description
 * @Author bran
 * @Date 2020/5/8 10:10
 */
public class InviteTest extends TestHeader {

    @Test
    public void test_get_banners_v4() throws Exception {

        GetBannersV4Param param = new GetBannersV4Param();
        param.setLanguage(LanguageEnum.CN);
        param.setVersion("1.3.4");
        param.setRegion(RegionEnum.COMMON);
        param.setUid("bbb0");
        RestfulTemplate template = new RestfulTemplate(HOST, accessid, accesskey);
        template.getForEntityBase("/v4/app/discover/get_banners", param);
    }

    @Test
    public void test_get_user_info() throws Exception {
        RestfulTemplate template = new RestfulTemplate(HOST, manageraccessid, manageraccesskey);
        template.getForEntityBase("/v2/app/user/info", null);
    }

    @Test
    public void test_get_invite_user_info() throws Exception {
        UserInfoParam param = new UserInfoParam();
        param.setUid("0c9bd8652a6e808bac3138df80181a2b141947b266fdba00bc867bf40f66d5dc");
        RestfulTemplate template = new RestfulTemplate(HOST, accessid, accesskey);
        template.getForEntityBase("/invite/get_user_info", param);
    }

    @Test
    public void test_invite_get_list() throws Exception {
        UserListParam param = new UserListParam();
        param.setUid("0c9bd8652a6e808bac3138df80181a2b141947b266fdba00bc867bf40f66d5dc");
        RestfulTemplate template = new RestfulTemplate(HOST, accessid, accesskey);
        template.getForEntityBase("/invite/get_list", param);
    }

    @Test
    public void test_get_invite_code() throws Exception {
        InviteCodeParam param = new InviteCodeParam();
        param.setUid("374d7e96c3320a81f99a8e6f67c376402fea665c8ccdc75f3966ff8a89be7c00");
        RestfulTemplate template = new RestfulTemplate(HOST, accessid, accesskey);
        template.getForEntityBase("/invite/get_invite_code", param);
    }

    @Test
    public void test_invite_get_phone_code() throws Exception {
        PhoneCodeParam param = new PhoneCodeParam();
        param.setMobile("15737978239");
        param.setCode("06aa13020060110f977a23ba02e07070");
        param.setXaxis(new BigDecimal("0"));
        param.setYaxis(new BigDecimal("0"));
        param.setLanguage(LanguageEnum.EN);
        RestfulTemplate template = new RestfulTemplate(HOST, accessid, accesskey);
        template.getForEntityBase("/invite/get_phone_code", param);
    }

    @Test
    public void test_verify_phone_code() throws Exception {
        PhoneVerifyParam param = new PhoneVerifyParam();
        param.setMobile("15737978239");
        RestfulTemplate template = new RestfulTemplate(HOST, accessid, accesskey);
        template.getForEntityBase("/invite/verify_phone_code_bind", param);
    }

    @Test
    public void test_invite_rec_invite() throws Exception {
        RecInviteParam param = new RecInviteParam();
        param.setMobile("15737978253");
        param.setInviteCode("0998759ce28e4d7c8e3645f2c91010b4");
        param.setPhoneCode("625368");
        RestfulTemplate template = new RestfulTemplate(HOST, accessid, accesskey);
        template.postForEntityBase("/invite/rec_invite", param);
    }

    @Test
    public void test_invite_get_reward() throws Exception {
        RestfulTemplate template = new RestfulTemplate(HOST, accessid, accesskey);
        template.getForEntityBase("/invite/get_reward", null);
    }

    @Test
    public void test_invite_get_phone_code_bind() throws Exception {
        PhoneCodeParam param = new PhoneCodeParam();
        param.setMobile("15737978250");
        param.setCode("06aa13020060110f977a23ba02e07070");
        param.setXaxis(new BigDecimal("0"));
        param.setYaxis(new BigDecimal("0"));
        RestfulTemplate template = new RestfulTemplate(HOST, accessid, accesskey);
        template.getForEntityBase("/invite/get_phone_code_bind", param);
    }

    @Test
    public void test_invite_app_bind_phone() throws Exception {
        BindPhoneParam phoneParam = new BindPhoneParam();
        phoneParam.setCode("528656");
        phoneParam.setUid("bbb1");
        phoneParam.setMobile("15737978250");
        RestfulTemplate template = new RestfulTemplate(HOST, accessid, accesskey);
        template.putForEntityBase("/invite/app_bind_phone", phoneParam);
    }

    @Test
    public void test_get_country_code() throws Exception {
        RestfulTemplate template = new RestfulTemplate(HOST, accessid, accesskey);
        template.getForEntityBase("/invite/get_country_code");
    }
}
