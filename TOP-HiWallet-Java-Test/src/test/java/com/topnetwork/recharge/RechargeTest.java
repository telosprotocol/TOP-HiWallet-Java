package com.topnetwork.recharge;

import com.topnetwork.common.TestHeader;
import com.topnetwork.wallet.param.recharge.GetRechargeHistoryParam;
import com.topnetwork.wallet.param.recharge.RechargeListParam;
import com.topnetwork.wallet.param.recharge.RechargeParam;
import com.topnetwork.wallet.result.recharge.GetRechargeHistoryResult;
import org.junit.Test;
import com.gitee.magic.framework.base.rest.RestfulTemplate;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * @ClassName ChangellyTest
 * @Description
 * @Author bran
 * @Date 2020/6/15 16:47
 */
public class RechargeTest extends TestHeader {

    @Test
    public void test_recharge() throws Exception {
        RestfulTemplate template = new RestfulTemplate(HOST, manageraccessid, manageraccesskey);
        RechargeListParam paramRoot = new RechargeListParam();
        RechargeParam param = new RechargeParam();
        param.setAmount(new BigDecimal("500"));
        param.setCertNum("XQN750405714780233076572160");

        RechargeParam param2 = new RechargeParam();
        param2.setAmount(new BigDecimal("500"));
        param2.setCertNum("XQN7504057147802330765725640");

        paramRoot.setPhoneCode("000000");
        paramRoot.setRechargeList(Arrays.asList(param, param2));
        template.postForEntityBase("/recharge/recharge", paramRoot);
    }

    @Test
    public void test_get_recharge_history() throws Exception {
        RestfulTemplate template = new RestfulTemplate(HOST, manageraccessid, manageraccesskey);
        GetRechargeHistoryParam param = new GetRechargeHistoryParam();
        param.setCertNum("XQN750405215435");
        template.getForEntityPage("/recharge/get_recharge_history", param, GetRechargeHistoryResult.class);
    }

    @Test
    public void test_get_phone_code() throws Exception {
        RestfulTemplate template = new RestfulTemplate(HOST, manageraccessid, manageraccesskey);
        template.getForEntityBase("/recharge/get_phone_code");
    }

}
