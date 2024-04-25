package com.topnetwork.wallet;

import com.topnetwork.common.TestHeader;
import com.topnetwork.wallet.common.enums.PlatformEnum;
import com.topnetwork.wallet.param.wallet.ValidatorUpdateParam;
import com.topnetwork.wallet.param.wallet.VersionDelParam;
import com.topnetwork.wallet.param.wallet.VersionListParam;
import com.topnetwork.wallet.param.wallet.validator.ValidatorAddParam;
import com.topnetwork.wallet.param.wallet.validator.ValidatorStatusParam;
import org.junit.Test;
import com.gitee.magic.framework.base.rest.RestfulTemplate;

/**
 * @program: TOP-HiWallet-Java
 * @description:
 * @author: Tyrone
 * @create: 2020-12-03 17:18
 **/
public class ValidatorTest extends TestHeader {


    @Test
    public void test_add_version() throws Exception {
        ValidatorAddParam validatorAddParam = new ValidatorAddParam();
        validatorAddParam.setPlatform(PlatformEnum.IOS);
        validatorAddParam.setVersion("1.99");
        validatorAddParam.setDefi(true);
        RestfulTemplate template = new RestfulTemplate(HOST, manageraccessid, manageraccesskey);
        template.postForEntityBase("/validator/add_version", validatorAddParam);
    }

    @Test
    public void test_update_version() throws Exception {
        ValidatorUpdateParam validatorAddParam = new ValidatorUpdateParam();
        validatorAddParam.setId(784110011050622976L);
        validatorAddParam.setPlatform(PlatformEnum.IOS);
        validatorAddParam.setVersion("1.988");
        validatorAddParam.setDefi(false);
        RestfulTemplate template = new RestfulTemplate(HOST, manageraccessid, manageraccesskey);
        template.postForEntityBase("/validator/update_version", validatorAddParam);
    }

    @Test
    public void test_delete_version() throws Exception {
        VersionDelParam validatorAddParam = new VersionDelParam();
        validatorAddParam.setId(784110011050622976L);
        RestfulTemplate template = new RestfulTemplate(HOST, manageraccessid, manageraccesskey);
        template.postForEntityBase("/validator/del_version", validatorAddParam);
    }

    @Test
    public void test_find_version_detail() throws Exception {
        VersionDelParam validatorAddParam = new VersionDelParam();
        validatorAddParam.setId(784107414688366592L);
        RestfulTemplate template = new RestfulTemplate(HOST, manageraccessid, manageraccesskey);
        template.postForEntityBase("/validator/find_version_detail", validatorAddParam);
    }

    @Test
    public void test_find_version_list() throws Exception {
        VersionListParam validatorAddParam = new VersionListParam();
        RestfulTemplate template = new RestfulTemplate(HOST, manageraccessid, manageraccesskey);
        template.postForEntityBase("/validator/find_version_list", validatorAddParam);
    }

    @Test
    public void test_sYhNlPv() throws Exception {
        ValidatorStatusParam validatorAddParam = new ValidatorStatusParam();
        validatorAddParam.setPlatform(PlatformEnum.IOS);
        validatorAddParam.setVersion("1.99");
        RestfulTemplate template = new RestfulTemplate(HOST, accessid, accesskey);
        template.getForEntityBase("/v1/conf/tab_bar", validatorAddParam);
    }


}
