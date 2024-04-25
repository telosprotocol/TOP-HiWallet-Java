package com.topnetwork.wallet.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.common.bean.PhoneCodeRedisData;
import com.common.bean.WechatUserInfo;
import com.base.core.mvc.business.CommonBusiness;
import com.common.utils.DateUtils;
import com.common.utils.PhoneCodeUtils;
import com.common.utils.RedisUtils;
import com.base.service.base.system.service.ConfigureService;
import com.topnetwork.coin.EthCoinService;
import com.topnetwork.common.service.SecurityService;
import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.common.enums.CurrencyUnitEnum;
import com.topnetwork.wallet.common.enums.InviteStatusEnum;
import com.topnetwork.wallet.common.enums.LanguageEnum;
import com.topnetwork.wallet.common.enums.RewardFromType;
import com.topnetwork.wallet.common.enums.RewardSide;
import com.topnetwork.wallet.common.enums.RewardType;
import com.topnetwork.wallet.dao.AppInviteDao;
import com.topnetwork.wallet.entity.AppActivity;
import com.topnetwork.wallet.entity.AppActivityReward;
import com.topnetwork.wallet.entity.AppBehaviorConfig;
import com.topnetwork.wallet.entity.AppInvite;
import com.topnetwork.wallet.entity.AppUser;
import com.topnetwork.wallet.param.wallet.invite.BindPhoneParam;
import com.topnetwork.wallet.param.wallet.invite.InviteCodeParam;
import com.topnetwork.wallet.param.wallet.invite.ManagerPhoneCodeParam;
import com.topnetwork.wallet.param.wallet.invite.PhoneCodeParam;
import com.topnetwork.wallet.param.wallet.invite.PhoneVerifyParam;
import com.topnetwork.wallet.param.wallet.invite.PutCodeParam;
import com.topnetwork.wallet.param.wallet.invite.RecInviteParam;
import com.topnetwork.wallet.param.wallet.invite.UserInfoParam;
import com.topnetwork.wallet.param.wallet.invite.UserListParam;
import com.topnetwork.wallet.result.wallet.appuser.UserCertResult;
import com.topnetwork.wallet.result.wallet.invite.BindPhoneResult;
import com.topnetwork.wallet.result.wallet.invite.InviteCodeResult;
import com.topnetwork.wallet.result.wallet.invite.ManagerPhoneCodeResult;
import com.topnetwork.wallet.result.wallet.invite.PhoneCodeResult;
import com.topnetwork.wallet.result.wallet.invite.RewardNumberResult;
import com.topnetwork.wallet.result.wallet.invite.UserInfoResult;
import com.topnetwork.wallet.result.wallet.invite.UserListResult;
import com.topnetwork.wallet.service.AppActivityRewardService;
import com.topnetwork.wallet.service.AppActivityService;
import com.topnetwork.wallet.service.AppBehaviorConfigService;
import com.topnetwork.wallet.service.AppInviteService;
import com.topnetwork.wallet.service.AppMessageLogService;
import com.topnetwork.wallet.service.AppUserBalanceService;
import com.topnetwork.wallet.service.AppUserService;
import com.topnetwork.wechat.service.WeChatService;

import com.gitee.magic.framework.head.exception.BusinessException;
import com.gitee.magic.framework.base.result.QueryResult;
import com.base.core.framework.sql.service.impl.SqlBaseServiceImpl;
import com.gitee.magic.jdbc.persistence.source.jdbc.sqlplus.conditions.query.QueryWrapper;
import com.gitee.magic.core.json.JsonArray;

