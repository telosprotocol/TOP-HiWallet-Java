package com.topnetwork.riskcenter.engine;

import com.topnetwork.riskcenter.entity.Rule;
import com.topnetwork.riskcenter.service.RuleService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import com.gitee.magic.framework.base.context.Cache;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName RiskHandle
 * @Description
 * @Author bran
 * @Date 2020/7/17 15:52
 */
@Component("riskHandle")
public class RiskHandle implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Autowired
    private RuleService ruleService;


    public boolean executeAll(Cache cache) {
        List<Rule> all = ruleService.findAllAvailable();
        List<RuleBase> baseList = new ArrayList<>();
        for (Rule rule : all) {
            baseList.add(getBeansByName(rule.getBean(), RuleBase.class));
        }
        for (RuleBase ruleBase : baseList) {
            if (!ruleBase.check(cache)) {
                return false;
            }
        }
        return true;
    }

    public boolean executeSingle(Cache cache, String beanName) {
        RuleBase beansByName = getBeansByName(beanName, RuleBase.class);
        return beansByName.check(cache);
    }

    private <T> T getBeansByName(String name, Class<T> requiredType) {
        return applicationContext.getBean(name, requiredType);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
