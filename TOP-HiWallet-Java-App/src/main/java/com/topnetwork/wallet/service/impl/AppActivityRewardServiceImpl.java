package com.topnetwork.wallet.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.base.core.mvc.business.CommonBusiness;
import com.common.utils.DateUtils;
import com.common.utils.RedisUtils;
import com.base.service.base.system.service.ConfigureService;
import com.topnetwork.reward.RewardService;
import com.topnetwork.reward.bean.MethodName;
import com.topnetwork.reward.bean.RewardMessage;
import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.common.enums.ActivityType;
import com.topnetwork.wallet.common.enums.BalanceType;
import com.topnetwork.wallet.common.enums.LanguageEnum;
import com.topnetwork.wallet.common.enums.RewardFromType;
import com.topnetwork.wallet.common.enums.RewardSide;
import com.topnetwork.wallet.common.enums.RewardType;
import com.topnetwork.wallet.dao.AppActivityRewardDao;
import com.topnetwork.wallet.entity.AppActivity;
import com.topnetwork.wallet.entity.AppActivityReward;
import com.topnetwork.wallet.entity.AppBehaviorConfig;
import com.topnetwork.wallet.entity.AppUser;
import com.topnetwork.wallet.entity.AppWithdrawOrder;
import com.topnetwork.wallet.param.wallet.appuser.ReceiveAwardParam;
import com.topnetwork.wallet.param.wallet.appuser.RewordHistParam;
import com.topnetwork.wallet.param.wallet.appuser.UserRewardParam;
import com.topnetwork.wallet.result.wallet.appuser.RewardHistResult;
import com.topnetwork.wallet.result.wallet.appuser.UserInfoResult;
import com.topnetwork.wallet.result.wallet.appuser.UserRewardResult;
import com.topnetwork.wallet.service.AppActivityRewardService;
import com.topnetwork.wallet.service.AppActivityService;
import com.topnetwork.wallet.service.AppBehaviorConfigService;
import com.topnetwork.wallet.service.AppUserBalanceService;

import com.gitee.magic.framework.head.exception.BusinessException;
import com.gitee.magic.framework.base.result.QueryResult;
import com.base.core.framework.sql.service.impl.SqlBaseServiceImpl;
import com.gitee.magic.jdbc.persistence.source.jdbc.sqlplus.conditions.query.QueryWrapper;

