package com.topnetwork.wallet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.base.core.context.annotation.AuthenticationCheck;
import com.base.core.context.annotation.LoginAuthorityCheck;
import com.base.core.context.annotation.RepeatSubmit;
import com.base.core.context.annotation.SecretKeyAuthorityCheck;
import com.base.core.context.mvc.BaseController;
import com.gitee.magic.framework.base.result.BaseResponse;
import com.gitee.magic.framework.base.result.PageResponse;
import com.gitee.magic.framework.base.result.ResultResponse;
import com.topnetwork.wallet.entity.AppUser;
import com.topnetwork.wallet.param.wallet.activity.ActivityAddParam;
import com.topnetwork.wallet.param.wallet.activity.ActivityBaseParam;
import com.topnetwork.wallet.param.wallet.activity.ActivityConfParam;
import com.topnetwork.wallet.param.wallet.activity.ActivityPageParam;
import com.topnetwork.wallet.param.wallet.activity.ActivityTicketParam;
import com.topnetwork.wallet.param.wallet.activity.ActivityUpdParam;
import com.topnetwork.wallet.param.wallet.activity.BehaviorPageParam;
import com.topnetwork.wallet.param.wallet.activity.BehaviorUpdateParam;
import com.topnetwork.wallet.param.wallet.activity.BtcPriceListParam;
import com.topnetwork.wallet.param.wallet.activity.GameConfigPageParam;
import com.topnetwork.wallet.param.wallet.activity.GameConfigUpdParam;
import com.topnetwork.wallet.param.wallet.activity.GameHistParam;
import com.topnetwork.wallet.param.wallet.activity.GuessConfigParam;
import com.topnetwork.wallet.param.wallet.activity.GuessReportParam;
import com.topnetwork.wallet.param.wallet.activity.PartInGuessParam;
import com.topnetwork.wallet.param.wallet.activity.QuestionAddParam;
import com.topnetwork.wallet.param.wallet.activity.QuestionDetailParam;
import com.topnetwork.wallet.param.wallet.activity.QuestionListParam;
import com.topnetwork.wallet.param.wallet.activity.QuestionPageParam;
import com.topnetwork.wallet.param.wallet.activity.QuestionUpdParam;
import com.topnetwork.wallet.param.wallet.activity.RecBrickParam;
import com.topnetwork.wallet.param.wallet.activity.RecQuestionParam;
import com.topnetwork.wallet.param.wallet.activity.RewardGuessParam;
import com.topnetwork.wallet.param.wallet.activity.RewardPageParam;
import com.topnetwork.wallet.param.wallet.appuser.UserInfoParam;
import com.topnetwork.wallet.result.wallet.activity.ActivityPageResult;
import com.topnetwork.wallet.result.wallet.activity.ActivityTicketResult;
import com.topnetwork.wallet.result.wallet.activity.BehaviorPageResult;
import com.topnetwork.wallet.result.wallet.activity.BtcPriceListResult;
import com.topnetwork.wallet.result.wallet.activity.GameConfigPageResult;
import com.topnetwork.wallet.result.wallet.activity.GameHistResult;
import com.topnetwork.wallet.result.wallet.activity.GuessConfigResult;
import com.topnetwork.wallet.result.wallet.activity.QuestionDetailResult;
import com.topnetwork.wallet.result.wallet.activity.QuestionListResult;
import com.topnetwork.wallet.result.wallet.activity.QuestionPageResult;
import com.topnetwork.wallet.result.wallet.activity.RecBrickResult;
import com.topnetwork.wallet.result.wallet.activity.RecQuestionResult;
import com.topnetwork.wallet.result.wallet.activity.RewardPageResult;
import com.topnetwork.wallet.result.wallet.appuser.UserInfoResult;
import com.topnetwork.wallet.service.AppActivityHistService;
import com.topnetwork.wallet.service.AppActivityService;
import com.topnetwork.wallet.service.AppBehaviorConfigService;
import com.topnetwork.wallet.service.AppBtcPriceService;
import com.topnetwork.wallet.service.AppGameConfigService;
import com.topnetwork.wallet.service.AppQuestionService;
import com.topnetwork.wallet.service.AppUserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping
@CrossOrigin
@Tag(name = "新青年活动接口")
public class ActivityController extends BaseController {

    /**
     * manager
     */
    @Autowired
    private AppActivityService appActivityService;
    @Autowired
    private AppQuestionService appQuestionService;
    @Autowired
    private AppGameConfigService appGameConfigService;
    @Autowired
    private AppBehaviorConfigService appBehaviorConfigService;

