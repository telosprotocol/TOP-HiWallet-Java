package com.topnetwork.wallet.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.base.core.context.annotation.RequestLimit;
import com.base.service.base.logger.entity.LoginLogDO;
import com.base.service.base.logger.service.LoginLogService;
import com.base.service.base.system.service.ConfigureService;
import com.topnetwork.common.service.RedisLock;
import com.topnetwork.common.service.SecurityService;
import com.topnetwork.reward.RewardService;
import com.topnetwork.reward.bean.MethodName;
import com.topnetwork.reward.bean.RewardMessage;
import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.common.enums.ActivityType;
import com.topnetwork.wallet.common.enums.CertType;
import com.topnetwork.wallet.common.enums.PlatformEnum;
import com.topnetwork.wallet.common.enums.RewardType;
import com.topnetwork.wallet.dao.AppUserDao;
import com.topnetwork.wallet.entity.AppActivityReward;
import com.topnetwork.wallet.entity.AppUser;
import com.topnetwork.wallet.entity.AppUserBalance;
import com.topnetwork.wallet.entity.AppUserCert;
import com.topnetwork.wallet.entity.AppUserDevice;
import com.topnetwork.wallet.entity.SecretPlatform;
import com.topnetwork.wallet.param.wallet.appuser.IsNewParam;
import com.topnetwork.wallet.param.wallet.appuser.UserDataParam;
import com.topnetwork.wallet.param.wallet.appuser.UserInfoParam;
import com.topnetwork.wallet.param.wallet.appuser.UserRegisterParam;
import com.topnetwork.wallet.result.wallet.appuser.IsNewResult;
import com.topnetwork.wallet.result.wallet.appuser.UserCertResult;
import com.topnetwork.wallet.result.wallet.appuser.UserDataResult;
import com.topnetwork.wallet.result.wallet.appuser.UserInfoResult;
import com.topnetwork.wallet.result.wallet.appuser.UserInfoV2Result;
import com.topnetwork.wallet.result.wallet.appuser.UserRegisterResult;
import com.topnetwork.wallet.service.AppActivityRewardService;
import com.topnetwork.wallet.service.AppActivityService;
import com.topnetwork.wallet.service.AppUserBalanceService;
import com.topnetwork.wallet.service.AppUserCertService;
import com.topnetwork.wallet.service.AppUserDeviceService;
import com.topnetwork.wallet.service.AppUserService;

import com.gitee.magic.framework.head.exception.BusinessException;
import com.base.core.framework.sql.service.impl.SqlBaseServiceImpl;
import com.gitee.magic.jdbc.persistence.source.jdbc.sqlplus.conditions.query.QueryWrapper;
import com.gitee.magic.core.json.JsonObject;