@Service("appActivityRewardService")
@Transactional
public class AppActivityRewardServiceImpl extends SqlBaseServiceImpl<AppActivityReward, Long>
        implements AppActivityRewardService {

    @SuppressWarnings("unused")
    private AppActivityRewardDao appActivityRewardDao;

    @Autowired
    private AppUserBalanceService appUserBalanceService;

    public AppActivityRewardServiceImpl(@Qualifier("appActivityRewardDao") AppActivityRewardDao appActivityRewardDao) {
        super(appActivityRewardDao);
        this.appActivityRewardDao = appActivityRewardDao;
    }

    @Override
    public List<AppActivityReward> getThisDayByType(RewardType behaviorType, Long uid, RewardFromType type) {
        return getThisDay(behaviorType, uid, type, true);
    }

    @Override
    public List<AppActivityReward> getThisDayEffectiveByType(RewardType behaviorType, Long uid, RewardFromType type) {
        return getThisDay(behaviorType, uid, type, false);
    }

    private List<AppActivityReward> getThisDay(RewardType behaviorType, Long uid, RewardFromType type, Boolean createTime) {
        List<AppActivityReward> rewardList = new ArrayList<>();
        List<AppActivityReward> unReceiveReward = getUserUnReceiveReward(uid);
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("type", behaviorType.toString()).eq("uid", uid).eq("source", type.toString());
        if (createTime) {
            wrapper.ge("createTime", DateUtils.getThisDayStart()).le("createTime", DateUtils.getThisDayEnd());
        } else {
            wrapper.ge("effectiveTime", DateUtils.getThisDayStart()).le("effectiveTime", DateUtils.getThisDayEnd());
        }
        List<AppActivityReward> receiveReward = appActivityRewardDao.queryForList(wrapper);
        rewardList.addAll(receiveReward);
        for (AppActivityReward appActivityReward : unReceiveReward) {
            if (behaviorType.equals(appActivityReward.getType())
                    && type.equals(appActivityReward.getSource())
                    && (!appActivityReward.getReceive())) {
                if (createTime) {
                    if (appActivityReward.getCreateTime().before(DateUtils.getThisDayEnd())
                            && appActivityReward.getCreateTime().after(DateUtils.getThisDayStart())) {
                        rewardList.add(appActivityReward);
                    }
                } else {
                    if (appActivityReward.getEffectiveTime().before(DateUtils.getThisDayEnd())
                            && appActivityReward.getEffectiveTime().after(DateUtils.getThisDayStart())) {
                        rewardList.add(appActivityReward);
                    }
                }

            }
        }
        return rewardList;
    }


    @Autowired
    private RedisTemplate<String, AppActivityReward> redisTemplate;

    private List<AppActivityReward> getUserUnReceiveReward(Long uid) {
        List<AppActivityReward> listTemp = redisTemplate.opsForList().range(RedisUtils.getRewardKey(uid.toString()), 0, -1);
        return listTemp;
    }

    private void deleteAppActivityRewardFromRedis(AppActivityReward appActivityReward) {
        String key = appActivityReward.getUid().toString();
        List<AppActivityReward> listTemp = getUserUnReceiveReward(appActivityReward.getUid());
        if (ObjectUtils.isEmpty(listTemp)) {
            return;
        }

        for (int i = 0; i < listTemp.size(); i++) {
            AppActivityReward activityReward = listTemp.get(i);
            if (appActivityReward.getId().equals(activityReward.getId())) {
                listTemp.remove(activityReward);
                break;
            }
        }
        redisTemplate.delete(RedisUtils.getRewardKey(key));
        if (!ObjectUtils.isEmpty(listTemp)) {
            redisTemplate.opsForList().rightPushAll(RedisUtils.getRewardKey(key), listTemp);
        }
    }

    @Override
    public void saveList(List<AppActivityReward> rewardList) {

        /**
         * 2020/04/14前奖励直接入mysql库逻辑
         */
//        saveBatch(rewardList);
        /**
         * 2020/04/14后奖励入redis逻辑
         */
        if (rewardList == null || rewardList.size() <= 0) {
            return;
        }
        Date date = new Date();
        List<AppActivityReward> userUnReceiveReward = getUserUnReceiveReward(rewardList.get(0).getUid());
        rewardList.addAll(userUnReceiveReward);
        for (int i = 0; i < rewardList.size(); i++) {
            AppActivityReward appActivityReward = rewardList.get(i);
            if (appActivityReward.getExpirationTime().before(new Date())) {
                rewardList.remove(appActivityReward);
                continue;
            }
            if (appActivityReward.getExpirationTime().after(date)) {
                date = appActivityReward.getExpirationTime();
            }
            if (appActivityReward.getId() == null) {
                appActivityReward.setId(appActivityRewardDao.getSnowflakeIdWorkerNextId());
            }
        }

        String key = RedisUtils.getRewardKey(rewardList.get(0).getUid().toString());
        redisTemplate.delete(key);
        redisTemplate.opsForList().rightPushAll(key, rewardList);
        redisTemplate.expireAt(key, date);

    }

    @Override
    public BigDecimal sumByUser(AppUser appUser) {
        Map<String, Object> param = new HashMap<>();
        param.put("uid", appUser.getId());
        List<UserInfoResult> userInfoResult = queryForMapMapper(UserInfoResult.class, "sumRewordAmount", param);
        return userInfoResult.get(0).getAirdrop();
    }

    @Override
    public List<UserRewardResult> getUserReward(UserRewardParam param, AppUser appUser) {
        /**
         * 2020/04/14前奖励从mysql库查询
         */
        QueryWrapper wrapper = new QueryWrapper();
        Date now = new Date();
        wrapper.eq("uid", appUser.getId()).eq("receive", "false").ge("expirationTime", now).eq("source", RewardFromType.AIRDROP.toString());
        Calendar cal = Calendar.getInstance();
        cal.setTime(now);
        cal.add(Calendar.HOUR, 24);
        wrapper.le("effectiveTime", cal.getTime()).eq("side", RewardSide.REWARD.toString());
        wrapper.orderByAsc("effectiveTime");
        List<AppActivityReward> rewardListDataBase = appActivityRewardDao.queryForList(wrapper);

        /**
         * 2020/04/14后奖励从redis库查询,并支持之前存入库中未过期的奖励
         */
        List<AppActivityReward> rewardListRedis = new ArrayList<>();
        List<AppActivityReward> listTemp = getUserUnReceiveReward(appUser.getId());
        /**
         * 2020/4/26注册和每日奖励为查询时发放
         */
        createRegisterAndDailyReward(listTemp, appUser);
        listTemp = getUserUnReceiveReward(appUser.getId());
        if (listTemp != null) {
            for (AppActivityReward appActivityReward : listTemp) {
                if (appActivityReward.getEffectiveTime().before(cal.getTime())
                        && RewardSide.REWARD.equals(appActivityReward.getSide())
                        && RewardFromType.AIRDROP.equals(appActivityReward.getSource())
                        && (!appActivityReward.getReceive())
                        && appActivityReward.getExpirationTime().after(new Date())) {
                    rewardListRedis.add(appActivityReward);
                }
                if (appActivityReward.getExpirationTime().before(new Date())) {
                    deleteAppActivityRewardFromRedis(appActivityReward);
                }
            }
        }
        if (rewardListDataBase != null) {
            rewardListRedis.addAll(rewardListDataBase);
        }
        //根据生效时间排序
        Collections.sort(rewardListRedis, new Comparator<AppActivityReward>() {
            @Override
            public int compare(AppActivityReward o1, AppActivityReward o2) {
                return o1.getEffectiveTime().compareTo(o2.getEffectiveTime());
            }
        });
        return transformation(rewardListRedis, param.getLanguage(), now);
    }

    @Autowired
    private ConfigureService configureService;
    @Autowired
    private AppActivityService appActivityService;
    @Autowired
    private AppBehaviorConfigService appBehaviorConfigService;
    @Autowired
    private RewardService rewardService;

    private void createRegisterAndDailyReward(List<AppActivityReward> listTemp, AppUser appUser) {
        //获取空投过期时间配置
        Integer effectiveTimeConf = Integer.valueOf(configureService.getString("airdropEffectiveTime").toString());

        /**
         * 查找数据库中已领取的生成时间为三天（奖励过期时间）内的
         */
        QueryWrapper wrapper = new QueryWrapper();
        Date now = new Date();
        wrapper.eq("uid", appUser.getId()).eq("receive", "true").eq("source", RewardFromType.AIRDROP.toString());
        Calendar cal = Calendar.getInstance();
        cal.setTime(now);
        cal.add(Calendar.DATE, -effectiveTimeConf);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        wrapper.ge("createTime", DateUtils.getDayStart(cal.getTime())).le("createTime", now).eq("side", RewardSide.REWARD.toString());
        List<AppActivityReward> rewardListDataBase = appActivityRewardDao.queryForList(wrapper);

        int registerDay = DateUtils.getDayDiff(now, appUser.getCreateTime());
        int times = registerDay >= effectiveTimeConf ? effectiveTimeConf : (registerDay == 0 ? 1 : registerDay);

        if (listTemp != null) {
            rewardListDataBase.addAll(listTemp);
        }

        //未发放奖励的日期与当前日期相差天数列表
        List<Integer> notSendDay = new ArrayList<>();
        for (int i = 0; i < times; i++) {
            cal.setTime(now);
            cal.add(Calendar.DATE, -i);
            boolean hasSend = false;
            for (AppActivityReward appActivityReward : rewardListDataBase) {
                if (appActivityReward.getType().equals(RewardType.REGISTER) || appActivityReward.getType().equals(RewardType.DAILY)) {
                    if (appActivityReward.getCreateTime().after(DateUtils.getDayStart(cal.getTime())) && appActivityReward.getCreateTime().before(DateUtils.getDayEnd(cal.getTime()))) {
                        //当前日期已发放
                        hasSend = true;
                        break;
                    }
                }
            }
            if (!hasSend) {
                notSendDay.add(i);
            }
        }

        if (notSendDay.size() <= 0) {
            return;
        }

        RewardMessage rewardMessage = new RewardMessage();


        AppBehaviorConfig appBehaviorConfigD = appBehaviorConfigService.findByActivityType(RewardType.DAILY);
        if (appBehaviorConfigD != null) {
            if (DateUtils.getDayDiff(appUser.getCreateTime(), new Date()) <= appBehaviorConfigD.getTimeInOnce()) {
                AppActivity appActivityD = appActivityService.findActivityByType(RewardType.DAILY);
                //发放每日奖励
                if (appActivityD != null) {
                    rewardMessage.setAppActivity(appActivityD);
                    rewardMessage.setUid(appUser.getId());
                    rewardMessage.setNotSendDay(notSendDay);
                    rewardMessage.setActivityType(ActivityType.AIRDROP);
                    rewardMessage.setMethodName(MethodName.count);
                    rewardService.execute(rewardMessage);
                }
            }
        }

        AppActivity appActivityR = appActivityService.findActivityByType(RewardType.REGISTER);
        //发放注册奖励
        if (appActivityR != null) {
            AppBehaviorConfig appBehaviorConfig = appBehaviorConfigService.findByAid(appActivityR.getId());
            if (appBehaviorConfig == null) {
                return;
            }
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, -appBehaviorConfig.getTimeInOnce());
            if (appUser.getCreateTime().after(calendar.getTime())) {
                rewardMessage.setAppActivity(appActivityR);
                rewardMessage.setUid(appUser.getId());
                rewardMessage.setUserCreateTime(appUser.getCreateTime());
                rewardMessage.setNotSendDay(notSendDay);
                rewardMessage.setActivityType(ActivityType.AIRDROP);
                rewardMessage.setMethodName(MethodName.reset);
                rewardService.execute(rewardMessage);
            }
        }

    }


    @Override
    public QueryResult<List<RewardHistResult>> getRewardHistory(RewordHistParam param, AppUser appUser) {
        Map<String, Object> params = CommonBusiness.httpPageParam(param);
        params.put("uid", appUser.getId());
        QueryResult<List<AppActivityReward>> QueryResult = queryForPage(AppActivityReward.class, "getRewordHist", params);
        QueryResult<List<RewardHistResult>> result = new QueryResult<>();
        if(ObjectUtils.isEmpty(QueryResult)) {
        	return result;
        }
        Date now = new Date();
        result.setPageInfo(QueryResult.getPageInfo());
        result.setResult(transformationHist(QueryResult.getResult(), param.getLanguage(), now));
        return result;
    }


    @Override
    public void receiveAward(ReceiveAwardParam param, AppUser appUser) {

        AppActivityReward appActivityReward = new AppActivityReward();
        boolean fromRedis = false;

        /**
         * 2020/04/14前奖励从mysql库查询
         */
//        QueryWrapper wrapper = new QueryWrapper();
//        wrapper.eq("uid", appUser.getId()).eq("receive", "false").eq("id", param.getRewardId());
//        appActivityReward = get(appActivityRewardDao.queryForList(wrapper));

        /**
         * 2020/04/14后奖励从redis库查询，并支持之前mysql中未领取的奖励
         */

        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("uid", appUser.getId()).eq("id", param.getRewardId());
        AppActivityReward appActivityRewardR = get(appActivityRewardDao.queryForList(wrapper));
        if (appActivityRewardR != null) {
            if (appActivityRewardR.getReceive()) {
                appActivityReward.setUid(appUser.getId());
                appActivityReward.setId(param.getRewardId());
                deleteAppActivityRewardFromRedis(appActivityReward);
                throw new BusinessException(CodeRes.CODE_15031);
            } else {
                fromRedis = false;
                appActivityReward = appActivityRewardR;
            }
        } else {
            fromRedis = true;
            List<AppActivityReward> listTemp = getUserUnReceiveReward(appUser.getId());
            for (AppActivityReward appActivityRewardTemp : listTemp) {
                if (appActivityRewardTemp.getId().equals(param.getRewardId())) {
                    appActivityReward = appActivityRewardTemp;
                    break;
                }
            }
        }

        if (appActivityReward.getId() == null) {
            throw new BusinessException(CodeRes.CODE_15031);
        }

        appUserBalanceService.receiveAward(appActivityReward);
        appActivityReward.setReceive(true);
        appActivityReward.setReceiveTime(new Date());
        if (fromRedis) {
            deleteAppActivityRewardFromRedis(appActivityReward);
            appActivityRewardDao.persist(appActivityReward);
        } else {
            save(appActivityReward);
        }

    }

    @Override
    public AppActivityReward findNewUserReward(AppUser appUser) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("uid", appUser.getId()).eq("type", RewardType.NEWUSER.toString());
        List<AppActivityReward> list = appActivityRewardDao.queryForList(wrapper);
        if (ObjectUtils.isEmpty(list)) {
            List<AppActivityReward> listTemp = getUserUnReceiveReward(appUser.getId());
            if (ObjectUtils.isEmpty(listTemp)) {
                return null;
            } else {
                for (AppActivityReward appActivityReward : listTemp) {
                    if (appActivityReward.getType().equals(RewardType.NEWUSER) && appActivityReward.getExpirationTime().after(new Date())) {
                        return appActivityReward;
                    }
                }
            }
        } else if (list.size() > 1) {
            return list.get(0);
        } else {
            return get(list);
        }
        return null;
    }

    @Override
    public long saveReturnId(AppActivityReward appActivityReward) {
        Long id = appActivityRewardDao.getSnowflakeIdWorkerNextId();
        appActivityReward.setId(id);
        long i = appActivityRewardDao.persist(appActivityReward);
        return i > 0 ? id : null;
    }

    @Override
    public void addWithdrawRecord(AppWithdrawOrder appWithdrawOrder) {
        AppActivityReward appActivityReward = getWithdrawAppActivityReward(appWithdrawOrder.getUserId(), appWithdrawOrder.getAmount(), RewardType.WITHDRAW);
        AppActivityReward appActivityRewardFree = getWithdrawAppActivityReward(appWithdrawOrder.getUserId(), appWithdrawOrder.getFee(), RewardType.WITHDRAWFEE);

        save(appActivityReward);
        appUserBalanceService.receiveAward(appActivityReward);

        save(appActivityRewardFree);
        appUserBalanceService.receiveAward(appActivityRewardFree);

    }

    private AppActivityReward getWithdrawAppActivityReward(Long uid, BigDecimal amount, RewardType withdraw) {
        AppActivityReward appActivityReward = new AppActivityReward();
        Date now = new Date();
        AppActivity appActivity = appActivityService.findActivityByType(withdraw);
        appActivityReward.setUid(uid);
        appActivityReward.setAid(appActivity.getId());
        appActivityReward.setAmount(amount);
        appActivityReward.setComment(appActivity.getTitle() + "," + appActivity.getEnglishTitle());
        appActivityReward.setCreateTime(now);
        appActivityReward.setEffectiveTime(now);
        appActivityReward.setExpirationTime(now);
        appActivityReward.setReceive(true);
        appActivityReward.setReceiveTime(now);
        appActivityReward.setSide(RewardSide.SPEND);
        appActivityReward.setSource(RewardFromType.GAME);
        appActivityReward.setType(withdraw);
        appActivityReward.setUnit(BalanceType.TOP);
        appActivityReward.setVerification(false);
        return appActivityReward;

    }

    private List<UserRewardResult> transformation(List<AppActivityReward> rewardList, LanguageEnum language, Date now) {
        List<UserRewardResult> listReturn = new ArrayList<>();
        for (AppActivityReward appActivityReward : rewardList) {
            UserRewardResult userRewardResult = new UserRewardResult();
            userRewardResult.setAmount(appActivityReward.getAmount());
            userRewardResult.setSide(appActivityReward.getSide());
            userRewardResult.setUnit(appActivityReward.getUnit());
            userRewardResult.setRewardType(appActivityReward.getType());
            if (appActivityReward.getEffectiveTime().before(now)) {
                userRewardResult.setCanReceive(true);
                userRewardResult.setCountDown(0L);
            } else {
                userRewardResult.setCanReceive(false);
                userRewardResult.setCountDown(appActivityReward.getEffectiveTime().getTime() - now.getTime());
            }
            userRewardResult.setRewardId(appActivityReward.getId());
            String comment[] = appActivityReward.getComment().split(",");
            if (language.equals(LanguageEnum.CN)) {
                userRewardResult.setType(comment[0]);
            } else {
                userRewardResult.setType(comment[1]);
            }
            listReturn.add(userRewardResult);
        }
        return listReturn;

    }

    private List<RewardHistResult> transformationHist(List<AppActivityReward> result, LanguageEnum language, Date now) {
        List<RewardHistResult> listReturn = new ArrayList<>();
        for (AppActivityReward appActivityReward : result) {
            RewardHistResult userRewardResult = new RewardHistResult();
            userRewardResult.setAmount(appActivityReward.getAmount());
            userRewardResult.setSide(appActivityReward.getSide());
            userRewardResult.setUnit(appActivityReward.getUnit());
            userRewardResult.setRewardId(appActivityReward.getId());
            String comment[] = appActivityReward.getComment().split(",");
            if (language.equals(LanguageEnum.CN)) {
                userRewardResult.setType(comment[0]);
            } else {
                userRewardResult.setType(comment[1]);
            }
            userRewardResult.setReceiveTime(appActivityReward.getReceiveTime().getTime());
            listReturn.add(userRewardResult);
        }
        return listReturn;
    }

    @Override
    public List<AppActivityReward> get7DaysReceiveReward(Long userId) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("uid", userId)
                .eq("side", RewardSide.REWARD.toString())
                .eq("receive", "true")
                .gt("receiveTime", DateUtils.addDay(new Date(), -7));
        return appActivityRewardDao.queryForList(wrapper);
    }
}