    @Operation(summary= "查找活动分页列表")
    @AuthenticationCheck
    @PostMapping(value = "/game/find_activity_page")
    public PageResponse<List<ActivityPageResult>> findActivityPage(@RequestBody ActivityPageParam param) {
        return response(appActivityService.getActivityPage(param));
    }

    @Operation(summary= "更新活动")
    @AuthenticationCheck
    @PostMapping(value = "/game/update_activity")
    public BaseResponse updateActivity(@RequestBody ActivityUpdParam param) {
        appActivityService.updateActivity(param);
        return response();
    }

    @Operation(summary= "添加活动")
    @AuthenticationCheck
    @PostMapping(value = "/game/add_activity")
    public BaseResponse updateActivity(@RequestBody ActivityAddParam param) {
        appActivityService.addActivity(param);
        return response();
    }

    @Operation(summary= "查找问题分页列表")
    @AuthenticationCheck
    @PostMapping(value = "/game/find_question_page")
    public PageResponse<List<QuestionPageResult>> findQuestionPage(@RequestBody QuestionPageParam param) {
        return response(appQuestionService.findQuestionPage(param));
    }

    @Operation(summary= "查找问题详情")
    @AuthenticationCheck
    @PostMapping(value = "/game/find_question_detail")
    public ResultResponse<QuestionDetailResult> findQuestionDetail(@RequestBody QuestionDetailParam param) {
        return response(appQuestionService.findQuestionDetail(param));
    }

    @Operation(summary= "增加问题问题详情")
    @AuthenticationCheck
    @PostMapping(value = "/game/add_question")
    public BaseResponse addQuestion(@RequestBody QuestionAddParam param) {
        appQuestionService.addQuestion(param);
        return response();
    }

    @Operation(summary= "修改问题")
    @AuthenticationCheck
    @PostMapping(value = "/game/update_question")
    public BaseResponse updateQuestion(@RequestBody QuestionUpdParam param) {
        appQuestionService.updateQuestion(param);
        return response();
    }

    @Operation(summary= "查找游戏数值配置信息")
    @AuthenticationCheck
    @PostMapping(value = "/game/find_game_config_page")
    public PageResponse<List<GameConfigPageResult>> findGameConfigPage(@RequestBody GameConfigPageParam param) {
        return response(appActivityService.findGameConfigPage(param));
    }

    @Operation(summary= "修改游戏数值配置信息")
    @AuthenticationCheck
    @PostMapping(value = "/game/update_game_config")
    public BaseResponse updateGameConfig(@RequestBody GameConfigUpdParam param) {
        appGameConfigService.updateGameConfig(param);
        return response();
    }

    @Operation(summary= "查找空投分页记录")
    @AuthenticationCheck
    @PostMapping(value = "/game/find_reward_page")
    public PageResponse<List<RewardPageResult>> findRewardPage(@RequestBody RewardPageParam param) {
        return response(appActivityService.findRewardPage(param));
    }

    @Operation(summary= "查找空投配置分页记录")
    @AuthenticationCheck
    @PostMapping(value = "/game/find_behavior_page")
    public PageResponse<List<BehaviorPageResult>> findBehaviorPage(@RequestBody BehaviorPageParam param) {
        return response(appActivityService.findBehaviorPage(param));
    }

    @Operation(summary= "修改空投配置")
    @AuthenticationCheck
    @PostMapping(value = "/game/update_behavior")
    public BaseResponse updateBehavior(@RequestBody BehaviorUpdateParam param) {
        appBehaviorConfigService.updateBehavior(param);
        return response();
    }

    /**
     * h5
     */

    @Autowired
    private AppUserService appUserService;

    @CrossOrigin
    @Operation(summary= "用户余额获取")
    @LoginAuthorityCheck
    @PostMapping(value = "/game/get_balance")
    public ResultResponse<UserInfoResult> info() {
        AppUser appUser = getHttp().getCache(AppUser.class);
        return response(appUserService.getTopBalance(appUser));
    }

    @CrossOrigin
    @Operation(summary= "用户余额获取")
    @SecretKeyAuthorityCheck(9)
    @PostMapping(value = "/game/get_balance_for_guess")
    public ResultResponse<UserInfoResult> info(@RequestBody UserInfoParam param) {
        return response(appUserService.getTopBalanceForGuess(param));
    }

    @CrossOrigin
    @Operation(summary= "砖块，答题游戏开始")
    @LoginAuthorityCheck
    @PostMapping(value = "/game/start")
    @RepeatSubmit(interval = 5, expired = true)
    public BaseResponse start(@RequestBody ActivityConfParam param) {
        AppUser appUser = getHttp().getCache(AppUser.class);
        appActivityService.start(param, appUser);
        return response();
    }