@Service("appInviteService")
@Transactional
public class AppInviteServiceImpl extends SqlBaseServiceImpl<AppInvite, Long>
        implements AppInviteService {

    @SuppressWarnings("unused")
    private AppInviteDao appInviteDao;
    @Autowired
    private AppBehaviorConfigService appBehaviorConfigService;

    public AppInviteServiceImpl(@Qualifier("appInviteDao") AppInviteDao appInviteDao) {
        super(appInviteDao);
        this.appInviteDao = appInviteDao;
    }


    @Override
    public UserInfoResult getInfo(UserInfoParam param) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("uid", param.getUid());
        List<AppInvite> appInvites = appInviteDao.queryForList(wrapper);
        Integer number = 0;
        Integer all = 0;
        for (AppInvite appInvite : appInvites) {
            if (!appInvite.getEffective()) {
                continue;
            }
            all++;
            if (InviteStatusEnum.bind.equals(appInvite.getInviteStatus())) {
                number++;
            }
        }
        AppBehaviorConfig appBehaviorConfig = appBehaviorConfigService.findByActivityType(RewardType.INVITE);
        UserInfoResult userInfoResult = new UserInfoResult();
        userInfoResult.setNumber(all);
        userInfoResult.setTotalRev(new BigDecimal(number).multiply(new BigDecimal(appBehaviorConfig.getMaxAmount())));
        return userInfoResult;
    }

    @Override
    public QueryResult<List<UserListResult>> getList(UserListParam param) {
        QueryResult<List<UserListResult>> listQueryResult = new QueryResult<>();
        Map<String, Object> params = CommonBusiness.httpPageParam(param);
        params.put("effective", "true");
        QueryResult<List<AppInvite>> queryResultSource = queryForPage(AppInvite.class, "findUserInviteList", params);
        AppBehaviorConfig appBehaviorConfig = appBehaviorConfigService.findByActivityType(RewardType.INVITE);
        if(ObjectUtils.isEmpty(queryResultSource)) {
        	return listQueryResult;
        }
        listQueryResult.setPageInfo(queryResultSource.getPageInfo());
        listQueryResult.setResult(tran(queryResultSource.getResult(), appBehaviorConfig.getMaxAmount()));
        return listQueryResult;
    }

    private List<UserListResult> tran(List<AppInvite> result, Integer reward) {
        List<UserListResult> listResults = new ArrayList<>();
        for (AppInvite appInvite : result) {
            UserListResult userListResult = new UserListResult();
            userListResult.setCreateTime(appInvite.getCreateTime().getTime());
            userListResult.setIconUrl(appInvite.getIconUrl());
            userListResult.setName(appInvite.getName());
            userListResult.setPhone(PhoneCodeUtils.getPrivateMobile(appInvite.getCountryCode(), appInvite.getMobile()));
            userListResult.setStatus(appInvite.getInviteStatus());
            if (InviteStatusEnum.bind.equals(appInvite.getInviteStatus())) {
                userListResult.setTotal(new BigDecimal(reward));
            } else {
                userListResult.setTotal(new BigDecimal("0"));
            }
            listResults.add(userListResult);
        }
        return listResults;
    }

    @Override
    public InviteCodeResult getInviteCode(InviteCodeParam param) {
        AppUser appUser = appUserService.getUserByUid(param.getUid());
        if (appUser == null) {
            throw new BusinessException(CodeRes.CODE_15009);
        }
        InviteCodeResult inviteCodeResult = new InviteCodeResult();
        String code = param.getUid();
        inviteCodeResult.setCode(code);
        if (!StringUtils.isEmpty(appUser.getMobile())) {
            AppInvite appInvite = getByCountryAndMobile(appUser.getMobile(), appUser.getCountryCode());
            if (appInvite != null) {
                inviteCodeResult.setIconUrl(appInvite.getIconUrl());
                inviteCodeResult.setName(appInvite.getName());
            }
        }
        if (StringUtils.isEmpty(inviteCodeResult.getName())) {
            UserCertResult userCert = appUserService.findUserCert(appUser);
            String certNum = userCert.getCertNum();
            inviteCodeResult.setName(certNum.substring(0, 4) + "*" + certNum.substring(certNum.length() - 4));

        }
        return inviteCodeResult;
    }

    @Autowired
    private RedisTemplate<String, PhoneCodeRedisData> redisTemplate;
    @Autowired
    private SecurityService securityService;

    @Override
    public PhoneCodeResult getPhoneCode(PhoneCodeParam param) {

        securityService.check(param.getCode(), param.getXaxis(), param.getYaxis());

        verifyUniqueness(param.getMobile(), param.getCountryCode());
        return sendCode(param.getCountryCode(), param.getMobile(), param.getLanguage());
    }

    @Override
    public void verifyPhoneCode(PhoneVerifyParam param) {
        verifyUniqueness(param.getMobile(), param.getCountryCode());
    }


    @Override
    public ManagerPhoneCodeResult getPhoneCodeForManager(ManagerPhoneCodeParam param) {
        ManagerPhoneCodeResult result = new ManagerPhoneCodeResult();
        String key = RedisUtils.getPhoneCodeKey(param.getMobile());
        PhoneCodeRedisData data = redisTemplate.opsForValue().get(key);
        String key2 = RedisUtils.getPhoneCodeKey(param.getMobile() + "errorTime");
        PhoneCodeRedisData data1 = redisTemplate.opsForValue().get(key2);
        if (data == null) {
            return result;
        }
        if (data1 != null) {
            data.setErrorTimes(data1.getErrorTimes());
        }
        BeanUtils.copyProperties(data, result);
        return result;
    }

    @Override
    public void verifyPhoneCodeBind(PhoneVerifyParam param) {
        verifyMobileBind(param.getCountryCode(), param.getMobile());
    }

    @Override
    public PhoneCodeResult getPhoneCodeBind(PhoneCodeParam param) {

        securityService.check(param.getCode(), param.getXaxis(), param.getYaxis());

        verifyMobileBind(param.getCountryCode(), param.getMobile());
        return sendCode(param.getCountryCode(), param.getMobile(), param.getLanguage());
    }

    private void verifyMobileBind(Integer countryCode, String mobile) {
        AppUser appUser = appUserService.getUserByMobile(countryCode, mobile);
        if (appUser != null) {
            throw new BusinessException(CodeRes.CODE_18010);
        }
    }

    @Autowired
    private AppMessageLogService appMessageLogService;

    private PhoneCodeResult sendCode(Integer countryCode, String mobile, LanguageEnum languageEnum) {
        String code = PhoneCodeUtils.getCode();
        PhoneCodeResult phoneCodeResult = PhoneCodeUtils.sendCode(countryCode, mobile, code, languageEnum, redisTemplate);
        appMessageLogService.saveLog(countryCode + mobile, code, languageEnum);
        return phoneCodeResult;
    }

    private void verifyUniqueness(String mobile, Integer countryCode) {

        AppInvite appInvite = getByCountryAndMobile(mobile, countryCode);
        if (appInvite != null) {
            throw new BusinessException(CodeRes.CODE_18002);
        }
        AppUser appUser = appUserService.getUserByMobile(countryCode, mobile);
        if (appUser != null) {
            throw new BusinessException(CodeRes.CODE_18001);
        }
    }

    private void verifyPhoneCode(String mobile, String phoneCode) {
        PhoneCodeUtils.verifyPhoneCode(mobile, phoneCode, redisTemplate);
    }

    @Autowired
    private AppUserService appUserService;

    @Override
    public void recInvite(RecInviteParam param) {

        verifyPhoneCode(param.getMobile(), param.getPhoneCode());
        verifyUniqueness(param.getMobile(), param.getCountryCode());

        AppInvite appInvite = new AppInvite();
        appInvite.setCreateTime(new Date());
        appInvite.setInviteStatus(InviteStatusEnum.unBind);
        appInvite.setMobile(param.getMobile());
        appInvite.setCountryCode(param.getCountryCode());
        appInvite.setEffective(true);
        AppUser appUser = appUserService.getUserByUid(param.getInviteCode());
        if (appUser == null) {
            throw new BusinessException(CodeRes.CODE_18003);
        }
        appInvite.setUid(param.getInviteCode());
        save(appInvite);
    }

    @Autowired
    private ConfigureService configureService;
    @Autowired
    private EthCoinService ethCoinService;

    @Override
    public RewardNumberResult getRewardNumber() {
        RewardNumberResult rewardNumberResult = new RewardNumberResult();
        AppBehaviorConfig appBehaviorConfig_invite = appBehaviorConfigService.findByActivityType(RewardType.INVITE);
        rewardNumberResult.setInviteReward(new BigDecimal(appBehaviorConfig_invite.getMaxAmount()));
        AppBehaviorConfig appBehaviorConfig_newUser = appBehaviorConfigService.findByActivityType(RewardType.NEWUSER);
        rewardNumberResult.setNewUserReward(new BigDecimal(appBehaviorConfig_newUser.getMaxAmount()));
        String effectiveTimeConf = String.valueOf(configureService.getValue("airdropEffectiveTime"));
        rewardNumberResult.setInviteTimeLimit(Integer.valueOf(effectiveTimeConf));
        String priceFromTokenView = ethCoinService.getPriceFromTokenView("top-network", CurrencyUnitEnum.CNY);
        rewardNumberResult.setPrice(priceFromTokenView);
        return rewardNumberResult;
    }

    @Override
    public List<Integer> getCountryCodes() {
        List<Integer> results = new ArrayList<>();
        JsonArray countryCodes = (JsonArray) configureService.getValue("countryCode");
        for (Object countryCode : countryCodes) {
            if (countryCode == null) {
                continue;
            }
            results.add(Integer.parseInt(countryCode.toString()));
        }
        Collections.sort(results);
        return results;
    }

    @Autowired
    private AppActivityService appActivityService;

    @Override
    public BindPhoneResult appBindPhone(BindPhoneParam param) {

        BindPhoneResult bindPhoneResult = new BindPhoneResult();

        verifyPhoneCode(param.getMobile(), param.getCode());
        verifyMobileBind(param.getCountryCode(), param.getMobile());

        AppUser appUser = appUserService.getUserByUid(param.getUid());
        if (appUser == null) {
            throw new BusinessException(CodeRes.CODE_15009);
        }
        if (!StringUtils.isEmpty(appUser.getMobile())) {
            throw new BusinessException(CodeRes.CODE_18011);
        }
        appUser.setCountryCode(param.getCountryCode());
        appUser.setMobile(param.getMobile());
        appUserService.save(appUser);
        //判断是否被邀请，发放奖励
        AppInvite appInvite = getByCountryAndMobile(param.getMobile(), param.getCountryCode());
        String effectiveTimeConf = String.valueOf(configureService.getValue("airdropEffectiveTime"));
        if (appInvite != null) {
            //被邀请
            if (!appInvite.getUid().equals(appUser.getUid())) {
                //不是本人邀请本人

                //判断邀请人是否也被本人邀请过
                AppUser appUserInviter = appUserService.getUserByUid(appInvite.getUid());
                boolean invite = false;
                if (!StringUtils.isEmpty(appUserInviter.getMobile())) {
                    QueryWrapper wrapper2 = new QueryWrapper();
                    wrapper2.eq("mobile", appUserInviter.getMobile());
                    AppInvite appInvite2 = get(appInviteDao.queryForList(wrapper2));
                    if (appInvite2 == null) {
                        invite = false;
                    } else if (appInvite2.getUid().equals(appUser.getUid())) {
                        invite = true;
                    } else {
                        invite = false;
                    }
                } else {
                    invite = false;
                }
                if (!invite) {
                    AppBehaviorConfig appBehaviorConfig = appBehaviorConfigService.findByActivityType(RewardType.INVITE);
                    AppActivity appActivity = appActivityService.findActivityByType(RewardType.INVITE);
                    if (DateUtils.getDayDiff(new Date(), appInvite.getCreateTime()) <= Integer.valueOf(effectiveTimeConf)) {
                        //被邀请人在过期时间内，给被邀请人发奖励
                        sendInviteReward(appUser, appActivity, appBehaviorConfig, effectiveTimeConf);
                    }
                    //给邀请人发奖励
                    sendInviteReward(appUserInviter, appActivity, appBehaviorConfig, effectiveTimeConf);
                    //更新邀请状态
                    appInvite.setInviteStatus(InviteStatusEnum.bind);
                    bindPhoneResult.setMessage(CodeRes.CODE_18013);
                } else {
                    bindPhoneResult.setMessage(CodeRes.CODE_18015);
                    appInvite.setEffective(false);
                }
            } else {
                bindPhoneResult.setMessage(CodeRes.CODE_18014);
                appInvite.setEffective(false);
            }
            save(appInvite);
        } else {
            bindPhoneResult.setMessage(CodeRes.CODE_18013);
        }
        //给绑定手机号的用户发送绑定奖励
        sendBindReward(appUser, effectiveTimeConf);

        return bindPhoneResult;
    }

    private void sendBindReward(AppUser appUser, String effectiveTimeConf) {
        AppBehaviorConfig appBehaviorConfig = appBehaviorConfigService.findByActivityType(RewardType.BINDPHONE);
        AppActivity appActivity = appActivityService.findActivityByType(RewardType.BINDPHONE);
        sendReward(appUser, appActivity, appBehaviorConfig, effectiveTimeConf);
    }

    private void sendInviteReward(AppUser appUser, AppActivity appActivity, AppBehaviorConfig appBehaviorConfig, String effectiveTimeConf) {
        sendReward(appUser, appActivity, appBehaviorConfig, effectiveTimeConf);
    }

    @Autowired
    private AppUserBalanceService appUserBalanceService;
    @Autowired
    private AppActivityRewardService appActivityRewardService;

    private void sendReward(AppUser appUser, AppActivity appActivity, AppBehaviorConfig appBehaviorConfig, String effectiveTimeConf) {
        Date now = new Date();
        AppActivityReward appActivityReward = new AppActivityReward();
        appActivityReward.setAid(appActivity.getId());
        String commit = "";
        if (RewardType.BINDPHONE.equals(appActivity.getBehaviorType())) {

            if (!StringUtils.isEmpty(appUser.getMobile())) {
                commit = appActivity.getTitle() + "(" + PhoneCodeUtils.getPrivateMobile(appUser.getCountryCode(), appUser.getMobile()) + ")," + appActivity.getEnglishTitle() + "(" + PhoneCodeUtils.getPrivateMobile(appUser.getCountryCode(), appUser.getMobile()) + ")";
            } else {
                commit = appActivity.getTitle() + "," + appActivity.getEnglishTitle();
            }
        } else {
            commit = appActivity.getTitle() + "," + appActivity.getEnglishTitle();
        }
        appActivityReward.setComment(commit);
        appActivityReward.setEffectiveTime(now);
        appActivityReward.setCreateTime(now);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.DATE, Integer.valueOf(effectiveTimeConf));
        appActivityReward.setExpirationTime(calendar.getTime());
        appActivityReward.setUid(appUser.getId());
        appActivityReward.setType(appActivity.getBehaviorType());
        appActivityReward.setSide(RewardSide.REWARD);
        appActivityReward.setSource(RewardFromType.AIRDROP);
        appActivityReward.setVerification(false);
        appActivityReward.setReceive(true);
        appActivityReward.setReceiveTime(now);
        appActivityReward.setAmount(new BigDecimal(appBehaviorConfig.getMaxAmount()));
        appActivityReward.setUnit(appBehaviorConfig.getUnit());
        appUserBalanceService.receiveAward(appActivityReward);
        appActivityRewardService.save(appActivityReward);
    }

    @Autowired
    private WeChatService weChatService;

    @Override
    public void putCode(PutCodeParam param) {
        AppInvite appInvite = getByCountryAndMobile(param.getMobile(), param.getCountryCode());
        if (appInvite == null) {
            throw new BusinessException(CodeRes.CODE_11013);
        }
        WechatUserInfo weChat = weChatService.getWeChat(param.getUserCode());
        if (weChat != null) {
            QueryWrapper wrapper = new QueryWrapper();
            wrapper.eq("openid", weChat.getOpenid());
            AppInvite appInvite_wx = get(appInviteDao.queryForList(wrapper));
            if (appInvite_wx == null) {
                appInvite.setIconUrl(weChat.getHeadimgurl());
                appInvite.setOpenid(weChat.getOpenid());
                appInvite.setName(weChat.getNickname());
                save(appInvite);
            }
        }
    }

    private AppInvite getByCountryAndMobile(String mobile, Integer countryCode) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("countryCode", countryCode).eq("mobile", mobile);
        return get(appInviteDao.queryForList(wrapper));
    }

}
