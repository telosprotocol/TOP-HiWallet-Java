package com.topnetwork.riskcenter.engine.rules;

import com.topnetwork.riskcenter.engine.RuleBase;
import org.springframework.stereotype.Component;
import com.gitee.magic.framework.base.context.Cache;

/**
 * @ClassName RuleExample
 * @Description 例子
 * @Author bran
 * @Date 2020/7/17 15:50
 */
@Component("ruleExample")
public class RuleExample implements RuleBase {

    @Override
    public boolean check(Cache cache) {
        System.out.println("aaa");
        return true;
    }
}