    @CrossOrigin
    @Operation(summary= "上报砖块游戏结果")
    @LoginAuthorityCheck
    @RepeatSubmit(interval = 5, expired = true)
    @PostMapping(value = "/game/rec_brick_result")
    public ResultResponse<RecBrickResult> recBrickResult(@RequestBody RecBrickParam param) {
        AppUser appUser = getHttp().getCache(AppUser.class);
        return response(appActivityService.recBrickResult(param, appUser));
    }

    @CrossOrigin
    @Operation(summary= "砖块游戏复活")
    @LoginAuthorityCheck
    @RepeatSubmit(interval = 5, expired = true)
    @PostMapping(value = "/game/reset")
    public BaseResponse reset(@RequestBody ActivityConfParam param) {
        AppUser appUser = getHttp().getCache(AppUser.class);
        appActivityService.reset(param, appUser);
        return response();
    }

    @CrossOrigin
    @Operation(summary= "砖块游戏结束")
    @LoginAuthorityCheck
    @RepeatSubmit(interval = 5, expired = true)
    @PostMapping(value = "/game/end")
    public BaseResponse end(@RequestBody ActivityBaseParam param) {
        AppUser appUser = getHttp().getCache(AppUser.class);
        appActivityService.end(param, appUser);
        return response();
    }

    @Operation(summary= "上报答题结果")
    @LoginAuthorityCheck
    @RepeatSubmit(interval = 5, expired = true)
    @PostMapping(value = "/game/rec_question_result")
    public ResultResponse<RecQuestionResult> recQuestionResult(@RequestBody RecQuestionParam param) {
        AppUser appUser = getHttp().getCache(AppUser.class);
        return response(appActivityService.recQuestionResult(param, appUser));
    }

    @Operation(summary= "查找问题列表")
    @LoginAuthorityCheck
    @PostMapping(value = "/game/find_question_list")
    public ResultResponse<List<QuestionListResult>> findQuestionList(@RequestBody QuestionListParam param) {
        return response(appQuestionService.getQuestionList(param));
    }

    @Operation(summary= "查找游戏门票")
    @LoginAuthorityCheck
    @PostMapping(value = "/game/find_activity_ticket")
    public ResultResponse<ActivityTicketResult> findActivityTicket(@RequestBody ActivityTicketParam param) {
        AppUser appUser = getHttp().getCache(AppUser.class);
        return response(appActivityService.findActivityTicket(param, appUser));
    }

    /**
     * guess
     */

    @Autowired
    private AppActivityHistService appActivityHistService;
    @Autowired
    private AppBtcPriceService appBtcPriceService;

    @Operation(summary= "查找游戏历史记录")
    @LoginAuthorityCheck
    @PostMapping(value = "/game/find_game_hist")
    public PageResponse<List<GameHistResult>> findGameHist(@RequestBody GameHistParam param) {
        AppUser appUser = getHttp().getCache(AppUser.class);
        return response(appActivityHistService.findGameHist(param, appUser));
    }

    @Operation(summary= "上报竞猜数据")
    @LoginAuthorityCheck
    @RepeatSubmit(interval = 5, expired = true)
    @PostMapping(value = "/game/guess/rec_guess_result")
    public BaseResponse recGuessResult(@RequestBody GuessReportParam param) {
        AppUser appUser = getHttp().getCache(AppUser.class);
        appActivityService.recGuessResult(param, appUser);
        return response();
    }

    @Operation(summary= "获取本期竞猜数据")
    @LoginAuthorityCheck
    @PostMapping(value = "/game/guess/find_game_config")
    public ResultResponse<GuessConfigResult> findGameConfig(@RequestBody GuessConfigParam param) {
        AppUser appUser = getHttp().getCache(AppUser.class);
        return response(appActivityHistService.findGuessGameConfig(param, appUser));
    }

    @SecretKeyAuthorityCheck(9)
    @Operation(summary= "参与新版奖励")
    @PostMapping(value = "/game/guess/part_in_guess")
    public BaseResponse partInGuess(@RequestBody PartInGuessParam param) {
        appActivityService.partInGuess(param);
        return response();
    }


    @Operation(summary= "发放新版奖励")
    @SecretKeyAuthorityCheck(9)
    @PostMapping(value = "/game/guess/reward")
    public BaseResponse reward(@RequestBody RewardGuessParam param) {
        appActivityService.reward(param);
        return response();
    }

    @Operation(summary= "获取本周btc价格列表")
    @LoginAuthorityCheck
    @PostMapping(value = "/game/guess/find_btc_price_list")
    public PageResponse<List<BtcPriceListResult>> findBtcPriceList(@RequestBody BtcPriceListParam param) {
        return response(appBtcPriceService.findInWeekList(param));
    }
}
