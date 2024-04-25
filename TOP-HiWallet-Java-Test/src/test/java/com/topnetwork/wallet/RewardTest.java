package com.topnetwork.wallet;

import com.topnetwork.common.TestHeader;
import com.topnetwork.wallet.common.enums.*;
import com.topnetwork.wallet.param.wallet.activity.*;
import com.topnetwork.wallet.param.wallet.appuser.*;
import com.topnetwork.wallet.result.wallet.appuser.UserRegisterResult;
import org.junit.Test;
import com.gitee.magic.framework.base.rest.RestfulTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class RewardTest extends TestHeader {

    @Test
    public void test_register() throws Exception {

        UserRegisterParam param = new UserRegisterParam();
        param.setDeviceId("1F02C6AE-8D83-4380-BFCD-E52EB9C2FFB4");
        param.setUid("b430f59d8b862829e2b16efc670a30c8e349b280f6cedd4fade257bb165b08a2");
        param.setCode("06aa13020060110f977a23ba02e07070");
        param.setXaxis(new BigDecimal("0"));
        param.setYaxis(new BigDecimal("0"));

        RestfulTemplate template = new RestfulTemplate(HOST, accessid, accesskey);
        UserRegisterResult userRegisterResult = template.postForEntityResult("/v1/app/user/register", param, UserRegisterResult.class);
        System.out.println(userRegisterResult.getAccessid());
    }

    @Test
    public void test_user_info() throws Exception {
        RestfulTemplate template = new RestfulTemplate(HOST, clientaccessid, clientaccesskey);
        template.postForEntityBase("/v1/app/user/info", null);
    }

    @Test
    public void test_user_reward() throws Exception {
        UserRewardParam param = new UserRewardParam();
        param.setLanguage(LanguageEnum.EN);
        RestfulTemplate template = new RestfulTemplate(HOST, manageraccessid, manageraccesskey);
        template.postForEntityBase("/v1/app/user/reward", param);
    }

    @Test
    public void test_user_receive_award() throws Exception {
        ReceiveAwardParam param = new ReceiveAwardParam();
        param.setRewardId(705106684674048000L);
        RestfulTemplate template = new RestfulTemplate(HOST, manageraccessid, manageraccesskey);
        template.postForEntityBase("/v1/app/user/receive_award", param);
    }

    @Test
    public void test_user_find_reword_hist() throws Exception {
        RewordHistParam param = new RewordHistParam();
        param.setLanguage(LanguageEnum.EN);
        param.setSide(RewardSide.REWARD);
        RestfulTemplate template = new RestfulTemplate(HOST, manageraccessid, manageraccesskey);
        template.postForEntityBase("/v1/app/user/find_reward_history", param);
    }

    @Test
    public void test_user_find_activity_list() throws Exception {
        ActivityListParam param = new ActivityListParam();
        param.setLanguage(LanguageEnum.VN);
        RestfulTemplate template = new RestfulTemplate(HOST, manageraccessid, manageraccesskey);
        template.postForEntityBase("/v1/app/activity/find_activity_list", param);
    }

    @Test
    public void test_rec_suc_tran() throws Exception {
        RecSucTranParam param = new RecSucTranParam();
        param.setHash("aaaaaaaa");
        RestfulTemplate template = new RestfulTemplate(HOST, manageraccessid, manageraccesskey);
        template.postForEntityBase("/v1/app/activity/rec_suc_tran", param);
    }

    @Test
    public void test_game_start() throws Exception {
        ActivityConfParam param = new ActivityConfParam();
        param.setAid(14L);
        RestfulTemplate template = new RestfulTemplate(HOST, manageraccessid, manageraccesskey);
        template.postForEntityBase("/game/start", param);
    }

    @Test
    public void test_rec_question_result() throws Exception {
        RecQuestionParam param = new RecQuestionParam();
        param.setAid(14L);

        List<AnswerParam> answerParams = new ArrayList<>();
        AnswerParam answerParam = new AnswerParam();
        answerParam.setQid(1L);
        answerParam.setAnswer(Answer.A);
        answerParams.add(answerParam);

        answerParam.setQid(2L);
        answerParam.setAnswer(Answer.B);
        answerParams.add(answerParam);
        answerParam.setQid(3L);
        answerParam.setAnswer(Answer.A);
        answerParams.add(answerParam);
        answerParam.setQid(4L);
        answerParam.setAnswer(Answer.A);
        answerParams.add(answerParam);
        answerParam.setQid(5L);
        answerParam.setAnswer(Answer.A);
        answerParams.add(answerParam);
        answerParam.setQid(6L);
        answerParam.setAnswer(Answer.A);
        answerParams.add(answerParam);
        answerParam.setQid(7L);
        answerParam.setAnswer(Answer.A);
        answerParams.add(answerParam);

        param.setAnswerList(answerParams);
        RestfulTemplate template = new RestfulTemplate(HOST, manageraccessid, manageraccesskey);
        template.postForEntityBase("/game/rec_question_result", param);
    }

    @Test
    public void test_get_balance() throws Exception {
        RestfulTemplate template = new RestfulTemplate(HOST, manageraccessid, manageraccesskey);
        template.postForEntityBase("/game/get_balance", null);
    }

    @Test
    public void test_start() throws Exception {
        ActivityConfParam param = new ActivityConfParam();
        param.setAid(14L);
        RestfulTemplate template = new RestfulTemplate(HOST, manageraccessid, manageraccesskey);
        template.postForEntityBase("/game/start", param);
    }

    @Test
    public void test_rec_brick_result() throws Exception {
        RecBrickParam param = new RecBrickParam();
        param.setAid(16L);
        param.setRecord(28);
        RestfulTemplate template = new RestfulTemplate(HOST, manageraccessid, manageraccesskey);
        template.postForEntityBase("/game/rec_brick_result", param);
    }

    @Test
    public void test_reset() throws Exception {
        ActivityConfParam param = new ActivityConfParam();
        param.setAid(16L);
        RestfulTemplate template = new RestfulTemplate(HOST, manageraccessid, manageraccesskey);
        template.postForEntityBase("/game/reset", param);
    }

    @Test
    public void test_end() throws Exception {
        ActivityConfParam param = new ActivityConfParam();
        param.setAid(14L);
        RestfulTemplate template = new RestfulTemplate(HOST, manageraccessid, manageraccesskey);
        template.postForEntityBase("/game/end", param);
    }

    @Test
    public void test_find_game_hist() throws Exception {
        GameHistParam param = new GameHistParam();
        param.setAid(14l);
        param.setPageIndex(1);
        param.setPageSize(10);
        RestfulTemplate template = new RestfulTemplate(HOST, manageraccessid, manageraccesskey);
        template.postForEntityBase("/game/find_game_hist", param);
    }

    @Test
    public void test_find_btc_price_list() throws Exception {
        BtcPriceListParam param = new BtcPriceListParam();
//        param.setAid(14l);
        param.setPageIndex(1);
        param.setPageSize(10);
        RestfulTemplate template = new RestfulTemplate(HOST, manageraccessid, manageraccesskey);
        template.postForEntityBase("/game/guess/find_btc_price_list", param);
    }

    @Test
    public void test_find_game_config() throws Exception {
        GuessConfigParam param = new GuessConfigParam();
        param.setAid(15L);
        RestfulTemplate template = new RestfulTemplate(HOST, manageraccessid, manageraccesskey);
        template.postForEntityBase("/game/guess/find_game_config", param);
    }

    @Test
    public void test_rec_guess_result() throws Exception {
        GuessReportParam param = new GuessReportParam();
        param.setAid(15L);
        param.setAmount(GuessAmount.TOP20);
        param.setStatus(UpsAndDowns.FALL);
        RestfulTemplate template = new RestfulTemplate(HOST, manageraccessid, manageraccesskey);
        template.postForEntityBase("/game/guess/rec_guess_result", param);
    }


    @Test
    public void test_find_question_list() throws Exception {
        QuestionListParam param = new QuestionListParam();
        param.setAid(14L);
        param.setLanguage(LanguageEnum.EN);
        RestfulTemplate template = new RestfulTemplate(HOST, manageraccessid, manageraccesskey);
        template.postForEntityBase("/game/find_question_list", param);
    }


    @Test
    public void test_is_new() throws Exception {
        IsNewParam param = new IsNewParam();
        param.setDeviceId("clientId-安卓");
        param.setUid("190e35f7e0d71cebef45");
        RestfulTemplate template = new RestfulTemplate(HOST, accessid, accesskey);
        template.postForEntityBase("/v1/app/user/is_new", param);
    }

    @Test
    public void test_find_user_cert() throws Exception {
        RestfulTemplate template = new RestfulTemplate(HOST, manageraccessid, manageraccesskey);
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
                template.postForEntityBase("/v1/app/user/find_user_cert", null);
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
                template.postForEntityBase("/v1/app/user/find_user_cert", null);
            }
        });
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
                template.postForEntityBase("/v1/app/user/find_user_cert", null);
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();
        Thread.sleep(100000);
    }

    @Test
    public void test_find_activity_page() throws Exception {
        ActivityPageParam param = new ActivityPageParam();
        param.setPageIndex(1);
        param.setPageSize(10);
        RestfulTemplate template = new RestfulTemplate(HOST, manageraccessid, manageraccesskey);
        template.postForEntityBase("/game/find_activity_page", param);
    }

    @Test
    public void test_find_activity_ticket() throws Exception {
        ActivityTicketParam param = new ActivityTicketParam();
        param.setAid(14L);
        RestfulTemplate template = new RestfulTemplate(HOST, manageraccessid, manageraccesskey);
        template.postForEntityBase("/game/find_activity_ticket", param);
    }


    @Test
    public void test_find_reward_page() throws Exception {
        RewardPageParam param = new RewardPageParam();
        param.setCertCode("XQN750405215631");
        param.setStatus(-1);
        param.setPageIndex(1);
        param.setPageSize(10);
        RestfulTemplate template = new RestfulTemplate(HOST, manageraccessid, manageraccesskey);
        template.postForEntityBase("/game/find_reward_page", param);
    }

}