@Service("appUserService")
@Transactional
public class AppUserServiceImpl extends SqlBaseServiceImpl<AppUser, Long>
        implements AppUserService {

    @SuppressWarnings("unused")
    private AppUserDao appUserDao;

    @Autowired
    private AppUserDeviceService appUserDeviceService;
    @Autowired
    RewardService rewardService;
    @Autowired
    private AppActivityService appActivityService;
    @Autowired
    private AppActivityRewardService appActivityRewardService;
    @Autowired
    private AppUserBalanceService appUserBalanceService;
    @Autowired
    private AppUserCertService appUserCertService;
    @Autowired
    private ConfigureService configureService;
    @Autowired
    private LoginLogService loginLogService;
    @Autowired
    private SecurityService securityService;

    public AppUserServiceImpl(@Qualifier("appUserDao") AppUserDao appUserDao) {
        super(appUserDao);
        this.appUserDao = appUserDao;
    }

    @Override
    @RequestLimit(max = 3, key = "#requestIP", interval = 24 * 60 * 60)
    public UserRegisterResult register(UserRegisterParam param, String requestIP, SecretPlatform secretPlatform) {

        securityService.check(param.getCode(), param.getXaxis(), param.getYaxis());

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("uid", param.getUid());
        AppUser user = get(appUserDao.queryForList(queryWrapper));

        if (user == null) {
            user = new AppUser();
            user.setCreateTime(new Date());
            user.setMobile(param.getMobile());
            user.setUid(param.getUid());
            Long id = appUserDao.saveReturnId(user);
            user.setId(id);
            if (id != null && id > 0) {
                //增加用户top余额
                appUserBalanceService.addTopBalance(id, new BigDecimal("0.00"));
                List<AppUserDevice> userDevice = appUserDeviceService.getByDeviceId(param.getDeviceId());
                if (userDevice == null || userDevice.isEmpty()) {
                    //新用户，发送新用户空投
                    RewardMessage rewardMessage = new RewardMessage();
                    rewardMessage.setUid(id);
                    rewardMessage.setActivityType(ActivityType.AIRDROP);
                    rewardMessage.setAppActivity(appActivityService.findActivityByType(RewardType.NEWUSER));
                    rewardMessage.setMethodName(MethodName.award);
                    rewardService.execute(rewardMessage);
                }
            }
        }
        appUserDeviceService.saveUserDevice(param.getDeviceId(), user.getId());

        LoginLogDO loginLog = new LoginLogDO();
        loginLog.setUserId(user.getId());
        loginLog.setRequestIp(requestIP);
        if (secretPlatform.getPlatform().equals(PlatformEnum.HTML)) {
            loginLog.setSource("WEB");
        } else {
            loginLog.setSource(secretPlatform.getPlatform().toString());
        }
        loginLog.setUserType("APPLOGIN");
//        loginLogService.buildLog(loginLog);
        loginLog=loginLogService.buildLog(loginLog.getUserId(), loginLog.getRequestIp(), loginLog.getSource(), loginLog.getUserType());
        UserRegisterResult result = new UserRegisterResult();
        result.setAccessid(loginLog.getAccessId());
        result.setAccesskey(loginLog.getAccessKey());
        return result;
    }

    @Override
    public void delete() {
        List<AppUser> userList = appUserDao.queryForListMapper("findSameUserList", null);
        for (AppUser appUser : userList) {
            AppUserBalance topBalance = appUserBalanceService.getTopBalance(appUser.getId());
            if (topBalance == null
                    || (topBalance.getBalance().subtract(topBalance.getFreezeBalance()))
                    .compareTo(BigDecimal.ZERO) == 0) {
                if (topBalance != null) {
                    appUserBalanceService.remove(topBalance);
                }
                appUserDao.remove(appUser);
            }
        }
    }

    @Override
    public IsNewResult isNew(IsNewParam param) {
        IsNewResult isNewResult = new IsNewResult();
        AppUser appUser = getUserByUid(param.getUid());
        if (appUser == null) {
            List<AppUserDevice> devices = appUserDeviceService.getByDeviceId(param.getDeviceId());
            if (ObjectUtils.isEmpty(devices)) {
                isNewResult.setNewUser(true);
            } else {
                isNewResult.setNewUser(false);
            }
        } else {
            AppActivityReward appActivityReward = appActivityRewardService.findNewUserReward(appUser);
            if (appActivityReward == null) {
                isNewResult.setNewUser(false);
            } else {
                if (appActivityReward.getExpirationTime().after(new Date())) {
                    isNewResult.setNewUser(!appActivityReward.getReceive());
                } else {
                    isNewResult.setNewUser(false);
                }
            }
        }
        return isNewResult;
    }

    @Override
    public AppUser findById(Long userId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", userId);
        return get(appUserDao.queryForList(queryWrapper));
    }

    @Override
    public UserInfoResult getUserInfo(AppUser appUser) {
        UserInfoResult userInfoResult = getTopBalance(appUser);
        BigDecimal air = appActivityRewardService.sumByUser(appUser);
        userInfoResult.setAirdrop(air == null ? new BigDecimal("0.00") : air);

        AppActivityReward appActivityReward = appActivityRewardService.findNewUserReward(appUser);
        if (appActivityReward == null) {
            userInfoResult.setNewUser(false);
        } else {
            if (appActivityReward.getExpirationTime().after(new Date())) {
                userInfoResult.setNewUser(!appActivityReward.getReceive());
            } else {
                userInfoResult.setNewUser(false);
            }
        }
        return userInfoResult;
    }

    @Override
    public UserInfoV2Result getUserV2Info(AppUser appUser) {
        UserInfoResult userInfoResult = getUserInfo(appUser);
        UserInfoV2Result userInfoV2Result = new UserInfoV2Result();
        BeanUtils.copyProperties(userInfoResult, userInfoV2Result);
        AppUser database = getUserById(appUser.getId());
        userInfoV2Result.setPhoneBind(!StringUtils.isEmpty(database.getMobile()));
        JsonObject jsonObject = (JsonObject) configureService.getValue("inviteTabConfig");
        userInfoV2Result.setBindUrl(String.valueOf(jsonObject.get("bindUrl")));
        userInfoV2Result.setInviteUrl(String.valueOf(jsonObject.get("inviteUrl")));
        return userInfoV2Result;
    }

    @Override
    public UserInfoResult getTopBalance(AppUser appUser) {
        UserInfoResult userInfoResult = new UserInfoResult();
        AppUserBalance appUserBalance = appUserBalanceService.getTopBalance(appUser.getId());
        if (appUserBalance == null) {
            userInfoResult.setBalance(new BigDecimal("0.00"));
        } else {
            userInfoResult.setBalance(appUserBalance.getBalance().subtract(appUserBalance.getFreezeBalance()));
        }
        return userInfoResult;
    }

    @Override
    public UserInfoResult getTopBalanceForGuess(UserInfoParam param) {
        AppUser appUser = getUserByUid(param.getUid());
        if (appUser == null) {
            throw new BusinessException(CodeRes.CODE_15009);
        }
        return getTopBalance(appUser);
    }

    @Override
    public AppUser getUserByUid(String uid) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("uid", uid);
        AppUser appUser = get(appUserDao.queryForList(wrapper));
        return appUser;
    }

    @Override
    public AppUser getUserByMobile(Integer countryCode, String mobile) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("countryCode", countryCode).eq("mobile", mobile);
        return get(appUserDao.queryForList(wrapper));
    }

    @Autowired
    private RedisLock redisLock;

    @Override
    public UserCertResult findUserCert(AppUser appUser) {
        String key = "find_user_cert_" + appUser.getId();
        boolean lock = redisLock.lock(key);
        // 执行逻辑操作
        if (lock) {
            UserCertResult userCertResult = new UserCertResult();
            AppUserCert appUserCert = appUserCertService.getByUserId(appUser.getId());
            if (appUserCert == null) {
                appUserCert = addNewUserCert(appUser.getId());
            }
            userCertResult.setCertNum(appUserCert.getCertNum());
            userCertResult.setCreateTime(appUserCert.getCreateTime().getTime());
            redisLock.delete(key);
            return userCertResult;
        } else {
            throw new BusinessException(CodeRes.CODE_429);
        }
    }

    @Override
    public List<AppUser> findCreateInTimeAll(Integer dayNum) {
        Map<String, Object> param = new HashMap<>();
        param.put("dayNum", dayNum);
        return appUserDao.queryForListMapper("findCreateInTimeAll", param);
    }

    @Override
    public UserDataResult loginUserData(UserDataParam param) {
        LoginLogDO loginLog = loginLogService.authority(param.getAccessId(), "APPLOGIN");
        UserDataResult userDataResult = new UserDataResult();
        userDataResult.setAccessId(loginLog.getAccessId());
        userDataResult.setAccessKey(loginLog.getAccessKey());
        AppUser appUser = getUserById(loginLog.getUserId());
        userDataResult.setId(appUser.getId());
        userDataResult.setLevel(appUser.getLevel());
        userDataResult.setMobile(appUser.getMobile());
        userDataResult.setUid(appUser.getUid());
        return userDataResult;
    }

    public AppUser getUserById(Long userId) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("id", userId);
        AppUser appUser = get(appUserDao.queryForList(wrapper));
        return appUser;
    }

    private AppUserCert addNewUserCert(Long uid) {
        AppUserCert appUserCert = new AppUserCert();
        appUserCert.setCreateTime(new Date());
        appUserCert.setType(CertType.NEWUSER);
        appUserCert.setUid(uid);
        String certNumPrefix = String.valueOf(configureService.getValue("certNumPrefix"));
        appUserCert.setCertNum(certNumPrefix + appUserDao.getSnowflakeIdWorkerNextId());
        appUserCertService.save(appUserCert);
        return appUserCert;

    }

}
