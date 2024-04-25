package com.topnetwork.reward;

import com.topnetwork.reward.bean.RewardMessage;
import com.topnetwork.reward.game.RecordGameReward;
import com.topnetwork.wallet.common.enums.ActivityType;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import com.gitee.magic.framework.head.exception.BusinessException;
import com.gitee.magic.core.exception.ApplicationException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;

@Component
public class RewardService implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    /**
     * 发放奖励
     *
     * @param rewardMessage 奖励所需信息
     */
    public void execute(RewardMessage rewardMessage) {

        Reward reward = null;
        Collection<Reward> rewards = getBeansByType(Reward.class);
        for (Reward activityReward : rewards) {
            if (rewardMessage.getActivityType().equals(ActivityType.GAME)) {
                if (activityReward.getRewardType() != null && activityReward.getRewardType().contains(rewardMessage.getAppActivity().getBehaviorType())) {
                    reward = activityReward;
                }
            } else {
                if (activityReward.getActivityType().equals(rewardMessage.getActivityType())) {
                    reward = activityReward;
                }
            }

        }
        if (reward == null) {
            reward = new RecordGameReward();
        }
        try {
            Method method = reward.getClass().getDeclaredMethod(rewardMessage.getMethodName().toString(), RewardMessage.class);
            method.invoke(reward, rewardMessage);
        } catch (NoSuchMethodException e) {
            throw new ApplicationException(e);
        } catch (IllegalAccessException e) {
            throw new ApplicationException(e);
        } catch (InvocationTargetException e) {
            if ("com.gitee.magic.framework.head.exception.BusinessException".equals(e.getTargetException().getClass().getName())) {
                throw (BusinessException) e.getTargetException();
            } else {
                throw new ApplicationException(e);
            }
        }


    }

    private <T> Collection<T> getBeansByType(Class<T> tClass) {
        Map<String, T> tBeans = applicationContext.getBeansOfType(tClass);
        if (tBeans.size() > 0) {
            return tBeans.values();
        }
        return null;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
